package mrajaona.swingy.elements.characters.hero;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.AccessLevel;
import lombok.Getter;

class HeroBuilder {

    private static HeroBuilder builder = new HeroBuilder();

    private HeroBuilder() {}

    public static HeroBuilder getBuilder() {
        return (builder);
    }

    @NotBlank(message = "Your hero needs a name.")
    @Getter private String  heroName;

    @NotBlank(message = "Invalid Class.")
    @Getter private String  heroClass;

/*
    @Getter private Helm     helm;
    @Getter private Armor    armor;
    @Getter private Weapon   weapon;
*/

    @Positive(message = "Invalid value (level)")
    @Getter private int level         = 1;

    @PositiveOrZero(message = "Invalid value (experience)")
    @Getter private int experience    = 0;

    @PositiveOrZero(message = "Invalid value (base attack)")
    @Getter private int baseAttack    = 0;
    @PositiveOrZero(message = "Invalid value (base defense)")
    @Getter private int baseDefense   = 0;
    @Positive(message = "Invalid value (base hit points)")
    @Getter private int baseHitPoints = 1;

    @PositiveOrZero(message = "Invalid value (attack)")
    @Getter private int attack        = 0;
    @PositiveOrZero(message = "Invalid value (defense)")
    @Getter private int defense       = 0;
    @PositiveOrZero(message = "Invalid value (max hit points)")
    @Getter private int maxHitPoints  = 0;
    @PositiveOrZero(message = "Invalid value (hit points)")
    @Getter private int hitPoints     = 0;

    // builder setters

    protected HeroBuilder setHeroName(String value) {
    heroName = value;
        return (this);
    }

    protected HeroBuilder setHeroClass(String value) {
        heroClass = value;
        return (this);
    }

/*
    protected HeroBuilder setHelm(Helm value) {
        helm = value;
        return (this);
    }

    protected HeroBuilder setArmor(Armor value) {
        armor = value;
        return (this);
    }

    protected HeroBuilder setWeapon(Weapon value) {
        weapon = value;
        return (this);
    }
*/

    protected HeroBuilder setLevel(int value) {
        level = value;
        return (this);
    }

    protected HeroBuilder setExperience(int value) {
        experience = value;
        return (this);
    }

    protected HeroBuilder setBaseAttack(int value) {
    baseAttack = value;
        return (this);
    }

    protected HeroBuilder setBaseDefense(int value) {
        baseDefense = value;
        return (this);
    }

    protected HeroBuilder setBaseHitPoints(int value) {
        baseHitPoints = value;
        return (this);
    }

    protected HeroBuilder setAttack(int value) {
        attack = value;
        return (this);
    }

    protected HeroBuilder setDefense(int value) {
        defense = value;
        return (this);
    }

    protected HeroBuilder setMaxHitPoints(int value) {
        maxHitPoints = value;
        return (this);
    }

    protected HeroBuilder setHitPoints(int value) {
        hitPoints = value;
        return (this);
    }

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

    // Creation

    Hero newHero() {
        Scanner inputScanner = new Scanner(System.in);
        Hero hero = null;

        while (hero == null) {
            if (heroClass == null || heroClass.trim().isEmpty()) {
                System.out.println("Choose your class (Warrior, Thief, Mage, Priest) :");
                heroClass = inputScanner.nextLine();
            }
            if (heroName == null || heroName.trim().isEmpty()) {
                System.out.println("Name your hero :");
                heroName = inputScanner.nextLine();
            }

            hero = build(true);
        }

        inputScanner.close();
        return (hero);
    }

    Hero build(boolean init) {

        if (init == true) {
            BaseStats stats = BASE_STATS_MAP.get(heroClass);

            if (stats == null) {
                heroClass = new String();
            } else {
                baseAttack    = stats.atk * 5;
                attack        = baseAttack;
                baseDefense   = stats.def * 5;
                defense       = baseDefense;
                baseHitPoints = stats.hp * 5 * 5;
                maxHitPoints  = baseHitPoints;
                hitPoints     = baseHitPoints;
            }
        }

        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        //It validates bean instances
        Validator validator = factory.getValidator();

        //Validate bean
        Set<ConstraintViolation<HeroBuilder>> constraintViolations = validator.validate(this);

        //Show errors
        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<HeroBuilder> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
            return (null);
        } else {
            System.out.println("Valid Object");
            return (new Hero(this));
        }

    }


}
