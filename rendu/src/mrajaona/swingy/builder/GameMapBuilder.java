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

public class GameMapBuilder {

    @Getter private long id;

    @Range(min=0, max=100)
    @Getter private int level;

    @Range(min=5, max=505)
    @Getter private int size;

    @Valid
    @Getter private int[] heroCoord = new int[2];
    @NotEmpty @Size(min=2, max=2)
    @Getter private int[] prevCoord = new int[2];
    /* x = 0 is west ** y = 0 is south */

    // TODO : enemies positions
    @NotNull
    @Getter private HashMap<EnemyData, int[]> enemies;
    // map.put(enemy, coord);

    public GameMapBuilder setId(long id) {
        this.id = id;
        return (this);
    }

    public GameMapBuilder setLevel(int level) {
        this.level = level;
        size = (level - 1) * 5 + 10 - (level % 2);

        return (this);
    }

    public GameMapBuilder setHeroCoord(int[] coord) {
        return (
            setHeroCoord(
                coord[0],
                coord[1]
                ));
    }

    public GameMapBuilder setPrevCoord(int[] coord) {
        return (
            setPrevCoord(
                coord[0],
                coord[1]
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
        } else if (
            !GameMapModel.checkCoord(heroCoord, size)
            || !GameMapModel.checkCoord(prevCoord, size)
            ) {
            // TODO : Exception
            System.out.println("Invalid map");
            error = true;
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
