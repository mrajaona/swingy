package mrajaona.swingy.model;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.GameMapData;
import mrajaona.swingy.data.SaveFileData;
import mrajaona.swingy.data.character.HeroData;

public class SaveFileModel {

    @SuppressWarnings("unused")
    private SaveFileModel() {}

    public static void updateFile(HeroData hero, GameMapData map) {
        SaveFileData data = GameData.getData().getSaveFile();

        data.setHero(hero);
        data.setMap(map);
    }

}
