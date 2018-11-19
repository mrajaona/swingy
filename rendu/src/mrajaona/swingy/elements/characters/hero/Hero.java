package mrajaona.swingy.elements.characters.hero;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import mrajaona.swingy.elements.characters.CharacterModel;

public class Hero extends CharacterModel {
	
	@NotNull @NotEmpty
	private String	heroName;
	@NotNull @NotEmpty
	private String	heroClass;
	
	public Hero() {
		super(10, 10, 100);
		heroName	= "heroName";
		heroClass	= "heroClass";
	}
}
