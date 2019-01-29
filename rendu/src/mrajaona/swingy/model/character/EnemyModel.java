package mrajaona.swingy.model.character;

import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.data.artifact.WeaponData;
import mrajaona.swingy.exception.InvalidViewTypeException;
import mrajaona.swingy.model.GameMapModel;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.util.Util.ArtifactType;
import mrajaona.swingy.view.helper.MainHelper;

/*
** Enemy Model
** Manipulates Enemy data
*/

public class EnemyModel {

    @SuppressWarnings("unused")
    private EnemyModel() {}

    public static ArtifactData generateArtifact() {
        ArtifactType type = ArtifactType.random();

        ResourceMap resMap;
        switch (type) {
            case HELM :
                resMap = (ResourceMap) ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.ArtifactResource",
                    GameData.getData().getLocale() )
                .getObject("HelmList");
                break ;
            case ARMOR :
                resMap = (ResourceMap) ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.ArtifactResource",
                    GameData.getData().getLocale() )
                .getObject("ArmorList");
                break ;
            case WEAPON :
                resMap = (ResourceMap) ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.ArtifactResource",
                    GameData.getData().getLocale() )
                .getObject("WeaponList");
                break ;
            default :
                // Exception
                resMap = null;
                break ;
        }
        if (resMap == null) {
            // Exception
            return null;
        }
        ArrayList<String> keyList = resMap.keyList();
        keyList.remove("none");

        Random rand     = new Random();
        String name     = keyList.get(rand.nextInt(keyList.size()));
        int    mapLevel = GameData.getData().getMap().getLevel();
        int    modifier = (mapLevel/2) + ((rand.nextInt(6) + 1) - 3);
        modifier = modifier < 1 ? 1 : modifier;

        ArtifactData artifact;
        switch (type) {
            case HELM :
                artifact = new HelmData(name, modifier);
                break ;
            case ARMOR :
                artifact = new ArmorData(name, modifier);
                break ;
            case WEAPON :
                artifact = new WeaponData(name, modifier);
                break ;
            default :
                // Exception
                artifact = null;
                break ;
        }
        return (artifact);
    }

    public static void die() throws InvalidViewTypeException {
        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgDied"),
            GameData.getData().getEnemy().getEnemyType() // %1$s
        );
        MainHelper.printMsg(msg);

        HeroModel.earnExp(GameData.getData().getEnemy().getExperience());

        GameMapModel.enemyDied();

        Random rand  = new Random();
        boolean drop = rand.nextInt(10) == 0 ? false : true;

        if (drop)
            GameModel.drop(generateArtifact());
        else
            GameModel.noDrop();
    }

}
