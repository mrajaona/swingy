package mrajaona.swingy.elements.characters;

import javax.validation.constraints.Positive;

public class CharacterModel {

	// members
	
	@Positive(message = "Invalid value")
	private int	level;
	// @Max(value = level*1000+(level - 1)^2*450, message = "Too much experience")
	@Positive(message = "Invalid value (experience)")
	private int	experience;

	@Positive(message = "Invalid value (base attack)")
	private int	baseAttack;
	@Positive(message = "Invalid value (base defense)")
	private int	baseDefense;
	@Positive(message = "Invalid value (base hit points)")
	private int baseHitPoints;
	
	@Positive(message = "Invalid value (attack)")
	private int	attack;
	@Positive(message = "Invalid value (defense)")
	private int	defense;
	@Positive(message = "Invalid value (hit points)")
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
	
	// constructors
	@SuppressWarnings("unused")
	private CharacterModel() {}
	
	public	CharacterModel(
			int baseAttack,
			int baseDefense,
			int baseHitPoints) {
		this.level			= 1;
		this.experience		= 0;
		this.baseAttack		= baseAttack;
		this.attack			= this.baseAttack;
		this.baseDefense	= baseDefense;
		this.defense		= this.baseDefense;
		this.baseHitPoints	= baseHitPoints;
		this.hitPoints		= this.baseHitPoints;	
	}
	
	public	CharacterModel(
			int level,
			int experience,
			int baseAttack,
			int baseDefense,
			int baseHitPoints
			) {
		this.level			= level;
		this.experience		= experience;
		this.baseAttack		= baseAttack;
		this.attack			= this.baseAttack;
		this.baseDefense	= baseDefense;
		this.defense		= this.baseDefense;
		this.baseHitPoints	= baseHitPoints;
		this.hitPoints		= this.baseHitPoints;
	}
}
