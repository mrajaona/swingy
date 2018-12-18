package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.GameLoop;
import mrajaona.swingy.Save;
import mrajaona.swingy.builder.HeroBuilder;

public class TestController {

    private static TestController controller = new TestController();

    private TestController() {}

    public static TestController getController() {
        return (controller);
    }

	public void newHero() {
		GameLoop.setHero(
			HeroBuilder.getBuilder().newHero()
			);
	}

	public void loadHero(long id) throws SQLException, IOException {
		GameLoop.setHero(
			HeroBuilder.getBuilder().loadHero(
				Save.getManager().load(id)
				)
			);
	}

	public void saveHero() throws SQLException, IOException {
		Save.getManager().save();
	}

}
