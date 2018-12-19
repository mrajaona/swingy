package mrajaona.swingy.model.character;

import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.data.artifact.WeaponData;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.model.artifact.ArmorModel;
import mrajaona.swingy.model.artifact.HelmModel;
import mrajaona.swingy.model.artifact.WeaponModel;

/*
** Hero Model
** Manipulates Hero data
*/

public class HeroModel extends CharacterModel {

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
    }

    public void equip(ArmorData armor) {
        ArmorModel.getModel().equip(armor);
    }

    public void equip(WeaponData weapon) {
        WeaponModel.getModel().equip(weapon);
    }

    public void unequip(HelmData helm) {
        HelmModel.getModel().remove();
    }

    public void unequip(ArmorData armor) {
        ArmorModel.getModel().remove();
    }

    public void unequip(WeaponData weapon) {
        WeaponModel.getModel().remove();
    }

    public void move(String direction) {

    }

    public void run() {

    }

    public void fight(EnemyData enemy) {

    }

}
