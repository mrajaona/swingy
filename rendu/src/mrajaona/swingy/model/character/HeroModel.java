package mrajaona.swingy.model.character;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import lombok.Getter;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.data.artifact.WeaponData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.model.GameMapModel;
import mrajaona.swingy.model.artifact.ArmorModel;
import mrajaona.swingy.model.artifact.HelmModel;
import mrajaona.swingy.model.artifact.WeaponModel;
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

    public static void earnExp(int amount) {
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
    }

    public static void levelUp(HeroData hero) {
    	if (hero.getLevel() < HERO_MAX_LVL) {
            hero.setLevel(hero.getLevel() + 1);
            String msg = ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() ).getString("msgLvlUp")
                .replace("<hero>", GameData.getData().getHero().getHeroName())
                .replace("<level>", Integer.toString(hero.getLevel()));
            MainHelper.printMsg(msg);
            fullRecover();
    	}
    }

    public static void equip(HelmData helm) {
        HelmModel.equip(helm);
        updateStats();
    }

    public static void equip(ArmorData armor) {
        ArmorModel.equip(armor);
        updateStats();
    }

    public static void equip(WeaponData weapon) {
        WeaponModel.equip(weapon);
        updateStats();
    }

    public static void unequip(HelmData helm) {
        HelmModel.remove();
        updateStats();
    }

    public static void unequip(ArmorData armor) {
        ArmorModel.remove();
        updateStats();
    }

    public static void unequip(WeaponData weapon) {
        WeaponModel.remove();
        updateStats();
    }

    private static void updateStats() {
        HeroData hero = GameData.getData().getHero();

        hero.setAttack(hero.getBaseAttack() + hero.getWeapon().getModifier());
        hero.setDefense(hero.getBaseDefense() + hero.getArmor().getModifier());
        hero.setMaxHitPoints(hero.getBaseHitPoints() + hero.getHelm().getModifier());
        if (hero.getHitPoints() > hero.getMaxHitPoints())
            hero.setHitPoints(hero.getMaxHitPoints());

        updateUI();
    }

    public static void viewStats() {
        HeroData hero = GameData.getData().getHero();
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.StatResource", GameData.getData().getLocale() );
        ResourceBundle artifactLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() );

        MainHelper.printMsg(
            System.lineSeparator() +
            locale.getString("name")          + " : " + hero.getHeroName() + System.lineSeparator() +
            locale.getString("class")         + " : " + hero.getHeroClass() + System.lineSeparator() +
            locale.getString("level")         + " : " + hero.getLevel() + System.lineSeparator() +
            locale.getString("experience")    + " : " + hero.getExperience() + System.lineSeparator() +

            locale.getString("helm")          + " : " + artifactLocale.getString(hero.getHelm().getName())   + " (" + hero.getHelm().getModifier()   + ")" + System.lineSeparator() +
            locale.getString("armor")         + " : " + artifactLocale.getString(hero.getArmor().getName())  + " (" + hero.getArmor().getModifier()  + ")" + System.lineSeparator() +
            locale.getString("weapon")        + " : " + artifactLocale.getString(hero.getWeapon().getName()) + " (" + hero.getWeapon().getModifier() + ")" + System.lineSeparator() +

            locale.getString("baseAttack")    + " : " + hero.getBaseAttack() + System.lineSeparator() +
            locale.getString("attack")        + " : " + hero.getAttack() + System.lineSeparator() +
            locale.getString("baseDefense")   + " : " + hero.getBaseDefense() + System.lineSeparator() +
            locale.getString("defense")       + " : " + hero.getDefense() + System.lineSeparator() +
            locale.getString("baseHitPoints") + " : " + hero.getBaseHitPoints() + System.lineSeparator() +
            locale.getString("maxHitPoints")  + " : " + hero.getMaxHitPoints() + System.lineSeparator() +
            locale.getString("hitPoints")     + " : " + hero.getHitPoints() + System.lineSeparator()
        );
    }

    public static void move(String direction) throws SQLException, IOException {
        // TODO : check map exists
        GameMapModel.move(direction);
    }

    public static void run() {
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() );

        {
	        String msg = locale.getString("msgRun")
	            .replace("<hero>", GameData.getData().getHero().getHeroName());
	        MainHelper.printMsg(msg);
        }
        // TODO : wait for more suspense

        if ( (Math.random() * 10) % 2 == 0) { // 1/2
            String msg = locale.getString("msgRunSuccess");
            MainHelper.printMsg(msg);
            GameMapModel.goBack();
        } else {
            String msg = locale.getString("msgRunFail");
            MainHelper.printMsg(msg);
            fight();
        }
    }

    public static void fight() {
        // EnemyController
        // TODO : loop until someone dies
    }

    // Character methods

    public static void fullRecover() {
        HeroData hero = GameData.getData().getHero();
        hero.setHitPoints(hero.getMaxHitPoints());
    }

    public static void recoverHP(int amount) {
        HeroData hero = GameData.getData().getHero();

        hero.setHitPoints(hero.getHitPoints() + amount);
        if (hero.getHitPoints() > hero.getMaxHitPoints())
            hero.setHitPoints(hero.getMaxHitPoints());
    }

    public static void loseHP(int amount) {
        HeroData hero = GameData.getData().getHero();

        hero.setHitPoints(hero.getHitPoints() - amount);
        if (hero.getHitPoints() < 0) {
            hero.setHitPoints(0);
        }
    }

    public static void beAttacked(int enemyAtk) {
        HeroData hero = GameData.getData().getHero();
        loseHP(enemyAtk - hero.getDefense());
    }

    public static void die() {
        String msg = ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() ).getString("msgDied")
        .replace("<subject>", GameData.getData().getHero().getHeroName());
        MainHelper.printMsg(msg);
    }

    private static void updateUI() {
        GUIMain.getScreen().updateTable();
    }

}
