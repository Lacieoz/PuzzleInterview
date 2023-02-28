package rrpuzzle.model;

import lombok.Getter;
import lombok.Setter;
import rrpuzzle.utils.Utils;

import java.util.List;

@Getter
@Setter
public class Room {

    private int id;
    private String name;
    private Integer north;
    private Integer south;
    private Integer east;
    private Integer west;
    private List<Object> objects;

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        boolean isEqual = id == room.getId();
        isEqual = isEqual && Utils.equalStringWithNull(name, room.getName());
        isEqual = isEqual && Utils.equalIntegerWithNull(north, room.getNorth());
        isEqual = isEqual && Utils.equalIntegerWithNull(south, room.getSouth());
        isEqual = isEqual && Utils.equalIntegerWithNull(east, room.getEast());;
        isEqual = isEqual && Utils.equalIntegerWithNull(west, room.getWest());
        isEqual = isEqual && ((objects == null && room.getObjects() == null) || objects.equals(room.getObjects()));
        return isEqual;
    }

}
