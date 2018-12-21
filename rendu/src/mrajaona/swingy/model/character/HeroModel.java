package mrajaona.swingy.model.character;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.data.artifact.WeaponData;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.model.artifact.ArmorModel;
import mrajaona.swingy.model.artifact.HelmModel;
import mrajaona.swingy.model.artifact.WeaponModel;

/*
** Hero Model
** Manipulates Hero data
*/

public class HeroModel implements CharacterModel {

    private static HeroModel model = new HeroModel();

    private HeroModel() {}

    public static HeroModel getModel() {
        return (model);
    }

    public void earnExp(int amount) {

    }

    public void levelUp() {

    }

    public void equip(HelmData helm) {
        HelmModel.getModel().equip(helm);
        updateStats();
    }

    public void equip(ArmorData armor) {
        ArmorModel.getModel().equip(armor);
        updateStats();
    }

    public void equip(WeaponData weapon) {
        WeaponModel.getModel().equip(weapon);
        updateStats();
    }

    public void unequip(HelmData helm) {
        HelmModel.getModel().remove();
        updateStats();
    }

    public void unequip(ArmorData armor) {
        ArmorModel.getModel().remove();
        updateStats();
    }

    public void unequip(WeaponData weapon) {
        WeaponModel.getModel().remove();
        updateStats();
    }

    private void updateStats() {
        HeroData hero = GameData.getHero();

        hero.setAttack(hero.getBaseAttack() + hero.getWeapon().getModifier());
        hero.setDefense(hero.getBaseDefense() + hero.getArmor().getModifier());
        hero.setMaxHitPoints(hero.getBaseHitPoints() + hero.getHelm().getModifier());
        if (hero.getHitPoints() > hero.getMaxHitPoints())
            hero.setHitPoints(hero.getMaxHitPoints());
    }

    public void move(String direction) {

    }

    public void run() {

    }

    public void fight(EnemyData enemy) {

    }

    @Override
    public void fullRecover() {
        HeroData hero = GameData.getHero();
        hero.setHitPoints(hero.getMaxHitPoints());
    }

    @Override
    public void recoverHP(int amount) {
        HeroData hero = GameData.getHero();

        hero.setHitPoints(hero.getHitPoints() + amount);
        if (hero.getHitPoints() > hero.getMaxHitPoints())
            hero.setHitPoints(hero.getMaxHitPoints());
    }

    @Override
    public void loseHP(int amount) {
        HeroData hero = GameData.getHero();

        hero.setHitPoints(hero.getHitPoints() - amount);
        if (hero.getHitPoints() < 0) {
            hero.setHitPoints(0);
        }
    }

    @Override
    public void beAttacked(int enemyAtk) {
        HeroData hero = GameData.getHero();
        loseHP(enemyAtk - hero.getDefense());
    }

    @Override
    public void die() {
        ;
    }

}
