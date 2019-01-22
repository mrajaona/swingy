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
        String msg = ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() ).getString("msgEquip")
            .replace("<hero>", GameData.getData().getHero().getHeroName())
            .replace("<artifact>", armor.getName());
        MainHelper.printMsg(msg);
    }

    public static void remove() {
        ArtifactData artifact = GameData.getData().getHero().getArmor();
        String msg = ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() ).getString("msgUnquip")
            .replace("<hero>", GameData.getData().getHero().getHeroName())
            .replace("<artifact>", artifact.getName());
        MainHelper.printMsg(msg);
        artifact.remove();
    }

}
