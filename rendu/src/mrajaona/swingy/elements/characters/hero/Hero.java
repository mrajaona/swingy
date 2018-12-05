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

    public Hero() {
        super();
    }

    public Hero(
        final String heroName,
        final String heroClass,
        final int level,
        final int experience,
        final int baseAttack,
        final int baseDefense,
        final int baseHitPoints) {
        super(level, experience, baseAttack, baseDefense, baseHitPoints);
        this.heroName   = heroName;
        this.heroClass  = heroClass;
    }

    public void initStats() {
        int[] statsArray = BASE_STATS_MAP.get(heroClass);

        if (statsArray == null) {
            heroClass = new String();
        } else {
            setBaseAttack(statsArray[0] * 5);
            setAttack(this.getBaseAttack());
            setBaseDefense(statsArray[1] * 5);
            setDefense(this.getBaseDefense());
            setBaseHitPoints(statsArray[2] * 5 * 5);
            setHitPoints(this.getBaseHitPoints());
        }

    }
}
