package mrajaona.swingy.util;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import mrajaona.swingy.elements.characters.enemy.Enemy;


public class EnemyFactory {

    // Nested interfaces

    private interface EnemyCreator {
        public Enemy make(final int enemyLevel);
    }

    // Enemy types

    public static final String SLIME       = "Slime";
    public static final String GOBLIN      = "Goblin";
	
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

        Enemy enemy = creator.make(enemyLevel);

        return (enemy);
    }

}
