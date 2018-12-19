package mrajaona.swingy.model.character;

/*
** Enemy Model
** Manipulates Enemy data
*/

public class EnemyModel implements CharacterModel {

    private static EnemyModel model = new EnemyModel();

    private EnemyModel() {}

    public static EnemyModel getModel() {
        return (model);
    }

    @Override
    public void fullRecover() {
        ;
    }

    @Override
    public void recoverHP(int amount) {
        ;
    }

    @Override
    public void loseHP(int amount) {
        ;
    }

    @Override
    public void beAttacked(int enemyAtk) {
        ;
    }

    @Override
    public void die() {
        ;
    }

}
