package mrajaona.swingy.elements.characters.hero;

import javax.validation.constraints.NotNull;

import mrajaona.swingy.elements.characters.CharacterModel;

public class Hero extends CharacterModel {
	
	@NotNull
	private String	name;
	
}
