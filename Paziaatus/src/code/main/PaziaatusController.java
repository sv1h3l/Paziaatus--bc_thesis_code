package main;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import beings.Player;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import pazaak.CardsImages;
import pazaak.Pazaak;
import pazaak.RealCard;
import residue.Constants;
import residue.Item;
import residue.Shop;
import residue.Tools;

public class PaziaatusController
{
	private Pazaak			pazaak;
	private GameProperties	gameProperties;

	private String	dialogCaller;
	private Image	navigationActualImageKeeper;

	private List<Node> paneActualNodesKeeper;

	@FXML private Group blurGroup;

	@FXML private Pane	paneMap;
	@FXML private Pane	panePazaak;
	@FXML private Pane	paneMainMenu;
	@FXML private Pane	paneOptions;
	@FXML private Pane	paneTravel;
	@FXML private Pane	paneWork;
	@FXML private Pane	paneCantine;
	@FXML private Pane	paneSleep;
	@FXML private Pane	paneFuelOrRepair;
	@FXML private Pane	paneGear;
	@FXML private Pane	paneCards;
	@FXML private Pane	paneInventory;
	@FXML private Pane	paneFeatures;
	@FXML private Pane	paneHowToPlay;

	@FXML public ImageView	stand;
	@FXML public ImageView	nextTurn;
	@FXML public ImageView	nextSetStartLeaveGame;
	@FXML private ImageView	bannerOpponent;
	@FXML private ImageView	bannerPlayer;

	@FXML private ImageView	imgPlayersTable1;
	@FXML private ImageView	imgPlayersTable2;
	@FXML private ImageView	imgPlayersTable3;
	@FXML private ImageView	imgPlayersTable4;
	@FXML private ImageView	imgPlayersTable5;
	@FXML private ImageView	imgPlayersTable6;
	@FXML private ImageView	imgPlayersTable7;
	@FXML private ImageView	imgPlayersTable8;
	@FXML private ImageView	imgPlayersTable9;

	@FXML private ImageView	imgOpponentsTable1;
	@FXML private ImageView	imgOpponentsTable2;
	@FXML private ImageView	imgOpponentsTable3;
	@FXML private ImageView	imgOpponentsTable4;
	@FXML private ImageView	imgOpponentsTable5;
	@FXML private ImageView	imgOpponentsTable6;
	@FXML private ImageView	imgOpponentsTable7;
	@FXML private ImageView	imgOpponentsTable8;
	@FXML private ImageView	imgOpponentsTable9;

	@FXML private ImageView	imgPlayersHand1;
	@FXML private ImageView	imgPlayersHand2;
	@FXML private ImageView	imgPlayersHand3;
	@FXML private ImageView	imgPlayersHand4;

	@FXML private ImageView	imgOpponentsHand1;
	@FXML private ImageView	imgOpponentsHand2;
	@FXML private ImageView	imgOpponentsHand3;
	@FXML private ImageView	imgOpponentsHand4;

	@FXML private ImageView	pointPlayer1;
	@FXML private ImageView	pointPlayer2;
	@FXML private ImageView	pointPlayer3;
	@FXML private ImageView	pointEnemy1;
	@FXML private ImageView	pointEnemy2;
	@FXML private ImageView	pointEnemy3;

	@FXML private ImageView	handLeft1;
	@FXML private ImageView	handLeft2;
	@FXML private ImageView	handLeft3;
	@FXML private ImageView	handLeft4;
	@FXML private ImageView	handRight1;
	@FXML private ImageView	handRight2;
	@FXML private ImageView	handRight3;
	@FXML private ImageView	handRight4;

	@FXML private ImageView	options;
	@FXML private ImageView	quit;
	@FXML private ImageView	game1;
	@FXML private ImageView	game2;
	@FXML private ImageView	game3;
	@FXML private ImageView	trash1;
	@FXML private ImageView	trash2;
	@FXML private ImageView	trash3;

	@FXML private Text	energy;
	@FXML private Text	fullness;
	@FXML private Text	hydration;
	@FXML private Text	health;
	@FXML private Text	credits;

	@FXML private Pane		paneDialog;
	@FXML private ImageView	dialogBackground;
	@FXML private Text		dialogText;
	@FXML private ImageView	dialogYes;
	@FXML private ImageView	dialogNo;
	@FXML private ImageView	dialogOk;

	@FXML private ImageView	res1;
	@FXML private ImageView	res2;
	@FXML private ImageView	res3;
	@FXML private ImageView	res4;
	@FXML private ImageView	arrow;
	@FXML private ImageView	cancel;
	@FXML private ImageView	save;

	@FXML private ImageView	gear;
	@FXML private ImageView	cards;
	@FXML private ImageView	inventory;

	@FXML private ImageView middle;

	@FXML private ImageView	featuresBackground;
	@FXML private ImageView	featuresSelect;
	@FXML private ImageView	featuresTitles;
	@FXML private Text		featuresName;
	@FXML private Text		features1;
	@FXML private Text		features2;
	@FXML private Text		features3;
	@FXML private Text		features4;
	@FXML private Text		features5;
	@FXML private Text		features6;
	@FXML private Text		features7;

	@FXML private Text	playersScore;
	@FXML private Text	opponentsScore;

	@FXML private Pane				paneLoading;
	@FXML private ProgressBar		loadingBar;
	@FXML private ProgressIndicator	loadingIndicator;

	@FXML private ImageView	map;
	@FXML private ImageView	actual;
	@FXML private ImageView	character;
	@FXML private ImageView	travel;
	@FXML private ImageView	sleep;
	@FXML private ImageView	weapons;
	@FXML private ImageView	repair;
	@FXML private ImageView	cantine;
	@FXML private ImageView	food;
	@FXML private ImageView	armor;
	@FXML private ImageView	tech;
	@FXML private ImageView	work;
	@FXML private ImageView	medications;
	@FXML private ImageView	jewelry;
	@FXML private ImageView	fuel;
	@FXML private ImageView	exit;

	@FXML private ImageView	shopBanner;
	@FXML private Pane		paneShop;
	@FXML private ImageView	shopSlots;
	@FXML private ImageView	shopSlot1;
	@FXML private ImageView	shopSlot2;
	@FXML private ImageView	shopSlot3;
	@FXML private ImageView	shopSlot4;
	@FXML private ImageView	shopSlot5;
	@FXML private ImageView	shopSlot6;
	@FXML private ImageView	shopSlot7;
	@FXML private ImageView	shopSlot8;
	@FXML private ImageView	shopSlot9;
	@FXML private ImageView	shopSlot10;

	@FXML private ImageView	invSlots;
	@FXML private ImageView	invSlot1;
	@FXML private ImageView	invSlot2;
	@FXML private ImageView	invSlot3;
	@FXML private ImageView	invSlot4;
	@FXML private ImageView	invSlot5;
	@FXML private ImageView	invSlot6;
	@FXML private ImageView	invSlot7;
	@FXML private ImageView	invSlot8;
	@FXML private ImageView	invSlot9;
	@FXML private ImageView	invSlot10;
	@FXML private ImageView	invSlot11;
	@FXML private ImageView	invSlot12;

	@FXML private ImageView	gearSlots;
	@FXML private ImageView	gearImplant;
	@FXML private ImageView	gearHelmet;
	@FXML private ImageView	gearNecklace;
	@FXML private ImageView	gearArtifact;
	@FXML private ImageView	gearHand;
	@FXML private ImageView	gearWear;
	@FXML private ImageView	gearGloves;
	@FXML private ImageView	gearRing;
	@FXML private ImageView	gearSpeeder;
	@FXML private ImageView	gearBoots;
	@FXML private ImageView	gearBelt;
	@FXML private ImageView	gearDroid;

	@FXML private ImageView	travelLeft;
	@FXML private ImageView	travelRight;
	@FXML private ImageView	priceLeft;
	@FXML private ImageView	priceRight;

	@FXML private ImageView	hunter;
	@FXML private ImageView	worker;
	@FXML private ImageView	archeologist;
	@FXML private ImageView	technician;

	public PauseTransition	opponentPlayedCard				= new PauseTransition(Duration.millis(650));
	public PauseTransition	visualizeWithDelayOpsTable		= new PauseTransition(Duration.millis(200));
	public PauseTransition	visualizeWithShortDelayPlsTable	= new PauseTransition(Duration.millis(400));
	public PauseTransition	visualizeWithDelayPlsTable		= new PauseTransition(Duration.millis(850));
	public PauseTransition	opponentPlayedCardSoWait		= new PauseTransition(Duration.millis(1400));

	private Timeline	loadingBarTimeline;
	private Timeline	loadingIndicatorTimeline;

	@FXML private void initialize()
	{
		Player player = new Player(100, 100, 100, 100, 999999, 0, 0, 0, 0, 0, null);
		gameProperties = new GameProperties("1600x900", paneMainMenu, paneCantine, player, this);
		paneActualNodesKeeper = new ArrayList<>();

		Item newItem = new Item("helma", 0, 0, 0, 15, 0, 0, 0, "", "rings/1.png");
		gameProperties.getJewelry().addItemIntoShop(newItem);

		Item newItem1 = new Item("implantát", 0, 0, 20, 20, 1, 0, 0, "", "rings/2.png");
		gameProperties.getJewelry().addItemIntoShop(newItem1);

		Item newItem2 = new Item("laserový meč", 0, 35, 0, 04, 1, 0, 0, "", "rings/3.png");
		gameProperties.getJewelry().addItemIntoShop(newItem2);

		Item newItem3 = new Item("droid", 0, 0, 0, 4, 42, 0, 0, "", "rings/5.png");
		gameProperties.getJewelry().addItemIntoShop(newItem3);

		Item newItem4 = new Item("nástroj", 0, 0, 0, 56, 1, 0, 0, "", "rings/5.png");
		gameProperties.getJewelry().addItemIntoShop(newItem4);

		Item newItem5 = new Item("jídlo", 0, 0, 0, 45, 1, Constants.NO_VALUE, Constants.NO_VALUE, "", "rings/6.png");
		gameProperties.getJewelry().addItemIntoShop(newItem5);

		Item newItem6 = new Item("implantát", 0, 0, 0, 91, 1, 0, 0, "", "rings/7.png");
		gameProperties.getJewelry().addItemIntoShop(newItem6);

		Item newItem7 = new Item("implantát", 0, 0, 0, 40, 1, 0, 0, "", "rings/8.png");
		gameProperties.getPlayer().addGear(newItem7);
		gearImplant.setImage(new Image(this.getClass().getResourceAsStream("/images/1600x900/items/rings/8.png")));

		energy.setText(String.valueOf(player.getEnergy()));
		fullness.setText(String.valueOf(player.getFullness()));
		hydration.setText(String.valueOf(player.getHydration()));
		health.setText(String.valueOf(player.getHealth()));
		credits.setText(String.valueOf(player.getCredits()));

		loadingIndicatorTimeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(loadingIndicator.progressProperty(), 0)),
				new KeyFrame(Duration.seconds(1), new KeyValue(loadingIndicator.progressProperty(), 1)));

		loadingIndicatorTimeline.setOnFinished(event -> {
			paneMap.setVisible(false);
			loadingIndicator.setVisible(false);
			actual.setImage(navigationActualImageKeeper);
			disableTravellingImages(false);
			keeperNodesVisibler(true);
		});

		loadingBarTimeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(loadingBar.progressProperty(), 0)),
				new KeyFrame(Duration.seconds(3), new KeyValue(loadingBar.progressProperty(), 1)));

		loadingBarTimeline.setOnFinished(event -> {
			paneLoading.setVisible(false);
		});

	}

	@FXML private void mainClick() throws InterruptedException
	{
		pazaak = new Pazaak(this);
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

		if (!pazaak.getPlayer().getCardsForMatch().get(numberOfSource).isUsed())
		{
			pazaak.setPlayersScore(pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(numberOfSource), pazaak.getCardsOnPlayersTable()));
			pazaak.getPlayer().getCardsForMatch().get(numberOfSource).used();
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

		if (!pazaak.getPlayer().getCardsForMatch().get(numberOfSource).isUsed())
		{
			pazaak.getPlayer().getCardsForMatch().get(numberOfSource).makeTurn(left);
			visualizationOfTableCards(true);
		}
	}

	public void visualizationOfTableCards(boolean visualizationOfPlayersSide)
	{
		int nthChildrenOfPazaakPane;
		List<RealCard> cardsOnTable;
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

		for (RealCard card : cardsOnTable)
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
		List<RealCard> cardsInHand;
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

		for (RealCard card : cardsInHand)
		{
			imageViewOfCard = (ImageView) imageViewsOfHandCards.get(nthChildrenOfPazaakPane);
			if (!card.isUsed() && !visualizationOfPlayersSide)
				imageViewOfCard.setImage(new Image(getClass().getResource(CardsImages.BACK.getFirstImage()).toString()));
			else if (!card.isUsed() && visualizationOfPlayersSide)
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

	private Image whichSideOfCard(RealCard card)
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

		for (RealCard card : pazaak.getPlayer().getCardsForMatch())
		{
			if (!card.isUsed())
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
		PaziaatusController classObj = this;

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

	@FXML private void mouseEnteredImgGlow03(Event event)
	{
		ImageView enteredImg = (ImageView) event.getSource();
		enteredImg.setEffect(new Glow(0.3));
	}

	@FXML private void mouseEnteredImgGlow06(Event event)
	{
		ImageView enteredImg = (ImageView) event.getSource();
		enteredImg.setEffect(new Glow(0.6));
	}

	@FXML private void mouseEnteredImgGlow08(Event event)
	{
		ImageView enteredImg = (ImageView) event.getSource();
		enteredImg.setEffect(new Glow(0.8));
	}

	@FXML private void mouseExitedImg(Event event)
	{
		ImageView enteredImg = (ImageView) event.getSource();
		enteredImg.setEffect(null);
	}

	@FXML private void gameClcs(Event event)
	{
		switch (Tools.idFromSource(event.toString()))
		{
			case "character":
			{
				paneMap.setVisible(false);
				keeperNodesVisibler(false);
				characterNodesVisibler(true);
				break;
			}
			case "actual":
			{
				characterNodesVisibler(false);
				paneMap.setVisible(false);
				keeperNodesVisibler(true);
				break;
			}
			case "map":
			{
				keeperNodesVisibler(false);
				characterNodesVisibler(false);
				paneMap.setVisible(true);
				break;
			}
			case "featuresSelect":
			{
				if (gameProperties.isFeaturesOfGear())
					featuresSelect.setImage(new Image(this.getClass().getResourceAsStream("/images/1600x900/hud/residue/item_info.png")));
				else
					featuresSelect.setImage(new Image(this.getClass().getResourceAsStream("/images/1600x900/hud/residue/char_info.png")));
				gameProperties.setFeaturesOfGear(!gameProperties.isFeaturesOfGear());
				break;
			}
			default:
			{
				yesOrNoDialog("exit");
			}
		}
	}

	private void yesOrNoDialog(String idFromSource)
	{
		dialogCaller = idFromSource;

		switch (idFromSource)
		{
			case "exit":
			{
				dialogText.setText("Opravdu chcete odejít do hlaního menu?\n\nUložil jste si pozici?");
				break;
			}
			case "trash1":
			{
				dialogText.setText("Opravdu chcete smazat herní pozici číslo 1?");
				break;
			}
			case "trash2":
			{
				dialogText.setText("Opravdu chcete smazat herní pozici číslo 2?");
				break;
			}
			case "trash3":
			{
				dialogText.setText("Opravdu chcete smazat herní pozici číslo 3?");
			}
		}

		dialogNo.setVisible(true);
		dialogYes.setVisible(true);
		dialogOk.setVisible(false);

		showDialog();
	}

	private void disableTravellingImages(boolean disable)
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);

		List<Node> children = paneMap.getChildren();

		for (int i = 0; i < children.size() - 1; i++)
		{
			children.get(i).setEffect(disable ? colorAdjust : null);
			children.get(i).setDisable(disable);
		}

		actual.setEffect(disable ? colorAdjust : null);
		character.setEffect(disable ? colorAdjust : null);
		map.setEffect(disable ? colorAdjust : null);
		exit.setEffect(disable ? colorAdjust : null);

		actual.setDisable(disable);
		character.setDisable(disable);
		map.setDisable(disable);
		exit.setDisable(disable);
	}

	@FXML private void travellingClcs(Event event)
	{
		String idOfSource = Tools.idFromSource(event.toString());

		switch (idOfSource)
		{
			case "travel":
			{
				addNodesToKeeper(middle, paneTravel);
				break;
			}
			case "sleep":
			{
				addNodesToKeeper(middle, paneSleep);
				break;
			}
			case "weapons":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures, paneShop);
				shopBanner.setImage(getImg("banners/banner_weapons.png", false));
				setShopsImages(gameProperties.getWeapons());
				break;
			}
			case "cantine":
			{
				addNodesToKeeper(middle, paneCards, paneFeatures, paneCantine);
				break;
			}
			case "food":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures, paneShop);
				shopBanner.setImage(getImg("banners/banner_food.png", false));
				setShopsImages(gameProperties.getFood());
				break;
			}
			case "armor":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures, paneShop);
				shopBanner.setImage(getImg("banners/banner_armor.png", false));
				setShopsImages(gameProperties.getArmor());
				break;
			}
			case "technician":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures, paneShop);
				shopBanner.setImage(getImg("banners/banner_technician.png", false));
				setShopsImages(gameProperties.getTech());
				break;
			}
			case "work":
			{
				addNodesToKeeper(middle, paneWork);
				break;
			}
			case "medications":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures, paneShop);
				shopBanner.setImage(getImg("banners/banner_medications.png", false));
				setShopsImages(gameProperties.getMedications());
				break;
			}
			case "jewelry":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures, paneShop);
				shopBanner.setImage(getImg("banners/banner_jewelry.png", false));
				setShopsImages(gameProperties.getJewelry());
				break;
			}
			default:
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures, paneFuelOrRepair);
			}
		}

		if (/* gameProperties.getPlayer().getPlanet().equals("Narr Sheyda") && */ Tools.getRandomNumber(8) == 0)
			gameProperties.getMissionsAndAssaults().assault();

		loadingIndicator.setVisible(true);
		navigationActualImageKeeper = getImg("/icons/navigation/navigation_" + idOfSource + ".png", false);
		disableTravellingImages(true);
		loadingIndicatorTimeline.play();
	}

	private void setShopsImages(Shop shop) // TODO
	{
		gameProperties.setActualShop(shop);
		int i = 0;
		for (Item item : shop.getShopItems())
		{
			if (item != null)
				setImageOfNthSlot(i + 1, item.getImg(), true);
			else
				setImageOfNthSlot(i + 1, null, true);
			i++;
		}
	}

	private void addNodesToKeeper(Node... nodes)
	{
		keeperNodesVisibler(false);
		paneActualNodesKeeper.clear();
		for (Node node : nodes)
			paneActualNodesKeeper.add(node);
	}

	private void keeperNodesVisibler(boolean visible)
	{
		for (Node node : paneActualNodesKeeper)
			node.setVisible(visible);
	}

	private void characterNodesVisibler(boolean visible)
	{
		paneGear.setVisible(visible);
		paneCards.setVisible(visible);
		paneInventory.setVisible(visible);
		paneFeatures.setVisible(visible);
		middle.setVisible(visible);
	}

	@FXML private void mainMenuClcs(Event event)
	{
		switch (Tools.idFromSource(event.toString()))
		{
			case "game1": // TODO načítání jednotlivých pozic
			{
				characterNodesVisibler(true);
				paneMainMenu.setVisible(false);
				break;
			}
			case "game2":
			{
				characterNodesVisibler(true);
				paneMainMenu.setVisible(false);
				break;
			}
			case "game3":
			{
				characterNodesVisibler(true);
				paneMainMenu.setVisible(false);
				break;
			}
			case "trash1": // TODO mazání herních pozic
			{
				yesOrNoDialog("trash1");
				break;
			}
			case "trash2":
			{
				yesOrNoDialog("trash2");
				break;
			}
			case "trash3":
			{
				yesOrNoDialog("trash3");
				break;
			}
			case "options":
			{
				switch (gameProperties.getResolution())
				{
					case "1600x900":
					{
						arrow.setLayoutY(330);
						break;
					}
					case "1280x720":
					{
						arrow.setLayoutY(410);
						break;
					}
					case "960x540":
					{
						arrow.setLayoutY(491);
						break;
					}
					default:
						arrow.setLayoutY(572);
				}
				gameProperties.setNewResolution(gameProperties.getResolution());
				paneOptions.setVisible(true);
				break;
			}
			case "howToPlay":
			{
				paneHowToPlay.setVisible(true);
				break;
			}
			case "howToPlayOk":
			{
				paneHowToPlay.setVisible(false);
				break;
			}
			default:
			{
				Stage stage = (Stage) quit.getScene().getWindow();
				stage.close();
			}
		}

	}

	@FXML private void optionsClcs(Event event)
	{
		switch (Tools.idFromSource(event.toString()))
		{
			case "res1":
			{
				gameProperties.setNewResolution("1600x900");
				arrow.setLayoutY(330);
				break;
			}
			case "res2":
			{
				gameProperties.setNewResolution("1280x720");
				arrow.setLayoutY(410);
				break;
			}
			case "res3":
			{
				gameProperties.setNewResolution("960x540");
				arrow.setLayoutY(491);
				break;
			}
			case "res4":
			{
				gameProperties.setNewResolution("640x360");
				arrow.setLayoutY(572);
				break;
			}
			case "cancel":
			{
				options.setVisible(false);
				break;
			}
			default:
			{
				gameProperties.setResolution(gameProperties.getNewResolution());
				options.setVisible(false);
			}
		}
	}

	@FXML private void shopSlotClc(Event event)
	{
		MouseEvent mouseEvent = (MouseEvent) event;
		if (mouseEvent.getButton() == MouseButton.PRIMARY)
			itemLeftClc(event, false);
		else if (mouseEvent.getButton() == MouseButton.SECONDARY)
			shopSlotRightClc(event);
	}

	private void shopSlotRightClc(Event event)
	{
		int nthSlot = -1;
		Item item = null;

		ImageView img = (ImageView) event.getSource();
		if (img.getImage() == null)
			return;

		item = gameProperties.getActualShop().getNthShopItem(Tools.getNumberFromString(Tools.idFromSource(event.toString()), "shopSlot") - 1);
		nthSlot = gameProperties.getPlayer().addItemInInventory(item);

		if (nthSlot == -1)
			return;

		if (img.getImage() != null)
			img.setImage(null);

		setImageOfNthSlot(nthSlot + 1, item.getImg(), false);
	}

	private void setImageOfNthSlot(int nthSlot, String imgPath, boolean shop)
	{
		String fieldName;
		if (shop)
			fieldName = "shopSlot" + nthSlot;
		else
			fieldName = "invSlot" + nthSlot;

		ImageView fieldValue = null; // invSlot2
		PaziaatusController classObj = this;
		try
		{
			Field field = classObj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			fieldValue = (ImageView) field.get(classObj);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
		{
			e.printStackTrace();
		}

		if (imgPath == null)
			fieldValue.setImage(null);
		else
			fieldValue.setImage(getImg(imgPath, true));
	}

	private Image getImg(String partOfPath, boolean items)
	{
		String itemsOrHud;
		if (items)
			itemsOrHud = "/items/";
		else
			itemsOrHud = "/hud/";

		return new Image(this.getClass().getResourceAsStream("/images/" + gameProperties.getResolution() + itemsOrHud + partOfPath));
	}

	@FXML private void invSlotClc(Event event)
	{
		MouseEvent mouseEvent = (MouseEvent) event;
		if (mouseEvent.getButton() == MouseButton.PRIMARY)
			itemLeftClc(event, true);
		else if (mouseEvent.getButton() == MouseButton.SECONDARY)
			invSlotRightClc(event);
	}

	@FXML private void gearSlotClicked(Event event)
	{
		MouseEvent mouseEvent = (MouseEvent) event;
		if (mouseEvent.getButton() == MouseButton.PRIMARY)
			gearSlotClick(event, true);
		else if (mouseEvent.getButton() == MouseButton.SECONDARY)
			gearSlotClick(event, false);
	}

	private void gearSlotClick(Event event, boolean leftClick)
	{
		int nthSlot = 0;
		List<Node> paneGearContent = paneGear.getChildren();

		for (; nthSlot < paneGearContent.size(); nthSlot++)
		{
			if (event.getSource().equals(paneGearContent.get(nthSlot)))
				break;
		}
		nthSlot--;

		if (gameProperties.getPlayer().getNthGear(nthSlot) == null)
			return;

		Item item = gameProperties.getPlayer().getNthGear(nthSlot);

		if (leftClick)
		{
			DropShadow dropShadow = new DropShadow();
			dropShadow.setColor(Color.WHITE);

			ImageView gearImageView = (ImageView) paneGear.getChildren().get(nthSlot + 1);
			gearImageView.setEffect(dropShadow);

			showFeatures(item);
		} else
			takeOffGear(nthSlot, item);
	}

	private void takeOffGear(int nthGearSlot, Item item)
	{
		int nthInventorySlot = gameProperties.getPlayer().addItemInInventory(item);

		if (nthInventorySlot == -1)
			generalDialog("Není dostatek místa v inventáři.");
		else
		{
			gameProperties.getPlayer().getGear()[nthGearSlot] = null;
			ImageView gearImageView = (ImageView) paneGear.getChildren().get(nthGearSlot + 1);
			ImageView inventoryImageView = (ImageView) paneInventory.getChildren().get(nthInventorySlot + 1);
			setImage(gearImageView, inventoryImageView, false);
			gearImageView.setImage(getGearDefaultImage(nthGearSlot));

			gearImageView.setEffect(null);
			gearImageView.setOnMouseEntered(null);
			gearImageView.setOnMouseExited(null);
		}
	}

	private Image getGearDefaultImage(int nthGearSlot)
	{
		String partOfPath;
		switch (nthGearSlot)
		{
			case 0:
				partOfPath = "implant";
				break;
			case 1:
				partOfPath = "helmet";
				break;
			case 2:
				partOfPath = "necklace";
				break;
			case 3:
				partOfPath = "artifact";
				break;
			case 5:
				partOfPath = "waist";
				break;
			case 6:
				partOfPath = "gloves";
				break;
			case 7:
				partOfPath = "ring";
				break;
			case 8:
				partOfPath = "speeder";
				break;
			case 9:
				partOfPath = "boots";
				break;
			case 10:
				partOfPath = "belt";
				break;
			case 11:
				partOfPath = "droid";
				break;
			default:
				partOfPath = "hand";
		}
		return getImg("icons/gear/gear_" + partOfPath + ".png", false);
	}

	private void invSlotRightClc(Event event)
	{
		ImageView img = (ImageView) event.getSource();
		if (img.getImage() == null)
			return;

		int nthSlot = Tools.getNumberFromString(Tools.idFromSource(event.toString()), "invSlot") - 1;

		if (paneGear.isVisible())
		{
			changeGear(nthSlot);

		} else if (paneFuelOrRepair.isVisible())
		{

		} else
		{
			gameProperties.getPlayer().sellItem(nthSlot);
			//gameOptions.getPlayer().addOrRemoveCredits(item.getPrice());
			ImageView invImageView = (ImageView) paneInventory.getChildren().get(nthSlot + 1);
			invImageView.setImage(null);

		}
	}

	private void changeGear(int nthInvSlot)
	{
		Item item = gameProperties.getPlayer().getNthItemFromInventory(nthInvSlot);
		int nthGearSlot = getNthGearSlot(item.getItemType());
		Item gear = gameProperties.getPlayer().getNthGear(nthGearSlot);

		gameProperties.getPlayer().changeItem(gameProperties.getPlayer().changeGear(item, nthGearSlot), nthInvSlot);
		ImageView gearImgView = (ImageView) paneGear.getChildren().get(nthGearSlot + 1);
		ImageView inventoryImageView = (ImageView) paneInventory.getChildren().get(nthInvSlot + 1);

		if (gear != null)
		{
			swapImages(gearImgView, inventoryImageView);
		} else
		{
			setImage(gearImgView, inventoryImageView, true);
		}
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

	protected synchronized void changeValuesOfNecessitiesOfLife()
	{
		energy.setText(String.valueOf(gameProperties.getPlayer().getEnergy()));
		fullness.setText(String.valueOf(gameProperties.getPlayer().getFullness()));
		hydration.setText(String.valueOf(gameProperties.getPlayer().getHydration()));
		health.setText(String.valueOf(gameProperties.getPlayer().getHealth()));
	}

	private void swapImages(ImageView gearImageView, ImageView inentoryvImageView)
	{
		Image tmpImg = gearImageView.getImage();
		gearImageView.setImage(inentoryvImageView.getImage());
		inentoryvImageView.setImage(tmpImg);
	}

	private void setImage(ImageView gearImageView, ImageView inventoryImageView, boolean setGearImageView)
	{
		if (setGearImageView)
		{
			gearImageView.setImage(inventoryImageView.getImage());
			inventoryImageView.setImage(null);
		} else
			inventoryImageView.setImage(gearImageView.getImage());
	}

	private void itemLeftClc(Event event, boolean inventory)
	{
		ImageView image = (ImageView) event.getSource();
		if (image.getImage() == null)
			return;

		swapEffectsOfImages(image);

		String prefix;
		if (inventory)
			prefix = "invSlot";
		else
			prefix = "shopSlot";

		int nthSlot = Tools.getNumberFromString(Tools.idFromSource(event.toString()), prefix) - 1;

		if (inventory)
			showFeatures(gameProperties.getPlayer().getNthItemFromInventory(nthSlot));
		else
			showFeatures(gameProperties.getActualShop().getNthShopItem(nthSlot));

	}

	private void swapEffectsOfImages(ImageView newImage)
	{
		ImageView oldImage = gameProperties.getClickedImage();
		if (oldImage != null)
		{
			oldImage.setEffect(null);
			oldImage.setOnMouseEntered(event -> {
				mouseEnteredImgGlow03(event);
			});
			oldImage.setOnMouseExited(event -> {
				mouseExitedImg(event);
			});
		}

		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.WHITE);

		newImage.setEffect(dropShadow);
		newImage.setOnMouseExited(null);
		newImage.setOnMouseEntered(null);
		gameProperties.setClickedImage(newImage);
	}

	private void showFeatures(Item item)
	{
		gameProperties.setClickedItem(item);

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
		featuresTitles.setImage(getImg(partOfPath, false));

		String[] values = item.getContentOfFieldsLikeString();

		featuresName.setText(itemType);
		features1.setText(values[0]);
		features2.setText(values[1]);
		features3.setText(values[2]);
		features4.setText(values[3]);
		features5.setText(values[4]);
		features6.setText(values[5]);
		features7.setText(values[6]);
	}

	@FXML private void dialogYesClick(Event event)
	{
		blurGroup.setEffect(null);
		switch (dialogCaller)
		{
			case "exit":
			{
				gameProperties.stopTimeChecker();
				keeperNodesVisibler(false);
				paneMap.setVisible(false);
				paneMainMenu.setVisible(true);
			}
		}

		blurGroup.setEffect(null);
		paneDialog.setVisible(false);
	}

	@FXML private void dialogNoOrOkCick(Event event)
	{
		blurGroup.setEffect(null);
		paneDialog.setVisible(false);
	}

	public void generalDialog(String text)
	{
		dialogText.setText(text);
		dialogNo.setVisible(false);
		dialogYes.setVisible(false);
		dialogOk.setVisible(true);

		showDialog();
	}

	private void showDialog()
	{
		GaussianBlur blurEffect = new GaussianBlur(15);
		blurGroup.setEffect(blurEffect);

		paneDialog.setVisible(true);
	}

	@FXML private void travelClicks(Event event)
	{
		MouseEvent mouseEvent = (MouseEvent) event;
		if (mouseEvent.getButton() != MouseButton.PRIMARY)
			return;

		switch (Tools.idFromSource(event.toString()))
		{
			case "travelLeft":
			{
				travelLeft.setVisible(false);
				priceLeft.setVisible(false);
				gameProperties.getPlayer().setPlanet("Narr Sheyda");
				break;
			}
			case "travelRight":
			{
				travelRight.setVisible(false);
				priceRight.setVisible(false);
				gameProperties.getPlayer().setPlanet("Kerusant");
			}
		}
	}

	private void workDuration()
	{
		int duration = gameProperties.getPlayer().getWeight() / 4;

		loadingIndicatorTimeline.getKeyFrames().set(1, new KeyFrame(Duration.seconds(duration), new KeyValue(loadingBar.progressProperty(), 1)));
	}

	@FXML private void workClicks(Event event)
	{
		MouseEvent mouseEvent = (MouseEvent) event;
		if (mouseEvent.getButton() != MouseButton.PRIMARY)
			return;

		switch (Tools.idFromSource(event.toString()))
		{
			case "hunter":
			{
				paneLoading.setVisible(true);
				loadingIndicatorTimeline.play();
				generalDialog("Vrátil ses z práce lovce.\n\nVydělal sis:\n xxx kreditů");
				break;
			}
			case "worker":
			{
				paneLoading.setVisible(true);
				loadingIndicatorTimeline.play();
				generalDialog("Vrátil ses z práce.\n\nVydělal sis:\n xxx kreditů");
				break;
			}
			case "archeologist":
			{
				paneLoading.setVisible(true);
				loadingIndicatorTimeline.play();
				generalDialog("Vrátil ses z práce archeologa.\n\nVydělal sis:\n xxx kreditů");
				break;
			}
			case "technician":
			{
				paneLoading.setVisible(true);
				loadingIndicatorTimeline.play();
				generalDialog("Vrátil ses z práce technika.\n\nVydělal sis:\n xxx kreditů");
			}
		}
	}
}