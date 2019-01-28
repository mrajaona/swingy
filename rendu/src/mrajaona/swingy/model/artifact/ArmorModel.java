package mrajaona.swingy.model.artifact;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.view.helper.MainHelper;

public class ArmorModel {

    @SuppressWarnings("unused")
    private ArmorModel() {}

    public static void equip(ArmorData armor) {
        GameData.getData().getHero().getArmor().change(armor.getName(), armor.getModifier());
        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgEquip"),
            GameData.getData().getHero().getHeroName(), // %1$s
            ((ResourceMap) ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() ).getObject("ArmorList"))
                .get(armor.getName()) // %2$s
            );
        MainHelper.printMsg(msg);
    }

    public static void remove() {
        ArtifactData armor = GameData.getData().getHero().getArmor();
        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgUnequip"),
            GameData.getData().getHero().getHeroName(), // %1$s
            ((ResourceMap) ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() ).getObject("ArmorList"))
                .get(armor.getName()) // %2$s
            );
        MainHelper.printMsg(msg);
        armor.remove();
    }

}
