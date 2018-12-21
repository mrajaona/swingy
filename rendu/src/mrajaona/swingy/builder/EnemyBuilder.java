package mrajaona.swingy.builder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import mrajaona.swingy.Util;
import mrajaona.swingy.data.character.EnemyData;

/*
** Enemy builder
** Creates and verifies new Enemy data
*/
public class EnemyBuilder {

    private static EnemyBuilder builder = new EnemyBuilder();

    private EnemyBuilder() {}

    public static EnemyBuilder getBuilder() {
        return (builder);
    }

    // TODO : localization

    @NotBlank(message = "Please define the enemy type")
    @Getter private String  enemyType;

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

    protected EnemyBuilder setEnemyType(String value) {
        enemyType = value;
        return (this);
    }

    protected EnemyBuilder setLevel(int value) {
        level = value;
        return (this);
    }

    protected EnemyBuilder setExperience(int value) {
        experience = value;
        return (this);
    }

    protected EnemyBuilder setBaseAttack(int value) {
    baseAttack = value;
        return (this);
    }

    protected EnemyBuilder setBaseDefense(int value) {
        baseDefense = value;
        return (this);
    }

    protected EnemyBuilder setBaseHitPoints(int value) {
        baseHitPoints = value;
        return (this);
    }

    protected EnemyBuilder setAttack(int value) {
        attack = value;
        return (this);
    }

    protected EnemyBuilder setDefense(int value) {
        defense = value;
        return (this);
    }

    protected EnemyBuilder setMaxHitPoints(int value) {
        maxHitPoints = value;
        return (this);
    }

    protected EnemyBuilder setHitPoints(int value) {
        hitPoints = value;
        return (this);
    }

    // Creation

    EnemyData newEnemy(final String enemyType, final int enemyLevel) {
        Util.EnemyBaseStats stats = Util.ENEMY_BASE_STATS_MAP.get(enemyType);
        if (stats == null)
            return (null);

        return this.setEnemyType(enemyType)
        .setLevel(enemyLevel)
        .setExperience(level * stats.getExp())
        .setBaseAttack(level * stats.getAtk())
        .setBaseDefense(level * stats.getDef())
        .setBaseHitPoints(level * stats.getHp())
        .setAttack(baseAttack)
        .setDefense(baseDefense)
        .setMaxHitPoints(baseHitPoints)
        .setHitPoints(baseHitPoints)
        .build();
    }

    EnemyData build() {

        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        //It validates bean instances
        Validator validator = factory.getValidator();

        //Validate bean
        Set<ConstraintViolation<EnemyBuilder>> constraintViolations = validator.validate(this);

        //Show errors
        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<EnemyBuilder> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
            return (null);
        } else {
            System.out.println("Valid Object"); // DEBUG
            return (new EnemyData(this));
        }

    }

}
