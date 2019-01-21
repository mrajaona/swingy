package mrajaona.swingy.builder;

import java.util.HashMap;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import mrajaona.swingy.data.GameMapData;
import mrajaona.swingy.data.character.EnemyData;

public class GameMapBuilder {

    @Getter private long id;

    @Range(min=0, max=100)
    @Getter private int level;

    @Range(min=5, max=505)
    @Getter private int size;

    @NotNull // length == 2
    // @Valid // @PositiveOrZero
    @Getter private int[] heroCoord = new int[2];
    @NotNull
    // @Valid // @PositiveOrZero
    @Getter private int[] prevCoord = new int[2];
    /* x = 0 is west ** y = 0 is south */

    // TODO : enemies positions
    @NotNull
    @Getter private HashMap<EnemyData, int[]> enemies;
    // map.put(enemy, coords);

    public GameMapBuilder setId(long id) {
        this.id = id;
        return (this);
    }

    public GameMapBuilder setLevel(int level) {
        this.level = level;
        size = (level - 1) * 5 + 10 - (level % 2);

        return (this);
    }

    public GameMapBuilder setHeroCoord(int[] coords) {
        if (coords.length != 2) {
            // Exception
        }
        return (
            setHeroCoord(
                coords[0],
                coords[1]
                ));
    }

    public GameMapBuilder setPrevCoord(int[] coords) {
        if (coords.length != 2) {
            // Exception
        }
        return (
            setPrevCoord(
                coords[0],
                coords[1]
                ));
    }

    public GameMapBuilder setHeroCoord(int x, int y) {
        heroCoord[0] = x;
        heroCoord[1] = y;
        return (this);
    }

    public GameMapBuilder setPrevCoord(int x, int y) {
        prevCoord[0] = x;
        prevCoord[1] = y;
        return (this);
    }

    // TODO : enemies positions
    public GameMapBuilder setEnemies(HashMap<EnemyData, int[]> enemies) {
        this.enemies = new HashMap<EnemyData, int[]> (enemies);
        return (this);
    }

    public GameMapData build() {

        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        //It validates bean instances
        Validator validator = factory.getValidator();

        //Validate bean
        Set<ConstraintViolation<GameMapBuilder>> mapConstraintViolations = validator.validate(this);

        boolean error = false;

        //Show errors
        if (mapConstraintViolations.size() > 0) {
            // TODO : Exception
            System.out.println("Invalid map");
            error = true;

            for(ConstraintViolation<GameMapBuilder> violation : mapConstraintViolations) {
                System.out.println(violation.getPropertyPath() + " : " + violation.getMessage());
            }

        }

        if (error == true)
            return (null);
        else {
            System.out.println("Valid map"); // DEBUG
            return (new GameMapData(this));
        }

    }

    // TODO : enemies positions
    public GameMapData newMap() {
        enemies = new HashMap<EnemyData, int[]>();
        return (
            setLevel(0)
            .setHeroCoord(0, 0)
            .setPrevCoord(0, 0)
            .build()
            );
    }

    // TODO : enemies positions
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
