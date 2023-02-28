package rrpuzzle.inputExtractor;

import com.fasterxml.jackson.databind.ObjectMapper;
import rrpuzzle.constants.Constants;
import rrpuzzle.exception.InputException;
import rrpuzzle.model.MapModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class contains static methods to extract inputs from the files in the resources folder
 */
public class InputExtractor {

    /** this method extract the data of the map v2.json input file and returns a MapModel object
     *
     * @param rootPath - the root path where the file containing the map is searched
     * @return MapModel object with all the data of the json
     * @throws IOException
     */
    public static MapModel retrieveMapModel (String rootPath) throws InputException {
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(rootPath + Constants.NAME_FILE_MAP);
        File file = new File(String.valueOf(path));

        MapModel mapModel = null;

        try {
            mapModel = objectMapper.readValue(file, MapModel.class);
        } catch (Exception e) {
            throw new InputException("The Map given as input isn't a valid json");
        }
        return mapModel;
    }

    /** this method extract the data of the idStart.txt input file and returns an Integer,
     *       stripping all the spaces and considering the first line of the file which is not empty
     *
     * @param rootPath - the root path where the file containing the starting room id is searched
     * @return Integer with the id of the starting room
     * @throws IOException
     */
    public static Integer retrieveIdStart(String rootPath) throws IOException, InputException {
        Integer idStart = null;

        Path path = Paths.get(rootPath + Constants.NAME_FILE_IDSTART);

        Scanner sc = new Scanner(new File(String.valueOf(path)));

        while (sc.hasNextLine() && idStart == null) {
            String line = sc.nextLine();
            line = line.strip();
            if (!"".equals(line)) {
                try {
                    idStart = Integer.valueOf(line);
                } catch (Exception e) {
                    throw new InputException("The starting room id isn't an integer!");
                }
            }

        }

        if (idStart == null)
            throw new InputException("The starting room id isn't specified!");

        return idStart;
    }

    /** this method extract the data of the objectsToFind.txt input file and returns an array of strings,
     *       stripping all the spaces and considering the first line of the file which is not empty
     *
     * @param rootPath - the root path where the file containing the objects to find is searched
     * @return String[]
     * @throws IOException
     */
    public static String[] retrieveObjectsToFind(String rootPath) throws IOException, InputException {

        String[] objectsToFind = null;

        Path path = Paths.get(rootPath + Constants.NAME_FILE_OBJECTSTOFIND);

        Scanner sc = new Scanner(new File(String.valueOf(path)));

        while (sc.hasNextLine() && objectsToFind == null) {
            String line = sc.nextLine();
            line = line.strip();
            if (!"".equals(line))
                objectsToFind = line.split(",");
        }

        if (objectsToFind == null)
            throw new InputException("The objects to find are not specified!");

        for (int i = 0; i < objectsToFind.length; i++) {
            objectsToFind[i] = objectsToFind[i].strip();
        }

        return objectsToFind;
    }

}
