package mrajaona.swingy.elements.characters.hero;

import javax.validation.constraints.NotBlank;

import mrajaona.swingy.elements.characters.CharacterModel;

public class Hero extends CharacterModel {
	
	@NotBlank(message = "Please name your hero")
	private String	heroName;

	@NotBlank(message = "Please choose a class")
	private String	heroClass;
	
	public Hero(
		final String heroName,
		final String heroClass,
		final int baseAttack,
		final int baseDefense,
		final int baseHitPoints) {
		super(baseAttack, baseDefense, baseHitPoints);
		this.heroName	= heroName;
		this.heroClass	= heroClass;
	}
}
