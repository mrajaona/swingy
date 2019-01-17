package mrajaona.swingy.data;

import java.util.Locale;
import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.data.GameMapData;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.util.Util.GameScreen;

public class GameData {

    private static GameData data = new GameData();

    private GameData() {}

    public static GameData getData() {
        return (data);
    }

    @Getter @Setter private static Locale locale;

    @Getter         private static Scanner inputScanner    = new Scanner(System.in);
    @Getter @Setter private static Util.ViewTypes viewType = Util.ViewTypes.CONSOLE;

    @Getter @Setter private static HeroData hero;
    // current enemy
    @Getter @Setter private static EnemyData enemy;

    @Getter @Setter private static GameMapData map;

    @Getter @Setter private static GameScreen screen       = GameScreen.TITLE;

}
