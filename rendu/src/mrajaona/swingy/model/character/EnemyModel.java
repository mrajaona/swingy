package mrajaona.swingy.model.character;

/*
** Enemy Model
** Manipulates Enemy data
*/

public class EnemyModel extends CharacterModel {

    private static EnemyModel model = new EnemyModel();

    private EnemyModel() {}

    public static EnemyModel getModel() {
        return (model);
    }

}
