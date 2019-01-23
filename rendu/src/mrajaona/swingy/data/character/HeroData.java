package mrajaona.swingy.data.character;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import lombok.Setter;
import mrajaona.swingy.builder.HeroBuilder;
import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.data.artifact.WeaponData;

/*
** Data for Hero
** Modifiable via the Hero model
** Constructed with the Hero builder
*/

@DatabaseTable(tableName = "heroes")
public class HeroData extends CharacterData {

	private static final long serialVersionUID = -3707747501395187023L;

	@DatabaseField(generatedId = true)
    @Getter private long id;

    @DatabaseField(canBeNull = false)
    @Getter private String  heroName;

    @DatabaseField(canBeNull = false)
    @Getter private String  heroClass;

    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    @Getter @Setter private HelmData     helm;

    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    @Getter @Setter private ArmorData    armor;

    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    @Getter @Setter private WeaponData   weapon;

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
