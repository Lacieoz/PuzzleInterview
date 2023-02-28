package rrpuzzle.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import rrpuzzle.utils.Utils;

@Getter
@Setter
@AllArgsConstructor
public class Object {

    private String name;

    public Object () {}

    @Override
    public boolean equals (java.lang.Object o) {
        if (this == o) return true;
        if (!(o instanceof Object object)) return false;
        return Utils.equalStringWithNull(name, object.getName());
    }
}
