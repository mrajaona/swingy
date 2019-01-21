package mrajaona.swingy.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import lombok.Setter;
import mrajaona.swingy.builder.SaveFileBuilder;
import mrajaona.swingy.data.character.HeroData;

@DatabaseTable(tableName = "saves")
public class SaveFileData {

    @DatabaseField(generatedId = true)
    @Getter private long id;

    @DatabaseField(foreign = true)
    @Getter @Setter private HeroData	hero;

    @DatabaseField(foreign = true)
    @Getter @Setter private GameMapData map;

    // necessary for ORMLite
    SaveFileData() {}

    public SaveFileData(SaveFileBuilder builder) {
        if (builder.getId() != 0)
            this.id = builder.getId();
       	this.hero = builder.getHero();
    	this.map  = builder.getMap();
    }

}
