package mrajaona.swingy.elements.characters;

import javax.validation.constraints.Positive;

public class CharacterModel {

	// members
	
	@Positive(message = "Invalid value")
	private int	level;
	@Positive(message = "Invalid value")
	private int	experience;

	@Positive(message = "Invalid value")
	private int	baseAttack;
	@Positive(message = "Invalid value")
	private int	baseDefense;
	@Positive(message = "Invalid value")
	private int baseHitPoints;
	
	@Positive(message = "Invalid value")
	private int	attack;
	@Positive(message = "Invalid value")
	private int	defense;
	@Positive(message = "Invalid value")
	private int hitPoints;
	
	// getters
	
	public int getLevel() {
		return (level);
	}
	
	public int getExperience() {
		return (experience);
	}
	
	public int getBaseAttack() {
		return (baseAttack);
	}
	
	public int getBaseDefense() {
		return (baseDefense);
	}
	
	public int getBaseHitPoints() {
		return (baseHitPoints);
	}
	
	public int getAttack() {
		return (attack);
	}
	
	public int getDefense() {
		return (defense);
	}
	
	public int getHitPoints() {
		return (hitPoints);
	}
	
}
