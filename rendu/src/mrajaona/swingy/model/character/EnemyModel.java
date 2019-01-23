package mrajaona.swingy.model.character;

import mrajaona.swingy.model.GameMapModel;
import mrajaona.swingy.model.GameModel;

/*
** Enemy Model
** Manipulates Enemy data
*/

public class EnemyModel {

    @SuppressWarnings("unused")
    private EnemyModel() {}

    // Character methods

    public static void fullRecover() {
        ;
    }

    public static void recoverHP(int amount) {
        ;
    }

    public static void loseHP(int amount) {
        ;
    }

    public static void beAttacked(int enemyAtk) {
        ;
    }

    public static void die() {
        // remove from game data
        GameModel.enemyEncounterEnd();
        // remove from map
        GameMapModel.enemyDied();
        // TODO : drop artifact
        GameModel.drop(null);
    }

}
