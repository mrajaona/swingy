package mrajaona.swingy.elements.characters;

import javax.validation.constraints.NotNull;

public abstract class CharacterController {
	
	// members
	
	@NotNull
	private CharacterModel	model;
	@NotNull
	private CharacterView	view;
	
	// methods
		
	public abstract void attack(CharacterController target);
	// public abstract void levelUp();
	// abstract void die();
	
	public void updateView() {
		view.show();
	}
	
}
