package mrajaona.swingy.builder;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArmorData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.artifact.HelmData;
import mrajaona.swingy.data.artifact.WeaponData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.exception.HeroBuilderException;
import mrajaona.swingy.exception.InvalidViewTypeException;
import mrajaona.swingy.model.character.HeroModel;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.view.helper.BuildHelper;

/*
** Hero builder
** Creates and verifies new Hero data
*/

public class HeroBuilder {

    @Getter private long id = 0;

    @NotBlank @Pattern(regexp = "^[A-Za-z ]+$")
    @Getter private String  heroName;

    @NotBlank
    @Getter private String  heroClass;

    @NotNull
    @Getter private HelmData     helm;
    @NotNull
    @Getter private ArmorData    armor;
    @NotNull
    @Getter private WeaponData   weapon;

    @Range(min=1, max=HeroModel.HERO_MAX_LVL)
    @Getter private int level         = 1;

    @PositiveOrZero
    @Getter private double experience = 0;

    @PositiveOrZero
    @Getter private int baseAttack    = 0;
    @PositiveOrZero
    @Getter private int baseDefense   = 0;
    @Positive
    @Getter private int baseHitPoints = 1;

    @PositiveOrZero
    @Getter private int attack        = 0;
    @PositiveOrZero
    @Getter private int defense       = 0;
    @Positive
    @Getter private int maxHitPoints  = 0;
    @PositiveOrZero
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

    public HeroBuilder setExperience(double value) {
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

    public HeroData newHero() throws HeroBuilderException, InvalidViewTypeException {

        HeroData hero = null;

        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.HeroResource", GameData.getData().getLocale() );

        while (hero == null) {
            if (heroClass == null || heroClass.trim().isEmpty()) {
                BuildHelper.listClasses();
                heroClass = BuildHelper.ask(locale.getString("msgCreateHeroClass"));
            }
            if (heroName == null || heroName.trim().isEmpty()) {
                BuildHelper.listClasses();
                heroName = BuildHelper.ask(locale.getString("msgCreateHeroName"));
            }
            hero = build(true);
        }

        return (hero);
    }

    public HeroData newHero(String heroClass, String heroName) throws HeroBuilderException, InvalidViewTypeException {

        HeroData hero = null;

            if ( (heroClass == null || heroClass.trim().isEmpty())
                || (heroName == null || heroName.trim().isEmpty()) )
                return null;

        this.heroClass = heroClass;
        this.heroName = heroName;

        hero = build(true);

        return (hero);
    }

    public HeroData loadHero(HeroData loaded) throws HeroBuilderException, InvalidViewTypeException {
        if (loaded == null)
            return (null);

        if (
            (loaded.getHeroClass() == null || loaded.getHeroClass().trim().isEmpty())
            || (loaded.getHeroName() == null || loaded.getHeroName().trim().isEmpty())
            || Arrays.asList(Util.heroTypes).contains(loaded.getHeroClass()) == false
        ) {
            throw (new HeroBuilderException());
        }

        if (
               loaded.getAttack()       != loaded.getBaseAttack()   + loaded.getWeapon().getModifier()
            || loaded.getDefense()      != loaded.getBaseDefense()  + loaded.getArmor().getModifier()
            || loaded.getMaxHitPoints() != loaded.getMaxHitPoints() + loaded.getHelm().getModifier()
            || loaded.getHitPoints() > loaded.getMaxHitPoints()
            ) {
            throw (new HeroBuilderException());
        }

        return (
        setId(loaded.getId())
        .setHeroName      (loaded.getHeroName())
        .setHeroClass     (loaded.getHeroClass())
        .setLevel         (loaded.getLevel())
        .setExperience    (loaded.getExperience())
        .setBaseAttack    (loaded.getBaseAttack())
        .setBaseDefense   (loaded.getBaseDefense())
        .setBaseHitPoints (loaded.getBaseHitPoints())
        .setHelm          (new HelmData(loaded.getHelm()))
        .setArmor         (new ArmorData(loaded.getArmor()))
        .setWeapon        (new WeaponData(loaded.getWeapon()))
        .setAttack        (loaded.getAttack())
        .setDefense       (loaded.getDefense())
        .setMaxHitPoints  (loaded.getHitPoints())
        .setHitPoints     (maxHitPoints)
        .build(false));
    }

    private HeroData build(boolean init) throws HeroBuilderException, InvalidViewTypeException  {

        ResourceBundle locale    = ResourceBundle.getBundle( "mrajaona.swingy.locale.HeroResource", GameData.getData().getLocale() );
        ResourceBundle errLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.ErrorResource", GameData.getData().getLocale() );

        // delocalize and init heroClass
        if (init) {
            ResourceMap map = (ResourceMap) locale.getObject("ClassesList");
            String      tmp = map.getKeyByValue(heroClass);

            if (tmp == null) {
                heroClass = new String();
                BuildHelper.printMsg(errLocale.getString("invalidClass"));
            } else {
                heroClass = tmp;
            }

            Util.HeroBaseStats stats = Util.HERO_BASE_STATS_MAP.get(heroClass);

            if (helm == null)
                helm   = new HelmData(ArtifactData.NO_ARTIFACT_KEY, 0);
            if (armor == null)
                armor  = new ArmorData(ArtifactData.NO_ARTIFACT_KEY, 0);
            if (weapon == null)
                weapon = new WeaponData(ArtifactData.NO_ARTIFACT_KEY, 0);

            if (stats == null) {
                heroClass = new String();
            } else {
                baseAttack    = 5 * stats.getAtk();
                attack        = baseAttack;
                baseDefense   = 5 * stats.getDef();
                defense       = baseDefense;
                baseHitPoints = 5 * stats.getHp() * 5;
                maxHitPoints  = baseHitPoints;
                hitPoints     = baseHitPoints;
            }
        }

        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        //It validates bean instances
        Validator validator = factory.getValidator();

        //Validate bean
        Set<ConstraintViolation<HeroBuilder>> invalidName = validator.validateProperty(this, "heroName");
        if (invalidName.size() > 0) {
            BuildHelper.printMsg(errLocale.getString("invalidName"));
            heroName = new String();
        }

        if ( heroClass.isEmpty() || heroName.isEmpty() ) {
            return (null) ;
        }

        //Validate bean
        Set<ConstraintViolation<HelmData>> helmConstraintViolations       = validator.validate(helm);
        Set<ConstraintViolation<ArmorData>> armorConstraintViolations     = validator.validate(armor);
        Set<ConstraintViolation<WeaponData>> weaponConstraintViolations   = validator.validate(weapon);
        Set<ConstraintViolation<HeroBuilder>> heroConstraintViolations    = validator.validate(this);

        //Show errors
        if (
            (helmConstraintViolations.size() > 0)
            || (armorConstraintViolations.size() > 0)
            || (weaponConstraintViolations.size() > 0)
            || (heroConstraintViolations.size() > 0)
        ) {
            throw (new HeroBuilderException());
        }

        return (new HeroData(this));

    }

}
