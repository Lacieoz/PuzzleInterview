package rrpuzzle.solver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class used as key in a Map parameter contained in the Solver object to identify the route
 * from the room with ID = x to the room with ID = y and viceversa, without distinction.
 */
@Getter
@Setter
@AllArgsConstructor
public class KeyDistance {

    private int x;
    private int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyDistance key)) return false;
        return x == key.x && y == key.y || x == key.y && y == key.x;
    }

    @Override
    public int hashCode() {
        return (x * 31) + (y * 31);
    }
}
