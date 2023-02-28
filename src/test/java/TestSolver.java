import org.junit.Test;
import rrpuzzle.model.MapModel;
import rrpuzzle.model.Room;
import rrpuzzle.solver.DistanceInfo;
import rrpuzzle.solver.KeyDistance;
import rrpuzzle.solver.Solver;
import rrpuzzle.solver.StepSolution;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSolver {

    @Test
    public void solverReadData() {

        MapModel mapModel = InputCreator.createMapModel();
        Integer idStart = InputCreator.createIdStart();
        String[] objectsToFind = InputCreator.createObjectsToFind();

        Solver solver = new Solver(mapModel, idStart, objectsToFind);

        solver.generateDataStructures();

        assertEquals(solver.getRoomsMap().size(), 5);
        for (Room room : mapModel.getRooms())
            assertEquals(room, solver.getRoomsMap().get(room.getId()));

        assertEquals(solver.getDistancesMap().size(), 15);

        DistanceInfo distanceInfo12 = new DistanceInfo(1, new ArrayList<>());
        assertEquals(distanceInfo12, solver.getDistancesMap().get(new KeyDistance(1, 2)));
        assertEquals(distanceInfo12, solver.getDistancesMap().get(new KeyDistance(2, 1)));

        DistanceInfo distanceInfo13 = new DistanceInfo(3, new ArrayList<>(List.of(2, 5)));
        assertEquals(distanceInfo13, solver.getDistancesMap().get(new KeyDistance(1, 3)));
        assertEquals(distanceInfo13, solver.getDistancesMap().get(new KeyDistance(3, 1)));

        DistanceInfo distanceInfo14 = new DistanceInfo(2, new ArrayList<>(List.of(2)));
        assertEquals(distanceInfo14, solver.getDistancesMap().get(new KeyDistance(1, 4)));
        assertEquals(distanceInfo14, solver.getDistancesMap().get(new KeyDistance(4, 1)));

        DistanceInfo distanceInfo23 = new DistanceInfo(2, new ArrayList<>(List.of(5)));
        assertEquals(distanceInfo23, solver.getDistancesMap().get(new KeyDistance(2, 3)));
        assertEquals(distanceInfo23, solver.getDistancesMap().get(new KeyDistance(3, 2)));

        DistanceInfo distanceInfo24 = new DistanceInfo(1, new ArrayList<>());
        assertEquals(distanceInfo24, solver.getDistancesMap().get(new KeyDistance(2, 4)));
        assertEquals(distanceInfo24, solver.getDistancesMap().get(new KeyDistance(4, 2)));

        DistanceInfo distanceInfo34 = new DistanceInfo(3, new ArrayList<>(List.of(5, 2)));
        assertEquals(distanceInfo34, solver.getDistancesMap().get(new KeyDistance(3, 4)));
        assertEquals(distanceInfo34, solver.getDistancesMap().get(new KeyDistance(4, 3)));

    }

    @Test
    public void checkSolverSolution() {
        MapModel mapModel = InputCreator.createMapModel();
        Integer idStart = InputCreator.createIdStart();
        String[] objectsToFind = InputCreator.createObjectsToFind();

        Solver solver = new Solver(mapModel, idStart, objectsToFind);

        solver.generateDataStructures();

        solver.calculateSolution();


        assertEquals(4, solver.getFinalDistance());

        List<Integer> roomIdSequenceExpected = new ArrayList<>();
        roomIdSequenceExpected.add(2);
        roomIdSequenceExpected.add(4);
        roomIdSequenceExpected.add(3);
        assertEquals(roomIdSequenceExpected, solver.getRoomIdSequence());

        solver.generateFullRouteSequence();

        List<StepSolution> solutionExpected = new ArrayList<>();
        solutionExpected.add(new StepSolution(2, "Dining Room", "None"));
        solutionExpected.add(new StepSolution(4, "Sun Room", "Potted Plant"));
        solutionExpected.add(new StepSolution(2, "Dining Room", "None"));
        solutionExpected.add(new StepSolution(5, "Random Room", "None"));
        solutionExpected.add(new StepSolution(3, "Kitchen", "Knife"));

        assertEquals(solutionExpected, solver.getSolution());

    }

    @Test
    public void checkSolverSolution2() {
        MapModel mapModel = InputCreator.createMapModel2();
        Integer idStart = InputCreator.createIdStart2();
        String[] objectsToFind = InputCreator.createObjectsToFind2();

        Solver solver = new Solver(mapModel, idStart, objectsToFind);

        solver.generateDataStructures();

        solver.calculateSolution();

        assertEquals(7, solver.getFinalDistance());

        List<Integer> roomIdSequenceExpected = new ArrayList<>();
        roomIdSequenceExpected.add(1);
        roomIdSequenceExpected.add(3);
        roomIdSequenceExpected.add(4);
        roomIdSequenceExpected.add(6);

        assertEquals(roomIdSequenceExpected, solver.getRoomIdSequence());

        solver.generateFullRouteSequence();

        List<StepSolution> solutionExpected = new ArrayList<>();
        solutionExpected.add(new StepSolution(1, "Room 1", "None"));
        solutionExpected.add(new StepSolution(2, "Room 2", "None"));
        solutionExpected.add(new StepSolution(3, "Room 3", "Object 1"));
        solutionExpected.add(new StepSolution(2, "Room 2", "None"));
        solutionExpected.add(new StepSolution(1, "Room 1", "None"));
        solutionExpected.add(new StepSolution(4, "Room 4", "Object 2"));
        solutionExpected.add(new StepSolution(5, "Room 5", "None"));
        solutionExpected.add(new StepSolution(6, "Room 6", "Object 3"));

        assertEquals(solutionExpected, solver.getSolution());

    }

}
