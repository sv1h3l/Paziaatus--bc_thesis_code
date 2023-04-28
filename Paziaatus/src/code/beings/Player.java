package beings;

import residue.Constants;
import residue.Item;

public class Player extends PaziakPlayer
{
	private int	health;
	private int	fullness;
	private int	hydration;
	private int	energy;
	private int	weight;
	private int	appearance;
	private int	luck;
	private int	defense;
	private int	strength;
	private int	credits;

	private Item[]	gear;
	private Item[]	cards;
	private Item[]	inventory;

	private String actualPlanet;
	private String gameMode;
	private String specialization;

	public Player(int energy, int fullness, int hydration, int health, int credits, int weight, int appearance,
			int luck, int defense, int strength, String planet, String gameMode, String specialization)
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
		this.strength = strength;

		this.actualPlanet = planet;
		this.gameMode = gameMode;
		this.specialization = specialization;

		inventory = new Item[Constants.PLAYERS_INVENTORY_SIZE];
		gear = new Item[Constants.PLAYERS_GEAR_SIZE];
	}

	public Item[] getGear()
	{
		return gear;
	}

	public Item getNthGear(int nth)
	{
		return gear[nth];
	}

	public void changeGear(Item item, int nthGearSlot, int nthInventorySlot)
	{
		Item previousEquippedGear = gear[nthGearSlot];
		gear[nthGearSlot] = item;
		changeItem(previousEquippedGear, nthInventorySlot);
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

	public int addItemIntoInventory(Item item)
	{
		for (int i = 0; i < Constants.PLAYERS_INVENTORY_SIZE; i++)
		{
			if (inventory[i] == null)
			{
				inventory[i] = item;
				return i;
			}
		}
		return -1;
	}

	public boolean isFreeSlotInInventory()
	{
		for (int i = 0; i < Constants.PLAYERS_INVENTORY_SIZE; i++)
			if (inventory[i] == null)
				return true;
		return false;
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
		return strength;
	}

	public void setStrenght(int strenght)
	{
		this.strength = strenght;
	}

	public int getCredits()
	{
		return credits;
	}

	public void setCredits(int credits)
	{
		this.credits = credits;
	}

	public void travelOnNextPlanet()
	{
		if (actualPlanet.equals("Tarrys"))
			this.actualPlanet = "Narr Sheyda";
		else
			this.actualPlanet = "Kerusant";
	}

	public int planetMultiplier()
	{
		switch (actualPlanet)
		{
			case "Tarrys":
				return 1;
			case "Narr Sheyda":
				return 2;
			default:
				return 3;
		}
	}

	public String getActualPlanet()
	{
		return actualPlanet;
	}
}
