package mrajaona.swingy.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.text.html.HTMLDocument.Iterator;

import mrajaona.swingy.builder.EnemyBuilder;
import mrajaona.swingy.builder.GameMapBuilder;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.GameMapData;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.util.Coord;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.view.helper.MainHelper;

/*
** GameMap Model
** Manipulates GameMap data
*/

public class GameMapModel {

    @SuppressWarnings("unused")
    private GameMapModel() {}

    public static void removeMap() {
        GameMapBuilder builder = new GameMapBuilder();
        GameMapData map = builder.newMap();
        GameData.getData().setMap(map);
        GameData.getData().setEnemy(null);
        GameData.getData().setArtifact(null);
    }

    public static void initMap() throws SQLException, IOException {
        GameMapData map = GameData.getData().getMap();
        int level       = GameData.getData().getHero().getLevel();

        map.setLevel(level);

        int size = (level - 1) * 5 + 10 - (level % 2);
        map.setSize(size);

        // place hero at the center of the map
        map.initCoord(size / 2, size / 2);

        generateEnemies();
        checkInitPosition();
    }

    private static void generateEnemies() {
        int mapLevel = GameData.getData().getMap().getLevel();
        int mapSize  = GameData.getData().getMap().getSize();
        HashMap<Coord, EnemyData> enemies = new HashMap<Coord, EnemyData>();

        Random rand = new Random();

        for (int y = 0 ; y < mapSize ; y++) {
            for (int x = 0 ; x < mapSize ; x++) {

                boolean create = false;

                if (!( (x == 0 || x == mapSize - 1)
                    || (y == 0 || y == mapSize - 1) ))
                create = rand.nextInt(10) == 0 ? true : false;

                if (create) {
                    String enemyType = Util.enemyTypes[rand.nextInt(Util.enemyTypes.length)];
                    // set enemy level [mapLevel - 3, mapLevel + 3]
                    int enemyLevel = mapLevel + ((rand.nextInt(6) + 1) - 3);
                    // minimum enemy level is 1
                   enemyLevel = enemyLevel < 1 ? 1 : enemyLevel;

                    enemies.put(
                        new Coord(x, y),
                        new EnemyBuilder().newEnemy(enemyType, enemyLevel)
                    );
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
        ResourceBundle errLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.ErrorResource", GameData.getData().getLocale() );
        ResourceBundle locale    = ResourceBundle.getBundle( "mrajaona.swingy.locale.DirectionResource", GameData.getData().getLocale() );
        ResourceMap    resMap    = (ResourceMap) locale.getObject("DirectionList");
        String dir               = resMap.getKeyByValue(direction);

        if (dir == null) {
            MainHelper.printMsg(errLocale.getString("invalidDirection"));
            return ;
        }

        GameMapData map = GameData.getData().getMap();
        if (map == null) {
            // Exception
            return ;
        }

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
                ; // Exception
                break ;
        }

        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgMove"),
            GameData.getData().getHero().getHeroName(), // %1$s
            direction, // %2$s // localized
            map.getCoordX(), // %3$d
            map.getCoordY() // %4$d
        );

        MainHelper.printMsg(msg);

        if (!checkCoord(GameData.getData().getMap().getHeroCoord())) {
            // Exception
            return ;
        }

        checkPosition();
    }

    public static void checkInitPosition() throws SQLException, IOException {
        if (checkWin()) {
            GameModel.changeScreen(GameScreen.WIN);
        } else if (checkEnemy()) {
            MainHelper.changeSubScreen();
            GameModel.changeScreen(GameScreen.MAIN);
        } else {
            GameModel.changeScreen(GameScreen.MAIN); // Keep playing
        }
    }

    private static void checkPosition() throws SQLException, IOException {
        if (checkWin()) {
            GameModel.changeScreen(GameScreen.WIN);
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

            String msg = String.format(
                ResourceBundle.getBundle("mrajaona.swingy.locale.InterfaceResource",
                    GameData.getData().getLocale()
                ).getString("msgEncounter"),
                enemy.getLevel(), // %1$d
                ResourceBundle.getBundle("mrajaona.swingy.locale.EnemyResource",
                    GameData.getData().getLocale()
                ).getString(enemy.getEnemyType()) // %2$s
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
            || (y == 0 || y == max)
        ) {
            return (true);
        }

        return (false);
    }

    public static void goBack() throws SQLException, IOException {
        GameData.getData().getMap().goBack();
        GameModel.enemyEncounterEnd();
        checkPosition();
    }

    public static void enemyDied() {
        GameModel.enemyEncounterEnd(); // remove from game data
        GameData.getData().getMap().removeEnemy();
    }

}
