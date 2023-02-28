package rrpuzzle.solver;

import lombok.Getter;
import lombok.Setter;
import rrpuzzle.constants.Constants;
import rrpuzzle.model.MapModel;
import rrpuzzle.model.Object;
import rrpuzzle.model.Room;
import static java.util.Comparator.comparingInt;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Main Class that it's used to solve the problem.
 * Main steps :
 *  - FULL ARGS CONSTRUCTOR, feed inputs of the problem
 *  - DATA STRUCTURES GENERATION, to create the data structures useful to find the solution
 *  - CALCULATE SOLUTION, create route containing only rooms with objects
 *  - CREATE FULL ROUTE SEQUENCE, add intermediate rooms to the solution
 *  - PRINT SOLUTION, print solution as a table
 */

@Getter
@Setter
public class Solver {

    // INPUTS
    private MapModel mapModel;
    private Integer startingRoomId;
    private String[] objectsToFind;

    // GENERATED DATA STRUCTURES
    private Map<Integer, Room> roomsMap;
    private Map<KeyDistance, DistanceInfo> distancesMap;
    private Map<String, Integer> objectsMap;

    // DATA STRUCTURES FOR CALCULATIONS
    private List<Integer> roomIdSequence;

    // SOLUTION
    private List<StepSolution> solution;
    private int finalDistance;

    public Solver(MapModel mapModel, Integer startingRoomId, String[] objectsToFind) {
        this.mapModel = mapModel;
        this.startingRoomId = startingRoomId;
        this.objectsToFind = objectsToFind;

        this.roomsMap = new HashMap<>();
        this.distancesMap = new HashMap<>();
        this.objectsMap = new HashMap<>();

        this.roomIdSequence = new ArrayList<>();

        this.solution = new ArrayList<>();
    }

    /** DATA STRUCTURES GENERATION */
    public void generateDataStructures () {
        // create map to easily access rooms informations
        for (Room room : mapModel.getRooms()) {
            roomsMap.put(room.getId(), room);
            if (room.getObjects() == null)
                continue;
            for (Object object : room.getObjects()) {
                objectsMap.put(object.getName(), room.getId());
            }
        }

        this.calculateDistances();
    }

    /**
     * Method that calculates with recursion all the distances between all possible
     * couples of the rooms
     */
    private void calculateDistances () {
        for (Room room : mapModel.getRooms()) {

            // puts in the map that the distance of the room with itself is 0
            distancesMap.put(new KeyDistance(room.getId(), room.getId()), new DistanceInfo(1, new ArrayList<>()));

            calculateDistanceStep(room, room.getNorth(), new ArrayList<>(), 1);
            calculateDistanceStep(room, room.getSouth(), new ArrayList<>(), 1);
            calculateDistanceStep(room, room.getEast(), new ArrayList<>(), 1);
            calculateDistanceStep(room, room.getWest(), new ArrayList<>(), 1);
        }

    }

    /**
     * Additional method to recursively calculate distances
     * @param room - starting room of distance calculation
     * @param linkedRoomId - current room to calculate distance from starting room
     * @param previousRooms - rooms on the path between the starting room and the current one
     * @param distance - current distance value
     */
    private void calculateDistanceStep (Room room, Integer linkedRoomId, List<Integer> previousRooms, int distance) {
        if (linkedRoomId == null || room.getId() == linkedRoomId)
            return;

        KeyDistance keyDistance = new KeyDistance(room.getId(), linkedRoomId);
        DistanceInfo distanceInfo = new DistanceInfo(distance, new ArrayList<>(previousRooms));

        // if the pair already exists in the map, it's inserted only if the distance is less
        // otherwise it means we already passed from here with a shorter route
        if (distancesMap.get(keyDistance) != null && distancesMap.get(keyDistance).getDistance() < distance)
            return;
        // if distances are equal should not overwrite
        else if (distancesMap.get(keyDistance) == null || distancesMap.get(keyDistance).getDistance() > distance)
            distancesMap.put(keyDistance, distanceInfo);


        Room linkedRoom = roomsMap.get(linkedRoomId);
        previousRooms.add(linkedRoomId);
        distance++;
        calculateDistanceStep(room, linkedRoom.getNorth(), new ArrayList<>(previousRooms), distance);
        calculateDistanceStep(room, linkedRoom.getSouth(), new ArrayList<>(previousRooms), distance);
        calculateDistanceStep(room, linkedRoom.getEast(), new ArrayList<>(previousRooms), distance);
        calculateDistanceStep(room, linkedRoom.getWest(), new ArrayList<>(previousRooms), distance);
    }

    /** CALCULATE SOLUTION (depth search) */
    public void calculateSolution() {

        int n = objectsToFind.length + 1; // + 1 because of starting node

        // column: leading node (only 1)

        int row = (int)Math.pow(2, n);
        // first index : bitmask -> visited node set bits are 1
        // second index : number of objects to find + 1
        int[][] dist = new int[row][n];
        // keep track of best routes, one for different final node
        List<Integer>[] finalRoutes = new ArrayList[n];

        for(int i = 0; i < row; i++)
            Arrays.fill(dist[i], -1);

        // q list : [{previous room id, index of item to find (0 is starting node), mask}, ... ]
        LinkedList<int[]> q = new LinkedList<>();
        // routesQ list : keeps track of previous steps of the route
        LinkedList<List<Integer>> routesQ = new LinkedList<>();

        int initialmask = setbit(0,0);
        q.add(new int[]{startingRoomId, 0, initialmask});
        dist[initialmask][0] = 0;

        List<Integer> startingRoute = new ArrayList<>();
        startingRoute.add(startingRoomId);
        routesQ.add(startingRoute);

        while(q.size() > 0) {

            // take last to follow depth search
            int[] path = q.removeLast();
            List<Integer> route = routesQ.removeLast();

            int room = path[0];
            int lead = path[1];
            int mask = path[2];

            // ADDED : mettere un continue
            if (mask == row - 1)
                finalRoutes[lead] = new ArrayList<>(route);

            // iterate over neighbours of lead
            for(int k = 0; k < objectsToFind.length; k++){

                int newroom = objectsMap.get(objectsToFind[k]);
                int newlead = k + 1;
                int newmask = setbit(mask, newlead);

                int newdist = dist[mask][lead] + distancesMap.get(new KeyDistance(room, newroom)).getDistance();

                // ADDED : se ricerchiamo lo stesso oggetto, ignorare
                if (lead == newlead)
                    continue;
                // avoid cycle: checking if this set is already visited, avoids if the old distance is better
                if(dist[newmask][newlead] != -1 && dist[newmask][newlead] < newdist)
                    continue;

                dist[newmask][newlead] = newdist;
                q.add(new int[]{newroom, newlead, newmask});

                List<Integer> newroute = new ArrayList<>(route);
                newroute.add(newroom);
                routesQ.add(newroute);

            }
        }

        // best solutions, each has a different last node
        List<Integer> listPossibleSolutions = Arrays.stream(dist[dist.length - 1]).boxed().toList();

        // ADDED = considerare ii >= 0
        int indexSolution = IntStream.range(0, listPossibleSolutions.size()).boxed()
                .filter(ii -> ii > 0) // consider only the ones > 0, the first solution should be -1, because it shouldn't end in the starting room
                .min(comparingInt(listPossibleSolutions::get))
                .get();

        this.roomIdSequence = finalRoutes[indexSolution];
        this.finalDistance = dist[dist.length - 1][indexSolution];

    }

    /**
     * Set bit of added room in the mask
     * @param mask - starting mask
     * @param i - room to add
     * @return new mask
     */
    private int setbit(int mask, int i){
        return mask|(1<<i);
    }

    /** CREATE FULL ROUTE SEQUENCE
     *
     * This method generates the full route using the route calculated previously,
     * that has only the rooms with objects, so basically the intermediate rooms are added
     */
    public void generateFullRouteSequence () {

        Room startingRoom = roomsMap.get(startingRoomId);
        String objectsStartingRoom = Constants.NONE_VALUE;

        // adding starting room
        if (startingRoom.getObjects() != null && startingRoom.getObjects().size() > 0) {
            objectsStartingRoom = this.createStringObjectList(startingRoom.getObjects());
            startingRoom.setObjects(null);
        }
        solution.add( new StepSolution(startingRoomId, startingRoom.getName(), objectsStartingRoom) );

        // start to insert other intermediate rooms
        for (int i = 0; i < roomIdSequence.size() - 1; i++) {
            int startingRoomId = roomIdSequence.get(i);
            int finalRoomId = roomIdSequence.get(i + 1);

            if (startingRoomId != finalRoomId) {

                List<Integer> midRoomIds = startingRoomId < finalRoomId ?
                        distancesMap.get(new KeyDistance(startingRoomId, finalRoomId)).getRoute() :
                        distancesMap.get(new KeyDistance(startingRoomId, finalRoomId)).getReverseRoute();
                for (int midRoomId : midRoomIds) {
                    StepSolution stepSolution = new StepSolution(midRoomId, roomsMap.get(midRoomId).getName(), Constants.NONE_VALUE);
                    solution.add(stepSolution);
                }

                List<Object> objectsFound = roomsMap.get(finalRoomId).getObjects();
                roomsMap.get(finalRoomId).setObjects(null);
                String stringObjects = this.createStringObjectList(objectsFound);

                StepSolution stepSolution = new StepSolution(finalRoomId, roomsMap.get(finalRoomId).getName(), stringObjects);
                solution.add(stepSolution);
            }

        }

    }

    /**
     * This method format a list of Strings in a String
     * @param objects - list of objects to create string with
     * @return String - contains the objects with an intermediate comma ","
     */
    private String createStringObjectList(List<Object> objects) {

        if (objects == null || objects.size() == 0)
            return Constants.NONE_VALUE;

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < objects.size() - 1; i++)
            res.append(objects.get(i).getName()).append(", ");

        res.append(objects.get(objects.size() - 1).getName());

        return res.toString();
    }

    /** PRINT SOLUTION
     *
     * This method just print to console the solution after it's formatted as a table
     */
    public void printResults () {
        System.out.println(formatAsTable());
    }

    /**
     * This method formats the solution as a table
     * @return String - solution as a table
     */
    private String formatAsTable() {
        int nColumns = 3;
        int spaceBetweenCols = 5;
        int[] maxLengths = new int[nColumns];

        // calculating max number of characters for column
        for (int i = 0; i < nColumns; i++)
            maxLengths[i] = Math.max(maxLengths[i], Constants.HEADERS_TABLE[i].length());

        // calculating max number of characters for column using headers
        for (StepSolution row : this.solution) {
            maxLengths[0] = Math.max(maxLengths[0], String.valueOf(row.getId()).length());
            maxLengths[1] = Math.max(maxLengths[1], row.getRoomName().length());
            maxLengths[2] = Math.max(maxLengths[2], row.getObjectCollected().length());
        }

        // creating format
        StringBuilder formatBuilder = new StringBuilder();
        for (int maxLength : maxLengths)
            formatBuilder.append("%-").append(maxLength + spaceBetweenCols).append("s");
        String format = formatBuilder.toString();

        StringBuilder result = new StringBuilder();
        // formatting headers
        result.append(String.format(format, Constants.HEADERS_TABLE)).append("\n");

        // creating bar that splits headers and table body
        result.append(
                "-".repeat(
                    Arrays.stream(maxLengths).sum() + (spaceBetweenCols * (nColumns - 1))
                ))
                .append("\n");

        // formatting table body
        for (StepSolution row : this.solution)
            result.append(String.format(format, row.toArray())).append("\n");

        return result.toString();
    }

}
