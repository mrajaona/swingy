package mrajaona.swingy.model.character;

import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.data.artifact.WeaponData;
import mrajaona.swingy.exception.InvalidArtifactException;
import mrajaona.swingy.exception.ResourceMapException;
import mrajaona.swingy.exception.SwingyException;
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

    public static ArtifactData generateArtifact(int enemyLevel) throws InvalidArtifactException, ResourceMapException {
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
                throw (new InvalidArtifactException());
        }
        if (resMap == null) {
            throw (new ResourceMapException());
        }
        ArrayList<String> keyList = resMap.keyList();
        keyList.remove("none");

        Random rand     = new Random();
        String name     = keyList.get(rand.nextInt(keyList.size()));

        int    modifier = (enemyLevel/2) + ((rand.nextInt(6) + 1) - 3);
        modifier = modifier < 1 ? 3 : modifier * 3;

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
                throw (new InvalidArtifactException());
        }
        return (artifact);
    }

    public static void die() throws SwingyException {
        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgDied"),
            GameData.getData().getEnemy().getEnemyType() // %1$s
        );
        MainHelper.printMsg(msg);

        HeroModel.earnExp(GameData.getData().getEnemy().getExperience());

        int enemyLevel = GameData.getData().getEnemy().getLevel();

        GameMapModel.enemyDied();

        Random rand  = new Random();
        boolean drop = rand.nextInt(10) == 0 ? false : true;

        if (drop)
            GameModel.drop(generateArtifact(enemyLevel));
        else
            GameModel.noDrop();
    }

}
