package mrajaona.swingy.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.GameMapData;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.view.helper.MainHelper;

/*
** GameMap Model
** Manipulates GameMap data
*/

public class GameMapModel {

    @SuppressWarnings("unused")
    private GameMapModel() {}

    public static void initMap() {
        GameMapData map = GameData.getData().getMap();
        int level       = GameData.getData().getHero().getLevel();

        map.setLevel(level);

        int size = (level - 1) * 5 + 10 - (level % 2);
        map.setSize(size);

        // place hero at the center of the map
        map.initCoord(size / 2, size / 2);

        generateEnemies();
    }

    private static void generateEnemies() {
        int mapLevel = GameData.getData().getMap().getLevel();
        int mapSize  = GameData.getData().getMap().getSize();
        HashMap<EnemyData, int[]> enemies = new HashMap<EnemyData, int[]>();

        // TODO
        for (int y = 0 ; y < mapSize ; y++) {
            for (int x = 0 ; x < mapSize ; x++) {
//                if (conditions) {

//                    String enemyType = Util.enemyTypes[/*random*/ % Util.enemyTypes.length];
//                    int enemyLevel = mapLevel + (/* +- rand 3 lvl */) // min 1
/*
                    enemies.add(
                        new EnemyBuilder().newEnemy(type, level),
                        new int[] = {x, y}
                    );
                }
*/

            }
        // GameData.getData().getMap().setEnemies(enemies);
        }
    }

    public static void clear() {
        GameMapData map = GameData.getData().getMap();

        map.setLevel(0);
        map.cleanCoord();
        map.getEnemies().clear();
    }

    public static boolean checkCoord(int[] coord) {
        int size = GameData.getData().getMap().getSize();
        return (checkCoord(coord, size));
    }

    public static boolean checkCoord(int[] coord, int size) {
        if (
            coord == null
            || coord.length != 2
            || !(coord[0] >= 0 && coord[0] < size)
            || !(coord[1] >= 0 && coord[1] < size)
            )
            return (false);
        return (true);
    }

    // move hero
    public static void move(String direction) throws SQLException, IOException {
        // delocalize direction
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.DirectionResource", GameData.getData().getLocale() );
        ResourceMap resMap    = (ResourceMap) locale.getObject("DirectionList");
        direction             = resMap.getKeyByValue(direction);

        if (direction == null) {
            ; // TODO : Error : bad input
            return ;
        }

        GameMapData map = GameData.getData().getMap();

        switch(direction) {
            case "north" :
                map.setCoordX( map.getCoordX() + 1 );
                break ;
            case "south" :
                map.setCoordX( map.getCoordX() - 1 );
                break ;
            case "east" :
                map.setCoordY( map.getCoordY() + 1 );
                break ;
            case "west" :
                map.setCoordY( map.getCoordY() - 1 );
                break ;
            default :
                ; // TODO : Exception
                break ;
        }

        String msg = ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() ).getString("msgMove")
        .replace("<hero>", GameData.getData().getHero().getHeroName())
        .replace("<direction>", direction)
        .replace("<x>", Integer.toString(map.getCoordX()))
        .replace("<y>", Integer.toString(map.getCoordY()))
        ;

        MainHelper.printMsg(msg);

        if (!checkCoord(GameData.getData().getMap().getHeroCoord())) {
            // TODO : Exception
            return ;
        }

        checkPosition();
    }

    private static void checkPosition() throws SQLException, IOException {
        if (checkWin()) {
            // TODO : win screen
            MainHelper.printMsg( "YOU WIN" );
        } else if (checkEnemy()) {
            // TODO : Battle
        } else {
            MainHelper.waitForInput(); // Keep playing
        }
    }

    private static boolean checkEnemy() {
        return (false);
    }

    private static boolean checkWin() {
    	GameMapData map = GameData.getData().getMap();

        // check border
        int x = map.getCoordX();
        int y = map.getCoordY();
        if (
               x == 0 || x == (GameData.getData().getMap().getSize() - 1)
            || y == 0 || y == (GameData.getData().getMap().getSize() - 1)
        ) {
            return (true);
        }

        return (false);
    }


    public static void goBack() {
        GameData.getData().getMap().goBack();
    }

}
