package rrpuzzle.solver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rrpuzzle.utils.Utils;

/**
 * This class is used in the Solver object to define for each step of the solution route
 * the id of the room of arrival, its name and the objects collected.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StepSolution {

    private int id;
    private String roomName;
    private String objectCollected;

    public String[] toArray() {
        String[] array = new String[3];
        array[0] = String.valueOf(id);
        array[1] = roomName;
        array[2] = objectCollected;
        return array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StepSolution ss)) return false;
        boolean isEqual = id == ss.getId();
        isEqual = isEqual && Utils.equalStringWithNull(roomName, ss.getRoomName());
        isEqual = isEqual && Utils.equalStringWithNull(objectCollected, ss.getObjectCollected());
        return isEqual;
    }
}
