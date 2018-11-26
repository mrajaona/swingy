package mrajaona.swingy.util;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import mrajaona.swingy.elements.characters.enemy.Enemy;
import mrajaona.swingy.elements.characters.hero.Hero;

public class HeroFactory {
	
    // Nested interfaces

    private interface HeroCreator {
        public Hero make(final String heroName);
    }

    // Hero types

    public static final String WARRIOR	= "Warrior";
    public static final String THIEF	= "Thief";
    public static final String MAGE	    = "Mage";
    public static final String PRIEST	= "Priest";
    
    private static final String [] heroTypes    = {
        WARRIOR,
        THIEF,
        MAGE,
        PRIEST
    } ;
  
    private static final Map <String, HeroCreator> CREATOR_MAP = initCreatorMap();
    private static final Map <String, HeroCreator> initCreatorMap() {
        Map <String,HeroCreator> newMap = new HashMap <String, HeroCreator> (heroTypes.length);

        newMap.put(
            WARRIOR,
            new HeroCreator() {
                public Hero make(final String heroName) {
                    return (new Hero(heroName, WARRIOR, 10, 10, 100));
                }
            }
        );

        return (Collections.unmodifiableMap(newMap));
    }

    // Create hero

    public Hero     newHero(final String heroName, final String heroClass) {
        HeroCreator creator = CREATOR_MAP.get(heroClass);

        if (creator == null) {
            return null;
        }

        Hero hero = creator.make(heroName);

        return (hero);
    }

}
