package mrajaona.swingy.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import lombok.Getter;
import mrajaona.swingy.data.GameData;

public class Util {

    // UI

    public enum ViewTypes {
        GUI("gui"),
        CONSOLE("console");

        String view;

        private ViewTypes(String view) {
            this.view = view;
        }

        String getView() {
            return (view);
        }

        public static ViewTypes getKeyByValue(String value) {
            for ( ViewTypes v : ViewTypes.values() ) {
                if (v.getView().equals(value))
                    return (v);
            }

            return null;
        }

    };

    public enum ArtifactType {
        ARMOR("armor", "defense"),
        HELM("helm", "maxHitPoints"),
        WEAPON("weapon", "attack");

        String type;
        String stat;

        private ArtifactType(String type, String stat) {
            this.type = type;
            this.stat = stat;
        }

        private static final List<ArtifactType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int                SIZE   = VALUES.size();
        private static final Random             RANDOM = new Random();

        public static ArtifactType random()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }

        public String localizeType() {
            return (
                ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.ArtifactResource",
                    GameData.getData().getLocale() )
                .getString(type)
                );
        }

        public String localizeStat() {
            return (
                ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.StatResource",
                    GameData.getData().getLocale() )
                .getString(stat)
                );
        }

    }

    public enum GameScreen {
        TITLE,
        NEW,
        MAIN,
        WIN,
        LOSE
    }

    public enum SubScreen {
        MAIN,
        BATTLE,
        LOOT
    }

    // Hero types

    public static final String HERO_WARRIOR  = "warrior";
    public static final String HERO_THIEF    = "thief";
    public static final String HERO_MAGE     = "mage";
    public static final String HERO_PRIEST   = "priest";
    public static final String [] heroTypes  = {
        HERO_WARRIOR,
        HERO_THIEF,
        HERO_MAGE,
        HERO_PRIEST
    };

    // Hero base stats

    public static enum HeroBaseStats {
        WARRIOR(2, 5, 3),
        THIEF(4, 4, 2),
        MAGE(6, 2, 2),
        PRIEST(2, 2, 6);

        @Getter int atk;
        @Getter int def;
        @Getter int hp;

        private HeroBaseStats(int atk, int def, int hp) {
            this.atk = atk;
            this.def = def;
            this.hp  = hp;
        }
    }

    public static final Map <String, HeroBaseStats> HERO_BASE_STATS_MAP = initHeroBaseStatsMap();
    private static final Map <String, HeroBaseStats> initHeroBaseStatsMap() {
        Map <String, HeroBaseStats> newMap = new HashMap <String, HeroBaseStats> (heroTypes.length);

        newMap.put(HERO_WARRIOR, HeroBaseStats.WARRIOR);
        newMap.put(HERO_THIEF,   HeroBaseStats.THIEF);
        newMap.put(HERO_MAGE,    HeroBaseStats.MAGE);
        newMap.put(HERO_PRIEST,  HeroBaseStats.PRIEST);

        return (Collections.unmodifiableMap(newMap));
    }

    // Enemy types

    public static final String ENEMY_SLIME   = "slime";
    public static final String ENEMY_GOBLIN  = "goblin";
    public static final String [] enemyTypes = {
            ENEMY_SLIME,
            ENEMY_GOBLIN
    };

    // Enemy base stats

    public static enum EnemyBaseStats {
        SLIME_BASE_STATS(100, 1, 1, 10),
        GOBLIN_BASE_STATS(250, 5, 5, 15);

        @Getter int exp;
        @Getter int atk;
        @Getter int def;
        @Getter int hp;

        private EnemyBaseStats(int exp, int atk, int def, int hp) {
            this.exp = exp;
            this.atk = atk;
            this.def = def;
            this.hp  = hp;
        }
    }

    public static final Map <String, EnemyBaseStats> ENEMY_BASE_STATS_MAP = initEnemyBaseStatsMap();
    private static final Map <String, EnemyBaseStats> initEnemyBaseStatsMap() {
        Map <String, EnemyBaseStats> newMap = new HashMap <String, EnemyBaseStats> (enemyTypes.length);

        newMap.put(ENEMY_SLIME,  EnemyBaseStats.SLIME_BASE_STATS);
        newMap.put(ENEMY_GOBLIN, EnemyBaseStats.GOBLIN_BASE_STATS);

        return (Collections.unmodifiableMap(newMap));
    }

}
