package mrajaona.swingy.util;

import mrajaona.swingy.elements.characters.enemy.Enemy;
import mrajaona.swingy.elements.characters.hero.Hero;

public class CharacterFactory {
	public Hero		createHero() {
		Hero hero = new Hero();
		
		return hero;
	}
	
	public Enemy	newEnemy() {
		Enemy enemy = new Enemy();
		
		return enemy;
	}
}
