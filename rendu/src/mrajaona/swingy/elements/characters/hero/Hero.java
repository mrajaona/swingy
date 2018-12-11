package mrajaona.swingy.elements.characters.hero;

import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import mrajaona.swingy.elements.characters.CharacterModel;

public class Hero extends CharacterModel {

    @NotBlank(message = "Your hero needs a name.")
    @Getter private String  heroName;

    @NotBlank(message = "Invalid Class.")
    @Getter private String  heroClass;

/*
    @Getter @Setter(AccessLevel.PROTECTED) private Helm     helm;
    @Getter @Setter(AccessLevel.PROTECTED) private Armor    armor;
    @Getter @Setter(AccessLevel.PROTECTED) private Weapon   weapon;
*/

    // new
    private Hero() {
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
