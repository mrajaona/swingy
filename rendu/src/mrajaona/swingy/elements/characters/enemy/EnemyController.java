package mrajaona.swingy.elements.characters.enemy;

import javax.validation.constraints.NotNull;

import mrajaona.swingy.elements.characters.CharacterController;

public class EnemyController extends CharacterController {

    @NotNull
    private Enemy        model;
    @NotNull
    private EnemyView    view;

	@Override
	public void attack(CharacterController target) {
		System.out.println("Attack");
	}

	@Override
	public void updateView() {
		view.show(model);
	}

}
