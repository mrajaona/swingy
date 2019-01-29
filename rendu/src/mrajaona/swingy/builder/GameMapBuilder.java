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
import mrajaona.swingy.exception.GameMapBuilderException;
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

    public GameMapData build() throws GameMapBuilderException {

        // Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        // It validates bean instances
        Validator validator = factory.getValidator();

        // Validate bean
        Set<ConstraintViolation<GameMapBuilder>> mapConstraintViolations = validator.validate(this);

        // Show errors
        if (
            // invalid data
            mapConstraintViolations.size() > 0
            // invaliid coordinates for this map
            || !GameMapModel.checkCoord(heroCoord, size)
            || !GameMapModel.checkCoord(prevCoord, size)
        ) {
            throw (new GameMapBuilderException());
        }

        return (new GameMapData(this));

    }

    public GameMapData newMap() throws GameMapBuilderException {
        enemies = new HashMap<Coord, EnemyData>();
        return (
            setLevel(0)
            .setHeroCoord(0, 0)
            .setPrevCoord(0, 0)
            .build()
            );
    }

    public GameMapData loadMap(GameMapData loaded) throws GameMapBuilderException {
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
