package mrajaona.swingy.model.artifact;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.WeaponData;

public class WeaponModel implements ArtifactModel {

    private static WeaponModel model = new WeaponModel();

    private WeaponModel() {}

    public static WeaponModel getModel() {
        return (model);
    }

	public void equip(WeaponData weapon) {
		GameData.getHero().getWeapon().change(weapon.getName(), weapon.getModifier());
	}

	@Override
	public void remove() {
		GameData.getHero().getWeapon().remove();
	}

}
