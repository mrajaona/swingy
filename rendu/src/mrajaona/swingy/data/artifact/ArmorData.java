package mrajaona.swingy.data.artifact;

import mrajaona.swingy.util.Util.ArtifactType;

public class ArmorData extends ArtifactData {

    public ArmorData() {
        // TODO : generate random
        super("Random", 0, ArtifactType.ARMOR);
    }

    public ArmorData(String name, int modifier) {
        super(name, modifier, ArtifactType.ARMOR);
    }

    public ArmorData(ArmorData data) {
        super(data.getId(), data.getName(), data.getModifier(), ArtifactType.ARMOR);
    }

}
