package mrajaona.swingy.model.artifact;

import mrajaona.swingy.GameLoop;
import mrajaona.swingy.data.artifact.WeaponData;

public class WeaponModel implements ArtifactModel {

    private static WeaponModel model = new WeaponModel();

    private WeaponModel() {}

    public static WeaponModel getModel() {
        return (model);
    }

	public void equip(WeaponData weapon) {
		GameLoop.getHero().getWeapon().change(weapon.getName(), weapon.getModifier());
	}

	@Override
	public void remove() {
		GameLoop.getHero().getWeapon().remove();
	}

}
