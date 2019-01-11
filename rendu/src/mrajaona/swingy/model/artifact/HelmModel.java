package mrajaona.swingy.model.artifact;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.HelmData;

public class HelmModel {

    @SuppressWarnings("unused")
    private HelmModel() {}

	public static void equip(HelmData helm) {
		GameData.getHero().getHelm().change(helm.getName(), helm.getModifier());
	}

	public static void remove() {
		GameData.getHero().getHelm().remove();
	}

}
