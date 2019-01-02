package mrajaona.swingy.builder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import mrajaona.swingy.Util;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.data.artifact.WeaponData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.view.helper.BuildHelper;

/*
** Hero builder
** Creates and verifies new Hero data
*/

public class HeroBuilder {

    private static HeroBuilder builder = new HeroBuilder();

    private HeroBuilder() {}

    public static HeroBuilder getBuilder() {
        return (builder);
    }

    @Getter private long id = 0;

    // TODO : localization

    @NotBlank(message = "Your hero needs a name.")
    @Getter private String  heroName;

    @NotBlank(message = "Invalid Class.")
    @Getter private String  heroClass;

    @NotNull(message = "Missing helm info")
    @Getter private HelmData     helm;
    @NotNull(message = "Missing armor info")
    @Getter private ArmorData    armor;
    @NotNull(message = "Missing weapon info")
    @Getter private WeaponData   weapon;

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

    public HeroBuilder setId(long value) {
        id = value;
        return (this);
    }

    public HeroBuilder setHeroName(String value) {
        heroName = value;
        return (this);
    }

    public HeroBuilder setHeroClass(String value) {
        heroClass = value;
        return (this);
    }

    public HeroBuilder setHelm(HelmData value) {
        helm = value;
        return (this);
    }

    public HeroBuilder setArmor(ArmorData value) {
        armor = value;
        return (this);
    }

    public HeroBuilder setWeapon(WeaponData value) {
        weapon = value;
        return (this);
    }

    public HeroBuilder setLevel(int value) {
        level = value;
        return (this);
    }

    public HeroBuilder setExperience(int value) {
        experience = value;
        return (this);
    }

    public HeroBuilder setBaseAttack(int value) {
    baseAttack = value;
        return (this);
    }

    public HeroBuilder setBaseDefense(int value) {
        baseDefense = value;
        return (this);
    }

    public HeroBuilder setBaseHitPoints(int value) {
        baseHitPoints = value;
        return (this);
    }

    public HeroBuilder setAttack(int value) {
        attack = value;
        return (this);
    }

    public HeroBuilder setDefense(int value) {
        defense = value;
        return (this);
    }

    public HeroBuilder setMaxHitPoints(int value) {
        maxHitPoints = value;
        return (this);
    }

    public HeroBuilder setHitPoints(int value) {
        hitPoints = value;
        return (this);
    }

    // Creation

    public HeroData newHero() {

        HeroData hero = null;

        ResourceBundle locale = ResourceBundle.getBundle("mrajaona.swingy.locale.LocaleResource", GameData.getData().getLocale() );

        while (hero == null) {
            if (heroClass == null || heroClass.trim().isEmpty())
                heroClass = BuildHelper.ask(locale.getString("CreateHeroClass"));
            if (heroName == null || heroName.trim().isEmpty())
                heroName = BuildHelper.ask(locale.getString("CreateHeroName"));

            hero = build(true);
        }

        return (hero);
    }

    public HeroData loadHero(HeroData loaded) {
        return (
            setId(loaded.getId())
            .setHeroName(loaded.getHeroName())
            .setHeroClass(loaded.getHeroClass())
            .setHelm(new HelmData(loaded.getHelm()))
            .setArmor(new ArmorData(loaded.getArmor()))
            .setWeapon(new WeaponData(loaded.getWeapon()))
            .setLevel(loaded.getLevel())
            .setExperience(loaded.getExperience())
            .setBaseAttack(loaded.getBaseAttack())
            .setBaseDefense(loaded.getBaseDefense())
            .setBaseHitPoints(loaded.getBaseHitPoints())
            .setAttack(loaded.getAttack())
            .setDefense(loaded.getDefense())
            .setMaxHitPoints(loaded.getMaxHitPoints())
            .setHitPoints(loaded.getHitPoints())
            .build(false)
            );
    }

    private HeroData build(boolean init) {

        if (init == true) {
            Util.HeroBaseStats stats = Util.HERO_BASE_STATS_MAP.get(heroClass);

            if (stats == null) {
                heroClass = new String();
            } else {
                baseAttack    = stats.getAtk() * 5;
                attack        = baseAttack;
                baseDefense   = stats.getDef() * 5;
                defense       = baseDefense;
                baseHitPoints = stats.getHp() * 5 * 5;
                maxHitPoints  = baseHitPoints;
                hitPoints     = baseHitPoints;

                helm   = new HelmData(ArtifactData.NO_ARTIFACT_NAME, 0);
                armor  = new ArmorData(ArtifactData.NO_ARTIFACT_NAME, 0);
                weapon = new WeaponData(ArtifactData.NO_ARTIFACT_NAME, 0);
            }
        }

        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        //It validates bean instances
        Validator validator = factory.getValidator();

        //Validate bean
        Set<ConstraintViolation<HelmData>> helmConstraintViolations       = validator.validate(helm);
        Set<ConstraintViolation<ArmorData>> armorConstraintViolations     = validator.validate(armor);
        Set<ConstraintViolation<WeaponData>> weaponConstraintViolations   = validator.validate(weapon);
        Set<ConstraintViolation<HeroBuilder>> heroConstraintViolations    = validator.validate(this);

        boolean error = false;

        //Show errors
        if (helmConstraintViolations.size() > 0) {
            for (ConstraintViolation<HelmData> violation : helmConstraintViolations) {
                System.out.println(violation.getMessage());
            }
            error = true;
        }
        if (armorConstraintViolations.size() > 0) {
            for (ConstraintViolation<ArmorData> violation : armorConstraintViolations) {
                System.out.println(violation.getMessage());
            }
            error = true;
        }
        if (weaponConstraintViolations.size() > 0) {
            for (ConstraintViolation<WeaponData> violation : weaponConstraintViolations) {
                System.out.println(violation.getMessage());
            }
            error = true;
        }
        if (heroConstraintViolations.size() > 0) {
            for (ConstraintViolation<HeroBuilder> violation : heroConstraintViolations) {
                System.out.println(violation.getMessage());
            }
            error = true;
        }

        if (error == true)
            return (null);
        else {
            System.out.println("Valid Object"); // DEBUG
            return (new HeroData(this));
        }

    }

}
