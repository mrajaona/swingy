package mrajaona.swingy.data.artifact;

import mrajaona.swingy.util.Util.ArtifactType;

public class WeaponData extends ArtifactData {

	private static final long serialVersionUID = 3406749058141226863L;

	public WeaponData() {
        // TODO : generate random
        super("Random", 0, ArtifactType.WEAPON);
    }

    public WeaponData(String name, int modifier) {
        super(name, modifier, ArtifactType.WEAPON);
    }

    public WeaponData(WeaponData data) {
        super(data.getName(), data.getModifier(), ArtifactType.WEAPON);
    }

}
