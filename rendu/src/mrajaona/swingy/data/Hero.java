package mrajaona.swingy.data;

/*
** Data for Hero
** Modifiable via the Hero model
** Constructed with the Hero builder
*/

public class Hero extends Character {

    @DatabaseField(generatedId = true)
    @Getter private long id;

    @DatabaseField(canBeNull = false)
    @NotBlank(message = "Your hero needs a name.")
    @Getter private String  heroName;

    @DatabaseField(canBeNull = false)
    @NotBlank(message = "Invalid Class.")
    @Getter private String  heroClass;

    @DatabaseField
    @Getter @Setter(AccessLevel.PROTECTED) private Helm     helm;

    @DatabaseField
    @Getter @Setter(AccessLevel.PROTECTED) private Armor    armor;

    @DatabaseField
    @Getter @Setter(AccessLevel.PROTECTED) private Weapon   weapon;

    // necessary for ORMLite
    Hero() {
        super();
    }

    Hero(mrajaona.swingy.builder.Hero builder) {
        super(builder.getLevel(),
            builder.getExperience(),
            builder.getBaseAttack(), builder.getAttack(),
            builder.getBaseDefense(), builder.getDefense(),
            builder.getBaseHitPoints(), builder.getMaxHitPoints(), builder.getHitPoints());
        this.heroName   = builder.getHeroName();
        this.heroClass  = builder.getHeroClass();
    }

}
