import rrpuzzle.model.MapModel;
import rrpuzzle.model.Object;
import rrpuzzle.model.Room;

import java.util.ArrayList;
import java.util.List;

public class InputCreator {

    public static MapModel createMapModel() {

        MapModel mapModel = new MapModel();

        List<Room> rooms = new ArrayList<>();

        Room room1 = new Room();
        room1.setId(1);
        room1.setName("Hallway");
        room1.setNorth(2);
        room1.setObjects(new ArrayList<>());

        Room room2 = new Room();
        room2.setId(2);
        room2.setName("Dining Room");
        room2.setSouth(1);
        room2.setEast(4);
        room2.setWest(5);
        room2.setObjects(new ArrayList<>());

        Room room3 = new Room();
        room3.setId(3);
        room3.setName("Kitchen");
        room3.setEast(5);
        List<Object> objects3 = new ArrayList<>();
        objects3.add(new Object("Knife"));
        room3.setObjects(objects3);

        Room room4 = new Room();
        room4.setId(4);
        room4.setName("Sun Room");
        room4.setWest(2);
        List<Object> objects4 = new ArrayList<>();
        objects4.add(new Object("Potted Plant"));
        room4.setObjects(objects4);

        Room room5 = new Room();
        room5.setId(5);
        room5.setName("Random Room");
        room5.setWest(3);
        room5.setEast(2);
        List<Object> objects5 = new ArrayList<>();
        room5.setObjects(objects5);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);
        mapModel.setRooms(rooms);

        return mapModel;
    }

    public static MapModel createMapModel2() {

        MapModel mapModel = new MapModel();

        List<Room> rooms = new ArrayList<>();

        Room room1 = new Room();
        room1.setId(1);
        room1.setName("Room 1");
        room1.setWest(2);
        room1.setEast(4);
        room1.setObjects(new ArrayList<>());

        Room room2 = new Room();
        room2.setId(2);
        room2.setName("Room 2");
        room2.setWest(3);
        room2.setEast(1);
        room2.setObjects(new ArrayList<>());

        Room room3 = new Room();
        room3.setId(3);
        room3.setName("Room 3");
        room3.setEast(2);
        List<Object> objects3 = new ArrayList<>();
        objects3.add(new Object("Object 1"));
        room3.setObjects(objects3);

        Room room4 = new Room();
        room4.setId(4);
        room4.setName("Room 4");
        room4.setWest(1);
        room4.setEast(5);
        List<Object> objects4 = new ArrayList<>();
        objects4.add(new Object("Object 2"));
        room4.setObjects(objects4);

        Room room5 = new Room();
        room5.setId(5);
        room5.setName("Room 5");
        room5.setWest(4);
        room5.setEast(6);
        List<Object> objects5 = new ArrayList<>();
        room5.setObjects(objects5);

        Room room6 = new Room();
        room6.setId(6);
        room6.setName("Room 6");
        room6.setWest(5);
        List<Object> objects6 = new ArrayList<>();
        objects6.add(new Object("Object 3"));
        room6.setObjects(objects6);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);
        rooms.add(room6);
        mapModel.setRooms(rooms);

        return mapModel;
    }

    public static Integer createIdStart() {
        return 2;
    }

    public static Integer createIdStart2() {
        return 1;
    }

    public static String[] createObjectsToFind() {
        return new String[]{"Knife", "Potted Plant"};
    }

    public static String[] createObjectsToFind2() {
        return new String[]{"Object 1", "Object 2", "Object 3"};
    }
}
