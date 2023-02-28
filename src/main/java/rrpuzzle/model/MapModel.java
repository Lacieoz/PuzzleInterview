package rrpuzzle.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Main class that maps the data contained in the input json file
 */

@Getter
@Setter
public class MapModel {

    private List<Room> rooms;

}
