package mrajaona.swingy.elements.characters.hero;

import javax.validation.constraints.NotBlank;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import mrajaona.swingy.elements.characters.CharacterModel;

@DatabaseTable(tableName = "heroes")
public class Hero extends CharacterModel {

	@DatabaseField(generatedId = true)
	@Getter private long id;

	@DatabaseField(canBeNull = false)
    @NotBlank(message = "Your hero needs a name.")
    @Getter private String  heroName;

    @DatabaseField(canBeNull = false)
    @NotBlank(message = "Invalid Class.")
    @Getter private String  heroClass;

/*
    @DatabaseField
    @Getter @Setter(AccessLevel.PROTECTED) private Helm     helm;

    @DatabaseField
    @Getter @Setter(AccessLevel.PROTECTED) private Armor    armor;

    @DatabaseField
    @Getter @Setter(AccessLevel.PROTECTED) private Weapon   weapon;
*/

    // new
    Hero() {
        super();
    }

    // load
    public Hero(HeroBuilder builder) {
        super(builder.getLevel(),
            builder.getExperience(),
            builder.getBaseAttack(), builder.getAttack(),
            builder.getBaseDefense(), builder.getDefense(),
            builder.getBaseHitPoints(), builder.getMaxHitPoints(), builder.getHitPoints());
        this.heroName   = builder.getHeroName();
        this.heroClass  = builder.getHeroClass();
    }

}
