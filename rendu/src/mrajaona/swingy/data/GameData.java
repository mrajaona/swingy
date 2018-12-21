package mrajaona.swingy.data;

import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;
import mrajaona.swingy.Util;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.data.character.HeroData;

public class GameData {

    private static GameData data = new GameData();

    private GameData() {}

    public static GameData getData() {
        return (data);
    }

    @Getter public static Scanner inputScanner      = new Scanner(System.in);
    @Getter @Setter public static String viewType   = Util.VIEW_TYPE_CONSOLE;

    @Getter @Setter public static HeroData hero;
    // current enemy
    @Getter @Setter public static EnemyData enemy;
    // all enemies on map
    // TODO : map

}
