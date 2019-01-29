package mrajaona.swingy.data;

import java.util.HashMap;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import lombok.Setter;
import mrajaona.swingy.builder.GameMapBuilder;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.exception.InvalidCoordException;
import mrajaona.swingy.util.Coord;

/*
** Data for GameMap
** Modifiable via the GameMap model
*/

@DatabaseTable(tableName = "maps")
public class GameMapData {

    @DatabaseField(generatedId = true)
    @Getter private long id;

    @DatabaseField(canBeNull = false)
    @Range(min=0, max=100)
    @Getter @Setter private int level;

    @Range(min=5, max=505)
    @Getter @Setter private int size;

    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    @Valid
    @Getter private Coord heroCoord = new Coord();
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    @Valid
    @Getter private Coord prevCoord = new Coord();

    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    @NotNull
    @Getter private HashMap<Coord, EnemyData> enemies;

    // necessary for ORMLite
    GameMapData() {}

    public GameMapData(GameMapBuilder builder) {
        if (builder.getId() != 0)
            this.id       = builder.getId();
        this.level        = builder.getLevel();
        this.size         = builder.getSize();

        this.heroCoord    = builder.getHeroCoord();
        this.prevCoord    = builder.getPrevCoord();

        this.enemies      = new HashMap<Coord, EnemyData>(builder.getEnemies());
    }

    public void setEnemies(HashMap<Coord, EnemyData> enemies) {
        this.enemies = new HashMap<Coord, EnemyData>(enemies);
    }

    public int getCoordX() {
        return (heroCoord.getX());
    }

    public void setCoordX(int value) {
        prevCoord.setX(heroCoord.getX());
        heroCoord.setX(value);
    }

    public int getCoordY() {
        return (heroCoord.getY());
    }

    public void setCoordY(int value) {
        prevCoord.setY(heroCoord.getY());
        heroCoord.setY(value);
    }

    public void goBack() throws InvalidCoordException {
        heroCoord.setCoords(prevCoord);
    }

    public void cleanCoord() throws InvalidCoordException {
        heroCoord.setCoords(0, 0);
        prevCoord.setCoords(0, 0);
    }

    public void initCoord(int x, int y) throws InvalidCoordException {
        heroCoord.setCoords(x, y);
        prevCoord.setCoords(x, y);
    }

    public void removeEnemy() {
        // remove enemy at current position
        enemies.remove(heroCoord);
    }

}
