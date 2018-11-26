package mrajaona.swingy.elements.characters.hero;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import mrajaona.swingy.elements.characters.CharacterModel;

public class Hero extends CharacterModel {
	
	@NotNull @NotEmpty
	private String	heroName;
	@NotNull @NotEmpty
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
