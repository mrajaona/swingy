package mrajaona.swingy.elements.characters.hero;

import mrajaona.swingy.elements.characters.CharacterController;

public class HeroController extends CharacterController {

	@Override
	public void attack(CharacterController target) {
		System.out.println("Attack");
	}

	// level up : level * 1000 + (level - 1)^2 * 450

}
