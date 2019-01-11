package mrajaona.swingy.model.artifact;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.WeaponData;

public class WeaponModel {

	@SuppressWarnings("unused")
	private WeaponModel() {}

	public static void equip(WeaponData weapon) {
		GameData.getHero().getWeapon().change(weapon.getName(), weapon.getModifier());
	}

	public static void remove() {
		GameData.getHero().getWeapon().remove();
	}

}
