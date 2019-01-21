package mrajaona.swingy.data;

import java.util.HashMap;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import lombok.Setter;
import mrajaona.swingy.builder.GameMapBuilder;
import mrajaona.swingy.data.character.EnemyData;

/*
** Data for GameMap
** Modifiable via the GameMap model
*/

@DatabaseTable(tableName = "maps")
public class GameMapData {
    // Todo : Enemies

    @DatabaseField(generatedId = true)
    @Getter private long id;

    @DatabaseField(canBeNull = false)
    @Getter @Setter private int level;
    @Getter @Setter private int size;

    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    @Getter private int[] heroCoord = new int[2];
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    @Getter private int[] prevCoord = new int[2];
    /*
    ** coord[x, y]
    ** x = 0 is west
    ** y = 0 is south
    */

    // TODO : enemies positions
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    @Getter private HashMap<EnemyData, int[]> enemies;

    // necessary for ORMLite
    GameMapData() {}

    public GameMapData(GameMapBuilder builder) {
        if (builder.getId() != 0)
            this.id       = builder.getId();
        this.level        = builder.getLevel();
        this.size         = builder.getSize();

        int[] coord = builder.getHeroCoord();

        this.heroCoord[0] = coord[0];
        this.heroCoord[1] = coord[1];

        coord = builder.getPrevCoord();

        this.prevCoord[0] = coord[0];
        this.prevCoord[1] = coord[1];

        this.enemies      = new HashMap<EnemyData, int[]>(builder.getEnemies());
    }

    public int getCoordX() {
        return (heroCoord[0]);
    }

    public void setCoordX(int value) {
        prevCoord[0] = heroCoord[0];
        heroCoord[0] = value;
    }

    public int getCoordY() {
        return (heroCoord[1]);
    }

    public void setCoordY(int value) {
        prevCoord[1] = heroCoord[1];
        heroCoord[1] = value;
    }

    public void goBack() {
        heroCoord[0] = prevCoord[0];
        heroCoord[1] = prevCoord[1];
    }

    public void cleanCoord() {
        heroCoord[0] = 0;
        heroCoord[1] = 0;
        prevCoord[0] = 0;
        prevCoord[1] = 0;
    }

    public void initCoord(int x, int y) {
        heroCoord[0] = x;
        heroCoord[1] = y;
        prevCoord[0] = x;
        prevCoord[1] = y;
    }

    public void enemyDied(EnemyData enemy) {
        enemies.remove(enemy);
    }

}
