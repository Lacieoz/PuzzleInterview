import org.junit.Test;
import rrpuzzle.constants.Constants;
import rrpuzzle.model.Room;
import rrpuzzle.solver.DistanceInfo;
import rrpuzzle.solver.StepSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestSpecifics {

    @Test
    public void testReverseRouteDistanceInfo () {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        DistanceInfo distanceInfo = new DistanceInfo(2, list);

        List<Integer> reversedList = new ArrayList<>(Arrays.asList(3, 2, 1));
        assertEquals(reversedList, distanceInfo.getReverseRoute());
        List<Integer> normalList = new ArrayList<>(Arrays.asList(1, 2, 3));
        assertEquals(normalList, distanceInfo.getRoute());
    }

    @Test
    public void stepSolutionClassToArray () {
        StepSolution stepSolution = new StepSolution(1, "Play Room", Constants.NONE_VALUE);
        assertArrayEquals(new String[] {"1", "Play Room", Constants.NONE_VALUE}, stepSolution.toArray());
    }

    @Test
    public void equalsRoom1 () {
        Room room = new Room();
    }


}
