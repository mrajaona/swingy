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
        MainHelper.printMsg(
            GameData.getData().getHero().getHeroName()
            + ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource",
                                        GameData.getData().getLocale() ).getString("msgEquip")
            + weapon.getName() +"."
        );
    }

    public static void remove() {
        ArtifactData artifact = GameData.getData().getHero().getWeapon();

        MainHelper.printMsg(
            GameData.getData().getHero().getHeroName()
            + ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource",
                                        GameData.getData().getLocale() ).getString("msgUnequip")
            + artifact.getName() +"."
        );
        artifact.remove();
    }

}
