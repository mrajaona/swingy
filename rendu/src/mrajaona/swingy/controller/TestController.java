package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;

import mrajaona.swingy.GameLoop;
import mrajaona.swingy.Save;
import mrajaona.swingy.builder.HeroBuilder;
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

	public void printHero() {
		HeroData hero = GameLoop.getHero();
        MainHelper.show(
        	System.lineSeparator() +
            "Name       : " + hero.getHeroName()      + System.lineSeparator() +
            "Class      : " + hero.getHeroClass()     + System.lineSeparator() +
            "Level      : " + hero.getLevel()         + System.lineSeparator() +
            "Experience : " + hero.getExperience()    + System.lineSeparator() +

            "Helm       : " + hero.getHelm().getName() + " (" + hero.getHelm().getModifier() + ")" + System.lineSeparator() +
            "Armor      : " + hero.getArmor().getName() + " (" + hero.getArmor().getModifier() + ")" + System.lineSeparator() +
            "Weapon     : " + hero.getWeapon().getName() + " (" + hero.getWeapon().getModifier() + ")" + System.lineSeparator() +

            "Base Atk   : " + hero.getBaseAttack()    + System.lineSeparator() +
            "Atk        : " + hero.getAttack()        + System.lineSeparator() +
            "Base Def   : " + hero.getBaseDefense()   + System.lineSeparator() +
            "Def        : " + hero.getDefense()       + System.lineSeparator() +
            "Base HP    : " + hero.getBaseHitPoints() + System.lineSeparator() +
            "Max HP     : " + hero.getMaxHitPoints()  + System.lineSeparator() +
            "HP         : " + hero.getHitPoints()     + System.lineSeparator()
        );
	}

	public void changeWeapon() {
		HeroModel.getModel().equip(new WeaponData("Sword", 10));
	}

	public void removeWeapon() {
		HeroModel.getModel().unequip(GameLoop.getHero().getWeapon());
	}

}
