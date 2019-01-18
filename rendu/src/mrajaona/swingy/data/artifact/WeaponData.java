package mrajaona.swingy.data.artifact;

import mrajaona.swingy.util.Util;

public class WeaponData extends ArtifactData {

    public WeaponData() {
        // TODO : generate random
        super("Random", 0, Util.ArtifactType.WEAPON);
    }

    public WeaponData(String name, int modifier) {
        super(name, modifier, Util.ArtifactType.WEAPON);
    }

    public WeaponData(WeaponData data) {
        super(data.getId(), data.getName(), data.getModifier(), Util.ArtifactType.WEAPON);
    }

}
