package mrajaona.swingy.model.artifact;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.HelmData;

public class HelmModel implements ArtifactModel {

    private static HelmModel model = new HelmModel();

    private HelmModel() {}

    public static HelmModel getModel() {
        return (model);
    }

	public void equip(HelmData helm) {
		GameData.getHero().getHelm().change(helm.getName(), helm.getModifier());
	}

	@Override
	public void remove() {
		GameData.getHero().getHelm().remove();
	}

}
