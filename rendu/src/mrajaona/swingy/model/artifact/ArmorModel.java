package mrajaona.swingy.model.artifact;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;

public class ArmorModel {

	@SuppressWarnings("unused")
    private ArmorModel() {}

	public static void equip(ArmorData armor) {
		GameData.getData().getHero().getArmor().change(armor.getName(), armor.getModifier());
	}

	public static void remove() {
		GameData.getData().getHero().getArmor().remove();
	}

}
