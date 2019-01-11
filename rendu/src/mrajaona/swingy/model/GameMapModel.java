package mrajaona.swingy.model;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.GameMapData;
import mrajaona.swingy.util.ResourceMap;

/*
** GameMap Model
** Manipulates GameMap data
*/

public class GameMapModel {

    private static GameMapModel model = new GameMapModel();

    private GameMapModel() {}

    public static GameMapModel getModel() {
        return (model);
    }

    GameMapData initMap() {
        return (new GameMapData(GameData.getHero().getLevel()));
    }

    // move hero
    public void move(String direction) {
        // delocalize direction
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.DirectionResource", GameData.getData().getLocale() );
        ResourceMap resMap    = (ResourceMap) locale.getObject("DirectionList");
        direction             = resMap.getKeyByValue(direction);

        if (direction == null) {
            ; // TODO : Error : bad input
            return ;
        }

        GameMapData map = GameData.getMap();

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

        // check border
        int x = map.getCoordX();
        int y = map.getCoordY();
        if (
               x == 0 || x == (GameData.getMap().getSize() - 1)
            || y == 0 || y == (GameData.getMap().getSize() - 1)
        ) {
            // TODO : win
        }


    }

}
