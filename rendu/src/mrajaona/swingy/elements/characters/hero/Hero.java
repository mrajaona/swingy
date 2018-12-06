package mrajaona.swingy.elements.characters.hero;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import mrajaona.swingy.elements.characters.CharacterModel;

public class Hero extends CharacterModel {

    @NotBlank(message = "Your hero needs a name.")
    @Getter @Setter(AccessLevel.PROTECTED) private String  heroName;

    @NotBlank(message = "Invalid Class.")
    @Getter @Setter(AccessLevel.PROTECTED) private String  heroClass;

/*
    @Getter @Setter(AccessLevel.PROTECTED) private Helm     helm;
    @Getter @Setter(AccessLevel.PROTECTED) private Armor    armor;
    @Getter @Setter(AccessLevel.PROTECTED) private Weapon   weapon;
*/

    // Hero types

    private static final String WARRIOR  = "Warrior";
    private static final String THIEF    = "Thief";
    private static final String MAGE     = "Mage";
    private static final String PRIEST   = "Priest";

    private static final String [] heroTypes    = {
        WARRIOR,
        THIEF,
        MAGE,
        PRIEST
    } ;

    // Hero base stats

    protected static final Map <String, int[]> BASE_STATS_MAP = initBaseStatsMap();
    private static final Map <String, int[]> initBaseStatsMap() {
        Map <String, int[]> newMap = new HashMap <String, int[]> (heroTypes.length);

        newMap.put(
            WARRIOR,
            new int[] {2, 5, 3}
        );

        newMap.put(
            THIEF,
            new int[] {4, 4, 2}
        );

        newMap.put(
            MAGE,
            new int[] {6, 2, 2}
        );

        newMap.put(
            PRIEST,
            new int[] {2, 2, 6}
        );

        return (Collections.unmodifiableMap(newMap));
    }

    /*
        atk, def, hp
        base: nb * 5
        hp: base * 5

    */

    // new
    public Hero() {
        super();
    }

    // load
    public Hero(
        final String heroName,
        final String heroClass,
        final int level,
        final int experience,
        final int baseAttack,
        final int attack,
        final int baseDefense,
        final int defense,
        final int baseHitPoints,
        final int maxHitPoints,
        final int hitPoints
        ) {
        super(level, experience, baseAttack, attack, baseDefense, defense, baseHitPoints, maxHitPoints, hitPoints);
        this.heroName   = heroName;
        this.heroClass  = heroClass;
    }

    protected void initStats() {
        int[] statsArray = BASE_STATS_MAP.get(heroClass);

        if (statsArray == null) {
            heroClass = new String();
        } else {
            setBaseAttack(statsArray[0] * 5);
            setAttack(this.getBaseAttack());
            setBaseDefense(statsArray[1] * 5);
            setDefense(this.getBaseDefense());
            setBaseHitPoints(statsArray[2] * 5 * 5);
            setMaxHitPoints(this.getBaseHitPoints());
            setHitPoints(this.getBaseHitPoints());
        }

    }
}
