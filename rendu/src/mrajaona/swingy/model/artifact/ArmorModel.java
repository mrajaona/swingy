package mrajaona.swingy.model.artifact;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;

public class ArmorModel implements ArtifactModel {

    private static ArmorModel model = new ArmorModel();

    private ArmorModel() {}

    public static ArmorModel getModel() {
        return (model);
    }

	public void equip(ArmorData armor) {
		GameData.getHero().getArmor().change(armor.getName(), armor.getModifier());
	}

	@Override
	public void remove() {
		GameData.getHero().getArmor().remove();
	}

}
