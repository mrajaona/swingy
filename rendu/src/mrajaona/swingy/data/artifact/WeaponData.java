package mrajaona.swingy.data.artifact;

public class WeaponData extends ArtifactData {

    public WeaponData() {
        // TODO : generate random
        super("Random", 0);
    }

    public WeaponData(String name, int modifier) {
        super(name, modifier);
    }

    public WeaponData(WeaponData data) {
        super(data.getId(), data.getName(), data.getModifier());
    }

}
