package mrajaona.swingy.model.artifact;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.artifact.WeaponData;
import mrajaona.swingy.view.helper.MainHelper;

public class WeaponModel {

    @SuppressWarnings("unused")
    private WeaponModel() {}

    public static void equip(WeaponData weapon) {
        GameData.getData().getHero().getWeapon().change(weapon.getName(), weapon.getModifier());
        String msg = ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() ).getString("msgEquip")
            .replace("<hero>", GameData.getData().getHero().getHeroName())
            .replace("<artifact>", weapon.getName());
        MainHelper.printMsg(msg);
    }

    public static void remove() {
        ArtifactData artifact = GameData.getData().getHero().getWeapon();
        String msg = ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() ).getString("msgUnquip")
            .replace("<hero>", GameData.getData().getHero().getHeroName())
            .replace("<artifact>", artifact.getName());
        MainHelper.printMsg(msg);
        artifact.remove();
    }

}
