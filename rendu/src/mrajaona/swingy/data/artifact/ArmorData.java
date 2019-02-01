package mrajaona.swingy.data.artifact;

import mrajaona.swingy.util.Util.ArtifactType;

public class ArmorData extends ArtifactData {

    private static final long serialVersionUID = 4226401608499261885L;

    @SuppressWarnings("unused")
    private ArmorData() {
        super();
    }

    public ArmorData(String name, int modifier) {
        super(name, modifier, ArtifactType.ARMOR);
    }

    public ArmorData(ArmorData data) {
        super(data.getName(), data.getModifier(), ArtifactType.ARMOR);
    }

}
