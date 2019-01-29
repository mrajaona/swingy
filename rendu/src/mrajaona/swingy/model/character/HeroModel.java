package mrajaona.swingy.model.character;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.data.artifact.WeaponData;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.exception.DataException;
import mrajaona.swingy.exception.InvalidArtifactException;
import mrajaona.swingy.exception.InvalidViewTypeException;
import mrajaona.swingy.exception.SwingyException;
import mrajaona.swingy.model.GameMapModel;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.model.artifact.ArmorModel;
import mrajaona.swingy.model.artifact.HelmModel;
import mrajaona.swingy.model.artifact.WeaponModel;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.view.gui.GUIMain;
import mrajaona.swingy.view.helper.MainHelper;

/*
** Hero Model
** Manipulates Hero data
*/

public class HeroModel {

    @SuppressWarnings("unused")
    private HeroModel() {}

    public final static int HERO_MAX_LVL = 100;

    // XP

    public static void earnExp(double amount) throws InvalidViewTypeException {
        HeroData hero = GameData.getData().getHero();
        double exp      = hero.getExperience() + amount;
        double expToLvl = hero.getLevel() * 1000 + Math.pow( (hero.getLevel() - 1), 2) * 450;

        if (exp >= expToLvl) {
            if (hero.getLevel() < HERO_MAX_LVL) {
                levelUp(hero);
                exp -= expToLvl;
            } else {
                exp = expToLvl;
            }
        }

        hero.setExperience(exp);
        GUIMain.getScreen().updateTable();
    }

    public static void levelUp(HeroData hero) throws InvalidViewTypeException {
        if (hero.getLevel() < HERO_MAX_LVL) {
            hero.setLevel(hero.getLevel() + 1);
            String msg = String.format(
                ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgLvlUp"),
                GameData.getData().getHero().getHeroName(), // %1$s
                hero.getLevel() // %2$d
                );
            MainHelper.printMsg(msg);
            System.out.println(msg);
            CharacterModel.fullRecover(hero);
        }
    }

    // Equipment

    public static void equip(ArtifactData artifact) throws SQLException, IOException, SwingyException {
        switch(artifact.getType()) {
            case HELM :
                HelmModel.equip((HelmData) artifact);
                break ;
            case ARMOR :
                ArmorModel.equip((ArmorData) artifact);
                break ;
            case WEAPON :
                WeaponModel.equip((WeaponData) artifact);
                break ;
            default :
                throw (new InvalidArtifactException());
        }
        updateStats();
        GameModel.noDrop();
    }

    public static void unequip(ArtifactData artifact) throws InvalidArtifactException {
        switch(artifact.getType()) {
            case HELM :
                ((HelmData) artifact).remove();
                break ;
            case ARMOR :
                ((ArmorData) artifact).remove();
                break ;
            case WEAPON :
                ((WeaponData) artifact).remove();
                break ;
            default :
                throw (new InvalidArtifactException());
        }
        updateStats();
    }

    public static void noLoot() throws SQLException, IOException, InvalidViewTypeException {
        MainHelper.printMsg(ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgLeave"));
        GameModel.noDrop();
    }

    // Stats

    private static void updateStats() {
        HeroData hero = GameData.getData().getHero();

        hero.setAttack(hero.getBaseAttack() + hero.getWeapon().getModifier());
        hero.setDefense(hero.getBaseDefense() + hero.getArmor().getModifier());
        hero.setMaxHitPoints(hero.getBaseHitPoints() + hero.getHelm().getModifier());
        if (hero.getHitPoints() > hero.getMaxHitPoints())
            hero.setHitPoints(hero.getMaxHitPoints());

        GUIMain.getScreen().updateTable();
    }

    public static void viewStats() throws InvalidViewTypeException {
        HeroData hero = GameData.getData().getHero();
        ResourceBundle locale         = ResourceBundle.getBundle( "mrajaona.swingy.locale.StatResource", GameData.getData().getLocale() );
        ResourceBundle heroLocale     = ResourceBundle.getBundle( "mrajaona.swingy.locale.HeroResource", GameData.getData().getLocale() );
        ResourceBundle artifactLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() );
        ResourceMap    helmLocale     = (ResourceMap) artifactLocale.getObject( "HelmList" );
        ResourceMap    armorLocale    = (ResourceMap) artifactLocale.getObject( "ArmorList" );
        ResourceMap    weaponLocale   = (ResourceMap) artifactLocale.getObject( "WeaponList" );
        DecimalFormat  format         = new DecimalFormat("#");

        MainHelper.printMsg(
            System.lineSeparator() +
            locale.getString("name")          + " : " + hero.getHeroName() + System.lineSeparator() +
            locale.getString("class")         + " : " + ((ResourceMap) heroLocale.getObject("ClassesList")).get(hero.getHeroClass()) + System.lineSeparator() +
            locale.getString("level")         + " : " + hero.getLevel() + System.lineSeparator() +
            locale.getString("experience")    + " : " + format.format(hero.getExperience()) + " / " + format.format(hero.getLevel() * 1000 + Math.pow( (hero.getLevel() - 1), 2) * 450) + System.lineSeparator() +

            locale.getString("helm")          + " : " + helmLocale.get(hero.getHelm().getName())     + " (" + hero.getHelm().getModifier()   + ")" + System.lineSeparator() +
            locale.getString("armor")         + " : " + armorLocale.get(hero.getArmor().getName())   + " (" + hero.getArmor().getModifier()  + ")" + System.lineSeparator() +
            locale.getString("weapon")        + " : " + weaponLocale.get(hero.getWeapon().getName()) + " (" + hero.getWeapon().getModifier() + ")" + System.lineSeparator() +

            locale.getString("attack")        + " : " + Integer.toString(hero.getAttack()) + System.lineSeparator() +
            locale.getString("defense")       + " : " + Integer.toString(hero.getDefense()) + System.lineSeparator() +
            locale.getString("hitPoints")     + " : " + hero.getHitPoints() + " / " + hero.getMaxHitPoints() + System.lineSeparator()
        );

    }

    // move

    public static void move(String direction) throws SQLException, IOException, SwingyException {
        GameMapModel.move(direction);
    }

    public static void run() throws SQLException, IOException, SwingyException {
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() );
        String msg = String.format(
            locale.getString("msgRun"),
            GameData.getData().getHero().getHeroName() // %1$s
            );
        MainHelper.printMsg(msg);

        if (SwingUtilities.isEventDispatchThread()) { // sleep freezes the GUI
            runResult();
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            runResult();
        }

    }

    private static void runResult() throws SQLException, IOException, SwingyException {
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() );
        Random rand = new Random();

        if ( rand.nextInt(2) == 0) { // 1/2
            String msg = locale.getString("msgRunSuccess");
            MainHelper.printMsg(msg);
            GameMapModel.goBack();
        } else {
            String msg = locale.getString("msgRunFail");
            MainHelper.printMsg(msg);
            CharacterModel.attack(GameData.getData().getEnemy(), GameData.getData().getHero()); // Enemy gets initiative
            fight();
        }
    }

    // battle

    public static void fight() throws SQLException, IOException, SwingyException {
        HeroData  hero  = GameData.getData().getHero();
        EnemyData enemy = GameData.getData().getEnemy();

        if (hero == null || enemy == null) {
            throw (new DataException());
        }

        while (!CharacterModel.isDead(hero) && !CharacterModel.isDead(enemy)) {
            CharacterModel.fight(hero, enemy);
        }

        if (CharacterModel.isDead(hero)) {
            die();
            GameModel.changeScreen(GameScreen.LOSE);
        } else if (CharacterModel.isDead(enemy)) {
            EnemyModel.die();
        } else {
            throw (new DataException());
        }

    }

    public static void die() throws InvalidViewTypeException {
        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgDied"),
            GameData.getData().getHero().getHeroName() // %1$s
        );
        MainHelper.printMsg(msg);
    }

}
