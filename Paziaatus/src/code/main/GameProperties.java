package main;

import beings.Player;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import residue.Item;
import residue.MissionsAndAssaults;
import residue.Shop;
import residue.TimeChecker;
import residue.Tools;

public class GameProperties
{
	private String	resolution;
	private String	newResolution;

	private TimeChecker timeChecker;

	private Pane	paneActive;
	private Pane	paneActual;

	private Player				player;
	private MissionsAndAssaults	missionsAndAssaults;

	private Shop	armor;
	private Shop	weapons;
	private Shop	jewelry;
	private Shop	cards;
	private Shop	food;
	private Shop	drinks;
	private Shop	medications;
	private Shop	tech;

	private Shop		actualShop;
	private Item		clickedItem;
	private ImageView	clickedImage;

	private boolean featuresOfGear;

	private PaziaatusController paziaatusController;

	public GameProperties(String resolution, Pane paneActive, Pane paneActual, Player player, PaziaatusController paziaatusController)
	{
		this.resolution = resolution;
		this.paneActive = paneActive;
		this.paneActual = paneActual;
		this.player = player;
		this.paziaatusController = paziaatusController;

		missionsAndAssaults = new MissionsAndAssaults();
		armor = new Shop(6, 8, 10);
		weapons = new Shop(4, 6, 8);
		jewelry = new Shop(2, 4, 6);
		cards = new Shop(6, 8, 10);
		food = new Shop(4, 6, 8);
		drinks = new Shop(4, 6, 8);
		medications = new Shop(2, 4, 6);
		tech = new Shop(0, 4, 6);

		featuresOfGear = true;

		timeChecker = new TimeChecker(this);
		Thread thread = new Thread(timeChecker);
		thread.start();
	}

	public Item getClickedItem()
	{
		return clickedItem;
	}

	public void setClickedItem(Item clickedItem)
	{
		this.clickedItem = clickedItem;
	}

	public ImageView getClickedImage()
	{
		return clickedImage;
	}

	public void setClickedImage(ImageView clickedImage)
	{
		this.clickedImage = clickedImage;
	}

	public void stopTimeChecker()
	{
		timeChecker.stop();
	}

	public synchronized void updateNecessitiesOfLife(boolean work)
	{
		int energy = player.getEnergy() - 2 - Tools.getRandomNumber(3);
		int fullness = player.getFullness() - 2 - Tools.getRandomNumber(3);
		int hydration = player.getHydration() - 2 - Tools.getRandomNumber(3);

		energy = energy < 0 ? 0 : energy;
		fullness = fullness < 0 ? 0 : fullness;
		hydration = hydration < 0 ? 0 : hydration;

		player.setEnergy(energy);
		player.setFullness(fullness);
		player.setHydration(hydration);

		int[] array = { energy, fullness, hydration };

		for (int value : array)
		{
			int health = player.getHealth();

			if (value == 0)
				health = player.getHealth() - 2 - Tools.getRandomNumber(4);
			else if (value <= 10)
				health = player.getHealth() - 1 - Tools.getRandomNumber(3);
			else if (value <= 20)
				health = player.getHealth() - 0 - Tools.getRandomNumber(2);

			health = health > 0 ? health : 0;
			player.setHealth(health);
		}

		paziaatusController.changeValuesOfNecessitiesOfLife();

		if (player.getHealth() <= 0)
			return; // TODO smrt
	}

	public Shop getActualShop()
	{
		return actualShop;
	}

	public void setActualShop(Shop actualShop)
	{
		this.actualShop = actualShop;
	}

	public boolean isFeaturesOfGear()
	{
		return featuresOfGear;
	}

	public void setFeaturesOfGear(boolean featuresOfGear)
	{
		this.featuresOfGear = featuresOfGear;
	}

	public MissionsAndAssaults getMissionsAndAssaults()
	{
		return missionsAndAssaults;
	}

	protected String getResolution()
	{
		return resolution;
	}

	protected void setResolution(String resolution)
	{
		this.resolution = resolution;
	}

	protected String getNewResolution()
	{
		return newResolution;
	}

	protected void setNewResolution(String newResolution)
	{
		this.newResolution = newResolution;
	}

	protected Pane getPaneActive()
	{
		return paneActive;
	}

	protected void setPaneActive(Pane paneActive)
	{
		this.paneActive = paneActive;
	}

	protected Pane getPaneActual()
	{
		return paneActual;
	}

	protected void setPaneActual(Pane paneActual)
	{
		this.paneActual = paneActual;
	}

	protected Player getPlayer()
	{
		return player;
	}

	protected void setPlayer(Player player)
	{
		this.player = player;
	}

	protected Shop getArmor()
	{
		return armor;
	}

	protected void setArmor(Shop armor)
	{
		this.armor = armor;
	}

	protected Shop getWeapons()
	{
		return weapons;
	}

	protected void setWeapons(Shop weapons)
	{
		this.weapons = weapons;
	}

	protected Shop getJewelry()
	{
		return jewelry;
	}

	protected void setJewelry(Shop jewelry)
	{
		this.jewelry = jewelry;
	}

	protected Shop getCards()
	{
		return cards;
	}

	protected void setCards(Shop cards)
	{
		this.cards = cards;
	}

	protected Shop getFood()
	{
		return food;
	}

	protected void setFood(Shop food)
	{
		this.food = food;
	}

	protected Shop getDrinks()
	{
		return drinks;
	}

	protected void setDrinks(Shop drinks)
	{
		this.drinks = drinks;
	}

	protected Shop getMedications()
	{
		return medications;
	}

	protected void setMedications(Shop medications)
	{
		this.medications = medications;
	}

	protected Shop getTech()
	{
		return tech;
	}

	protected void setTech(Shop tech)
	{
		this.tech = tech;
	}

}
