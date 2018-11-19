package mrajaona.swingy.util;

import mrajaona.swingy.elements.characters.enemy.Enemy;
import mrajaona.swingy.elements.characters.hero.Hero;

public class CharacterFactory {
	public Hero		createHero() {
		Hero hero = new Hero();
		
		return hero;
	}
	
	public Enemy	newEnemy(
			String enemyType,
			int level,
			int experience,
			int baseAttack,
			int baseDefense,
			int baseHitPoints
			) {
		Enemy enemy = new Enemy(
				enemyType,
				level,
				experience,
				baseAttack,
				baseDefense,
				baseHitPoints
				);
		
		return enemy;
	}
}
