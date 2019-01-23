package mrajaona.swingy.model.character;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.model.GameMapModel;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.view.helper.MainHelper;

/*
** Enemy Model
** Manipulates Enemy data
*/

public class EnemyModel {

    @SuppressWarnings("unused")
    private EnemyModel() {}

    public static void die() {
        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() ).getString("msgDied"),
            GameData.getData().getEnemy().getEnemyType() // %1$s
        );
        MainHelper.printMsg(msg);

        HeroModel.earnExp(GameData.getData().getEnemy().getExperience());

        GameMapModel.enemyDied();
        // TODO : drop artifact
        GameModel.drop(null);
    }

}
