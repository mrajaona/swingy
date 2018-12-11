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

    public HeroController(Hero model) {
        this.model = model;
        this.view  = new HeroView();
    }

    public HeroController() {
        this.model = HeroBuilder.getBuilder().newHero();
        this.view  = new HeroView();
    }

/*
    public HeroController(saveFile) {
        this.model = new Hero();
        this.view  = new HeroView();
    }
*/

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
