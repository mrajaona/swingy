package mrajaona.swingy.model.artifact;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.view.helper.MainHelper;

public class HelmModel {

    @SuppressWarnings("unused")
    private HelmModel() {}

    public static void equip(HelmData helm) {
        GameData.getData().getHero().getHelm().change(helm.getName(), helm.getModifier());
        MainHelper.printMsg(
            GameData.getData().getHero().getHeroName()
            + ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource",
                                        GameData.getData().getLocale() ).getString("msgEquip")
            + helm.getName() +"."
        );
    }

    public static void remove() {
        ArtifactData artifact = GameData.getData().getHero().getHelm();

        MainHelper.printMsg(
            GameData.getData().getHero().getHeroName()
            + ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource",
                                        GameData.getData().getLocale() ).getString("msgUnequip")
            + artifact.getName() +"."
        );
        artifact.remove();
    }

}
