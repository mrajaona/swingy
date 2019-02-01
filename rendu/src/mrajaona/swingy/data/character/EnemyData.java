package mrajaona.swingy.data.character;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import mrajaona.swingy.builder.EnemyBuilder;

/*
** Data for Enemy
** Modifiable via the Enemy model
** Constructed with the Enemy builder
*/

@DatabaseTable(tableName = "enemies")
public class EnemyData extends CharacterData implements Serializable {

    private static final long serialVersionUID = 4476601624225562551L;

    @DatabaseField(generatedId = true)
    @Getter private long id;

    @DatabaseField(canBeNull = false)
    @NotBlank
    @Getter private String       enemyType;

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
