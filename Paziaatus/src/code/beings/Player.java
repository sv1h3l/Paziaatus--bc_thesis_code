package beings;

import residue.Item;

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

	private Item[]	gear;
	private Item[]	cards;
	private Item[]	inventory;

	private String planet;

	public final int INVENTORY_AND_GEAR_SIZE = 12;

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

		inventory = new Item[INVENTORY_AND_GEAR_SIZE];
		gear = new Item[INVENTORY_AND_GEAR_SIZE];
	}

	public Item[] getGear()
	{
		return gear;
	}
	
	public Item getNthGear(int nth)
	{
		return gear[nth];
	}
	
	public Item changeGear(Item item, int nth)
	{
		Item previousEquippedGear = gear[nth];
		gear[nth] = item;
		return previousEquippedGear;
	}

	public void changeItem(Item item, int nth)
	{
		inventory[nth] = item;
	}
	
	public void sellItem(int nth)
	{
		inventory[nth] = null;
	}
	
	public void addOrRemoveCredits(int amount)
	{
		credits = credits + amount;
	}
	
	public void addGear(Item gear)
	{
		this.gear[0] = gear;
	}
	
	public void setGear(Item[] gear)
	{
		this.gear = gear;
	}

	public Item[] getCards()
	{
		return cards;
	}

	public void setCards(Item[] cards)
	{
		this.cards = cards;
	}

	public Item[] getInventory()
	{
		return inventory;
	}
	
	public Item getNthItemFromInventory(int nth)
	{
		return inventory[nth];
	}

	public void setInventory(Item[] inventory)
	{
		this.inventory = inventory;
	}

	public int addItemInInventory(Item item)
	{
		for (int i = 0; i < INVENTORY_AND_GEAR_SIZE; i++)
		{
			if (inventory[i] == null)
			{
				inventory[i] = item;
				return i;
			}
		}
		return -1;
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
