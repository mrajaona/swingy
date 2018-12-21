package mrajaona.swingy.model;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.builder.HeroBuilder;
import mrajaona.swingy.Save;
import mrajaona.swingy.Util;

public class GameModel {

    private static GameModel model = new GameModel();

    private GameModel() {}

    public static GameModel getModel() {
        return (model);
    }

    public void createHero() {
		GameData.setHero(
			HeroBuilder.getBuilder().newHero()
			);
    }

    public void loadHero(long id) throws SQLException, IOException {
		GameData.setHero(
			HeroBuilder.getBuilder().loadHero(
				Save.getManager().load(id)
				)
			);
    }

    public void changeViewType(Util.ViewType newType) {
    	if (newType == null)
    		; // throw exception
    	else {
    		GameData.setViewType(newType);
    		// update view
    	}
    }

}
