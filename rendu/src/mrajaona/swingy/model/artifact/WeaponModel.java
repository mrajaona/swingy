package mrajaona.swingy.model.artifact;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.artifact.WeaponData;
import mrajaona.swingy.exception.InvalidViewTypeException;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.view.helper.MainHelper;

public class WeaponModel {

    @SuppressWarnings("unused")
    private WeaponModel() {}

    public static void equip(WeaponData weapon) throws InvalidViewTypeException {
        GameData.getData().getHero().getWeapon().change(weapon.getName(), weapon.getModifier());
        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgEquip"),
            GameData.getData().getHero().getHeroName(), // %1$s
            ((ResourceMap) ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() ).getObject("WeaponList"))
                .get(weapon.getName()) // %2$s
            );
        MainHelper.printMsg(msg);
    }

    public static void remove() throws InvalidViewTypeException {
        ArtifactData weapon = GameData.getData().getHero().getWeapon();
        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgUnequip"),
            GameData.getData().getHero().getHeroName(), // %1$s
            ((ResourceMap) ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() ).getObject("WeaponList"))
                .get(weapon.getName()) // %2$s
            );
        MainHelper.printMsg(msg);
        weapon.remove();
    }

}
