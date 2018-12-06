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

    private static enum BaseStats {
        WARRIOR_BASE_STATS(2, 5, 3),
        THIEF_BASE_STATS(4, 4, 2),
        MAGE_BASE_STATS(6, 2, 2),
        PRIEST_BASE_STATS(2, 2, 6);

        int atk;
        int def;
        int hp;

        private BaseStats(int atk, int def, int hp) {
            this.atk = atk;
            this.def = def;
            this.hp  = hp;
        }
    }

    protected static final Map <String, BaseStats> BASE_STATS_MAP = initBaseStatsMap();
    private static final Map <String, BaseStats> initBaseStatsMap() {
        Map <String, BaseStats> newMap = new HashMap <String, BaseStats> (heroTypes.length);

        newMap.put(WARRIOR, BaseStats.WARRIOR_BASE_STATS);
        newMap.put(THIEF,   BaseStats.THIEF_BASE_STATS);
        newMap.put(MAGE,    BaseStats.MAGE_BASE_STATS);
        newMap.put(PRIEST,  BaseStats.PRIEST_BASE_STATS);

        return (Collections.unmodifiableMap(newMap));
    }

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
        BaseStats stats = BASE_STATS_MAP.get(heroClass);

        if (stats == null) {
            heroClass = new String();
        } else {
            setBaseAttack(stats.atk * 5);
            setAttack(this.getBaseAttack());
            setBaseDefense(stats.def * 5);
            setDefense(this.getBaseDefense());
            setBaseHitPoints(stats.hp * 5 * 5);
            setMaxHitPoints(this.getBaseHitPoints());
            setHitPoints(this.getBaseHitPoints());
        }

    }
}
