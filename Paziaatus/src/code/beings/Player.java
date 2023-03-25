package beings;

import java.util.ArrayList;
import java.util.List;

import items.Item;

public class Player extends PazaakPlayer
{
	private int	health;
	private int	fullness;
	private int	hydration;
	private int	energy;
	private int	weight;
	private int	appearance;
	private int	luck;
	private int	defense;
	private int	strenght;
	private int	credits;

	private List<Item> inventory;

	private String planet;
	//private Speeder speeder;

	public Player(int energy, int fullness, int hydration, int health, int credits, int weight, int appearance,
			int luck, int defense, int strenght, String planet)
	{
		this.energy = energy;
		this.fullness = fullness;
		this.hydration = hydration;
		this.health = health;
		this.credits = credits;

		this.weight = weight;
		this.appearance = appearance;
		this.luck = luck;
		this.defense = defense;
		this.strenght = strenght;

		this.planet = planet;

		inventory = new ArrayList<>();
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public int getFullness()
	{
		return fullness;
	}

	public void setFullness(int fullness)
	{
		this.fullness = fullness;
	}

	public int getHydration()
	{
		return hydration;
	}

	public void setHydration(int hydration)
	{
		this.hydration = hydration;
	}

	public int getEnergy()
	{
		return energy;
	}

	public void setEnergy(int energy)
	{
		this.energy = energy;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public int getAppearance()
	{
		return appearance;
	}

	public void setAppearance(int appearance)
	{
		this.appearance = appearance;
	}

	public int getLuck()
	{
		return luck;
	}

	public void setLuck(int luck)
	{
		this.luck = luck;
	}

	public int getDefense()
	{
		return defense;
	}

	public void setDefense(int defense)
	{
		this.defense = defense;
	}

	public int getStrenght()
	{
		return strenght;
	}

	public void setStrenght(int strenght)
	{
		this.strenght = strenght;
	}

	public int getCredits()
	{
		return credits;
	}

	public void setCredits(int credits)
	{
		this.credits = credits;
	}

	public String getPlanet()
	{
		return planet;
	}

	public void setPlanet(String planet)
	{
		this.planet = planet;
	}

}
