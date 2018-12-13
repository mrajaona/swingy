package mrajaona.swingy.data;

/*
** Data for Enemy
** Modifiable via the Enemy model
** Constructed with the Enemy builder
*/

public class Enemy extends Character {

    @NotBlank(message = "Please define the enemy type")
    @Getter private String  enemyType;

    private Enemy() {
        super();
    }

    public Enemy(mrajaona.swingy.builder.Enemy builder) {
        super(builder.getLevel(),
            builder.getExperience(),
            builder.getBaseAttack(), builder.getAttack(),
            builder.getBaseDefense(), builder.getDefense(),
            builder.getBaseHitPoints(), builder.getMaxHitPoints(), builder.getHitPoints());
        this.enemyType   = builder.getEnemyType();
    }

}
