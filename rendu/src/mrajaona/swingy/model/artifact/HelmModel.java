package mrajaona.swingy.model.artifact;

import mrajaona.swingy.GameLoop;
import mrajaona.swingy.data.artifact.HelmData;

public class HelmModel implements ArtifactModel {

    private static HelmModel model = new HelmModel();

    private HelmModel() {}

    public static HelmModel getModel() {
        return (model);
    }

	public void equip(HelmData helm) {
		GameLoop.getHero().getHelm().change(helm.getName(), helm.getModifier());
	}

	@Override
	public void remove() {
		GameLoop.getHero().getHelm().remove();
	}

}
