package mrajaona.swingy.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.text.html.HTMLDocument.Iterator;

import mrajaona.swingy.builder.EnemyBuilder;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.GameMapData;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.util.Coord;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.util.Util;
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
        HashMap<Coord, EnemyData> enemies = new HashMap<Coord, EnemyData>();

        Random rand = new Random();

        for (int y = 0 ; y < mapSize ; y++) {
            for (int x = 0 ; x < mapSize ; x++) {
                // TODO
                boolean create = rand.nextInt(10) == 0 ? true : false;

                if (create) {
                    String enemyType = Util.enemyTypes[rand.nextInt(Util.enemyTypes.length)];
                    // set enemy level [mapLevel - 3, mapLevel + 3]
                    int enemyLevel = mapLevel + ((rand.nextInt(6) + 1) - 3);
                    // minimum enemy level is 1
                    if (enemyLevel < 1)
                        enemyLevel = 1;

                    enemies.put(
                        new Coord(x, y),
                        new EnemyBuilder().newEnemy(enemyType, enemyLevel)
                    );

                    // Debug
                    System.out.println(String.format("lvl %1d %2$s at [%3$d, %4$d]", enemyLevel, enemyType, x, y));
                }

            }
            GameData.getData().getMap().setEnemies(enemies);
        }
    }

    public static void clear() {
        GameMapData map = GameData.getData().getMap();

        map.setLevel(0);
        map.cleanCoord();
        map.getEnemies().clear();
    }

    public static boolean checkCoord(Coord coord) {
        int size = GameData.getData().getMap().getSize();
        return (checkCoord(coord, size));
    }

    public static boolean checkCoord(Coord coord, int size) {
        if (
            coord == null
            || !(coord.getX() >= 0 && coord.getX() < size)
            || !(coord.getY() >= 0 && coord.getY() < size)
            )
            return (false);
        return (true);
    }

    // move hero
    public static void move(String direction) throws SQLException, IOException {
        // delocalize direction
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.DirectionResource", GameData.getData().getLocale() );
        ResourceMap resMap    = (ResourceMap) locale.getObject("DirectionList");
        String dir            = resMap.getKeyByValue(direction);

        if (dir == null) {
            ; // TODO : Error : bad input
            return ;
        }

        GameMapData map = GameData.getData().getMap();

        switch(dir) {
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

        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() ).getString("msgMove"),
            GameData.getData().getHero().getHeroName(), // %1$s
            direction, // %2$s // localized
            map.getCoordX(), // %3$d
            map.getCoordY() // %4$d
        );

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
            MainHelper.changeSubScreen();
        } else {
            MainHelper.waitForInput(); // Keep playing
        }
    }

    private static boolean checkEnemy() {
        HashMap<Coord, EnemyData> enemies = GameData.getData().getMap().getEnemies();

        GameMapData map = GameData.getData().getMap();
        EnemyData enemy = enemies.get(map.getHeroCoord());

        if (enemy != null) {
            GameModel.enemyEncounter(enemy);

            // Todo : localize enemy type
            String msg = String.format(
                ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() ).getString("msgEncounter"),
                enemy.getLevel(), // %1$d
                enemy.getEnemyType() // %2$s
            );

            MainHelper.printMsg(msg);
            return (true);
        }

        return (false);
    }

    private static boolean checkWin() {
        GameMapData map = GameData.getData().getMap();

        // check border
        int x = map.getHeroCoord().getX();
        int y = map.getHeroCoord().getY();
        int max = GameData.getData().getMap().getSize() - 1;
        if (
               (x == 0 || x == max)
            && (y == 0 || y == max)
        ) {
            return (true);
        }

        return (false);
    }

    public static void goBack() throws SQLException, IOException {
        GameData.getData().getMap().goBack();

        GameModel.enemyEncounterEnd();
        MainHelper.changeSubScreen();

        checkPosition();
    }

    public static void enemyDied() {
        GameData.getData().getMap().removeEnemy();
    }

}
