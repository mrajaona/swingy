package mrajaona.swingy.util;

import java.io.Serializable;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

public class Coord implements Serializable {

	private static final long serialVersionUID = 7470261414487830806L;

    @PositiveOrZero
    @Getter @Setter private int x;

    @PositiveOrZero
    @Getter @Setter private int y;

    private static transient ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static transient Validator validator      = factory.getValidator();

    /*
    ** x = 0 is west
    ** y = 0 is south
    */

    public Coord() {
        x = 0;
        y = 0;
        validate();
    }

    public Coord(int x, int y) {
        setCoords(x, y);
    }

    public void setCoords(Coord newCoord) {
        this.x = newCoord.x;
        this.y = newCoord.y;
        validate();
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
        validate();
    }

    private void validate() {
        Set<ConstraintViolation<Coord>> coordConstraintViolations = validator.validate(this);
        if (coordConstraintViolations.size() > 0) {
            // TODO : Exception
            System.out.println("Invalid coord");
        }
    }

    // For using as HashMap key
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coord))
            return false;
        if (obj == this)
            return true;

        Coord rhs = (Coord) obj;
        return ( x == rhs.x && y == rhs.y );
    }

    @Override
    public int hashCode() {
        return (x + y);
    }

}
