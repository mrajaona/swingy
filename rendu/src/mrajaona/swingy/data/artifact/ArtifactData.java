package mrajaona.swingy.data.artifact;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import lombok.Setter;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.util.Util.ArtifactType;

@DatabaseTable(tableName = "artifacts")
public class ArtifactData {

	@DatabaseField(generatedId = true)
    @Getter private long id;

    // TODO : localization

	@DatabaseField(canBeNull = false)
    @NotBlank
    @Getter @Setter private String name;

	@DatabaseField(canBeNull = false)
	@PositiveOrZero
    @Getter @Setter private int modifier;

    private Util.ArtifactType type;

    public static final String NO_ARTIFACT_KEY = "none";

    // necessary for ORMLite
    ArtifactData() {}

    public ArtifactData(long id, String name, int modifier, ArtifactType type) {
		this.id       = id;
        this.name     = name;
        this.modifier = modifier;
        this.type     = type;
    }

    public ArtifactData(String name, int modifier, Util.ArtifactType type) {
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
