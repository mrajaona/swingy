package mrajaona.swingy.data.artifact;

import mrajaona.swingy.util.Util;

public class HelmData extends ArtifactData {

    public HelmData() {
        // TODO : generate random
        super("Random", 0, Util.ArtifactType.HELM);
    }

    public HelmData(String name, int modifier) {
        super(name, modifier, Util.ArtifactType.HELM);
    }

    public HelmData(HelmData data) {
        super(data.getId(), data.getName(), data.getModifier(), Util.ArtifactType.HELM);
    }

}
