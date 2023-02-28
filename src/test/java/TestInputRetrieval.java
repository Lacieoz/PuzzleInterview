import org.junit.Test;
import rrpuzzle.constants.Constants;
import rrpuzzle.exception.InputException;
import rrpuzzle.inputExtractor.InputExtractor;
import rrpuzzle.model.MapModel;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Class to test the inputs retrieval
 */
public class TestInputRetrieval {

    @Test
    public void readMapJson() throws InputException {

        MapModel mapModel = InputExtractor.retrieveMapModel(Constants.ROOT_PATH_TEST);

        assertNotNull(mapModel);
        assertNotNull(mapModel.getRooms());
        assertTrue(mapModel.getRooms().size() > 0);

        MapModel mapModel1 = InputCreator.createMapModel();

        for (int i = 0; i < mapModel.getRooms().size(); i++)
            assertEquals(mapModel1.getRooms().get(i), mapModel.getRooms().get(i));

    }

    @Test
    public void readIdStart() throws IOException, InputException {

        Integer idStart = InputExtractor.retrieveIdStart(Constants.ROOT_PATH_TEST);

        assertNotNull(idStart);
        assertEquals(2, (int) idStart);
    }

    @Test(expected = InputException.class)
    public void emptyIdStart() throws Exception {
        Integer idStart = InputExtractor.retrieveIdStart(Constants.ROOT_PATH_TEST_EMPTY);
    }

    @Test
    public void readItemsToFind() throws IOException, InputException {

        String[] objectsToFind = InputExtractor.retrieveObjectsToFind(Constants.ROOT_PATH_TEST);

        assertNotNull(objectsToFind);
        assertTrue(objectsToFind.length > 0);
        assertEquals(new String[]{"Knife", "Potted Plant"}, objectsToFind);
    }

    @Test(expected = InputException.class)
    public void emptyItemsToFind() throws Exception {
        String[] objectsToFind = InputExtractor.retrieveObjectsToFind(Constants.ROOT_PATH_TEST_EMPTY);
    }
}
