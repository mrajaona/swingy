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
        String msg = ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() ).getString("msgEquip")
            .replace("<hero>", GameData.getData().getHero().getHeroName())
            .replace("<artifact>", helm.getName());
        MainHelper.printMsg(msg);
    }

    public static void remove() {
        ArtifactData artifact = GameData.getData().getHero().getHelm();
        String msg = ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() ).getString("msgUnquip")
            .replace("<hero>", GameData.getData().getHero().getHeroName())
            .replace("<artifact>", artifact.getName());
        MainHelper.printMsg(msg);
        artifact.remove();
    }

}
