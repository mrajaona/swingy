package mrajaona.swingy.data.artifact;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import lombok.Setter;
import mrajaona.swingy.util.Util.ArtifactType;

public class ArtifactData implements Serializable {

    // TODO : localization

	private static final long serialVersionUID = 5018899939736022531L;

	@NotBlank
    @Getter @Setter private String name;

	@PositiveOrZero
    @Getter @Setter private int modifier;

    @SuppressWarnings("unused")
	@Getter private transient ArtifactType type;

    public static final String NO_ARTIFACT_KEY = "none";

    // necessary for ORMLite
    ArtifactData() {}

    public ArtifactData(String name, int modifier, ArtifactType type) {
        this.name     = name;
        this.modifier = modifier;
        this.type     = type;
    }

    public void change(String name, int modifier) {
        this.name     = name;
        this.modifier = modifier;
    }

    public void remove() {
    	name     = NO_ARTIFACT_KEY;
    	modifier = 0;
    }

}
