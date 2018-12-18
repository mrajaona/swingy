package mrajaona.swingy.data.character;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import mrajaona.swingy.builder.EnemyBuilder;

/*
** Data for Enemy
** Modifiable via the Enemy model
** Constructed with the Enemy builder
*/

public class EnemyData extends CharacterData {

    @NotBlank(message = "Please define the enemy type")
    @Getter private String  enemyType;

    @SuppressWarnings("unused")
	private EnemyData() {
        super();
    }

    public EnemyData(EnemyBuilder builder) {
        super(builder.getLevel(),
            builder.getExperience(),
            builder.getBaseAttack(), builder.getAttack(),
            builder.getBaseDefense(), builder.getDefense(),
            builder.getBaseHitPoints(), builder.getMaxHitPoints(), builder.getHitPoints());
        this.enemyType   = builder.getEnemyType();
    }

}
