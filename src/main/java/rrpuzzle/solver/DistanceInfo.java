package rrpuzzle.solver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that contains the info about a particular route between two rooms,
 * the ones at the extremes of the route.
 * Route parameter is a list that contains the ids of the intermediate rooms
 * (not the ones at the extremes)
 *
 * EX :
 * DistanceInfo between room ID 1 and room ID 3
 *      distance = 3
 *      route = [2, 4]
 *
 */

@Getter
@Setter
@AllArgsConstructor
public class DistanceInfo {

    private Integer distance;
    private List<Integer> route;

    /**
     * The route parameter contains the ids of the intermediate rooms, ordered from the closest to
     * the farthest to the room at the edge with the smaller id.
     * This method returns the route parameter in reverse order in case you need the intermediate
     * rooms of the route from the room with the highest id value from the one to the smaller id value.
     *
     * EX :
     * complete route from room ID 1 to room ID 3
     *      1 -> 2 -> 4 -> 3
     *      rooms at the edge = 1, 3
     *      parameter route = [2, 4]
     *      getReverseRoute = [4, 2]
     */
    public List<Integer> getReverseRoute() {
        List<Integer> routeCopy = new ArrayList<>(route);
        Collections.reverse(routeCopy);
        return routeCopy;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (!(o instanceof DistanceInfo distanceInfo)) return false;
        boolean isEqual = distance.equals(distanceInfo.getDistance());
        isEqual = isEqual && route.equals(distanceInfo.getRoute());
        return isEqual;
    }
}
