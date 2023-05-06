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
	private int	skill;

	private Item[]	gear;
	private Item[]	deck;
	private Item[]	inventory;

	private String	actualPlanet;
	private String	gameMode;
	private String	specialization;

	private String backgroundColor;

	private int paziakBet;

	public Player(int energy, int fullness, int hydration, int health, int credits, int weight, int appearance, int luck, int defense, int strength,
			String planet, String gameMode, String specialization, int paziakBet)
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

		backgroundColor = "blue";

		this.paziakBet = paziakBet;

		inventory = new Item[Constants.PLAYERS_INVENTORY_SIZE];
		gear = new Item[Constants.PLAYERS_GEAR_SIZE];
		deck = new Item[Constants.PLAYERS_DECK_SIZE];
	}

	public int getPaziakBet()
	{
		return paziakBet;
	}

	public void setPaziakBet(int paziakBet)
	{
		this.paziakBet = paziakBet;
	}

	public boolean deckChecker()
	{
		for (int i = 0; i < Constants.PLAYERS_DECK_SIZE; i++)
			if (deck[i] == null)
				return false;
		return true;
	}

	public String getGameMode()
	{
		return gameMode;
	}

	public String getSpecialization()
	{
		return specialization;
	}

	public int getSkill()
	{
		return skill;
	}

	public void setSkill(int skill)
	{
		this.skill = skill;
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
		addFeatures(item);
		changeItem(previousEquippedGear, nthInventorySlot);
	}

	public void changeItem(Item item, int nth)
	{
		inventory[nth] = item;
	}

	public boolean checkCreditsValue(int credits)
	{
		if ((this.credits - credits) < 0)
			return false;
		else
			return true;
	}

	public void sellItem(int nth)
	{
		addOrRemoveWeight(inventory[nth], false);
		addOrRemoveCredits(inventory[nth].getPrice(), true);
		inventory[nth] = null;
	}

	public void addOrRemoveCredits(int amount, boolean add)
	{
		if (add)
			credits = credits + amount;
		else
			credits = credits - amount;

	}

	public void addOrRemoveWeight(Item item, boolean add)
	{
		if (add)
			weight = weight + item.getWeight();
		else
			weight = weight - item.getWeight();
	}

	public void addFeatures(Item item)
	{
		switch (item.getItemType())
		{
			case "světelný meč":
			case "laserová puška":
			{
				strength = strength + item.getPrimaryFeature();
				break;
			}
			case "nástroj":
			{
				skill = skill + item.getPrimaryFeature();
				break;
			}
			case "droid":
			case "vznášedlo":
			case "implantát":
			case "artefakt":
				break;
			default:
			{
				defense = defense + item.getPrimaryFeature();
			}
		}
		int value = item.getSecondaryFeature();
		appearance = appearance + (value == Constants.NO_VALUE ? 0 : value);
		value = item.getTernaryFeature();
		luck = luck + (value == Constants.NO_VALUE ? 0 : value);
	}

	public void removeFeatures(Item item)
	{
		switch (item.getItemType())
		{
			case "světelný meč":
			case "laserová puška":
			{
				strength = strength - item.getPrimaryFeature();
				break;
			}
			case "nástroj":
			{
				skill = skill - item.getPrimaryFeature();
				break;
			}
			case "droid":
			case "vznášedlo":
			case "implantát":
			case "artefakt":
				break;
			default:
			{
				defense = defense - item.getPrimaryFeature();
			}
		}
		appearance = appearance - item.getSecondaryFeature();
		luck = luck - item.getTernaryFeature();
	}

	public void setGear(Item[] gear)
	{
		this.gear = gear;
	}

	public Item[] getDeck()
	{
		return deck;
	}

	public void setDeck(Item[] deck)
	{
		this.deck = deck;
	}

	public Item[] getInventory()
	{
		return inventory;
	}

	public Item getNthItemFromInventory(int nth)
	{
		return inventory[nth];
	}

	public Item getNthCardFromDeck(int nth)
	{
		return deck[nth];
	}

	public void setInventory(Item[] inventory)
	{
		this.inventory = inventory;
	}

	public int addCardIntoDeck(Item card)
	{
		for (int i = 0; i < Constants.PLAYERS_DECK_SIZE; i++)
		{
			if (deck[i] == null)
			{
				deck[i] = card;
				return i;
			}
		}

		return -1;
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

	;

	public void setBackgroundColor(String backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}

	public String getBackgroundColor()
	{
		return backgroundColor;
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
		{
			this.actualPlanet = "Narr Sheyda";
			backgroundColor = "brown";
		} else
		{
			this.actualPlanet = "Kerusant";
			backgroundColor = "red";
		}
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

	public void useItem(Item item, int nthInvSlot)
	{
		inventory[nthInvSlot] = null;

		int addHealth = health + item.getPrimaryFeature();
		int addFullness = fullness + item.getSecondaryFeature();
		int addHydration = hydration + item.getTernaryFeature();

		health = addHealth > 100 ? 100 : addHealth;
		fullness = addFullness > 100 ? 100 : addFullness;
		hydration = addHydration > 100 ? 100 : addHydration;

		addOrRemoveWeight(item, false);
	}

	public void sellCard(int nthSlot)
	{
		addOrRemoveWeight(deck[nthSlot], false);
		addOrRemoveCredits(deck[nthSlot].getPrice(), true);
		deck[nthSlot] = null;
	}

	public int moveCardFromInventoryToDeck(int nthInvSlot)
	{
		int returnCode = addCardIntoDeck(getNthItemFromInventory(nthInvSlot));
		if (returnCode != -1)
			getInventory()[nthInvSlot] = null;
		return returnCode;
	}
}
