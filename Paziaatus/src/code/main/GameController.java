package main;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import paziak.DDDCardsImages;
import paziak.Paziak;
import paziak.SideDeckCard;
import residue.GameSaverLoader;
import residue.Tools;

public class GameController
{
	protected Stage stage;

	private Paziak		pazaak;
	private GameModel	gameModel;

	private String dialogCaller;

	@FXML protected Group blurGroup;

	@FXML protected Pane	paneMain;
	@FXML protected Pane	paneMap;
	@FXML protected Pane	panePazaak;
	@FXML protected Pane	paneMainMenu;
	@FXML protected Pane	paneOptions;
	@FXML protected Pane	paneTravel;
	@FXML protected Pane	paneWork;
	@FXML protected Pane	paneCantine;
	@FXML protected Pane	paneSleep;
	@FXML protected Pane	paneFuelOrRepair;
	@FXML protected Pane	paneGear;
	@FXML protected Pane	paneCards;
	@FXML protected Pane	paneInventory;
	@FXML protected Pane	paneFeatures;
	@FXML protected Pane	paneHowToPlay;

	@FXML public ImageView		stand;
	@FXML public ImageView		nextTurn;
	@FXML public ImageView		nextSetStartLeaveGame;
	@FXML protected ImageView	bannerOpponent;
	@FXML protected ImageView	bannerPlayer;

	@FXML protected ImageView	imgPlayersTable1;
	@FXML protected ImageView	imgPlayersTable2;
	@FXML protected ImageView	imgPlayersTable3;
	@FXML protected ImageView	imgPlayersTable4;
	@FXML protected ImageView	imgPlayersTable5;
	@FXML protected ImageView	imgPlayersTable6;
	@FXML protected ImageView	imgPlayersTable7;
	@FXML protected ImageView	imgPlayersTable8;
	@FXML protected ImageView	imgPlayersTable9;

	@FXML protected ImageView	imgOpponentsTable1;
	@FXML protected ImageView	imgOpponentsTable2;
	@FXML protected ImageView	imgOpponentsTable3;
	@FXML protected ImageView	imgOpponentsTable4;
	@FXML protected ImageView	imgOpponentsTable5;
	@FXML protected ImageView	imgOpponentsTable6;
	@FXML protected ImageView	imgOpponentsTable7;
	@FXML protected ImageView	imgOpponentsTable8;
	@FXML protected ImageView	imgOpponentsTable9;

	@FXML protected ImageView	imgPlayersHand1;
	@FXML protected ImageView	imgPlayersHand2;
	@FXML protected ImageView	imgPlayersHand3;
	@FXML protected ImageView	imgPlayersHand4;

	@FXML protected ImageView	imgOpponentsHand1;
	@FXML protected ImageView	imgOpponentsHand2;
	@FXML protected ImageView	imgOpponentsHand3;
	@FXML protected ImageView	imgOpponentsHand4;

	@FXML protected ImageView	pointPlayer1;
	@FXML protected ImageView	pointPlayer2;
	@FXML protected ImageView	pointPlayer3;
	@FXML protected ImageView	pointEnemy1;
	@FXML protected ImageView	pointEnemy2;
	@FXML protected ImageView	pointEnemy3;

	@FXML protected ImageView	handLeft1;
	@FXML protected ImageView	handLeft2;
	@FXML protected ImageView	handLeft3;
	@FXML protected ImageView	handLeft4;
	@FXML protected ImageView	handRight1;
	@FXML protected ImageView	handRight2;
	@FXML protected ImageView	handRight3;
	@FXML protected ImageView	handRight4;

	@FXML protected ImageView	options;
	@FXML protected ImageView	quit;
	@FXML protected ImageView	gameState1;
	@FXML protected ImageView	gameState2;
	@FXML protected ImageView	gameState3;
	@FXML protected Text		gameText1;
	@FXML protected Text		gameText2;
	@FXML protected Text		gameText3;

	@FXML protected ImageView	trash1;
	@FXML protected ImageView	trash2;
	@FXML protected ImageView	trash3;

	@FXML protected Text	energy;
	@FXML protected Text	fullness;
	@FXML protected Text	hydration;
	@FXML protected Text	health;
	@FXML protected Text	credits;

	@FXML protected Pane		paneSmallDialog;
	@FXML private ImageView		smallDialogBackground;
	@FXML protected Text		smallDialogText;
	@FXML protected ImageView	smallDialogYes;
	@FXML protected ImageView	smallDialogNo;
	@FXML protected ImageView	smallDialogOk;

	@FXML protected Pane		paneBigDialog;
	@FXML private ImageView		bigDialogBackground;
	@FXML protected Text		bigDialogText;
	@FXML protected ImageView	bigDialogOk;

	@FXML protected ImageView	res1;
	@FXML protected ImageView	res2;
	@FXML protected ImageView	res3;
	@FXML protected ImageView	res4;
	@FXML protected ImageView	arrow;
	@FXML protected ImageView	cancel;
	@FXML protected ImageView	save;

	@FXML protected ImageView	gear;
	@FXML protected ImageView	cards;
	@FXML protected ImageView	inventory;

	@FXML protected ImageView middle;

	@FXML protected ImageView	featuresBackground;
	@FXML protected ImageView	featuresSelect;
	@FXML protected ImageView	featuresTitles;
	@FXML protected Text		featuresName;
	@FXML protected Text		features1;
	@FXML protected Text		features2;
	@FXML protected Text		features3;
	@FXML protected Text		features4;
	@FXML protected Text		features5;
	@FXML protected Text		features6;
	@FXML protected Text		features7;

	@FXML protected Text	playersScore;
	@FXML protected Text	opponentsScore;

	@FXML protected Pane				paneLoading;
	@FXML protected ProgressBar			loadingBar;
	@FXML protected ProgressIndicator	loadingIndicator;

	@FXML protected ImageView	map;
	@FXML protected ImageView	actual;
	@FXML protected ImageView	character;
	@FXML protected ImageView	travel;
	@FXML protected ImageView	sleep;
	@FXML protected ImageView	weapons;
	@FXML protected ImageView	repair;
	@FXML protected ImageView	cantine;
	@FXML protected ImageView	grocery;
	@FXML protected ImageView	armor;
	@FXML protected ImageView	technique;
	@FXML protected ImageView	work;
	@FXML protected ImageView	medications;
	@FXML protected ImageView	jewelry;
	@FXML protected ImageView	fuel;
	@FXML protected ImageView	exit;

	@FXML protected ImageView	shopBanner;
	@FXML protected Pane		paneShop;
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

	@FXML protected ImageView	invSlots;
	@FXML protected ImageView	invSlot1;
	@FXML protected ImageView	invSlot2;
	@FXML protected ImageView	invSlot3;
	@FXML protected ImageView	invSlot4;
	@FXML protected ImageView	invSlot5;
	@FXML protected ImageView	invSlot6;
	@FXML protected ImageView	invSlot7;
	@FXML protected ImageView	invSlot8;
	@FXML protected ImageView	invSlot9;
	@FXML protected ImageView	invSlot10;
	@FXML protected ImageView	invSlot11;
	@FXML protected ImageView	invSlot12;

	@FXML protected ImageView	gearSlots;
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

	@FXML protected ImageView	travelLeft;
	@FXML protected ImageView	travelRight;
	@FXML protected ImageView	priceLeft;
	@FXML protected ImageView	priceRight;

	@FXML protected ImageView	hunter;
	@FXML protected ImageView	worker;
	@FXML protected ImageView	archeologist;
	@FXML protected ImageView	technician;

	@FXML protected Pane	paneNewGame;
	@FXML protected ImageView	back;
	@FXML protected ImageView	start;
	@FXML protected ImageView	specialization1;
	@FXML protected ImageView	specialization2;
	@FXML protected ImageView	specialization3;
	@FXML protected ImageView	mode1;
	@FXML protected ImageView	mode2;
	@FXML protected ImageView	mode3;

	public PauseTransition	opponentPlayedCard				= new PauseTransition(Duration.millis(650));
	public PauseTransition	visualizeWithDelayOpsTable		= new PauseTransition(Duration.millis(200));
	public PauseTransition	visualizeWithShortDelayPlsTable	= new PauseTransition(Duration.millis(400));
	public PauseTransition	visualizeWithDelayPlsTable		= new PauseTransition(Duration.millis(850));
	public PauseTransition	opponentPlayedCardSoWait		= new PauseTransition(Duration.millis(1400));

	@FXML private Pane paneForScaling;

	private File lockFile;

	public void setStage(Stage stage/*, File lockFile*/)
	{
		this.stage = stage;
		//this.lockFile = lockFile;

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

				//lockFile.delete();
			}
		});
	}

	@FXML private void mainClick() throws InterruptedException
	{
		pazaak = new Paziak(this);
		pointsHider();
		nextSetStartLeaveGame.setVisible(false);
		disableNextAndStandButtons(false);

		opponentPlayedCard.setOnFinished(event -> {
			pazaak.waitingForCardUse();
		});
		visualizeWithDelayOpsTable.setOnFinished(event -> {
			visualizationOfTableCards(false);
		});

		visualizeWithShortDelayPlsTable.setOnFinished(event -> {
			visualizationOfTableCards(true);
		});
		visualizeWithDelayPlsTable.setOnFinished(event -> {
			visualizationOfTableCards(true);
		});
		opponentPlayedCardSoWait.setOnFinished(event -> {
			visualizationOfTableCards(true);
		});

		pazaak.newGame();
	}

	private void pointsHider()
	{
		List<Node> playersCards = panePazaak.getChildren();
		for (int i = 36; i <= 41; i++)
			playersCards.get(i).setVisible(false);
	}

	@FXML private void endTurn() throws InterruptedException
	{
		disableNextAndStandButtons(true);
		darkenAllPlayersHandCards();
		pazaak.opponentsTurn();
	}

	@FXML private void stand() throws InterruptedException
	{
		pazaak.setPlayerStand(true);
		disableNextAndStandButtons(true);
		darkenCards(true);
		pazaak.opponentsTurn();
	}

	@FXML private void handCardClicked(Event event) throws InterruptedException
	{
		int numberOfSource = Tools.getNumberFromString(Tools.idFromSource(event.toString()), "imgPlayersHand") - 1;

		if (!pazaak.getPlayer().getCardsForMatch().get(numberOfSource).isCardUsed())
		{
			pazaak.setPlayersScore(pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(numberOfSource), pazaak.getCardsOnPlayersTable()));
			pazaak.getPlayer().getCardsForMatch().get(numberOfSource).useCard();
			visualizationOfTableCards(true);
			darkenPlayersHandCards(numberOfSource);
		}
	}

	@FXML private void turnCard(Event event) throws InterruptedException
	{

		String idOfSource = Tools.idFromSource(event.toString());
		boolean left = idOfSource.contains("Left");
		String removeThisFromidOfSource;
		if (left)
			removeThisFromidOfSource = "handLeft";
		else
			removeThisFromidOfSource = "handRight";
		int numberOfSource = Tools.getNumberFromString(idOfSource, removeThisFromidOfSource) - 1;

		if (!pazaak.getPlayer().getCardsForMatch().get(numberOfSource).isCardUsed())
		{
			pazaak.getPlayer().getCardsForMatch().get(numberOfSource).changeValue(left);
			visualizationOfTableCards(true);
		}
	}

	public void visualizationOfTableCards(boolean visualizationOfPlayersSide)
	{
		int nthChildrenOfPazaakPane;
		List<SideDeckCard> cardsOnTable;
		List<Node> imageViewsOfTableCards = panePazaak.getChildren();
		ImageView imageViewOfCard;

		if (visualizationOfPlayersSide)
		{
			playersScore.setText(String.valueOf(pazaak.getPlayersScore()));
			cardsOnTable = pazaak.getCardsOnPlayersTable();
			nthChildrenOfPazaakPane = 13;
			disableNextAndStandButtons(false);
			brightenPlayersHandCards();
		} else
		{
			opponentsScore.setText(pazaak.getOpponentsScore());
			cardsOnTable = pazaak.getCardsOnOpponentsTable();
			nthChildrenOfPazaakPane = 0;
		}

		for (SideDeckCard card : cardsOnTable)
		{
			imageViewOfCard = (ImageView) imageViewsOfTableCards.get(nthChildrenOfPazaakPane);
			imageViewOfCard.setImage(whichSideOfCard(card));
			nthChildrenOfPazaakPane++;
		}

		visualizationOfHandCards(visualizationOfPlayersSide);
	}

	public void visualizationOfHandCards(boolean visualizationOfPlayersSide)
	{
		int nthChildrenOfPazaakPane;
		List<SideDeckCard> cardsInHand;
		List<Node> imageViewsOfHandCards = panePazaak.getChildren();
		ImageView imageViewOfCard;

		if (visualizationOfPlayersSide)
		{
			cardsInHand = pazaak.getPlayer().getCardsForMatch();
			nthChildrenOfPazaakPane = 22;
		} else
		{
			cardsInHand = pazaak.getOpponent().getCardsForMatch();
			nthChildrenOfPazaakPane = 9;
		}

		for (SideDeckCard card : cardsInHand)
		{
			imageViewOfCard = (ImageView) imageViewsOfHandCards.get(nthChildrenOfPazaakPane);
			if (!card.isCardUsed() && !visualizationOfPlayersSide)
				imageViewOfCard.setImage(new Image(getClass().getResource(DDDCardsImages.BACK.getFirstImage()).toString()));
			else if (!card.isCardUsed() && visualizationOfPlayersSide)
				imageViewOfCard.setImage(whichSideOfCard(card));
			else
			{
				imageViewOfCard.setImage(null);
				if (visualizationOfPlayersSide)
				{
					imageViewsOfHandCards.get(nthChildrenOfPazaakPane + 4).setVisible(false);
					imageViewsOfHandCards.get(nthChildrenOfPazaakPane + 5).setVisible(false);
				}
			}
			nthChildrenOfPazaakPane++;
		}
	}

	private Image whichSideOfCard(SideDeckCard card)
	{
		Image image;
		if (card.hasInactiveLeftTurn())
		{
			if (card.hasInactiveRightTurn())
				image = new Image(getClass().getResource(card.getCard().getImages().getFirstImage()).toString());
			else
				image = new Image(getClass().getResource(card.getCard().getImages().getThirdImage()).toString());

		} else
		{
			if (card.hasInactiveRightTurn())
				image = new Image(getClass().getResource(card.getCard().getImages().getSecondImage()).toString());
			else
				image = new Image(getClass().getResource(card.getCard().getImages().getFourthImage()).toString());
		}

		return image;
	}

	public void pointVisibler(int playersSets, int opponentsSets)
	{
		if (playersSets == 3)
			pointPlayer1.setVisible(true);
		else if (playersSets == 2)
			pointPlayer2.setVisible(true);
		else if (playersSets == 1)
			pointPlayer3.setVisible(true);

		if (opponentsSets == 3)
			pointEnemy1.setVisible(true);
		else if (opponentsSets == 2)
			pointEnemy2.setVisible(true);
		else if (opponentsSets == 1)
			pointEnemy3.setVisible(true);
	}

	public void resetScore()
	{
		playersScore.setText(String.valueOf(pazaak.getPlayersScore()));
		opponentsScore.setText(pazaak.getOpponentsScore());
	}

	public void clearImages()
	{
		List<Node> playersHandCards = panePazaak.getChildren();
		ImageView imageVieqOfCard;

		for (int i = 0; i < 22; i++)
		{
			imageVieqOfCard = (ImageView) playersHandCards.get(i);
			imageVieqOfCard.setImage(null);

			if (i == 8)
				i = 13;
		}
	}

	public void hideAllHandButtons()
	{
		List<Node> playersHandButtons = panePazaak.getChildren();
		for (int i = 26; i <= 33; i++)
			playersHandButtons.get(i).setVisible(false);
	}

	public void showOrHidehandCards(boolean show)
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);

		List<Node> playersHandCards = panePazaak.getChildren();
		for (int i = 9; i <= 33; i++)
		{
			playersHandCards.get(i).setVisible(show);

			if (i == 12)
				i = 22;
			else if (i == 25 && show)
				break;
		}
	}

	public void disableNextAndStandButtons(boolean disable)
	{
		nextTurn.setDisable(disable);
		stand.setDisable(disable);
	}

	public void activateHandButtons()
	{
		deactivateOldHandButtons();

		ImageView imageViewHandLeft = imgPlayersHand1;
		ImageView imageViewHandRight = imgPlayersHand1;
		int i = 1;

		for (SideDeckCard card : pazaak.getPlayer().getCardsForMatch())
		{
			if (!card.isCardUsed())
				try
				{
					imageViewHandLeft = (ImageView) this.getClass().getDeclaredField("handLeft" + i).get(this);
					imageViewHandRight = (ImageView) this.getClass().getDeclaredField("handRight" + i).get(this);

					if (!card.getCard().getImages().getSecondImage().equals(""))
						imageViewHandLeft.setVisible(true);
					if (!card.getCard().getImages().getThirdImage().equals(""))
						imageViewHandRight.setVisible(true);
				} catch (IllegalAccessException | NoSuchFieldException e)
				{
					e.printStackTrace();
				}
			i++;
		}
	}

	private void darkenAllPlayersHandCards()
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);

		List<Node> playersHandCards = panePazaak.getChildren();
		for (int i = 22; i <= 33; i++)
		{
			playersHandCards.get(i).setDisable(true);
			playersHandCards.get(i).setEffect(colorAdjust);
		}
	}

	private void darkenPlayersHandCards(int nthUsedCard)
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);
		ImageView fieldValue = imgPlayersHand1;
		GameController classObj = this;

		String[] playersHandsWithoutNthHand = getOtherPlayersHandImgViews(nthUsedCard);
		for (String fieldName : playersHandsWithoutNthHand)
		{
			try
			{
				Field field = classObj.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				fieldValue = (ImageView) field.get(classObj);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
			{
				e.printStackTrace();
			}
			fieldValue.setDisable(true);
			fieldValue.setEffect(colorAdjust);
		}
	}

	public String[] getOtherPlayersHandImgViews(int nth)
	{
		String[] values = new String[3];
		for (int i = 0, j = 0; j < 3; i++)
			if (i != nth)
			{
				values[j] = "imgPlayersHand" + (i + 1);
				j++;
			}
		return values;
	}

	private void brightenPlayersHandCards()
	{
		List<Node> playersHandCards = panePazaak.getChildren();
		for (int i = 22; i <= 33; i++)
		{
			playersHandCards.get(i).setDisable(false);
			if (i < 26)
				playersHandCards.get(i).setEffect(null);
		}
	}

	private void deactivateOldHandButtons()
	{
		List<Node> playersHandButtons = panePazaak.getChildren();
		for (int i = 26; i <= 33; i++)
			playersHandButtons.get(i).setVisible(false);
	}

	private void brightenAllCards()
	{
		List<Node> playersCards = panePazaak.getChildren();
		for (int i = 0; i <= 25; i++)
			playersCards.get(i).setEffect(null);
	}

	public void darkenCards(boolean darkenPlayersCards)
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);
		List<Node> playersCards = panePazaak.getChildren();
		int i, max;

		if (darkenPlayersCards)
			i = 13;
		else
			i = 0;
		max = i + 12;

		for (; i <= max; i++)
			playersCards.get(i).setEffect(colorAdjust);
	}

	@FXML protected void mouseEnteredImgGlow03(Event event)
	{
		ImageView enteredImage = (ImageView) event.getSource();
		gameModel.setEffect(enteredImage, true, 0.3);
	}

	@FXML protected void mouseEnteredImgGlow06(Event event)
	{
		ImageView enteredImage = (ImageView) event.getSource();
		gameModel.setEffect(enteredImage, true, 0.6);
	}

	@FXML protected void mouseEnteredImgGlow08(Event event)
	{
		ImageView enteredImage = (ImageView) event.getSource();
		gameModel.setEffect(enteredImage, true, 0.8);
	}

	@FXML protected void mouseExitedImg(Event event)
	{
		ImageView enteredImage = (ImageView) event.getSource();
		gameModel.setEffect(enteredImage, false, 0);
	}

	@FXML private void gameClcs(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		switch (Tools.idFromSource(event.toString()))
		{
			case "character":
			{
				gameModel.setFieldVisible("paneMap", false);
				gameModel.keeperNodesVisibler(false);
				gameModel.characterNodesVisibler(true);
				break;
			}
			case "actual":
			{
				gameModel.characterNodesVisibler(false);
				gameModel.setFieldVisible("paneMap", false);
				gameModel.keeperNodesVisibler(true);
				break;
			}
			case "map":
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
	}

	@FXML private void travellingClcs(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		String idOfSource = Tools.idFromSource(event.toString());

		switch (idOfSource)
		{
			case "travel":
			{
				gameModel.addNodesToKeeper(middle, paneTravel);
				break;
			}
			case "sleep":
			{
				gameModel.addNodesToKeeper(middle, paneSleep);
				break;
			}
			case "weapons":
			{
				gameModel.addNodesToKeeper(middle, paneInventory, paneFeatures, paneShop);
				break;
			}
			case "cantine":
			{
				gameModel.addNodesToKeeper(middle, paneCards, paneFeatures, paneCantine);
				break;
			}
			case "grocery":
			{
				gameModel.addNodesToKeeper(middle, paneInventory, paneFeatures, paneShop);
				break;
			}
			case "armor":
			{
				gameModel.addNodesToKeeper(middle, paneInventory, paneFeatures, paneShop);
				break;
			}
			case "technique":
			{
				gameModel.addNodesToKeeper(middle, paneInventory, paneFeatures, paneShop);
				break;
			}
			case "work":
			{
				gameModel.addNodesToKeeper(middle, paneWork);
				break;
			}
			case "medications":
			{
				gameModel.addNodesToKeeper(middle, paneInventory, paneFeatures, paneShop);
				break;
			}
			case "jewelry":
			{
				gameModel.addNodesToKeeper(middle, paneInventory, paneFeatures, paneShop);
				break;
			}
			default:
				gameModel.addNodesToKeeper(middle, paneInventory, paneFeatures, paneFuelOrRepair);
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

		/*if (/* gameProperties.getPlayer().getPlanet().equals("Narr Sheyda") &&  Tools.getRandomNumber(8) == 0)
			gameModel.getMissionsAndAssaults().assault();*/

		gameModel.travellingClicks(idOfSource);
	}


	@FXML private void newGameClicks(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		switch (Tools.idFromSource(event.toString()))
		{
			case "specialization1":
			{
				gameModel.newGameChange(1, true);
				break;
			}
			case "specialization2":
			{
				gameModel.newGameChange(2, true);
				break;
			}
			case "specialization3":
			{
				gameModel.newGameChange(3, true);
				break;
			}
			case "mode1":
			{
				gameModel.newGameChange(1, false);
				break;
			}
			case "mode2":
			{
				gameModel.newGameChange(2, false);
				break;
			}
			case "mode3":
			{
				gameModel.newGameChange(3, false);
				break;
			}
			case "back":
			{
				gameModel.setFieldVisible("paneNewGame", false);
				break;
			}
			case "start":
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
			case "trash1":
			case "trash2":
			case "trash3":
			{
				gameModel.yesOrNoDialog(id);
				break;
			}
			case "options":
			{
				gameModel.optionsClick();
				break;
			}
			case "howToPlay":
			{
				gameModel.setFieldVisible("paneHowToPlay", true);
				break;
			}
			case "howToPlayOk":
			{
				gameModel.setFieldVisible("paneHowToPlay", false);
				break;
			}
			case "quit":
			{
				lockFile.delete();
				stage.close();
				break;
			}
			case "gameState1":
			{
				if(gameText1.getText().equals("Nová hra"))
					gameModel.newGame(1);
				else
					gameModel.loadGameState(id);
				break;
			}
			case "gameState2":
			{
				if(gameText2.getText().equals("Nová hra"))
					gameModel.newGame(2);
				else
					gameModel.loadGameState(id);
				break;
			}
			case "gameState3":
			{
				if(gameText3.getText().equals("Nová hra"))
					gameModel.newGame(3);
				else
					gameModel.loadGameState(id);
			}
		}
	}

	@FXML private void optionsClicks(Event event)
	{
		if (((MouseEvent) event).getButton() != MouseButton.PRIMARY)
			return;

		String id = Tools.idFromSource(event.toString());
		switch (id)
		{
			case "cancel":
			{
				gameModel.setFieldVisible(id, false);
				break;
			}
			case "save":
			{
				gameModel.changeResolution(true);
				break;
			}
			default:
				gameModel.newResolution(Tools.getNumberFromString(id, "res"));
		}
	}

	@FXML private void shopSlotClc(Event event)
	{
		ImageView image = (ImageView) event.getSource();
		if (image.getImage() == null)
			return;

		MouseEvent mouseEvent = (MouseEvent) event;
		if (mouseEvent.getButton() == MouseButton.PRIMARY)
			gameModel.itemLeftClick(image, Tools.getNumberFromString(Tools.idFromSource(event.toString()), "shopSlot") - 1, false);
		else if (mouseEvent.getButton() == MouseButton.SECONDARY)
			gameModel.shopSlotRightClick(image, Tools.getNumberFromString(Tools.idFromSource(event.toString()), "shopSlot") - 1);
		else
			return;
	}

	@FXML private void invSlotClc(Event event) // TODO inventorySlotClick
	{
		ImageView image = (ImageView) event.getSource();
		if (image.getImage() == null)
			return;

		MouseEvent mouseEvent = (MouseEvent) event;
		if (mouseEvent.getButton() == MouseButton.PRIMARY)
			gameModel.itemLeftClick(image, Tools.getNumberFromString(Tools.idFromSource(event.toString()), "invSlot") - 1, true);
		else if (mouseEvent.getButton() == MouseButton.SECONDARY)
		{
			int gearOrFuelRepairOrShop;
			if (paneGear.isVisible())
				gearOrFuelRepairOrShop = 0;
			else if (paneFuelOrRepair.isVisible())
				gearOrFuelRepairOrShop = 1;
			else
				gearOrFuelRepairOrShop = 2;
			gameModel.invSlotRightClc(image, Tools.getNumberFromString(Tools.idFromSource(event.toString()), "invSlot") - 1, gearOrFuelRepairOrShop);
		} else
			return;
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

		gameModel.gearSlotClick(nthSlot, primaryMouseButton);
	}

	protected synchronized void changeValuesOfNecessitiesOfLife()
	{
		energy.setText(String.valueOf(gameModel.getPlayer().getEnergy()));
		fullness.setText(String.valueOf(gameModel.getPlayer().getFullness()));
		hydration.setText(String.valueOf(gameModel.getPlayer().getHydration()));
		health.setText(String.valueOf(gameModel.getPlayer().getHealth()));
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
			case "travelLeft": // TODO předělat
			{
				gameModel.travelOnNextPlanet(true);
				break;
			}
			case "travelRight":
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