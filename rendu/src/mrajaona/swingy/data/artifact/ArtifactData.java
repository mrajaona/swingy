package mrajaona.swingy.data.artifact;

import com.j256.ormlite.field.DatabaseField;

import lombok.Getter;
import lombok.Setter;

public class ArtifactData {

    @Getter @Setter String name;

    @Getter @Setter int modifier;

    public ArtifactData(String name, int modifier) {
        this.name     = name;
        this.modifier = modifier;
    }

}
