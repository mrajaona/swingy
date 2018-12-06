package mrajaona.swingy.util;

/*
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import mrajaona.swingy.elements.characters.enemy.Enemy;
*/

public class EnemyFactory {
/*
    private static EnemyFactory factory = new EnemyFactory();

    private EnemyFactory() {}

    public static EnemyFactory getFactory() {
        return (factory);
    }

    // Nested interfaces

    private interface EnemyCreator {
        public Enemy make(final int enemyLevel);
    }

    // Enemy types

    private static final String SLIME           = "Slime";
    private static final String GOBLIN          = "Goblin";

    private static final String [] enemyTypes    = {
            SLIME,
            GOBLIN
        } ;

    private static final Map <String, EnemyCreator> CREATOR_MAP = initCreatorMap();
    private static final Map <String, EnemyCreator> initCreatorMap() {
        Map <String,EnemyCreator> newMap = new HashMap <String, EnemyCreator> (enemyTypes.length);

        newMap.put(
            SLIME,
            new EnemyCreator() {
                public Enemy make(int lvl) {
                    return (new Enemy(SLIME, lvl, lvl * 10, lvl * 1, lvl * 1, lvl * 5));
                }
            }
        );

        newMap.put(
            GOBLIN,
            new EnemyCreator() {
                public Enemy make(int lvl) {
                    return (new Enemy(GOBLIN, lvl, lvl * 25, lvl * 5, lvl * 5, lvl * 15));
                }
            }
        );

        return (Collections.unmodifiableMap(newMap));
    }

    // Create enemy

    public Enemy     newEnemy(final String enemyType, final int enemyLevel) {
        EnemyCreator creator = CREATOR_MAP.get(enemyType);

        if (creator == null) {
            return null;
        }

        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        //It validates bean instances
        Validator validator = factory.getValidator();

        Enemy enemy = creator.make(enemyLevel);

        //Validate bean
        Set<ConstraintViolation<Enemy>> constraintViolations = validator.validate(enemy);

        //Show errors
        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<Enemy> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
            return (null);
        } else {
            System.out.println("Valid Object");
            return (enemy);
        }

    }
*/
}
