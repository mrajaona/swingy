package mrajaona.swingy.model.artifact;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.view.helper.MainHelper;

public class ArmorModel {

    @SuppressWarnings("unused")
    private ArmorModel() {}

    public static void equip(ArmorData armor) {
        GameData.getData().getHero().getArmor().change(armor.getName(), armor.getModifier());
        MainHelper.printMsg(
            GameData.getData().getHero().getHeroName()
            + ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource",
                                        GameData.getData().getLocale() ).getString("msgEquip")
            + armor.getName() +"."
        );
    }

    public static void remove() {
        ArtifactData artifact = GameData.getData().getHero().getArmor();

        MainHelper.printMsg(
            GameData.getData().getHero().getHeroName()
            + ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource",
                                        GameData.getData().getLocale() ).getString("msgUnequip")
            + artifact.getName() +"."
        );
        artifact.remove();
    }

}
