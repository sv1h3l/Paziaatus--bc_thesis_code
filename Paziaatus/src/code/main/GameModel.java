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
	private String dialogCaller;

	private int gameState;

	private String	resolution;
	private String	newResolution;

	private TimeChecker timeChecker;

	private GameView gameView;

	private GameSaverLoader gameSaverLoader;

	private Player				player;
	private WorksAndAssaults	missionsAndAssaults;

	private Shop	armor;
	private Shop	weapons;
	private Shop	jewelry;
	private Shop	cards;
	private Shop	grocery;
	private Shop	medications;
	private Shop	technique;

	private Shop	actualShop;
	private Item	clickedItem;

	private boolean featuresOfGear;

	private int gameMode;
	private int specialization;
	private int gameStateNewGame;

	private GameController paziaatusController;

	private Database database;

	public GameModel(GameController paziaatusController, String resolution)
	{
		this.paziaatusController = paziaatusController;
		this.newResolution = resolution;
		this.gameSaverLoader = new GameSaverLoader();

		gameView = new GameView(paziaatusController);

		missionsAndAssaults = new WorksAndAssaults();
		armor = new Shop("armor", 6, 8, 10);
		weapons = new Shop("weapons", 4, 6, 8);
		jewelry = new Shop("jewelry", 2, 4, 6);
		cards = new Shop("cards", 6, 8, 10);
		grocery = new Shop("grocery", 4, 6, 8);
		medications = new Shop("medications", 2, 4, 6);
		technique = new Shop("tech", 0, 4, 6);

		featuresOfGear = true;

		changeResolution(false);
	}

	protected void initialize()
	{
		database = new Database();

		timeChecker = new TimeChecker(this);
		Thread thread = new Thread(timeChecker);
		thread.start();

		database.createShopItems(getShops(),player.getActualPlanet());

		gameView.updateStats(player);
	}

	protected void newResolution(int nthResolution)
	{
		newResolution = Constants.RESOLUTIONS[nthResolution];
		gameView.setArrowPosition(nthResolution * 100);
	}

	protected void changeResolution(boolean saveResolution)
	{
		if (saveResolution)
			gameSaverLoader.saveGameProperties(0, newResolution);

		resolution = newResolution;
		gameView.changeResolution(resolution);
	}

	protected void setEffect(ImageView image, boolean glow, double glowValue)
	{
		gameView.setEffect(image, glow, glowValue);
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
		gameView.characterNodesVisibler(visible);
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
		Shop[] shops = { armor, weapons, /*technique,*/ jewelry, grocery, medications/*, cards*/ };
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
		return missionsAndAssaults;
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
				gameView.setArrowPosition(330);
				break;
			}
			case "1280x720":
			{
				gameView.setArrowPosition(410);
				break;
			}
			case "960x540":
			{
				gameView.setArrowPosition(491);
				break;
			}
			default:
				gameView.setArrowPosition(572);
		}
		newResolution = resolution;
		gameView.setFieldVisible("paneOptions", true);
	}

	protected void changeFeatures()
	{
		featuresOfGear = !featuresOfGear;

		// TODO přžepnutí vlastností

		gameView.changeFeatures(featuresOfGear);
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

		gameView.setShopsImages(actualShop);
		gameView.setShopsBanner(shopType);
	}

	protected void travellingClicks(String idOfSource)
	{
		gameView.travellingClicks(idOfSource);
	}

	public void gearSlotClick(int nthSlot, boolean primaryMouseButton)
	{
		Item item = player.getNthGear(nthSlot);

		if (primaryMouseButton)
		{
			showFeatures(item);
			gameView.gearSlotClick(nthSlot);

		} else
			takeOffGear(nthSlot, item);
	}

	private void takeOffGear(int nthGearSlot, Item item)
	{
		int nthInventorySlot = player.addItemIntoInventory(item);

		if (nthInventorySlot == -1)
			gameView.smallGeneralDialog("Není dostatek místa v inventáři.");
		else
		{
			player.getGear()[nthGearSlot] = null;
			gameView.takeOffGear(nthGearSlot, nthInventorySlot);
		}
	}

	private void showFeatures(Item item)
	{
		setClickedItem(item);

		String itemType = item.getItemType();
		String partOfPath;

		if (itemType.equals("laserový meč") || itemType.equals("laserová puška"))
			partOfPath = "attack.png";
		else if (itemType.equals("droid") || itemType.equals("speeder"))
			partOfPath = "speed.png";
		else if (itemType.equals("jídlo") || itemType.equals("pití") || itemType.equals("léčivo"))
			partOfPath = "consumer.png";
		else if (itemType.equals("nástroj"))
			partOfPath = "skill.png";
		else if (itemType.equals("karta"))
			partOfPath = "card.png";
		else if (itemType.equals("implantát") || itemType.equals("artefakt"))
			partOfPath = "function.png";
		else
			partOfPath = "defense.png";

		partOfPath = "features/features_" + partOfPath;
		String[] values = item.getContentOfFieldsLikeString();

		gameView.showFeatures(partOfPath, itemType, values);
	}

	protected void itemLeftClick(ImageView image, int nthSlot, boolean inventory)
	{
		gameView.swapEffectsOfImages(image);

		if (inventory)
			showFeatures(player.getNthItemFromInventory(nthSlot));
		else
			showFeatures(actualShop.getNthShopItem(nthSlot));

	}

	protected void shopSlotRightClick(ImageView image, int nthSlot) // shopSlotRightClick
	{
		Item item = null;

		item = actualShop.getNthShopItem(nthSlot);
		nthSlot = getPlayer().addItemIntoInventory(item);

		if (nthSlot == -1)
		{
			gameView.smallGeneralDialog("Není dostatek místa v inventáři.");
			return;
		}

		gameView.ShopSlotRightClick(image, nthSlot, item.getImg());
	}

	protected void invSlotRightClc(ImageView image, int nthSlot, int gearOrFuelRepairOrShop)
	{
		if (gearOrFuelRepairOrShop == 0)
		{
			changeGear(nthSlot);

		} else if (gearOrFuelRepairOrShop == 1)
		{

		} else
		{
			getPlayer().sellItem(nthSlot);
			//getPlayer().addOrRemoveCredits(item.getPrice());
			gameView.sellItem(nthSlot);
		}
	}

	private void changeGear(int nthInvSlot)
	{
		Item item = getPlayer().getNthItemFromInventory(nthInvSlot);
		int nthGearSlot = getNthGearSlot(item.getItemType());
		Item gear = getPlayer().getNthGear(nthGearSlot);

		getPlayer().changeGear(item, nthGearSlot, nthInvSlot);

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

		String specialization = "", gameMode = "";

		switch (this.gameMode)
		{
			case 1:
			{
				gameMode = "klasický";
			}
			case 2:
			{
				gameMode = "rychlý";
			}
			case 3:
			{
				gameMode = "realistický";
			}
		}

		switch (this.specialization)
		{
			case 1:
			{
				specialization = "šedý jedi";
			}
			case 2:
			{
				specialization = "lovec odměn";
			}
			case 3:
			{
				specialization = "civilista";
			}
		}


		Player player = new Player(100, 100, 100, 100, 8420, 0, 0, 0, 0, 0, "Tarrys", gameMode, specialization);

		Item belt = new Item("opasek", 7, -1, 0, 600, 11, 100, 0, "", "belts/9");
		Item boots = new Item("boty", 8, 0, -1, 650, 17, 100, 0, "", "boots/2");
		Item wear = new Item("oděv", 14, 0, 0, 700, 24, 100, 0, "", "wears/24");
		Item weapon;

		if (this.specialization == 1)
			weapon = new Item("laserový meč", 17, 0, 1, 750, 14, 100, 0, "", "weapons/9");
		else if (this.specialization == 2)
			weapon = new Item("laserová puška", 20, 1, 0, 750, 19, 100, 0, "", "weapons/25");
		else
			weapon = new Item("nástroj", 12, -1, -1, 750, 24, 100, 0, "", "tools/1");

		Item[] gear = player.getGear();
		gear[4] = weapon;
		gear[5] = wear;
		gear[10] = belt;
		gear[9] = boots;

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
				String info = gameSaverLoader.saveGameState(player, gameState);
				quit();
				gameSaverLoader.saveGameProperties(gameState, info);
				gameView.changeGameText(info, gameState);
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

		gameView.dialogVisibleFalse();
	}

	protected void setGameSetText(String info1, String info2, String info3)
	{
		gameView.setGameSetText(info1, info2, info3);
	}

	protected void travelOnNextPlanet(boolean left)
	{
		getPlayer().travelOnNextPlanet();
		gameView.travelOnNextPlanet(left);
	}

	protected void workDuration(String workType)
	{
		int duration = player.getWeight() / 4;

		gameView.workDuration(duration);
	}

	protected void workClick(String workType)
	{
		workDuration(workType);
		WorksAndAssaults worksAndAssaults = new WorksAndAssaults();
		int workCode = 0, credits = 0;

		switch (workType)
		{
			case "hunter":
			{
				worksAndAssaults.hunter(player);
				workCode = 10;
				credits = (5000 * player.planetMultiplier()) + Tools.getRandomNumber(1000);
				break;
			}
			case "worker":
			{
				workCode = 4;
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

		player.addOrRemoveCredits(credits);
		gameView.workClick(workType, workCode, credits, player.getHealth());
		gameView.updateStats(player);
	}

	public void newGame(int gameState)
	{
		gameStateNewGame = gameState;
		specialization = 1;
		gameMode = 1;
		gameView.newGame();
	}

	public void newGameChange(int id, boolean specialization)
	{
		if(specialization)
			this.specialization=id;
		else
			gameMode=id;
		gameView.newGameChange(id, specialization);
	}
}
