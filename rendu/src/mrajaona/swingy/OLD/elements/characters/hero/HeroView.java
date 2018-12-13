package mrajaona.swingy.elements.characters.hero;

import mrajaona.swingy.elements.characters.CharacterModel;
import mrajaona.swingy.elements.characters.CharacterView;

public class HeroView implements CharacterView {

    @Override
    public void show(CharacterModel model) {
        Hero hero = (Hero) model;
        System.out.println(
            "Name       : " + hero.getHeroName()      + System.lineSeparator() +
            "Class      : " + hero.getHeroClass()     + System.lineSeparator() +
            "Level      : " + hero.getLevel()         + System.lineSeparator() +
            "Experience : " + hero.getExperience()    + System.lineSeparator() +
            "Base Atk   : " + hero.getBaseAttack()    + System.lineSeparator() +
            "Atk        : " + hero.getAttack()        + System.lineSeparator() +
            "Base Def   : " + hero.getBaseDefense()   + System.lineSeparator() +
            "Def        : " + hero.getDefense()       + System.lineSeparator() +
            "Base HP    : " + hero.getBaseHitPoints() + System.lineSeparator() +
            "Max HP     : " + hero.getMaxHitPoints()  + System.lineSeparator() +
            "HP         : " + hero.getHitPoints()
        );
    }

}
