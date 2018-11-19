package mrajaona.swingy.elements.characters.enemy;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import mrajaona.swingy.elements.characters.CharacterModel;

public class Enemy extends CharacterModel {
	
	@NotNull @NotEmpty
	private String	enemyType;
	
}
