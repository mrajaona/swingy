package mrajaona.swingy.data.artifact;

import mrajaona.swingy.util.Util.ArtifactType;

public class HelmData extends ArtifactData {

	private static final long serialVersionUID = 2825389860641051359L;

	public HelmData() {
        // TODO : generate random
        super("Random", 0, ArtifactType.HELM);
    }

    public HelmData(String name, int modifier) {
        super(name, modifier, ArtifactType.HELM);
    }

    public HelmData(HelmData data) {
        super(data.getName(), data.getModifier(), ArtifactType.HELM);
    }

}
