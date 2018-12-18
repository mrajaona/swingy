package mrajaona.swingy.elements.characters.enemy;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

import mrajaona.swingy.elements.characters.CharacterModel;

public class Enemy extends CharacterModel {

    @NotBlank(message = "Please define the enemy type")
    @Getter private String  enemyType;

    public Enemy(EnemyBuilder builder) {
        super(builder.getLevel(),
            builder.getExperience(),
            builder.getBaseAttack(), builder.getAttack(),
            builder.getBaseDefense(), builder.getDefense(),
            builder.getBaseHitPoints(), builder.getMaxHitPoints(), builder.getHitPoints());
        this.enemyType   = builder.getEnemyType();
    }

}
