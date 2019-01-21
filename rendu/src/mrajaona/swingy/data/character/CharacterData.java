package mrajaona.swingy.data.character;

import com.j256.ormlite.field.DatabaseField;

import lombok.Getter;
import lombok.Setter;

/*
** Data for Character
** Modifiable via the Character model
** Used in the Hero and Enemy datas
*/

public class CharacterData {

    // TODO : localization

    // members

    @DatabaseField(canBeNull = false)
    // @Positive
    @Getter @Setter private int level;

    @DatabaseField(canBeNull = false)
    // @PositiveOrZero
    @Getter @Setter private double experience;


    @DatabaseField(canBeNull = false)
    // @PositiveOrZero
    @Getter @Setter private int baseAttack;

    @DatabaseField(canBeNull = false)
    // @PositiveOrZero
    @Getter @Setter private int baseDefense;

    @DatabaseField(canBeNull = false)
    // @Positive
    @Getter @Setter private int baseHitPoints;


    @Getter @Setter private int attack;

    @Getter @Setter private int defense;

    @Getter @Setter private int maxHitPoints;

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
            double experience,
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
            double experience,
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
