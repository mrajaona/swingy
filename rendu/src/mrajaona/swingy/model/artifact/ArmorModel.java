package mrajaona.swingy.model.artifact;

import mrajaona.swingy.GameLoop;
import mrajaona.swingy.data.artifact.ArmorData;

public class ArmorModel implements ArtifactModel {

    private static ArmorModel model = new ArmorModel();

    private ArmorModel() {}

    public static ArmorModel getModel() {
        return (model);
    }

	public void equip(ArmorData armor) {
		GameLoop.getHero().getArmor().change(armor.getName(), armor.getModifier());
	}

	@Override
	public void remove() {
		GameLoop.getHero().getArmor().remove();
	}

}
