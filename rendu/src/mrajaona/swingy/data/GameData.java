package mrajaona.swingy.data;

import java.util.Locale;
import java.util.ResourceBundle;
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

    @Getter @Setter private static Locale          locale;
    @Getter @Setter private static ResourceBundle  resBundle;

    @Getter         private static Scanner inputScanner = new Scanner(System.in);
    @Getter @Setter private static String viewType      = Util.VIEW_TYPE_CONSOLE;

    @Getter @Setter private static HeroData hero;
    // current enemy
    @Getter @Setter private static EnemyData enemy;
    // all enemies on map
    // TODO : map

}
