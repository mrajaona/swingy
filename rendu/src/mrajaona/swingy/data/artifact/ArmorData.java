package mrajaona.swingy.data.artifact;

import mrajaona.swingy.util.Util;

public class ArmorData extends ArtifactData {

    public ArmorData() {
        // TODO : generate random
        super("Random", 0, Util.ArtifactType.ARMOR);
    }

    public ArmorData(String name, int modifier) {
        super(name, modifier, Util.ArtifactType.ARMOR);
    }

    public ArmorData(ArmorData data) {
        super(data.getId(), data.getName(), data.getModifier(), Util.ArtifactType.ARMOR);
    }

}
