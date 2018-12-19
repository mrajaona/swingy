package mrajaona.swingy.data.artifact;

public class ArmorData extends ArtifactData {

    public ArmorData() {
        // TODO : generate random
        super("Random", 0);
    }

    public ArmorData(String name, int modifier) {
        super(name, modifier);
    }

    public ArmorData(ArmorData data) {
        super(data.getId(), data.getName(), data.getModifier());
    }

}
