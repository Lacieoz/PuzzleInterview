package rrpuzzle;

import rrpuzzle.constants.Constants;
import rrpuzzle.exception.InputException;
import rrpuzzle.inputExtractor.InputExtractor;
import rrpuzzle.model.MapModel;
import rrpuzzle.solver.Solver;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, InputException {

        // Retrieve data from input files
        MapModel mapModel = InputExtractor.retrieveMapModel(Constants.ROOT_PATH);
        Integer idStart = InputExtractor.retrieveIdStart(Constants.ROOT_PATH);
        String[] objectsToFind = InputExtractor.retrieveObjectsToFind(Constants.ROOT_PATH);

        Solver solver = new Solver(mapModel, idStart, objectsToFind);

        // creates all the data structures useful in the next steps
        solver.generateDataStructures();

        // calculates the route of the rooms containing objects to visits
        solver.calculateSolution();

        // generates the full route with all the intermediate rooms
        solver.generateFullRouteSequence();

        // prints the results formatted
        solver.printResults();
    }
}
