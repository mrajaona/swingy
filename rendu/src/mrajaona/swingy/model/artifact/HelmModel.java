package mrajaona.swingy.model.artifact;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.view.helper.MainHelper;

public class HelmModel {

    @SuppressWarnings("unused")
    private HelmModel() {}

    public static void equip(HelmData helm) {
        GameData.getData().getHero().getHelm().change(helm.getName(), helm.getModifier());
        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgEquip"),
            GameData.getData().getHero().getHeroName(), // %1$s
            ((ResourceMap) ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() ).getObject("HelmList"))
                .get(helm.getName()) // %2$s
            );
        MainHelper.printMsg(msg);
    }

    public static void remove() {
        ArtifactData helm = GameData.getData().getHero().getHelm();
        String msg = String.format(
            ResourceBundle.getBundle( "mrajaona.swingy.locale.InterfaceResource", GameData.getData().getLocale() ).getString("msgUnequip"),
            GameData.getData().getHero().getHeroName(), // %1$s
            ((ResourceMap) ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() ).getObject("HelmList"))
                .get(helm.getName()) // %2$s
            );
        MainHelper.printMsg(msg);
        helm.remove();
    }

}
