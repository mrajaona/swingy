package mrajaona.swingy.builder;

import java.util.HashMap;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import mrajaona.swingy.data.GameMapData;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.model.GameMapModel;
import mrajaona.swingy.util.Coord;

public class GameMapBuilder {

    @Getter private long id;

    @Range(min=0, max=100)
    @Getter private int level;

    @Range(min=5, max=505)
    @Getter private int size;

    @Valid
    @Getter private Coord heroCoord = new Coord();
    @Valid
    @Getter private Coord prevCoord = new Coord();
    /* x = 0 is west ** y = 0 is south */

    @NotNull
    @Getter private HashMap<Coord, EnemyData> enemies;

    public GameMapBuilder setId(long id) {
        this.id = id;
        return (this);
    }

    public GameMapBuilder setLevel(int level) {
        this.level = level;
        size = (level - 1) * 5 + 10 - (level % 2);

        return (this);
    }

    public GameMapBuilder setHeroCoord(int x, int y) {
        heroCoord.setCoords(x, y);
        return (this);
    }

    public GameMapBuilder setPrevCoord(int x, int y) {
            prevCoord.setCoords(x, y);
        return (this);
    }

    public GameMapBuilder setHeroCoord(Coord newCoord) {
        heroCoord = newCoord;
        return (this);
    }

    public GameMapBuilder setPrevCoord(Coord newCoord) {
        prevCoord = newCoord;
        return (this);
    }

    public GameMapBuilder setEnemies(HashMap<Coord, EnemyData> enemies) {
        this.enemies = new HashMap<Coord, EnemyData> (enemies);
        return (this);
    }

    public GameMapData build() {

        // Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        // It validates bean instances
        Validator validator = factory.getValidator();

        // Validate bean
        Set<ConstraintViolation<GameMapBuilder>> mapConstraintViolations = validator.validate(this);

        boolean error = false;

        // Show errors
        if (mapConstraintViolations.size() > 0) {
            // Exception
            System.out.println("Invalid map");
            error = true;
        } else if (
            !GameMapModel.checkCoord(heroCoord, size)
            || !GameMapModel.checkCoord(prevCoord, size)
            ) {
            // Exception
            System.out.println("Invalid map");
            error = true;
        }

        if (
            heroCoord.getX() >= size || prevCoord.getY() >= size
            || heroCoord.getX() >= size || prevCoord.getY() >= size
            )
        {
            // Exception
            System.out.println("Invalid coordinates");
            error = true;
        }

        if (error == true)
            return (null);
        else {
            return (new GameMapData(this));
        }

    }

    public GameMapData newMap() {
        enemies = new HashMap<Coord, EnemyData>();
        return (
            setLevel(0)
            .setHeroCoord(0, 0)
            .setPrevCoord(0, 0)
            .build()
            );
    }

    public GameMapData loadMap(GameMapData loaded) {
        if (loaded == null)
            return (null);

        return (
            setId(loaded.getId())
            .setLevel(loaded.getLevel())
            .setHeroCoord(loaded.getHeroCoord())
            .setPrevCoord(loaded.getPrevCoord())
            .setEnemies(loaded.getEnemies())
            .build()
        );
    }

}
