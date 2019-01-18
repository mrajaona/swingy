package mrajaona.swingy.model.character;

import java.lang.Math;
import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.data.artifact.WeaponData;
import mrajaona.swingy.data.character.EnemyData;
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

    private static int HERO_MAX_LVL = 100;

    public static void earnExp(int amount) {
        HeroData hero = GameData.getData().getHero();
        double exp      = hero.getExperience() + amount;
        double expToLvl = hero.getLevel() * 1000 + Math.pow( (hero.getLevel() - 1), 2) * 450;

        if (exp >= expToLvl) {
            levelUp(hero);
            exp -= expToLvl;
        }

        hero.setExperience(exp);
    }

    public static void levelUp(HeroData hero) {
        hero.setLevel(hero.getLevel() + 1);
        fullRecover();
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

        MainHelper.print(
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

    public static void move(String direction) {
        // TODO : check map exists
        GameMapModel.move(direction);
    }

    public static void run() {
        if ( (Math.random() * 10) % 2 == 0) // 1/2
            GameMapModel.goBack();
        else
            fight();
    }

    public static void fight() {
        // EnemyController
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
        ;
    }

    private static void updateUI() {
        GUIMain.getScreen().updateTable();
    }

}
