package mrajaona.swingy.builder;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.util.Util;

/*
** Enemy builder
** Creates and verifies new Enemy data
*/
public class EnemyBuilder {

    // TODO : localization

    @NotBlank
    @Getter private String  enemyType;

    @Positive
    @Getter private int level         = 1;

    @PositiveOrZero
    @Getter private int experience    = 0;

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
    @PositiveOrZero
    @Getter private int maxHitPoints  = 0;
    @PositiveOrZero
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

    public EnemyData newEnemy(final String enemyType, final int enemyLevel) {
        Util.EnemyBaseStats stats = Util.ENEMY_BASE_STATS_MAP.get(enemyType);
        if (stats == null)
            return (null);

        return this.setEnemyType(enemyType)
        .setLevel(enemyLevel)
        .setExperience((1 + level/5) * stats.getExp())
        .setBaseAttack((1 + level/5) * stats.getAtk())
        .setBaseDefense((1 + level/5) * stats.getDef())
        .setBaseHitPoints((1 + level/5) * stats.getHp())
        .setAttack(baseAttack)
        .setDefense(baseDefense)
        .setMaxHitPoints(baseHitPoints)
        .setHitPoints(baseHitPoints)
        .build();
    }

    public EnemyData build() {

        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        //It validates bean instances
        Validator validator = factory.getValidator();

        //Validate bean
        Set<ConstraintViolation<EnemyBuilder>> constraintViolations = validator.validate(this);

        //Show errors
        if (constraintViolations.size() > 0) {
            // TODO : Exception
            System.out.println("Invalid enemy");
            return (null);
        } else {
            System.out.println("Valid enemy"); // DEBUG
            return (new EnemyData(this));
        }

    }

}
