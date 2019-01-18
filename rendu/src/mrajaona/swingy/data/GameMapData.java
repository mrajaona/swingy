package mrajaona.swingy.data;

import lombok.Getter;

/*
** Data for GameMap
** Modifiable via the GameMap model
*/

public class GameMapData {
    // Todo : Enemies

    @Getter private int size;

    private int[] heroCoord = new int[2];
    private int[] prevCoord = new int[2];
    /* x = 0 is west ** y = 0 is south */

    @SuppressWarnings("unused")
    private GameMapData() {}

    public GameMapData(int level) {
        size = (level - 1) * 5 + 10 - (level % 2);

        // place hero at the center of the map
        heroCoord[0] = size / 2; // x
        heroCoord[1] = size / 2; // y
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

}
