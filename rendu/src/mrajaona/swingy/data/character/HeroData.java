package mrajaona.swingy.data.character;

import javax.validation.constraints.NotBlank;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import lombok.Setter;
import mrajaona.swingy.builder.HeroBuilder;
import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.data.artifact.WeaponData;
import lombok.AccessLevel;

/*
** Data for Hero
** Modifiable via the Hero model
** Constructed with the Hero builder
*/

@DatabaseTable(tableName = "heroes")
public class HeroData extends CharacterData {

    @DatabaseField(generatedId = true)
    @Getter private long id;

    @DatabaseField(canBeNull = false)
    @NotBlank(message = "Your hero needs a name.")
    @Getter private String  heroName;

    @DatabaseField(canBeNull = false)
    @NotBlank(message = "Invalid Class.")
    @Getter private String  heroClass;

    @DatabaseField(foreign = true)
    @Getter @Setter private HelmData     helm;

    @DatabaseField(foreign = true)
    @Getter @Setter private ArmorData    armor;

    @DatabaseField(foreign = true)
    @Getter @Setter private WeaponData   weapon;

    @Getter @Setter private int coordX;
    @Getter @Setter private int coordY;

    // necessary for ORMLite
    HeroData() {
        super();
    }

    public HeroData(HeroBuilder builder) {
        super(builder.getLevel(),
            builder.getExperience(),
            builder.getBaseAttack(), builder.getAttack(),
            builder.getBaseDefense(), builder.getDefense(),
            builder.getBaseHitPoints(), builder.getMaxHitPoints(), builder.getHitPoints());
        if (builder.getId() != 0)
            this.id = builder.getId();
        this.heroName   = builder.getHeroName();
        this.heroClass  = builder.getHeroClass();
        this.helm       = new HelmData(builder.getHelm());
        this.armor      = new ArmorData(builder.getArmor());
        this.weapon     = new WeaponData(builder.getWeapon());
    }

}
