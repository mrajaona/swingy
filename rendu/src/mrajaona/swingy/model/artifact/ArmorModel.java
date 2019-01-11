package mrajaona.swingy.model.artifact;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;

public class ArmorModel {

	@SuppressWarnings("unused")
    private ArmorModel() {}

	public static void equip(ArmorData armor) {
		GameData.getHero().getArmor().change(armor.getName(), armor.getModifier());
	}

	public static void remove() {
		GameData.getHero().getArmor().remove();
	}

}
