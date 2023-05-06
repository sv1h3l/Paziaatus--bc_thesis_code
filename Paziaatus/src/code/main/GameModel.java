package main;

import java.lang.reflect.Field;

import beings.Player;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import residue.Constants;
import residue.Database;
import residue.GameSaverLoader;
import residue.Item;
import residue.Shop;
import residue.TimeChecker;
import residue.Tools;
import residue.WorksAndAssaults;

public class GameModel
{
	private String actualLocation;

	private String dialogCaller;

	private int gameState;

	private String	resolution;
	private String	newResolution;

	private TimeChecker timeChecker;

	private GameView gameView;

	private GameSaverLoader gameSaverLoader;

	private Player				player;
	private WorksAndAssaults	worksAndAssaults;

	private Shop	armor;
	private Shop	weapons;
	private Shop	jewelry;
	private Shop	cards;
	private Shop	grocery;
	private Shop	medications;
	private Shop	technique;

	private Shop actualShop;

	private Item clickedItem;

	private boolean featuresOfGear;

	private String	gameMode;
	private String	specialization;
	private int		gameStateNewGame;

	private boolean	fuelClick;
	private Item	itemForRepairingOrRefilling;

	private int droidsWorkCode, droidsCredits;

	private Database database;

	public GameModel(GameController paziaatusController, String resolution)
	{
		this.newResolution = resolution;
		this.gameSaverLoader = new GameSaverLoader();

		gameView = new GameView(paziaatusController);

		worksAndAssaults = new WorksAndAssaults();
		armor = new Shop("armor", 6, 8, 10);
		weapons = new Shop("weapons", 2, 4, 6);
		jewelry = new Shop("jewelry", 2, 4, 6);
		cards = new Shop("cards", 4, 6, 8);
		grocery = new Shop("grocery", 4, 6, 8);
		medications = new Shop("medications", 2, 4, 6);
		technique = new Shop("technique", 0, 2, 4);

		featuresOfGear = true;

		changeResolution(false);
	}

	protected void initialize()
	{
		database = new Database(player.getSpecialization());

		timeChecker = new TimeChecker(this, player.getGameMode());
		Thread thread = new Thread(timeChecker);
		thread.start();

		database.createShopItems(getShops(), player.getActualPlanet());

		if (featuresOfGear)
			gameView.changeFeatures(true, player);

		gameView.initialize();

		gameView.setCantineCardsImages(cards, player.getActualPlanet());
		gameView.setBet(player.getPaziakBet());
		gameView.updateUserInterfaceForSpecificPlanet(player.getActualPlanet());
		gameView.updateStats(player);
		gameView.colorClick(player.getBackgroundColor());
	}

	protected void newResolution(int nthResolution)
	{
		newResolution = Constants.RESOLUTIONS[nthResolution];
		gameView.setArrowPosition(nthResolution);
	}

	protected void changeResolution(boolean saveResolution)
	{
		if (saveResolution)
			gameSaverLoader.saveGameProperties(0, newResolution);

		resolution = newResolution;
		gameView.changeResolution(resolution);
	}

	protected void setEffect(ImageView image, boolean glow)
	{
		gameView.setEffect(image, glow);
	}

	protected void yesOrNoDialog(String idFromSource)
	{
		dialogCaller = idFromSource;
		String dialogText = "";

		switch (idFromSource)
		{
			case "exit":
			{
				dialogText = "Opravdu chcete odejít do hlaního menu?\n\nUložil jste si pozici?";
				break;
			}
			case "trash1":
			case "trash2":
			case "trash3":
				dialogText = "Opravdu chcete smazat herní pozici číslo " + Tools.getNumberFromString(idFromSource, "trash") + "?";
		}
		gameView.yesOrNoDialog(dialogText);
	}

	protected void addNodesToKeeper(Node... nodes)
	{
		gameView.keeperNodesVisibler(false);
		gameView.paneActualNodesKeeper.clear();
		for (Node node : nodes)
			gameView.paneActualNodesKeeper.add(node);
	}

	protected void keeperNodesVisibler(boolean visible)
	{
		gameView.keeperNodesVisibler(visible);
	}

	protected void characterNodesVisibler(boolean visible)
	{
		gameView.characterNodesVisibler(visible, featuresOfGear);
	}

	protected void quit()
	{
		database.stop();
		timeChecker.stop();
		gameView.quit();
	}

	protected Database getDatabase()
	{
		return database;
	}

	protected Shop[] getShops()
	{
		Shop[] shops = { armor, weapons, technique, jewelry, grocery, medications, cards };
		return shops;
	}

	public TimeChecker getTimeChecker()
	{
		return timeChecker;
	}

	public int getGameState()
	{
		return gameState;
	}

	public void setGameState(int gameState)
	{
		this.gameState = gameState;
	}

	public Item getClickedItem()
	{
		return clickedItem;
	}

	public void setClickedItem(Item clickedItem)
	{
		this.clickedItem = clickedItem;
	}

	public void stopTimeChecker()
	{
		timeChecker.stop();
	}

	public synchronized void updateNecessitiesOfLife(boolean work)
	{
		int energy = -2 - Tools.getRandomNumber(3);
		int fullness = -2 - Tools.getRandomNumber(3);
		int hydration = -2 - Tools.getRandomNumber(3);

		if (work)
		{
			energy = energy * 3;
			fullness = fullness * 3;
			hydration = hydration * 3;
		}

		energy = energy + player.getEnergy();
		fullness = fullness + player.getFullness();
		hydration = hydration + player.getHydration();

		energy = energy < 0 ? 0 : energy;
		fullness = fullness < 0 ? 0 : fullness;
		hydration = hydration < 0 ? 0 : hydration;

		player.setEnergy(energy);
		player.setFullness(fullness);
		player.setHydration(hydration);

		int[] array = { energy, fullness, hydration };

		boolean allert = false;

		for (int value : array)
		{
			int health = player.getHealth();

			if (value == 0)
			{
				health = player.getHealth() - 2 - Tools.getRandomNumber(4);
				allert = true;
			} else if (value <= 10)
			{
				health = player.getHealth() - 1 - Tools.getRandomNumber(3);
				allert = true;
			} else if (value <= 20)
			{
				health = player.getHealth() - 0 - Tools.getRandomNumber(2);
				allert = true;
			}

			health = health > 0 ? health : 0;
			player.setHealth(health);
		}

		gameView.updateStats(player);

		if (player.getHealth() == 0 && !work)
		{
			quit();
			gameView.bigGeneralDialog(
					"Tvé zdraví kleslo na nulu, a proto jsi zemřel.\n\nPro pokračování ve hře je nutné načíst poslední uloženou pozici, nebo si založit novou hru.");
			deathInRealisticMode(player.getGameMode());
		} else if (player.getHealth() <= 10 && !work && allert)
		{
			gameView.smallGeneralDialog("Tvé zdraví je téměř na nule.");
		}
	}

	protected void setFieldVisible(String nameOfField, boolean visible)
	{
		gameView.setFieldVisible(nameOfField, visible);
	}

	public Shop getActualShop()
	{
		return actualShop;
	}

	public boolean isFeaturesOfGear()
	{
		return featuresOfGear;
	}

	public void setFeaturesOfGear(boolean featuresOfGear)
	{
		this.featuresOfGear = featuresOfGear;
	}

	public WorksAndAssaults getMissionsAndAssaults()
	{
		return worksAndAssaults;
	}

	protected Player getPlayer()
	{
		return player;
	}

	protected void setPlayer(Player player)
	{
		this.player = player;
	}

	protected void optionsClick()
	{
		switch (resolution)
		{
			case "1600x900":
			{
				gameView.setArrowPosition(0);
				break;
			}
			case "1440x810":
			{
				gameView.setArrowPosition(1);
				break;
			}
			case "1280x720":
			{
				gameView.setArrowPosition(2);
				break;
			}
			case "1120x630":
			{
				gameView.setArrowPosition(3);
				break;
			}
			case "960x540":
			{
				gameView.setArrowPosition(4);
				break;
			}
			case "800x450":
			{
				gameView.setArrowPosition(5);
				break;
			}
			default:
				gameView.setArrowPosition(6);
		}
		newResolution = resolution;
		gameView.setFieldVisible("paneOptions", true);
	}

	protected void changeFeatures()
	{
		featuresOfGear = !featuresOfGear;

		gameView.changeFeatures(featuresOfGear, player);
	}

	protected void setShop(String shopType)
	{
		try
		{
			Field field = this.getClass().getDeclaredField(shopType);
			field.setAccessible(true);
			actualShop = (Shop) field.get(this);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{
			e.printStackTrace();
			return;
		}

		gameView.setShopsImages(actualShop, getPlayer().getActualPlanet());

		int numberOfShopSlots;
		if (getPlayer().getActualPlanet().equals("Tarrys"))
			numberOfShopSlots = actualShop.getCountOfShopSlotsFirstPlanet();
		else if (getPlayer().getActualPlanet().equals("Narr Sheyda"))
			numberOfShopSlots = actualShop.getCountOfShopSlotsSecondPlanet();
		else
			numberOfShopSlots = actualShop.getCountOfShopSlotsThirdPlanet();

		gameView.setShopsBannerAndSlots(numberOfShopSlots, shopType);
	}

	protected void travellingClicks(String idOfSource)
	{
		if (player.getActualPlanet().equals("Narr Sheyda") && Tools.getRandomNumber(8) == 0)
			assault(false);

		itemForRepairingOrRefilling = null;
		gameView.clearFuelOrRepairSlotImage();
		gameView.travellingClicks(idOfSource, durationOfTravelling());
	}

	private int durationOfTravelling()
	{
		int travellingSpeed = player.getGear()[8] == null ? 50 : 70;
		int duration = player.getWeight() / travellingSpeed;
		duration = duration < 2 ? 2 : duration;
		duration = player.getGameMode().equals("rychlý") ? duration / 2 : duration;
		return duration;
	}

	private int durationOfWorking(boolean worker)
	{
		int duration = (player.getWeight() / 6);

		if (player.getGear()[8] != null && player.getGear()[8].getSecondaryFeature() > 0)
		{
			int speed;
			Item speeder = player.getGear()[8];
			speed = 5 / (150 / speeder.getPrimaryFeature());
			int newFuelValue = speeder.getSecondaryFeature() - 10 - Tools.getRandomNumber(15);
			speeder.setSecondaryFeature(newFuelValue < 0 ? 0 : newFuelValue);
			duration = duration / speed;
		}

		if (worker && player.getGear()[4].getItemType().equals("nástroj"))
			duration = duration - (player.getGear()[4].getPrimaryFeature() / 2);

		duration = duration < 10 ? 10 : duration;
		duration = player.getGameMode().equals("rychlý") ? duration / 2 : duration;
		return duration;
	}

	public void gearSlotClick(ImageView image, int nthSlot, boolean primaryMouseButton)
	{
		Item item = player.getNthGear(nthSlot);

		if (primaryMouseButton)
		{
			gameView.swapEffectsOfImages(image);
			showFeatures(item);
			gameView.gearSlotClick(nthSlot);

		} else
		{
			if (gameView.droidIsWorking() && nthSlot == 11)
			{
				gameView.smallGeneralDialog("Nelze vyměnit droida, když je v práci.");
				return;
			}

			takeOffGear(nthSlot, item);
		}
	}

	private void takeOffGear(int nthGearSlot, Item item)
	{
		int nthInventorySlot = player.addItemIntoInventory(item);

		if (nthInventorySlot == -1)
			gameView.smallGeneralDialog("Není dostatek místa v inventáři.");
		else
		{
			player.getGear()[nthGearSlot] = null;
			player.removeFeatures(item);

			if (this.clickedItem != null && this.clickedItem.equals(item))
				gameView.clearFeatures();

			gameView.takeOffGear(nthGearSlot, nthInventorySlot);
			if (featuresOfGear)
				gameView.changeFeatures(true, player);
		}
	}

	private void showFeatures(Item item)
	{
		setClickedItem(item);

		String itemType = item.getItemType();
		String partOfPath;

		if (itemType.equals("laserový meč") || itemType.equals("laserová puška"))
			partOfPath = "attack";
		else if (itemType.equals("droid") || itemType.equals("speeder"))
			partOfPath = "speed";
		else if (itemType.equals("jídlo") || itemType.equals("pití") || itemType.equals("léčivo"))
			partOfPath = "consumer";
		else if (itemType.equals("nástroj"))
			partOfPath = "skill";
		else if (itemType.equals("karta"))
			partOfPath = "card";
		else if (itemType.equals("implantát") || itemType.equals("artefakt"))
			partOfPath = "function";
		else
			partOfPath = "defense";

		partOfPath = "features/features_" + partOfPath;
		String[] values = item.getContentOfFieldsLikeString();

		featuresOfGear = false;
		gameView.changeFeatures(false, null);

		gameView.showFeatures(partOfPath, itemType, values);
	}

	protected void itemLeftClick(ImageView image, int nthSlot, int paneCode)
	{
		gameView.swapEffectsOfImages(image);

		if (paneCode == 0)
			showFeatures(player.getNthItemFromInventory(nthSlot));
		else if (paneCode == 1)
			showFeatures(actualShop.getNthShopItem(nthSlot));
		else if (paneCode == 2)
			showFeatures(cards.getNthShopItem(nthSlot));
		else
			showFeatures(player.getNthCardFromDeck(nthSlot));
	}

	protected void shopSlotRightClick(ImageView image, int nthSlot)
	{
		Item item = null;

		item = actualShop.getNthShopItem(nthSlot);

		if (!player.checkCreditsValue(item.getPrice()))
		{
			gameView.notEnoughMoney();
			return;
		}

		nthSlot = getPlayer().addItemIntoInventory(item);

		if (nthSlot == -1)
		{
			gameView.smallGeneralDialog("Není dostatek místa v inventáři.");
			return;
		}

		if (this.clickedItem != null && this.clickedItem.equals(getPlayer().getInventory()[nthSlot]))
			gameView.clearFeatures();

		player.addOrRemoveWeight(item, true);
		player.addOrRemoveCredits(item.getPrice(), false);
		//gameView.changeFeatures(true, player);

		item.setPrice(item.getPrice() / 2);

		gameView.ShopSlotRightClick(image, nthSlot, item.getImg(), player, 0);
	}

	protected void invSlotRightClc(ImageView image, int nthSlot, int gearOrFuelRepairOrShop)
	{
		if (gearOrFuelRepairOrShop == 0)
		{
			gameView.addListener(image);
			changeGear(nthSlot);

		} else if (gearOrFuelRepairOrShop == 1)
		{
			if (fuelClick && player.getInventory()[nthSlot].getItemType().equals("vznášedlo"))
			{
				itemForRepairingOrRefilling = player.getInventory()[nthSlot];
				gameView.setFuelOrRepairSlotImage(itemForRepairingOrRefilling.getImg());
			} else if (fuelClick)
			{
				gameView.smallGeneralDialog("Tento předmět nelze natankovat.");
				return;
			} else if (!fuelClick && player.getInventory()[nthSlot].getMaxRepairPossibleUse() > 0
					&& typeCheckerForRepairing(player.getInventory()[nthSlot].getItemType()))
			{
				itemForRepairingOrRefilling = player.getInventory()[nthSlot];
				gameView.setFuelOrRepairSlotImage(itemForRepairingOrRefilling.getImg());
			} else
			{
				gameView.smallGeneralDialog("Tento předmět nelze opravit.");
				return;
			}

		} else
		{
			if (this.clickedItem != null && this.clickedItem.equals(getPlayer().getInventory()[nthSlot]))
			{
				gameView.clearFeatures();
				gameView.addListener(image);
			}

			getPlayer().sellItem(nthSlot);
			//gameView.changeFeatures(true, player);
			gameView.sellItem(nthSlot, player);
		}
	}

	private boolean typeCheckerForRepairing(String itemType)
	{
		switch (itemType)
		{
			case "jídlo":
			case "pití":
			case "léčivo":
			case "artefakt":
			case "implantát":
				return false;
		}
		return true;
	}

	private void changeGear(int nthInvSlot)
	{
		Item item = getPlayer().getNthItemFromInventory(nthInvSlot);

		if (item.getItemType() == "pití" || item.getItemType() == "jídlo" || item.getItemType() == "léčivo")
		{
			player.useItem(item, nthInvSlot);

			if (this.clickedItem != null && this.clickedItem.equals(item))
				gameView.clearFeatures();

			if (featuresOfGear)
				gameView.changeFeatures(true, player);

			gameView.useItem(nthInvSlot);
			gameView.updateStats(player);
			return;
		} else if (item.getItemType() == "karta")
		{
			int nthDeckSlot = player.moveCardFromInventoryToDeck(nthInvSlot);
			if (nthDeckSlot == -1)
			{
				gameView.smallGeneralDialog("Balíček karet je plný.");
				return;
			}

			if (this.clickedItem != null && this.clickedItem.equals(item))
				gameView.clearFeatures();

			gameView.setNthDeckImage(nthDeckSlot + 1, nthInvSlot + 1, item.getImg());
			return;
		}

		int nthGearSlot = getNthGearSlot(item.getItemType());
		Item gear = getPlayer().getNthGear(nthGearSlot);

		if (this.clickedItem != null && this.clickedItem.equals(item))
			gameView.clearFeatures();

		getPlayer().changeGear(item, nthGearSlot, nthInvSlot);
		if (featuresOfGear)
			gameView.changeFeatures(true, player);

		gameView.changeGear(nthInvSlot, nthGearSlot, gear);
	}

	private int getNthGearSlot(String itemType)
	{
		switch (itemType)
		{
			case "implantát":
				return 0;
			case "helma":
				return 1;
			case "náhrdelník":
				return 2;
			case "artefakt":
				return 3;
			case "oděv":
				return 5;
			case "rukavice":
				return 6;
			case "prsten":
				return 7;
			case "vznášedlo":
				return 8;
			case "boty":
				return 9;
			case "opasek":
				return 10;
			case "droid":
				return 11;
			default:
				return 4;
		}
	}

	protected void loadGameState(String id)
	{
		gameView.setFieldVisible("paneMainMenu", false);

		int nthGameState;
		if (id.equals("gameState1"))
			nthGameState = 1;
		else if (id.equals("gameState2"))
			nthGameState = 2;
		else
			nthGameState = 3;

		setGameState(nthGameState);

		this.player = gameSaverLoader.loadGameState(nthGameState);

		initialize();

		gameView.setImagesOfLoadedItems(player);
	}

	protected void createNewGame()
	{
		gameView.setFieldVisible("paneMainMenu", false);
		gameView.setFieldVisible("paneNewGame", false);

		setGameState(gameStateNewGame);

		Player player = new Player(100, 100, 100, 100, 8420999, 0, 0, 0, 0, 0, "Tarrys", this.gameMode, this.specialization, 1500);

		Item belt = new Item("opasek", 7, -1, 0, 600, 11, 100, 0, "", "belts/11");
		Item boots = new Item("boty", 8, 0, -1, 650, 17, 100, 0, "", "boots/2");
		Item wear = new Item("oděv", 14, 0, 0, 700, 24, 100, 0, "", "wears/24");
		Item weapon;

		Item med = new Item("léčivo", 18, -1, -2, 300, 2, Constants.NO_VALUE, Constants.NO_VALUE, "", "medications/9");
		Item food = new Item("jídlo", 4, 13, 1, 265, 3, Constants.NO_VALUE, Constants.NO_VALUE, "", "food/14");
		Item drink = new Item("pití", 3, 1, 12, 250, 2, Constants.NO_VALUE, Constants.NO_VALUE, "", "drinks/6");

		Item card1 = new Item("karta", 1, Constants.NO_VALUE, 1, 500, 1, Constants.NO_VALUE, Constants.NO_VALUE,
				"Zvýší celkovou hodnotu vyložených karet o 1.", "cards/i1");
		Item card2 = new Item("karta", 2, Constants.NO_VALUE, 1, 500, 1, Constants.NO_VALUE, Constants.NO_VALUE,
				"Zvýší celkovou hodnotu vyložených karet o 2.", "cards/i2");
		Item card3 = new Item("karta", 2, Constants.NO_VALUE, 1, 500, 1, Constants.NO_VALUE, Constants.NO_VALUE,
				"Zvýší celkovou hodnotu vyložených karet o 2.", "cards/i2");
		Item card4 = new Item("karta", 3, Constants.NO_VALUE, 1, 500, 1, Constants.NO_VALUE, Constants.NO_VALUE,
				"Zvýší celkovou hodnotu vyložených karet o 3.", "cards/i3");
		Item card5 = new Item("karta", 3, Constants.NO_VALUE, 1, 500, 1, Constants.NO_VALUE, Constants.NO_VALUE,
				"Zvýší celkovou hodnotu vyložených karet o 3.", "cards/i3");
		Item card6 = new Item("karta", 4, Constants.NO_VALUE, 1, 500, 1, Constants.NO_VALUE, Constants.NO_VALUE,
				"Zvýší celkovou hodnotu vyložených karet o 4.", "cards/i4");
		Item card7 = new Item("karta", 4, Constants.NO_VALUE, 1, 500, 1, Constants.NO_VALUE, Constants.NO_VALUE,
				"Zvýší celkovou hodnotu vyložených karet o 4.", "cards/i4");
		Item card8 = new Item("karta", 5, Constants.NO_VALUE, 1, 500, 1, Constants.NO_VALUE, Constants.NO_VALUE,
				"Zvýší celkovou hodnotu vyložených karet o 5.", "cards/i5");
		Item card9 = new Item("karta", 5, Constants.NO_VALUE, 1, 500, 1, Constants.NO_VALUE, Constants.NO_VALUE,
				"Zvýší celkovou hodnotu vyložených karet o 5.", "cards/i5");
		Item card10 = new Item("karta", 6, Constants.NO_VALUE, 1, 500, 1, Constants.NO_VALUE, Constants.NO_VALUE,
				"Zvýší celkovou hodnotu vyložených karet o 6.", "cards/i6");

		if (this.specialization.equals("šedý válečník"))
			weapon = new Item("světelný meč", 17, 0, 1, 750, 14, 100, 0, "", "weapons/9");
		else if (this.specialization.equals("lovec odměn"))
			weapon = new Item("laserová puška", 20, 1, 0, 750, 19, 100, 0, "", "weapons/25");
		else
			weapon = new Item("nástroj", 12, -1, -1, 750, 24, 100, 0, "", "tools/1");

		Item[] gear = player.getGear();
		gear[4] = weapon;
		gear[5] = wear;
		gear[10] = belt;
		gear[9] = boots;

		gameView.setNewGameListeneres(gear);

		Item[] inventory = player.getInventory();
		inventory[0] = med;
		inventory[1] = food;
		inventory[2] = drink;

		Item[] deck = player.getDeck();
		deck[0] = card1;
		deck[1] = card2;
		deck[2] = card3;
		deck[3] = card4;
		deck[4] = card5;
		deck[5] = card6;
		deck[6] = card7;
		deck[7] = card8;
		deck[8] = card9;
		deck[9] = card10;

		player.addFeatures(weapon);
		player.addFeatures(wear);
		player.addFeatures(belt);
		player.addFeatures(boots);

		player.addOrRemoveWeight(weapon, true);
		player.addOrRemoveWeight(wear, true);
		player.addOrRemoveWeight(belt, true);
		player.addOrRemoveWeight(boots, true);

		player.addOrRemoveWeight(med, true);
		player.addOrRemoveWeight(food, true);
		player.addOrRemoveWeight(drink, true);

		player.addOrRemoveWeight(card1, true);
		player.addOrRemoveWeight(card2, true);
		player.addOrRemoveWeight(card3, true);
		player.addOrRemoveWeight(card4, true);
		player.addOrRemoveWeight(card5, true);
		player.addOrRemoveWeight(card6, true);
		player.addOrRemoveWeight(card7, true);
		player.addOrRemoveWeight(card8, true);
		player.addOrRemoveWeight(card9, true);
		player.addOrRemoveWeight(card10, true);

		this.player = player;

		initialize();

		gameView.setImagesOfLoadedItems(player);
	}

	public void dialogNoOrOkCick()
	{
		gameView.dialogVisibleFalse();
	}

	public void dialogYesClick()
	{
		switch (dialogCaller)
		{
			case "exit":
			{
				quit();
				break;
			}
			case "trash1":
			case "trash2":
			case "trash3":
			{
				int id = Tools.getNumberFromString(dialogCaller, "trash");
				gameSaverLoader.removeGameState(id);
				gameView.changeGameText("Nová hra", id);
			}
		}

		gameView.trashDisablerAndEnabler();
		gameView.dialogVisibleFalse();
	}

	protected void setGameSetText(String info1, String info2, String info3)
	{
		gameView.setGameSetText(info1, info2, info3);
	}

	protected void travelOnNextPlanet(boolean left)
	{
		if (left && (player.getCredits() - 84500 < 0))
		{
			gameView.notEnoughMoney();
			return;
		} else if (!left && (player.getCredits() - 297000 < 0))
		{
			gameView.notEnoughMoney();
			return;
		}

		getPlayer().travelOnNextPlanet();
		getDatabase().createShopItems(getShops(), player.getActualPlanet());

		gameView.updateUserInterfaceForSpecificPlanet(getPlayer().getActualPlanet());
		gameView.setSleepAndTravelTimeline(left ? 1 : 2, player.getGameMode());
	}

	protected void workClick(String workType)
	{
		Item droid = player.getGear()[11];
		if (droid != null)
		{
			droidWork(droid, workType);
			return;
		}

		WorksAndAssaults worksAndAssaults = new WorksAndAssaults();
		int workCode = 0, credits = 0;
		boolean worker = false;

		switch (workType)
		{
			case "hunter":
			{
				workCode = worksAndAssaults.hunter(player);
				credits = (5000 * player.planetMultiplier()) + Tools.getRandomNumber(1000);
				break;
			}
			case "worker":
			{
				worker = true;
				workCode = 4;
				worksAndAssaults.TearGear(player.getGear(), false);
				credits = (1000 * player.planetMultiplier()) + Tools.getRandomNumber(1000);
				break;
			}
			case "archeologist":
			{
				workCode = worksAndAssaults.archeologistTechnician(player, true, database);
				credits = (2500 * player.planetMultiplier()) + Tools.getRandomNumber(1000);
				break;
			}
			case "technician":
				workCode = worksAndAssaults.archeologistTechnician(player, false, database);
				credits = (2500 * player.planetMultiplier()) + Tools.getRandomNumber(1000);
		}

		if (player.getHealth() < 1)
		{
			quit();
			deathInRealisticMode(player.getGameMode());
		}

		player.addOrRemoveCredits(credits, true);
		gameView.workClick(workType, workCode, credits, player.getHealth(), durationOfWorking(worker));
		gameView.updateStats(player);
		gameView.checkDestroyedGear(player.getGear());

		updateNecessitiesOfLife(true);
	}

	private void assault(boolean sleep)
	{
		worksAndAssaults.calculateFight(player);

		if (player.getHealth() < 1)
		{
			quit();
			deathInRealisticMode(player.getGameMode());
		}

		gameView.assault(sleep, player.getHealth());
		gameView.updateStats(player);
		gameView.checkDestroyedGear(player.getGear());

	}

	private void deathInRealisticMode(String modeType)
	{
		if (modeType.equals("realistický"))
		{
			gameSaverLoader.removeGameState(gameState);
			gameView.changeGameText("Nová hra", gameState);
			gameView.trashDisablerAndEnabler();
		}
	}

	private void droidWork(Item droid, String workType)
	{
		gameView.paneWorkDisable(true);
		WorksAndAssaults worksAndAssaults = new WorksAndAssaults();

		switch (workType)
		{
			case "hunter":
			{
				droidsWorkCode = worksAndAssaults.hunterDroid(droid);
				droidsCredits = (5000 * player.planetMultiplier()) + Tools.getRandomNumber(1000);
				break;
			}
			case "worker":
			{
				droidsWorkCode = 1;
				worksAndAssaults.TearGear(player.getGear(), false);
				droidsCredits = (1000 * player.planetMultiplier()) + Tools.getRandomNumber(1000);
				break;
			}
			case "archeologist":
			case "technician":
			{
				droidsWorkCode = worksAndAssaults.archeologistTechnicianDroid(droid);
				droidsCredits = (2500 * player.planetMultiplier()) + Tools.getRandomNumber(1000);
			}
		}

		gameView.setDroidIndicator(droid.getPrimaryFeature(), player, droidsWorkCode, droidsCredits);
	}

	public void newGame(int gameState)
	{
		gameStateNewGame = gameState;
		specialization = "šedý válečník";
		gameMode = "klasický";
		gameView.newGame();
	}

	public void newGameChange(String name, boolean specialization)
	{
		if (specialization)
			this.specialization = name;
		else
			gameMode = name;
		gameView.newGameChange(name, specialization);
	}

	protected void colorClick(String idFromSource)
	{
		player.setBackgroundColor(idFromSource);
		gameView.colorClick(idFromSource);
	}

	public void sleepClick(boolean left)
	{
		int price;
		boolean save = true;

		if (left)
			price = 1950;
		else
			price = 4120;

		switch (player.getActualPlanet())
		{
			case "Narr Sheyda":
			{
				price = price * 2;
				break;
			}
			case "Kerusant":
				price = price * 3;
		}

		if (!player.checkCreditsValue(price))
		{
			gameView.notEnoughMoney();
			return;
		}

		if (left)
		{
			player.setEnergy((player.getEnergy() + 50) > 100 ? 100 : player.getEnergy() + 50);
			if (Tools.getRandomNumber(5) == 0)
				assault(true);
			if(player.getHealth() < 1)
				save = false;
		} else
		{
			player.setEnergy((player.getEnergy() + 75) > 100 ? 100 : player.getEnergy() + 75);
			save = true;
		}

		player.addOrRemoveCredits(price, false);
		gameView.updateStats(player);

		if (save)
		{
			String info = gameSaverLoader.saveGameState(player, gameState);
			gameSaverLoader.saveGameProperties(gameState, info);
			gameView.changeGameText(info, gameState);
			getDatabase().createShopItems(getShops(), player.getActualPlanet());
			gameView.setSleepAndTravelTimeline(0, player.getGameMode()); //TODO
			gameView.setCantineCardsImages(cards, player.getActualPlanet());
		} else
		{
			quit();
			deathInRealisticMode(player.getGameMode());
		}

	}

	public void fuelOrRepair(boolean fuelClick)
	{
		this.fuelClick = fuelClick;
		gameView.setFuelVisible(fuelClick);
	}

	public void fuelOrRepairClick()
	{
		if (fuelClick && itemForRepairingOrRefilling == null)
			gameView.smallGeneralDialog("Nevybral jsi předmět k natankování.");
		else if (!fuelClick && itemForRepairingOrRefilling == null)
			gameView.smallGeneralDialog("Nevybral jsi předmět k opravení.");
		else if (fuelClick)
		{
			if (itemForRepairingOrRefilling.getSecondaryFeature() == 100)
			{
				gameView.smallGeneralDialog("Vznášedlo má plnou nádrž.");
				clearFuelOrRepairSlot();
				return;
			}

			int value = itemForRepairingOrRefilling.getSecondaryFeature() + 20 + Tools.getRandomNumber(10);
			itemForRepairingOrRefilling.setSecondaryFeature(value > 100 ? 100 : value);
			player.setCredits(-750 * player.planetMultiplier());
		} else
		{
			if (itemForRepairingOrRefilling.getWearAndTear() == 100)
			{
				gameView.smallGeneralDialog("Předmět není poškozený.");
				clearFuelOrRepairSlot();
				return;
			}
			if (itemForRepairingOrRefilling.getMaxRepairPossibleUse() < 1)
			{
				gameView.smallGeneralDialog("Předmět již nelze opravit.");
				clearFuelOrRepairSlot();
				return;
			}
			int value = itemForRepairingOrRefilling.getWearAndTear() + 15 + Tools.getRandomNumber(10);
			itemForRepairingOrRefilling.setWearAndTear(value > 100 ? 100 : value);
			player.setCredits(-650 * player.planetMultiplier());
			itemForRepairingOrRefilling.setMaxRepairPossibleUse(itemForRepairingOrRefilling.getMaxRepairPossibleUse() - 1);
		}
		return;
	}

	public void clearFuelOrRepairSlot()
	{
		itemForRepairingOrRefilling = null;
		gameView.clearFuelOrRepairSlotImage();

	}

	public void CantineClicks(String idFromSource)
	{
		int bet = player.getPaziakBet();
		boolean max = false;
		switch (idFromSource)
		{
			case "up1":
			{
				bet = bet + 100;
				break;
			}
			case "up2":
			{
				bet = bet + 1000;
				break;
			}
			case "up3":
			{
				max = true;
				break;
			}
			case "down1":
			{
				bet = bet - 100;
				break;
			}
			case "down2":
			{
				bet = bet - 1000;
				break;
			}
			case "down3":
			{
				bet = 400;
				break;
			}
			default:
			{
				if (player.deckChecker())
				{

				} else
					gameView.smallGeneralDialog("Abys mohl hrát karetní hru Paziak, tak tvůj balíček musí obsahovat 10 karet.");
				return;
			}
		}
		if (bet < 400)
			return;

		int maxPossibleBet = 2500;

		switch (idFromSource)
		{
			case "Kerusant":
				maxPossibleBet = maxPossibleBet + 2500;
			case "Narr Sheyda":
				maxPossibleBet = maxPossibleBet + 2500;
			default:
				maxPossibleBet = maxPossibleBet + 2500;
		}

		maxPossibleBet = maxPossibleBet + (player.getAppearance() * 200);

		if (max)
			player.setPaziakBet(maxPossibleBet);
		else
			player.setPaziakBet(bet > maxPossibleBet ? maxPossibleBet : bet);

		gameView.setBet(player.getPaziakBet());
	}

	public void cantineShopRightClick(ImageView image, int nthSlot)
	{
		Item card = null;

		card = cards.getNthShopItem(nthSlot);

		if (!player.checkCreditsValue(card.getPrice()))
		{
			gameView.notEnoughMoney();
			return;
		}

		nthSlot = getPlayer().addCardIntoDeck(card);

		if (nthSlot == -1)
		{
			gameView.smallGeneralDialog("Není dostatek místa v balíčku.");
			return;
		}

		if (this.clickedItem != null && this.clickedItem.equals(getPlayer().getDeck()[nthSlot]))
			gameView.clearFeatures();

		player.addOrRemoveWeight(card, true);
		player.addOrRemoveCredits(card.getPrice(), false);
		//gameView.changeFeatures(true, player);

		card.setPrice(card.getPrice() / 2);

		gameView.ShopSlotRightClick(image, nthSlot, card.getImg(), player, 2);
	}

	public void deckSlotRightClick(ImageView image, int nthSlot, boolean cantine)
	{
		if (cantine)
		{
			if (this.clickedItem != null && this.clickedItem.equals(getPlayer().getDeck()[nthSlot]))
				gameView.clearFeatures();

			gameView.addListener(image);
			player.sellCard(nthSlot);
			gameView.updateStats(player);
		} else
		{
			if (this.clickedItem != null && this.clickedItem.equals(getPlayer().getDeck()[nthSlot]))
				gameView.clearFeatures();

			int nthInventorySlot = getPlayer().addItemIntoInventory(player.getNthCardFromDeck(nthSlot));

			if (nthInventorySlot == -1)
			{
				gameView.smallGeneralDialog("Není dostatek místa v inventáři.");
				return;
			}

			gameView.setImageOfNthSlot(nthInventorySlot + 1, getPlayer().getDeck()[nthSlot].getImg(), 0);
			player.getDeck()[nthSlot] = null;
		}
		gameView.removeCardImage(image);
	}
}
