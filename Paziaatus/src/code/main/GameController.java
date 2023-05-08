package main;

import java.lang.reflect.Field;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import residue.GameSaverLoader;
import residue.Tools;

public class GameController
{
	protected Stage stage;

	@FXML protected Pane paneForScaling;

	@FXML protected Pane paneMain;

	@FXML protected Group blurGroup;

	@FXML protected Pane paneMainMenu;

	@FXML protected Text	informationTextOfGameState1;
	@FXML protected Text	informationTextOfGameState2;
	@FXML protected Text	informationTextOfGameState3;

	@FXML protected ImageView	trashCan1;
	@FXML protected ImageView	trashCan2;
	@FXML protected ImageView	trashCan3;

	@FXML protected ImageView	optionsButton;
	@FXML protected ImageView	quitButton;

	@FXML protected Pane		paneOptions;
	@FXML protected ImageView	resolutionPointer;

	@FXML protected Pane	paneHowToFeatures;
	@FXML protected Pane	paneHowToPlay;

	@FXML protected Pane paneNewGame;

	@FXML protected ImageView	characterSpecialization1;
	@FXML protected ImageView	characterSpecialization2;
	@FXML protected ImageView	characterSpecialization3;
	@FXML protected ImageView	gameMode1;
	@FXML protected ImageView	gameMode2;
	@FXML protected ImageView	gameMode3;

	@FXML protected ImageView	gameBackground;
	@FXML protected ImageView	middlePartition;

	@FXML protected ImageView	navigationCharacter;
	@FXML protected ImageView	navigationActualLocation;
	@FXML protected ImageView	navigationMap;
	@FXML protected ImageView	navigationExit;

	@FXML protected Text	energy;
	@FXML protected Text	fullness;
	@FXML protected Text	hydration;
	@FXML protected Text	health;
	@FXML protected Text	credits;

	@FXML protected Pane paneGear;

	@FXML protected ImageView	gearImplant;
	@FXML protected ImageView	gearHelmet;
	@FXML protected ImageView	gearNecklace;
	@FXML protected ImageView	gearArtifact;
	@FXML protected ImageView	gearHand;
	@FXML protected ImageView	gearWear;
	@FXML protected ImageView	gearGloves;
	@FXML protected ImageView	gearRing;
	@FXML protected ImageView	gearSpeeder;
	@FXML protected ImageView	gearBoots;
	@FXML protected ImageView	gearBelt;
	@FXML protected ImageView	gearDroid;

	@FXML protected ImageView	blueButton;
	@FXML protected ImageView	brownButton;
	@FXML protected ImageView	redButton;

	@FXML protected Pane paneDeck;

	@FXML protected ImageView	deckSlot1;
	@FXML protected ImageView	deckSlot2;
	@FXML protected ImageView	deckSlot3;
	@FXML protected ImageView	deckSlot4;
	@FXML protected ImageView	deckSlot5;
	@FXML protected ImageView	deckSlot6;
	@FXML protected ImageView	deckSlot7;
	@FXML protected ImageView	deckSlot8;
	@FXML protected ImageView	deckSlot9;
	@FXML protected ImageView	deckSlot10;

	@FXML protected Pane paneInventory;

	@FXML protected ImageView	inventorySlot1;
	@FXML protected ImageView	inventorySlot2;
	@FXML protected ImageView	inventorySlot3;
	@FXML protected ImageView	inventorySlot4;
	@FXML protected ImageView	inventorySlot5;
	@FXML protected ImageView	inventorySlot6;
	@FXML protected ImageView	inventorySlot7;
	@FXML protected ImageView	inventorySlot8;
	@FXML protected ImageView	inventorySlot9;
	@FXML protected ImageView	inventorySlot10;
	@FXML protected ImageView	inventorySlot11;
	@FXML protected ImageView	inventorySlot12;

	@FXML protected Pane paneFeaturesGear;

	@FXML protected Text	gearStrengthFeature;
	@FXML protected Text	gearDefenseFeature;
	@FXML protected Text	gearSkillFeature;
	@FXML protected Text	gearAppearanceFeature;
	@FXML protected Text	gearLuckFeature;
	@FXML protected Text	gearWeightFeature;

	@FXML protected Pane paneItemFeatures;

	@FXML protected ImageView	featuresSelect;
	@FXML protected ImageView	featuresTitles;
	@FXML protected Text		featuresName;

	@FXML protected Text	primaryFeature;
	@FXML protected Text	secondaryFeature;
	@FXML protected Text	ternaryFeature;
	@FXML protected Text	priceFeature;
	@FXML protected Text	weightFeature;
	@FXML protected Text	stateFeature;
	@FXML protected Text	possibleRepairesFeature;
	@FXML protected Text	functionFeature;

	@FXML protected Pane paneMap;

	@FXML protected ImageView	legend;
	@FXML protected ImageView	map;

	@FXML protected ImageView	migration;
	@FXML protected ImageView	lodging;
	@FXML protected ImageView	weapons;
	@FXML protected ImageView	repairer;
	@FXML protected ImageView	cantine;
	@FXML protected ImageView	grocery;
	@FXML protected ImageView	armor;
	@FXML protected ImageView	technique;
	@FXML protected ImageView	work;
	@FXML protected ImageView	medications;
	@FXML protected ImageView	jewelry;
	@FXML protected ImageView	filling;

	@FXML protected Pane paneMigration;

	@FXML protected ImageView	narrSheyda;
	@FXML protected ImageView	leftMigratePrice;
	@FXML protected ImageView	leftMigrate;

	@FXML protected ImageView	kerusant;
	@FXML protected ImageView	rightMigratePrice;
	@FXML protected ImageView	rightMigrate;

	@FXML protected Pane paneWork;

	@FXML protected ImageView	hunter;
	@FXML protected ImageView	worker;
	@FXML protected ImageView	archeologist;
	@FXML protected ImageView	technician;

	@FXML protected ProgressIndicator droidIndicator;

	@FXML protected Pane paneLodging;

	@FXML protected Text		leftHotelPrice;
	@FXML protected ImageView	leftHotel;

	@FXML protected Text		rightHotelPrice;
	@FXML protected ImageView	rightHotel;

	@FXML protected Pane paneShop;

	@FXML protected ImageView	shopBanner;
	@FXML protected ImageView	shopSlots;
	@FXML protected ImageView	shopSlot1;
	@FXML protected ImageView	shopSlot2;
	@FXML protected ImageView	shopSlot3;
	@FXML protected ImageView	shopSlot4;
	@FXML protected ImageView	shopSlot5;
	@FXML protected ImageView	shopSlot6;
	@FXML protected ImageView	shopSlot7;
	@FXML protected ImageView	shopSlot8;
	@FXML protected ImageView	shopSlot9;
	@FXML protected ImageView	shopSlot10;

	@FXML protected Pane paneFuelOrRepair;

	@FXML protected ImageView	fuelOrRepairBanner;
	@FXML protected ImageView	fuelOrRepairButton;
	@FXML protected ImageView	fuelOrRepairSlot;

	@FXML protected Pane paneCantine;

	@FXML protected ImageView	upButton1;
	@FXML protected ImageView	upButton2;
	@FXML protected ImageView	upButton3;
	@FXML protected ImageView	downButton1;
	@FXML protected ImageView	downButton2;
	@FXML protected ImageView	downButton3;

	@FXML protected ImageView	paziakStartButton;
	@FXML protected Text		paziakPlayersBet;

	@FXML protected ImageView	cardSlots;
	@FXML protected ImageView	cardSlot1;
	@FXML protected ImageView	cardSlot2;
	@FXML protected ImageView	cardSlot3;
	@FXML protected ImageView	cardSlot4;
	@FXML protected ImageView	cardSlot5;
	@FXML protected ImageView	cardSlot6;
	@FXML protected ImageView	cardSlot7;
	@FXML protected ImageView	cardSlot8;

	@FXML protected Pane panePaziak;

	@FXML public ImageView		paziakStandButton;
	@FXML public ImageView		paziakNextTurnButton;
	@FXML public ImageView		paziakMainButton;
	@FXML protected ImageView	paziakOpponentsBanner;
	@FXML protected ImageView	paziakPlayersBanner;

	@FXML protected Text		paziakPlayersScore;
	@FXML protected Text		paziakOpponentsScore;
	@FXML protected ImageView	paziakPlayersPoint1;
	@FXML protected ImageView	paziakPlayersPoint2;
	@FXML protected ImageView	paziakPlayersPoint3;
	@FXML protected ImageView	paziakOpponentsPoint1;
	@FXML protected ImageView	paziakOpponentsPoint2;
	@FXML protected ImageView	paziakOpponentsPoint3;

	@FXML protected ImageView	paziakPlayersLaidCard1;
	@FXML protected ImageView	paziakPlayersLaidCard2;
	@FXML protected ImageView	paziakPlayersLaidCard3;
	@FXML protected ImageView	paziakPlayersLaidCard4;
	@FXML protected ImageView	paziakPlayersLaidCard5;
	@FXML protected ImageView	paziakPlayersLaidCard6;
	@FXML protected ImageView	paziakPlayersLaidCard7;
	@FXML protected ImageView	paziakPlayersLaidCard8;
	@FXML protected ImageView	paziakPlayersLaidCard9;

	@FXML protected ImageView	paziakPlayersSideDeckCard1;
	@FXML protected ImageView	paziakPlayersSideDeckCard2;
	@FXML protected ImageView	paziakPlayersSideDeckCard3;
	@FXML protected ImageView	paziakPlayersSideDeckCard4;

	@FXML protected ImageView	paziakOpponentsLaidCard1;
	@FXML protected ImageView	paziakOpponentsLaidCard2;
	@FXML protected ImageView	paziakOpponentsLaidCard3;
	@FXML protected ImageView	paziakOpponentsLaidCard4;
	@FXML protected ImageView	paziakOpponentsLaidCard5;
	@FXML protected ImageView	paziakOpponentsLaidCard6;
	@FXML protected ImageView	paziakOpponentsLaidCard7;
	@FXML protected ImageView	paziakOpponentsLaidCard8;
	@FXML protected ImageView	paziakOpponentsLaidCard9;

	@FXML protected ImageView	paziakOpponentsSideDeckCard1;
	@FXML protected ImageView	paziakOpponentsSideDeckCard2;
	@FXML protected ImageView	paziakOpponentsSideDeckCard3;
	@FXML protected ImageView	paziakOpponentsSideDeckCard4;

	@FXML protected ImageView	paziakLeftTurnOfSideDeckCard1;
	@FXML protected ImageView	paziakLeftTurnOfSideDeckCard2;
	@FXML protected ImageView	paziakLeftTurnOfSideDeckCard3;
	@FXML protected ImageView	paziakLeftTurnOfSideDeckCard4;
	@FXML protected ImageView	paziakRightTurnOfSideDeckCard1;
	@FXML protected ImageView	paziakRightTurnOfSideDeckCard2;
	@FXML protected ImageView	paziakRightTurnOfSideDeckCard3;
	@FXML protected ImageView	paziakRightTurnOfSideDeckCard4;

	@FXML protected ProgressIndicator loadingIndicator;

	@FXML protected Pane		paneLoading;
	@FXML protected ProgressBar	loadingBar;

	@FXML protected Pane		paneLoadingSleepAndTravel;
	@FXML protected ProgressBar	loadingBarSleepAndTravel;

	@FXML protected Pane paneSmallDialog;

	@FXML private ImageView		smallDialogBackground;
	@FXML protected Text		smallDialogText;
	@FXML protected ImageView	smallDialogYesButton;
	@FXML protected ImageView	smallDialogNoButton;
	@FXML protected ImageView	smallDialogOkButton;

	@FXML protected Pane paneBigDialog;

	@FXML private ImageView	bigDialogBackground;
	@FXML protected Text	bigDialogText;

	private GameModel gameModel;

	public void setStage(Stage stage)
	{
		this.stage = stage;

		initialize();
	}

	private void initialize()
	{
		GameSaverLoader gameSaveLoader = new GameSaverLoader();
		String[] values = gameSaveLoader.loadGameProperties();

		gameModel = new GameModel(this, values[0]);
		gameModel.setGameSetText(values[1], values[2], values[3]);

		stage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override public void handle(WindowEvent event)
			{
				if (gameModel.getPlayer() != null)
					gameModel.quit();
				GameSaverLoader.gameIsAlreadyRunning(false);
			}
		});
	}

	@FXML private void paziakClicks(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		gameModel.paziakClicks(Tools.idFromSource(event.toString()));
	}

	@FXML private void paziakHandCardClicked(Event event) throws InterruptedException
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;
		
		gameModel.paziakHandCardClicked(Tools.getNumberFromString(Tools.idFromSource(event.toString()), "paziakPlayersSideDeckCard") - 1);
	}

	@FXML private void paziakTurnCard(Event event) throws InterruptedException
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;
		
		gameModel.paziakTurnCard(Tools.idFromSource(event.toString()));
	}

	@FXML protected void mouseEnteredImgGlow(Event event)
	{
		ImageView enteredImage = (ImageView) event.getSource();
		gameModel.setEffect(enteredImage, true);
	}

	@FXML protected void mouseExitedImg(Event event)
	{
		ImageView enteredImage = (ImageView) event.getSource();
		gameModel.setEffect(enteredImage, false);
	}

	@FXML private void gameClcs(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		switch (Tools.idFromSource(event.toString()))
		{
			case "navigationCharacter":
			{
				gameModel.setFieldVisible("paneMap", false);
				gameModel.keeperNodesVisibler(false);
				gameModel.characterNodesVisibler(true);
				break;
			}
			case "navigationActualLocation":
			{
				gameModel.characterNodesVisibler(false);
				gameModel.setFieldVisible("paneMap", false);
				gameModel.keeperNodesVisibler(true);
				break;
			}
			case "navigationMap":
			{
				gameModel.keeperNodesVisibler(false);
				gameModel.characterNodesVisibler(false);
				gameModel.setFieldVisible("paneMap", true);
				break;
			}
			case "featuresSelect":
			{
				gameModel.changeFeatures();
				break;
			}
			default:
			{
				gameModel.yesOrNoDialog("exit");
			}
		}
		gameModel.clearFuelOrRepairSlot();
	}

	@FXML private void travellingClcs(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		String idOfSource = Tools.idFromSource(event.toString());

		switch (idOfSource)
		{
			case "migration":
			{
				gameModel.addNodesToKeeper(middlePartition, paneMigration);
				break;
			}
			case "lodging":
			{
				gameModel.addNodesToKeeper(middlePartition, paneLodging);
				break;
			}
			case "weapons":
			{
				gameModel.addNodesToKeeper(middlePartition, paneInventory, paneItemFeatures, paneShop);
				break;
			}
			case "cantine":
			{
				gameModel.addNodesToKeeper(middlePartition, paneDeck, paneItemFeatures, paneCantine);
				break;
			}
			case "grocery":
			{
				gameModel.addNodesToKeeper(middlePartition, paneInventory, paneItemFeatures, paneShop);
				break;
			}
			case "armor":
			{
				gameModel.addNodesToKeeper(middlePartition, paneInventory, paneItemFeatures, paneShop);
				break;
			}
			case "technique":
			{
				gameModel.addNodesToKeeper(middlePartition, paneInventory, paneItemFeatures, paneShop);
				break;
			}
			case "work":
			{
				gameModel.addNodesToKeeper(middlePartition, paneWork);
				break;
			}
			case "medications":
			{
				gameModel.addNodesToKeeper(middlePartition, paneInventory, paneItemFeatures, paneShop);
				break;
			}
			case "jewelry":
			{
				gameModel.addNodesToKeeper(middlePartition, paneInventory, paneItemFeatures, paneShop);
				break;
			}
			case "filling":
			{
				gameModel.addNodesToKeeper(middlePartition, paneInventory, paneItemFeatures, paneFuelOrRepair);
				gameModel.fuelOrRepair(true);
				break;
			}
			case "repairer":
			{
				gameModel.addNodesToKeeper(middlePartition, paneInventory, paneItemFeatures, paneFuelOrRepair);
				gameModel.fuelOrRepair(false);
			}
		}

		switch (idOfSource)
		{
			case "armor":
			case "weapons":
			case "jewelry":
			case "grocery":
			case "medications":
			case "technique":
				gameModel.setShop(idOfSource);
		}

		gameModel.travellingClicks(idOfSource);
	}

	@FXML private void newGameClicks(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		switch (Tools.idFromSource(event.toString()))
		{
			case "characterSpecialization1":
			{
				gameModel.newGameChange("šedý válečník", true);
				break;
			}
			case "characterSpecialization2":
			{
				gameModel.newGameChange("lovec odměn", true);
				break;
			}
			case "characterSpecialization3":
			{
				gameModel.newGameChange("civilista", true);
				break;
			}
			case "gameMode1":
			{
				gameModel.newGameChange("klasický", false);
				break;
			}
			case "gameMode2":
			{
				gameModel.newGameChange("rychlý", false);
				break;
			}
			case "gameMode3":
			{
				gameModel.newGameChange("realistický", false);
				break;
			}
			case "backButton":
			{
				gameModel.setFieldVisible("paneNewGame", false);
				break;
			}
			case "startButton":
			{
				gameModel.createNewGame();
			}
		}

	}

	@FXML private void mainMenuClcs(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		String id = Tools.idFromSource(event.toString());
		switch (id)
		{
			case "trashCan1":
			case "trashCan2":
			case "trashCan3":
			{
				gameModel.yesOrNoDialog(id);
				break;
			}
			case "optionsButton":
			{
				gameModel.optionsClick();
				break;
			}
			case "howToPlayButton":
			{
				gameModel.setFieldVisible("paneHowToPlay", true);
				break;
			}
			case "howToFeaturesButton":
			{
				gameModel.setFieldVisible("paneHowToFeatures", true);
			}
			case "howToPlayOkButton":
			{
				gameModel.setFieldVisible("paneHowToPlay", false);
				break;
			}
			case "howToFeaturesOkButton":
			{
				gameModel.setFieldVisible("paneHowToFeatures", false);
				break;
			}
			case "quitButton":
			{
				stage.close();
				GameSaverLoader.gameIsAlreadyRunning(false);
				break;
			}
			case "gameState1":
			{
				if (informationTextOfGameState1.getText().equals("Nová hra"))
					gameModel.newGame(1);
				else
					gameModel.loadGameState(id);
				break;
			}
			case "gameState2":
			{
				if (informationTextOfGameState2.getText().equals("Nová hra"))
					gameModel.newGame(2);
				else
					gameModel.loadGameState(id);
				break;
			}
			case "gameState3":
			{
				if (informationTextOfGameState3.getText().equals("Nová hra"))
					gameModel.newGame(3);
				else
					gameModel.loadGameState(id);
			}
		}
	}

	@FXML private void cantineSlotClicks(Event event) // TODO
	{
		ImageView image = (ImageView) event.getSource();
		if (image.getImage() == null)
			return;

		MouseEvent mouseEvent = (MouseEvent) event;
		if (mouseEvent.getButton() == MouseButton.PRIMARY)
			gameModel.itemLeftClick(image, Tools.getNumberFromString(Tools.idFromSource(event.toString()), "cardSlot") - 1, 2);
		else if (mouseEvent.getButton() == MouseButton.SECONDARY)
			gameModel.cantineShopRightClick(image, Tools.getNumberFromString(Tools.idFromSource(event.toString()), "cardSlot") - 1);
	}

	@FXML private void cantineClicks(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		gameModel.CantineClicks(Tools.idFromSource(event.toString()));
	}

	@FXML private void deckSlotClick(Event event)
	{
		ImageView image = (ImageView) event.getSource();
		if (image.getImage() == null)
			return;

		MouseEvent mouseEvent = (MouseEvent) event;
		if (mouseEvent.getButton() == MouseButton.PRIMARY)
			gameModel.itemLeftClick(image, Tools.getNumberFromString(Tools.idFromSource(event.toString()), "deckSlot") - 1, 3);
		else if (mouseEvent.getButton() == MouseButton.SECONDARY)
			gameModel.deckSlotRightClick(image, Tools.getNumberFromString(Tools.idFromSource(event.toString()), "deckSlot") - 1, paneCantine.isVisible());
	}

	protected void trashDisablerAndEnabler()
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);

		if (informationTextOfGameState1.getText().equals("Nová hra"))
		{
			trashCan1.setDisable(true);
			trashCan1.setEffect(colorAdjust);;
		} else
		{
			trashCan1.setDisable(false);
			trashCan1.setEffect(null);
		}

		if (informationTextOfGameState2.getText().equals("Nová hra"))
		{
			trashCan2.setDisable(true);
			trashCan2.setEffect(colorAdjust);;
		} else
		{
			trashCan2.setDisable(false);
			trashCan2.setEffect(null);
		}

		if (informationTextOfGameState3.getText().equals("Nová hra"))
		{
			trashCan3.setDisable(true);
			trashCan3.setEffect(colorAdjust);;
		} else
		{
			trashCan3.setDisable(false);
			trashCan3.setEffect(null);
		}
	}

	@FXML private void lodgingClicks(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		gameModel.sleepClick(Tools.idFromSource(event.toString()).equals("leftLodging") ? true : false);
	}

	@FXML private void fuelOrRepairClick(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		gameModel.fuelOrRepairClick();
	}

	@FXML private void colorClicks(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		gameModel.colorClick(Tools.idFromSource(event.toString()));
	}

	@FXML private void optionsClicks(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		String id = Tools.idFromSource(event.toString());
		switch (id)
		{
			case "cancelButton":
			{
				gameModel.setFieldVisible("paneOptions", false);
				break;
			}
			case "saveButton":
			{
				gameModel.changeResolution(true);
				break;
			}
			default:
				gameModel.newResolution(Tools.getNumberFromString(id, "resolution") - 1);
		}
	}

	@FXML private void shopSlotClc(Event event)
	{
		ImageView image = (ImageView) event.getSource();
		if (image.getImage() == null)
			return;

		MouseEvent mouseEvent = (MouseEvent) event;
		if (mouseEvent.getButton() == MouseButton.PRIMARY)
			gameModel.itemLeftClick(image, Tools.getNumberFromString(Tools.idFromSource(event.toString()), "shopSlot") - 1, 1);
		else if (mouseEvent.getButton() == MouseButton.SECONDARY)
			gameModel.shopSlotRightClick(image, Tools.getNumberFromString(Tools.idFromSource(event.toString()), "shopSlot") - 1);
	}

	@FXML private void invSlotClc(Event event) // TODO inventorySlotClick
	{
		ImageView image = (ImageView) event.getSource();
		if (image.getImage() == null)
			return;

		MouseEvent mouseEvent = (MouseEvent) event;
		if (mouseEvent.getButton() == MouseButton.PRIMARY)
			gameModel.itemLeftClick(image, Tools.getNumberFromString(Tools.idFromSource(event.toString()), "inventorySlot") - 1, 0);
		else if (mouseEvent.getButton() == MouseButton.SECONDARY)
		{
			int gearOrFuelRepairOrShop;
			if (paneGear.isVisible())
				gearOrFuelRepairOrShop = 0;
			else if (paneFuelOrRepair.isVisible())
				gearOrFuelRepairOrShop = 1;
			else
				gearOrFuelRepairOrShop = 2;
			gameModel.invSlotRightClc(image, Tools.getNumberFromString(Tools.idFromSource(event.toString()), "inventorySlot") - 1, gearOrFuelRepairOrShop);
		}
	}

	@FXML private void gearSlotClicked(Event event) // TODO gearSlotClick
	{
		boolean primaryMouseButton;
		MouseEvent mouseEvent = (MouseEvent) event;
		if (mouseEvent.getButton() == MouseButton.PRIMARY)
			primaryMouseButton = true;
		else if (mouseEvent.getButton() == MouseButton.SECONDARY)
			primaryMouseButton = false;
		else
			return;

		int nthSlot = 0;
		List<Node> paneGearContent = paneGear.getChildren();

		for (; nthSlot < paneGearContent.size(); nthSlot++)
		{
			if (event.getSource().equals(paneGearContent.get(nthSlot)))
				break;
		}
		nthSlot--;

		if (gameModel.getPlayer().getNthGear(nthSlot) == null)
			return;

		ImageView image = (ImageView) event.getSource();
		gameModel.gearSlotClick(image, nthSlot, primaryMouseButton);
	}

	@FXML private void dialogYesClick()
	{
		gameModel.dialogYesClick();
	}

	@FXML private void dialogNoOrOkCick() // TODO rename Click
	{
		gameModel.dialogNoOrOkCick();
	}

	@FXML private void travelClicks(Event event) // TODO migrateClicks
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		switch (Tools.idFromSource(event.toString()))
		{
			case "leftMigrate": // TODO předělat
			{
				gameModel.travelOnNextPlanet(true);
				break;
			}
			case "rightMigrate":
				gameModel.travelOnNextPlanet(false);
		}
	}

	@FXML private void workClicks(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		gameModel.workClick(Tools.idFromSource(event.toString()));
	}

	protected Node getNodeThroughReflection(String nameOfNode)
	{
		try
		{
			Field field = this.getClass().getDeclaredField(nameOfNode);
			field.setAccessible(true);
			return (Node) field.get(this);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}