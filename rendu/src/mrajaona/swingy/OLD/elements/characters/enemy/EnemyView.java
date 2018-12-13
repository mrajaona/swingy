package mrajaona.swingy.elements.characters.enemy;

import mrajaona.swingy.elements.characters.CharacterModel;
import mrajaona.swingy.elements.characters.CharacterView;

public class EnemyView implements CharacterView {

	@Override
	public void show(CharacterModel model) {
        Enemy enemy = (Enemy) model;
        System.out.println(
            "Type       : " + enemy.getEnemyType()     + System.lineSeparator() +
            "Level      : " + enemy.getLevel()         + System.lineSeparator() +
            "Experience : " + enemy.getExperience()    + System.lineSeparator() +
            "Base Atk   : " + enemy.getBaseAttack()    + System.lineSeparator() +
            "Atk        : " + enemy.getAttack()        + System.lineSeparator() +
            "Base Def   : " + enemy.getBaseDefense()   + System.lineSeparator() +
            "Def        : " + enemy.getDefense()       + System.lineSeparator() +
            "Base HP    : " + enemy.getBaseHitPoints() + System.lineSeparator() +
            "Max HP     : " + enemy.getMaxHitPoints()  + System.lineSeparator() +
            "HP         : " + enemy.getHitPoints()
        );
	}

}
