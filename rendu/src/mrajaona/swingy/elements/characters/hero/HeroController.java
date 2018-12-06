package mrajaona.swingy.elements.characters.hero;

import java.util.Scanner;

import javax.validation.constraints.NotNull;

import mrajaona.swingy.elements.artifacts.Armor;
import mrajaona.swingy.elements.artifacts.Helm;
import mrajaona.swingy.elements.artifacts.Weapon;
import mrajaona.swingy.elements.characters.CharacterController;

public class HeroController extends CharacterController {

    @NotNull
    private Hero        model;
    @NotNull
    private HeroView    view;

    @SuppressWarnings("unused")
    private HeroController() {}

    public HeroController(Hero model, HeroView view) {
        this.model = model;
        this.view  = view;
    }

    public void initHero(Scanner inputScanner) {

        if (model.getHeroClass() == null || model.getHeroClass().trim().isEmpty()) {
            System.out.println("Choose your class (Warrior, Thief, Mage, Priest) :");
            model.setHeroClass(inputScanner.nextLine());
        }
        if (model.getHeroName() == null || model.getHeroName().trim().isEmpty()) {
            System.out.println("Name your hero :");
            model.setHeroName(inputScanner.nextLine());
        }

        model.initStats();
    }

    @Override
    public void attack(CharacterController target) {
        System.out.println("Attack");
    }

    public void equip(Helm helm) {

    }

    public void equip(Armor armor) {

    }

    public void equip(Weapon weapon) {

    }

    public void unequip(Helm helm) {

    }

    public void unequip(Armor armor) {

    }

    public void unequip(Weapon weapon) {

    }

    // level up : level * 1000 + (level - 1)^2 * 450

    // ********************************************

    @Override
	public void updateView() {
        view.show(model);
    }

}
