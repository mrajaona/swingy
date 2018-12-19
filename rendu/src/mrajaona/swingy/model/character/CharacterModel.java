package mrajaona.swingy.model.character;

/*
** Character Model
** Manipulates Character data
** Used in the Hero and Enemy models
*/

interface CharacterModel {

    public void fullRecover();
    public void recoverHP(int amount);
    public void loseHP(int amount);
    public void beAttacked(int enemyAtk);
    public void die();

}
