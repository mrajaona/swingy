package mrajaona.swingy.data.artifact;

public class HelmData extends ArtifactData {

    public HelmData() {
        // TODO : generate random
        super("Random", 0);
    }

    public HelmData(String name, int modifier) {
        super(name, modifier);
    }

    public HelmData(HelmData data) {
        super(data.getId(), data.getName(), data.getModifier());
    }

}
