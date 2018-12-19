package mrajaona.swingy.data.character;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.j256.ormlite.field.DatabaseField;

import lombok.Getter;
import lombok.Setter;

/*
** Data for Character
** Modifiable via the Character model
** Used in the Hero and Enemy datas
*/

public class CharacterData {

    // members

    @DatabaseField(canBeNull = false)
    @Positive(message = "Invalid value (level)")
    @Getter @Setter private int level;

    @DatabaseField(canBeNull = false)
    @PositiveOrZero(message = "Invalid value (experience)")
    @Getter @Setter private int experience;


    @DatabaseField(canBeNull = false)
    @PositiveOrZero(message = "Invalid value (base attack)")
    @Getter @Setter private int baseAttack;

    @DatabaseField(canBeNull = false)
    @PositiveOrZero(message = "Invalid value (base defense)")
    @Getter @Setter private int baseDefense;

    @DatabaseField(canBeNull = false)
    @Positive(message = "Invalid value (base hit points)")
    @Getter @Setter private int baseHitPoints;


    @DatabaseField(canBeNull = false)
    @PositiveOrZero(message = "Invalid value (attack)")
    @Getter @Setter private int attack;

    @DatabaseField(canBeNull = false)
    @PositiveOrZero(message = "Invalid value (defense)")
    @Getter @Setter private int defense;

    @DatabaseField(canBeNull = false)
    @PositiveOrZero(message = "Invalid value (max hit points)")
    @Getter @Setter private int maxHitPoints;

    @DatabaseField(canBeNull = false)
    @PositiveOrZero(message = "Invalid value (hit points)")
    @Getter @Setter private int hitPoints;

    // constructors

    // new
    public CharacterData() {
        this.level          = 1;
        this.experience     = 0;
        this.baseAttack     = 0;
        this.attack         = this.baseAttack;
        this.baseDefense    = 0;
        this.defense        = this.baseDefense;
        this.baseHitPoints  = 1;
        this.maxHitPoints   = this.baseHitPoints;
        this.hitPoints      = this.baseHitPoints;
    }

    // new enemy
    public  CharacterData(
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
        this.maxHitPoints   = this.baseHitPoints;
        this.hitPoints      = this.baseHitPoints;
    }

    // load
    public  CharacterData(
            int level,
            int experience,
            int baseAttack,
            int attack,
            int baseDefense,
            int defense,
            int baseHitPoints,
            int maxHitPoints,
            int hitPoints
            ) {
        this.level          = level;
        this.experience     = experience;
        this.baseAttack     = baseAttack;
        this.attack         = attack;
        this.baseDefense    = baseDefense;
        this.defense        = defense;
        this.baseHitPoints  = baseHitPoints;
        this.maxHitPoints   = maxHitPoints;
        this.hitPoints      = hitPoints;
    }

}
