package mrajaona.swingy.elements.characters;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class CharacterModel {

    // members
    
    @Positive(message = "Invalid value (level)")
    @Setter(AccessLevel.PROTECTED) @Getter private int level;
    
    @PositiveOrZero(message = "Invalid value (experience)")
    @Setter(AccessLevel.PROTECTED) @Getter private int experience;

    @PositiveOrZero(message = "Invalid value (base attack)")
    @Setter(AccessLevel.PROTECTED) @Getter private int baseAttack;
    @PositiveOrZero(message = "Invalid value (base defense)")
    @Setter(AccessLevel.PROTECTED) @Getter private int baseDefense;
    @Positive(message = "Invalid value (base hit points)")
    @Setter(AccessLevel.PROTECTED) @Getter private int baseHitPoints;
    
    @PositiveOrZero(message = "Invalid value (attack)")
    @Setter(AccessLevel.PROTECTED) @Getter private int attack;
    @PositiveOrZero(message = "Invalid value (defense)")
    @Setter(AccessLevel.PROTECTED) @Getter private int defense;
    @PositiveOrZero(message = "Invalid value (hit points)")
    @Setter(AccessLevel.PROTECTED) @Getter private int hitPoints;
    
    // constructors
    @SuppressWarnings("unused")
    public CharacterModel() {
        this.level          = 1;
        this.experience     = 0;
        this.baseAttack     = 0;
        this.attack         = this.baseAttack;
        this.baseDefense    = 0;
        this.defense        = this.baseDefense;
        this.baseHitPoints  = 1;
        this.hitPoints      = this.baseHitPoints;  
    }
    
    public  CharacterModel(
            int level,
            int experience,
            int baseAttack,
            int baseDefense,
            int baseHitPoints
            ) {
        this.level          = level;
        this.experience     = experience;
        this.baseAttack     = baseAttack;
        this.attack         = this.baseAttack;
        this.baseDefense    = baseDefense;
        this.defense        = this.baseDefense;
        this.baseHitPoints  = baseHitPoints;
        this.hitPoints      = this.baseHitPoints;
    }
}
