package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.Save;
import mrajaona.swingy.view.helper.MainHelper;
import mrajaona.swingy.data.artifact.WeaponData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.model.character.HeroModel;

public class TestController {

    private static TestController controller = new TestController();

    private TestController() {}

    public static TestController getController() {
        return (controller);
    }

	public void newHero() {
		GameModel.getModel().createHero();
	}

	public void loadHero(long id) throws SQLException, IOException {
		GameModel.getModel().loadHero(id);
	}

	public void saveHero() throws SQLException, IOException {
		Save.getManager().save();
	}

	public void printHero() {
		HeroData hero = GameData.getHero();
        ResourceBundle locale = ResourceBundle.getBundle( "mrajaona.swingy.locale.StatResource", GameData.getData().getLocale() );
        ResourceBundle artifactLocale = ResourceBundle.getBundle( "mrajaona.swingy.locale.ArtifactResource", GameData.getData().getLocale() );

        MainHelper.show(
        	System.lineSeparator() +
            locale.getString("name") + " : " + hero.getHeroName()      + System.lineSeparator() +
            locale.getString("class") + " : " + hero.getHeroClass()     + System.lineSeparator() +
            locale.getString("level") + " : " + hero.getLevel()         + System.lineSeparator() +
            locale.getString("experience") + " :" + hero.getExperience()    + System.lineSeparator() +

            locale.getString("helm") + " : " + artifactLocale.getString(hero.getHelm().getName()) + " (" + hero.getHelm().getModifier() + ")" + System.lineSeparator() +
            locale.getString("armor") + " : " + artifactLocale.getString(hero.getArmor().getName()) + " (" + hero.getArmor().getModifier() + ")" + System.lineSeparator() +
            locale.getString("weapon") + " : " + artifactLocale.getString(hero.getWeapon().getName()) + " (" + hero.getWeapon().getModifier() + ")" + System.lineSeparator() +

            locale.getString("baseAttack") + " : " + hero.getBaseAttack()    + System.lineSeparator() +
            locale.getString("attack") + " : " + hero.getAttack()        + System.lineSeparator() +
            locale.getString("baseDefense") + " : " + hero.getBaseDefense()   + System.lineSeparator() +
            locale.getString("defense") + " : " + hero.getDefense()       + System.lineSeparator() +
            locale.getString("baseHitPoints") + " : " + hero.getBaseHitPoints() + System.lineSeparator() +
            locale.getString("maxHitPoints") + " : " + hero.getMaxHitPoints()  + System.lineSeparator() +
            locale.getString("hitPoints") + " : " + hero.getHitPoints()     + System.lineSeparator()
        );
	}

	public void changeWeapon() {
		HeroModel.getModel().equip(new WeaponData("sword", 10));
	}

	public void removeWeapon() {
		HeroModel.getModel().unequip(GameData.getHero().getWeapon());
	}

}
