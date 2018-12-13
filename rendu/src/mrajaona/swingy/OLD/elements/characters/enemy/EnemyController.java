package mrajaona.swingy.elements.characters.enemy;

import javax.validation.constraints.NotNull;

import mrajaona.swingy.elements.characters.CharacterController;

import lombok.AccessLevel;
import lombok.Getter;

public class EnemyController extends CharacterController {

    @NotNull
    @Getter(AccessLevel.PROTECTED) private Enemy        model;
    @NotNull
    @Getter(AccessLevel.PROTECTED) private EnemyView    view;

    private EnemyController() {}

    public EnemyController(final String enemyType, final int enemyLevel) {
        this.model = EnemyBuilder.getBuilder().newEnemy(enemyType, enemyLevel);
        this.view  = new EnemyView();
    }

	@Override
	public void attack(CharacterController target) {
		System.out.println("Attack");
	}

	@Override
	public void updateView() {
		view.show(model);
	}

}
