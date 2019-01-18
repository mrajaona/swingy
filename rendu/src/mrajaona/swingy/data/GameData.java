package mrajaona.swingy.data;

import java.util.Locale;
import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;
import mrajaona.swingy.data.character.EnemyData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.data.GameMapData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.util.Util;
import mrajaona.swingy.util.Util.GameScreen;

public class GameData {

    private static GameData data = new GameData();

    private GameData() {}

    public static GameData getData() {
        return (data);
    }

    @Getter @Setter private Locale locale;

    @Getter         private Scanner inputScanner    = new Scanner(System.in);
    @Getter @Setter private Util.ViewTypes viewType = Util.ViewTypes.CONSOLE;

    @Getter @Setter private HeroData hero;
    // current enemy
    @Getter @Setter private EnemyData enemy;
    // dropped artifact
    @Getter @Setter private ArtifactData artifact;

    @Getter @Setter private GameMapData map;

    @Getter @Setter private GameScreen screen       = GameScreen.TITLE;

}
