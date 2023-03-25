package main;

import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beings.Player;
import pazaak.CardsImages;
import pazaak.Pazaak;
import pazaak.RealCard;

public class PaziaatusController
{
	private Pazaak		pazaak;
	private GameOptions	gameOptions;

	private List<Node> paneActualNodesKeeper;

	@FXML private Pane	paneMap;
	@FXML private Pane	panePazaak;
	@FXML private Pane	paneMainMenu;
	@FXML private Pane	paneOptions;
	@FXML private Pane	paneTravel;
	@FXML private Pane	paneSleep;
	@FXML private Pane	paneWeapons;
	@FXML private Pane	paneRepair;
	@FXML private Pane	paneCantine;
	@FXML private Pane	paneFood;
	@FXML private Pane	paneArmor;;
	@FXML private Pane	paneTech;
	@FXML private Pane	paneMission;
	@FXML private Pane	paneMedications;
	@FXML private Pane	paneJewelry;
	@FXML private Pane	paneFuel;
	@FXML private Pane	paneCharacter;
	@FXML private Pane	paneGear;
	@FXML private Pane	paneCards;
	@FXML private Pane	paneInventory;
	@FXML private Pane	paneFeatures;
	@FXML private Pane	paneMiddle;

	@FXML public ImageView	stand;
	@FXML public ImageView	next;

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

	@FXML private ImageView	point1Pl;
	@FXML private ImageView	point2Pl;
	@FXML private ImageView	point3Pl;
	@FXML private ImageView	point1En;
	@FXML private ImageView	point2En;
	@FXML private ImageView	point3En;

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
	@FXML private ImageView	features;
	@FXML private ImageView	itemInfo;
	@FXML private ImageView	charInfo;
	@FXML private ImageView	middle;

	@FXML private Text	playersScore;
	@FXML private Text	opponentsScore;

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
	@FXML private ImageView	mission;
	@FXML private ImageView	medications;
	@FXML private ImageView	jewelry;
	@FXML private ImageView	fuel;
	@FXML private ImageView	exit;

	@FXML public Button btnStart;

	public PauseTransition	opponentPlayedCard				= new PauseTransition(Duration.millis(650));
	public PauseTransition	visualizeWithDelayOpsTable		= new PauseTransition(Duration.millis(200));
	public PauseTransition	visualizeWithShortDelayPlsTable	= new PauseTransition(Duration.millis(400));
	public PauseTransition	visualizeWithDelayPlsTable		= new PauseTransition(Duration.millis(850));
	public PauseTransition	opponentPlayedCardSoWait		= new PauseTransition(Duration.millis(1400));

	@FXML private void initialize()
	{
		Player player = new Player(100, 99, 9, 1, 999999, 0, 0, 0, 0, 0, null);
		gameOptions = new GameOptions("1600x900", paneMainMenu, paneCantine, player);
		paneActualNodesKeeper = new ArrayList<>();
		
		energy.setText(String.valueOf(player.getEnergy()));
		fullness.setText(String.valueOf(player.getFullness()));
		hydration.setText(String.valueOf(player.getHydration()));
		health.setText(String.valueOf(player.getHealth()));
		credits.setText(String.valueOf(player.getCredits()));
	}

	@FXML private void start() throws InterruptedException
	{
		pazaak = new Pazaak(this);
		pointsHider();
		btnStart.setVisible(false);
		stand.setDisable(false);
		next.setDisable(false);

		opponentPlayedCard.setOnFinished(event -> {
			pazaak.waitingForCardUse();
		});
		visualizeWithDelayOpsTable.setOnFinished(event -> {
			visualizationOpponentsSide();
		});

		visualizeWithShortDelayPlsTable.setOnFinished(event -> {
			visualizationPlayersSide();
		});
		visualizeWithDelayPlsTable.setOnFinished(event -> {
			visualizationPlayersSide();
		});
		opponentPlayedCardSoWait.setOnFinished(event -> {
			visualizationPlayersSide();
		});

		pazaak.newGame();
	}

	private void pointsHider()
	{
		point1Pl.setVisible(false);
		point2Pl.setVisible(false);
		point3Pl.setVisible(false);
		point1En.setVisible(false);
		point2En.setVisible(false);
		point3En.setVisible(false);
	}

	@FXML private void endTurn() throws InterruptedException
	{
		next.setDisable(true);
		stand.setDisable(true);
		darkenAllPlayersHandCards();
		pazaak.opponentsTurn();
	}

	@FXML private void stand() throws InterruptedException
	{
		pazaak.setPlayerStand(true);
		next.setDisable(true);
		stand.setDisable(true);
		darkenPlayersCards();
		pazaak.opponentsTurn();
	}

	@FXML private void firstCardClicked() throws InterruptedException
	{
		if (!pazaak.getPlayer().getCardsForMatch().get(0).isUsed())
		{
			pazaak.setPlayersScore(
					pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(0), pazaak.getCardsOnPlayersTable()));
			pazaak.getPlayer().getCardsForMatch().get(0).used();
			visualizationPlayersSide();
			darkenPlayersHandCards(1);
		}
	}

	@FXML private void secondCardClicked() throws InterruptedException
	{
		if (!pazaak.getPlayer().getCardsForMatch().get(1).isUsed())
		{
			pazaak.setPlayersScore(
					pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(1), pazaak.getCardsOnPlayersTable()));
			pazaak.getPlayer().getCardsForMatch().get(1).used();
			visualizationPlayersSide();
			darkenPlayersHandCards(2);
		}
	}

	@FXML private void thirdCardClicked() throws InterruptedException
	{
		if (!pazaak.getPlayer().getCardsForMatch().get(2).isUsed())
		{
			pazaak.setPlayersScore(
					pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(2), pazaak.getCardsOnPlayersTable()));
			pazaak.getPlayer().getCardsForMatch().get(2).used();
			visualizationPlayersSide();
			darkenPlayersHandCards(3);
		}
	}

	@FXML private void fourthCardClicked() throws InterruptedException
	{
		if (!pazaak.getPlayer().getCardsForMatch().get(3).isUsed())
		{
			pazaak.setPlayersScore(
					pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(3), pazaak.getCardsOnPlayersTable()));
			pazaak.getPlayer().getCardsForMatch().get(3).used();
			visualizationPlayersSide();
			darkenPlayersHandCards(4);
		}
	}

	@FXML private void clickPlayersHandLeft1() throws InterruptedException
	{
		if (!pazaak.getPlayer().getCardsForMatch().get(0).isUsed())
		{
			pazaak.getPlayer().getCardsForMatch().get(0).makeLeftTurn();
			visualizationPlayersSide();
		}
	}

	@FXML private void clickPlayersHandLeft2() throws InterruptedException
	{
		if (!pazaak.getPlayer().getCardsForMatch().get(1).isUsed())
		{
			pazaak.getPlayer().getCardsForMatch().get(1).makeLeftTurn();
			visualizationPlayersSide();
		}
	}

	@FXML private void clickPlayersHandLeft3() throws InterruptedException
	{
		if (!pazaak.getPlayer().getCardsForMatch().get(2).isUsed())
		{
			pazaak.getPlayer().getCardsForMatch().get(2).makeLeftTurn();
			visualizationPlayersSide();
		}
	}

	@FXML private void clickPlayersHandLeft4() throws InterruptedException
	{
		if (!pazaak.getPlayer().getCardsForMatch().get(3).isUsed())
		{
			pazaak.getPlayer().getCardsForMatch().get(3).makeLeftTurn();
			visualizationPlayersSide();
		}
	}

	@FXML private void clickPlayersHandRight1() throws InterruptedException
	{
		if (!pazaak.getPlayer().getCardsForMatch().get(0).isUsed())
		{
			pazaak.getPlayer().getCardsForMatch().get(0).makeRightTurn();
			visualizationPlayersSide();
		}
	}

	@FXML private void clickPlayersHandRight2() throws InterruptedException
	{
		if (!pazaak.getPlayer().getCardsForMatch().get(1).isUsed())
		{
			pazaak.getPlayer().getCardsForMatch().get(1).makeRightTurn();
			visualizationPlayersSide();
		}
	}

	@FXML private void clickPlayersHandRight3() throws InterruptedException
	{
		if (!pazaak.getPlayer().getCardsForMatch().get(2).isUsed())
		{
			pazaak.getPlayer().getCardsForMatch().get(2).makeRightTurn();
			visualizationPlayersSide();
		}
	}

	@FXML private void clickPlayersHandRight4() throws InterruptedException
	{
		if (!pazaak.getPlayer().getCardsForMatch().get(3).isUsed())
		{
			pazaak.getPlayer().getCardsForMatch().get(3).makeRightTurn();
			visualizationPlayersSide();
		}
	}

	public void dialogDraw()
	{
		Dialog<String> dialog = new Dialog<>();

		dialog.setContentText("The set is ted.");

		ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(type);

		dialog.showAndWait();
		brightenAllCards();
	}

	public void dialogPlayerWinsTheSet()
	{
		Dialog<String> dialog = new Dialog<>();

		dialog.setContentText("You win the set");

		ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(type);

		dialog.showAndWait();
		brightenAllCards();
	}

	public void dialogOpponentWinsTheSet()
	{
		Dialog<String> dialog = new Dialog<>();

		dialog.setContentText("The opponent wins the set");

		ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(type);

		dialog.showAndWait();
		brightenAllCards();
	}

	public void dialogOpponentWinsTheGame()
	{
		Dialog<String> dialog = new Dialog<>();

		dialog.setContentText("The opponent wins the game");

		ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(type);

		dialog.showAndWait();
		brightenAllCards();
	}

	public void dialogPlayerWinsTheGame()
	{
		Dialog<String> dialog = new Dialog<>();

		dialog.setContentText("You wins the game");

		ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(type);

		dialog.showAndWait();
		brightenAllCards();
	}

	public void visualizationOpponentsSide()
	{
		int i;

		opponentsScore.setText(pazaak.getOpponentsScore());

		for (i = 0; i < pazaak.getCardsOnOpponentsTable().size(); i++)
		{
			switch (i)
			{
				case 0 -> imgOpponentsTable1.setImage(whichSideOfCard(pazaak.getCardsOnOpponentsTable(), i));
				case 1 -> imgOpponentsTable2.setImage(whichSideOfCard(pazaak.getCardsOnOpponentsTable(), i));
				case 2 -> imgOpponentsTable3.setImage(whichSideOfCard(pazaak.getCardsOnOpponentsTable(), i));
				case 3 -> imgOpponentsTable4.setImage(whichSideOfCard(pazaak.getCardsOnOpponentsTable(), i));
				case 4 -> imgOpponentsTable5.setImage(whichSideOfCard(pazaak.getCardsOnOpponentsTable(), i));
				case 5 -> imgOpponentsTable6.setImage(whichSideOfCard(pazaak.getCardsOnOpponentsTable(), i));
				case 6 -> imgOpponentsTable7.setImage(whichSideOfCard(pazaak.getCardsOnOpponentsTable(), i));
				case 7 -> imgOpponentsTable8.setImage(whichSideOfCard(pazaak.getCardsOnOpponentsTable(), i));
				case 8 -> imgOpponentsTable9.setImage(whichSideOfCard(pazaak.getCardsOnOpponentsTable(), i));
			}
		}

		for (i = 0; i < pazaak.getOpponent().getCardsForMatch().size(); i++)
		{
			switch (i)
			{
				case 0 ->
				{
					if (!pazaak.getOpponent().getCardsForMatch().get(i).isUsed())
						imgOpponentsHand1.setImage(
								new Image(getClass().getResource(CardsImages.BACK.getFirstImage()).toString()));
					else
						imgOpponentsHand1.setImage(null);
				}
				case 1 ->
				{
					if (!pazaak.getOpponent().getCardsForMatch().get(i).isUsed())
						imgOpponentsHand2.setImage(
								new Image(getClass().getResource(CardsImages.BACK.getFirstImage()).toString()));
					else
						imgOpponentsHand2.setImage(null);
				}
				case 2 ->
				{
					if (!pazaak.getOpponent().getCardsForMatch().get(i).isUsed())
						imgOpponentsHand3.setImage(
								new Image(getClass().getResource(CardsImages.BACK.getFirstImage()).toString()));
					else
						imgOpponentsHand3.setImage(null);
				}
				case 3 ->
				{
					if (!pazaak.getOpponent().getCardsForMatch().get(i).isUsed())
						imgOpponentsHand4.setImage(
								new Image(getClass().getResource(CardsImages.BACK.getFirstImage()).toString()));
					else
						imgOpponentsHand4.setImage(null);
				}
			}
		}
	}

	public void visualizationPlayersSide()
	{
		int i;

		playersScore.setText(String.valueOf(pazaak.getPlayersScore()));

		for (i = 0; i < pazaak.getCardsOnPlayersTable().size(); i++)
		{
			switch (i)
			{
				case 0 -> imgPlayersTable1.setImage(whichSideOfCard(pazaak.getCardsOnPlayersTable(), i));
				case 1 -> imgPlayersTable2.setImage(whichSideOfCard(pazaak.getCardsOnPlayersTable(), i));
				case 2 -> imgPlayersTable3.setImage(whichSideOfCard(pazaak.getCardsOnPlayersTable(), i));
				case 3 -> imgPlayersTable4.setImage(whichSideOfCard(pazaak.getCardsOnPlayersTable(), i));
				case 4 -> imgPlayersTable5.setImage(whichSideOfCard(pazaak.getCardsOnPlayersTable(), i));
				case 5 -> imgPlayersTable6.setImage(whichSideOfCard(pazaak.getCardsOnPlayersTable(), i));
				case 6 -> imgPlayersTable7.setImage(whichSideOfCard(pazaak.getCardsOnPlayersTable(), i));
				case 7 -> imgPlayersTable8.setImage(whichSideOfCard(pazaak.getCardsOnPlayersTable(), i));
				case 8 -> imgPlayersTable9.setImage(whichSideOfCard(pazaak.getCardsOnPlayersTable(), i));
			}
		}

		for (i = 0; i < pazaak.getPlayer().getCardsForMatch().size(); i++)
		{
			switch (i)
			{
				case 0 ->
				{
					if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
						imgPlayersHand1.setImage(whichSideOfCard(pazaak.getPlayer().getCardsForMatch(), i));
					else
					{
						imgPlayersHand1.setImage(null);
						handLeft1.setVisible(false);
						handRight1.setVisible(false);
					}
				}
				case 1 ->
				{
					if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
						imgPlayersHand2.setImage(whichSideOfCard(pazaak.getPlayer().getCardsForMatch(), i));
					else
					{
						imgPlayersHand2.setImage(null);
						handLeft2.setVisible(false);
						handRight2.setVisible(false);
					}
				}
				case 2 ->
				{
					if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
						imgPlayersHand3.setImage(whichSideOfCard(pazaak.getPlayer().getCardsForMatch(), i));
					else
					{
						imgPlayersHand3.setImage(null);
						handLeft3.setVisible(false);
						handRight3.setVisible(false);
					}
				}
				case 3 ->
				{
					if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
						imgPlayersHand4.setImage(whichSideOfCard(pazaak.getPlayer().getCardsForMatch(), i));
					else
					{
						imgPlayersHand4.setImage(null);
						handLeft4.setVisible(false);
						handRight4.setVisible(false);
					}
				}
			}
		}
		if (!pazaak.isEnd())
		{
			setButtonsActive();
			brightenPlayersHandCards();
		}

	}

	private Image whichSideOfCard(List<RealCard> personsTable, int i)
	{
		Image image;
		if (personsTable.get(i).hasInactiveLeftTurn())
		{
			if (personsTable.get(i).hasInactiveRightTurn())
				image = new Image(
						getClass().getResource(personsTable.get(i).getCard().getImages().getFirstImage()).toString());
			else
				image = new Image(
						getClass().getResource(personsTable.get(i).getCard().getImages().getThirdImage()).toString());

		} else
		{
			if (personsTable.get(i).hasInactiveRightTurn())
				image = new Image(
						getClass().getResource(personsTable.get(i).getCard().getImages().getSecondImage()).toString());
			else
				image = new Image(
						getClass().getResource(personsTable.get(i).getCard().getImages().getFourthImage()).toString());
		}

		return image;
	}

	public void pointVisibler(int playersSets, int opponentsSets)
	{
		if (playersSets == 3)
			point1Pl.setVisible(true);
		else if (playersSets == 2)
			point2Pl.setVisible(true);
		else if (playersSets == 1)
			point3Pl.setVisible(true);

		if (opponentsSets == 3)
			point1En.setVisible(true);
		else if (opponentsSets == 2)
			point2En.setVisible(true);
		else if (opponentsSets == 1)
			point3En.setVisible(true);
	}

	public void resetScore()
	{
		playersScore.setText(String.valueOf(pazaak.getPlayersScore()));
		opponentsScore.setText(pazaak.getOpponentsScore());
	}

	public void clearImages()
	{
		imgPlayersTable1.setImage(null);
		imgPlayersTable2.setImage(null);
		imgPlayersTable3.setImage(null);
		imgPlayersTable4.setImage(null);
		imgPlayersTable5.setImage(null);
		imgPlayersTable6.setImage(null);
		imgPlayersTable7.setImage(null);
		imgPlayersTable8.setImage(null);
		imgPlayersTable9.setImage(null);

		imgOpponentsTable1.setImage(null);
		imgOpponentsTable2.setImage(null);
		imgOpponentsTable3.setImage(null);
		imgOpponentsTable4.setImage(null);
		imgOpponentsTable5.setImage(null);
		imgOpponentsTable6.setImage(null);
		imgOpponentsTable7.setImage(null);
		imgOpponentsTable8.setImage(null);
		imgOpponentsTable9.setImage(null);
	}

	public void hideAllHandButtons()
	{
		handLeft1.setVisible(false);
		handLeft2.setVisible(false);
		handLeft3.setVisible(false);
		handLeft4.setVisible(false);

		handRight1.setVisible(false);
		handRight2.setVisible(false);
		handRight3.setVisible(false);
		handRight4.setVisible(false);
	}

	public void hideHandCards()
	{

		imgOpponentsHand1.setVisible(false);
		imgOpponentsHand2.setVisible(false);
		imgOpponentsHand3.setVisible(false);
		imgOpponentsHand4.setVisible(false);

		imgPlayersHand1.setVisible(false);
		imgPlayersHand2.setVisible(false);
		imgPlayersHand3.setVisible(false);
		imgPlayersHand4.setVisible(false);

		handLeft1.setVisible(false);
		handLeft2.setVisible(false);
		handLeft3.setVisible(false);
		handLeft4.setVisible(false);

		handRight1.setVisible(false);
		handRight2.setVisible(false);
		handRight3.setVisible(false);
		handRight4.setVisible(false);
	}

	public void showHandCards()
	{

		imgOpponentsHand1.setVisible(true);
		imgOpponentsHand2.setVisible(true);
		imgOpponentsHand3.setVisible(true);
		imgOpponentsHand4.setVisible(true);

		imgPlayersHand1.setVisible(true);
		imgPlayersHand2.setVisible(true);
		imgPlayersHand3.setVisible(true);
		imgPlayersHand4.setVisible(true);
	}

	private void setButtonsActive()
	{
		next.setDisable(false);
		stand.setDisable(false);
	}

	public void activateHandButtons()
	{
		deactivateOldHandButtons();

		for (int i = 0; i < pazaak.getPlayer().getCardsForMatch().size(); i++)
		{
			switch (i)
			{
				case 0 ->
				{
					if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
					{
						if (!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getSecondImage()
								.equals(""))
							handLeft1.setVisible(true);
						if (!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getThirdImage()
								.equals(""))
							handRight1.setVisible(true);
					} else
						continue;
				}
				case 1 ->
				{
					if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
					{
						if (!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getSecondImage()
								.equals(""))
							handLeft2.setVisible(true);
						if (!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getThirdImage()
								.equals(""))
							handRight2.setVisible(true);
					} else
						continue;
				}
				case 2 ->
				{
					if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
					{
						if (!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getSecondImage()
								.equals(""))
							handLeft3.setVisible(true);
						if (!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getThirdImage()
								.equals(""))
							handRight3.setVisible(true);
					} else
						continue;
				}
				case 3 ->
				{
					if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
					{
						if (!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getSecondImage()
								.equals(""))
							handLeft4.setVisible(true);
						if (!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getThirdImage()
								.equals(""))
							handRight4.setVisible(true);
					} else
						continue;
				}
			}
		}
	}

	private void darkenAllPlayersHandCards()
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);
		imgPlayersHand1.setEffect(colorAdjust);
		imgPlayersHand2.setEffect(colorAdjust);
		imgPlayersHand3.setEffect(colorAdjust);
		imgPlayersHand4.setEffect(colorAdjust);
		imgPlayersHand1.setDisable(true);
		imgPlayersHand2.setDisable(true);
		imgPlayersHand3.setDisable(true);
		imgPlayersHand4.setDisable(true);
		handLeft1.setDisable(true);
		handLeft2.setDisable(true);
		handLeft3.setDisable(true);
		handLeft4.setDisable(true);
		handRight1.setDisable(true);
		handRight2.setDisable(true);
		handRight3.setDisable(true);
		handRight4.setDisable(true);
	}

	private void darkenPlayersHandCards(int nthUsedCard)
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);

		switch (nthUsedCard)
		{
			case 1:
			{
				imgPlayersHand2.setEffect(colorAdjust);
				imgPlayersHand3.setEffect(colorAdjust);
				imgPlayersHand4.setEffect(colorAdjust);
				imgPlayersHand2.setDisable(true);
				imgPlayersHand3.setDisable(true);
				imgPlayersHand4.setDisable(true);
			}
			case 2:
			{

				imgPlayersHand3.setEffect(colorAdjust);
				imgPlayersHand4.setEffect(colorAdjust);
				imgPlayersHand1.setDisable(true);
				imgPlayersHand3.setDisable(true);
				imgPlayersHand4.setDisable(true);
			}
			case 3:
			{
				imgPlayersHand1.setEffect(colorAdjust);
				imgPlayersHand2.setEffect(colorAdjust);
				imgPlayersHand4.setEffect(colorAdjust);
				imgPlayersHand1.setDisable(true);
				imgPlayersHand2.setDisable(true);
				imgPlayersHand4.setDisable(true);
			}
			case 4:
			{
				imgPlayersHand1.setEffect(colorAdjust);
				imgPlayersHand2.setEffect(colorAdjust);
				imgPlayersHand3.setEffect(colorAdjust);
				imgPlayersHand1.setDisable(true);
				imgPlayersHand2.setDisable(true);
				imgPlayersHand3.setDisable(true);
			}
		}
	}

	private void brightenPlayersHandCards()
	{
		imgPlayersHand1.setEffect(null);
		imgPlayersHand2.setEffect(null);
		imgPlayersHand3.setEffect(null);
		imgPlayersHand4.setEffect(null);
		imgPlayersHand1.setDisable(false);
		imgPlayersHand2.setDisable(false);
		imgPlayersHand3.setDisable(false);
		imgPlayersHand4.setDisable(false);
		handLeft1.setDisable(false);
		handLeft2.setDisable(false);
		handLeft3.setDisable(false);
		handLeft4.setDisable(false);
		handRight1.setDisable(false);
		handRight2.setDisable(false);
		handRight3.setDisable(false);
		handRight4.setDisable(false);
	}

	private void deactivateOldHandButtons()
	{
		handLeft1.setVisible(false);
		handRight1.setVisible(false);
		handLeft2.setVisible(false);
		handRight2.setVisible(false);
		handLeft3.setVisible(false);
		handRight3.setVisible(false);
		handLeft4.setVisible(false);
		handRight4.setVisible(false);
	}

	private void brightenAllCards()
	{
		imgPlayersTable1.setEffect(null);
		imgPlayersTable2.setEffect(null);
		imgPlayersTable3.setEffect(null);
		imgPlayersTable4.setEffect(null);
		imgPlayersTable5.setEffect(null);
		imgPlayersTable6.setEffect(null);
		imgPlayersTable7.setEffect(null);
		imgPlayersTable8.setEffect(null);
		imgPlayersTable9.setEffect(null);
		imgPlayersHand1.setEffect(null);
		imgPlayersHand2.setEffect(null);
		imgPlayersHand3.setEffect(null);
		imgPlayersHand4.setEffect(null);

		imgOpponentsTable1.setEffect(null);
		imgOpponentsTable2.setEffect(null);
		imgOpponentsTable3.setEffect(null);
		imgOpponentsTable4.setEffect(null);
		imgOpponentsTable5.setEffect(null);
		imgOpponentsTable6.setEffect(null);
		imgOpponentsTable7.setEffect(null);
		imgOpponentsTable8.setEffect(null);
		imgOpponentsTable9.setEffect(null);
		imgOpponentsHand1.setEffect(null);
		imgOpponentsHand2.setEffect(null);
		imgOpponentsHand3.setEffect(null);
		imgOpponentsHand4.setEffect(null);
	}

	public void darkenPlayersCards()
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);

		imgPlayersTable1.setEffect(colorAdjust);
		imgPlayersTable2.setEffect(colorAdjust);
		imgPlayersTable3.setEffect(colorAdjust);
		imgPlayersTable4.setEffect(colorAdjust);
		imgPlayersTable5.setEffect(colorAdjust);
		imgPlayersTable6.setEffect(colorAdjust);
		imgPlayersTable7.setEffect(colorAdjust);
		imgPlayersTable8.setEffect(colorAdjust);
		imgPlayersTable9.setEffect(colorAdjust);
		imgPlayersHand1.setEffect(colorAdjust);
		imgPlayersHand2.setEffect(colorAdjust);
		imgPlayersHand3.setEffect(colorAdjust);
		imgPlayersHand4.setEffect(colorAdjust);
	}

	public void darkenOpponentsCards()
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);

		imgOpponentsTable1.setEffect(colorAdjust);
		imgOpponentsTable2.setEffect(colorAdjust);
		imgOpponentsTable3.setEffect(colorAdjust);
		imgOpponentsTable4.setEffect(colorAdjust);
		imgOpponentsTable5.setEffect(colorAdjust);
		imgOpponentsTable6.setEffect(colorAdjust);
		imgOpponentsTable7.setEffect(colorAdjust);
		imgOpponentsTable8.setEffect(colorAdjust);
		imgOpponentsTable9.setEffect(colorAdjust);
		imgOpponentsHand1.setEffect(colorAdjust);
		imgOpponentsHand2.setEffect(colorAdjust);
		imgOpponentsHand3.setEffect(colorAdjust);
		imgOpponentsHand4.setEffect(colorAdjust);
	}

	@FXML private void mouseEnteredStandBtn()
	{
		stand.setEffect(new Glow(0.6));
	}

	@FXML private void mouseEnteredNextBtn()
	{
		next.setEffect(new Glow(0.6));
	}

	@FXML private void firstCardEntered()
	{
		imgPlayersHand1.setEffect(new Glow(0.3));
	}

	@FXML private void secondCardEntered()
	{
		imgPlayersHand2.setEffect(new Glow(0.3));
	}

	@FXML private void thirdCardEntered()
	{
		imgPlayersHand3.setEffect(new Glow(0.3));
	}

	@FXML private void fourthCardEntered()
	{
		imgPlayersHand4.setEffect(new Glow(0.3));
	}

	@FXML private void mouseEnteredHandRight1()
	{
		handRight1.setEffect(new Glow(0.6));
	}

	@FXML private void mouseEnteredHandRight2()
	{
		handRight2.setEffect(new Glow(0.6));
	}

	@FXML private void mouseEnteredHandRight3()
	{
		handRight3.setEffect(new Glow(0.6));
	}

	@FXML private void mouseEnteredHandRight4()
	{
		handRight4.setEffect(new Glow(0.6));
	}

	@FXML private void mouseEnteredHandLeft1()
	{
		handLeft1.setEffect(new Glow(0.6));
	}

	@FXML private void mouseEnteredHandLeft2()
	{
		handLeft2.setEffect(new Glow(0.6));
	}

	@FXML private void mouseEnteredHandLeft3()
	{
		handLeft3.setEffect(new Glow(0.6));
	}

	@FXML private void mouseEnteredHandLeft4()
	{
		handLeft4.setEffect(new Glow(0.6));
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
		switch (idFromSource(event.toString()))
		{
			case "character":
			{
				paneMap.setVisible(false);
				nodesVisibler(false);
				characterVisibler(true);
				break;
			}
			case "actual":
			{
				characterVisibler(false);
				paneMap.setVisible(false);
				nodesVisibler(true);
				break;
			}
			case "map":
			{
				nodesVisibler(false);
				characterVisibler(false);
				paneMap.setVisible(true);
				break;
			}
			case "travel":
			{
				addNodesToKeeper(middle);
				actual.setImage(new Image("/images/icons/navicons/navTravel.png"));
				changeActualPane(paneTravel);
				break;
			}
			case "sleep":
			{
				actual.setImage(new Image("/images/icons/navicons/navSleep.png"));
				changeActualPane(paneSleep);
				break;
			}
			case "weapons":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures);
				actual.setImage(new Image("/images/icons/navicons/navWeapons.png"));
				changeActualPane(paneWeapons);
				break;
			}
			case "repair":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures);
				actual.setImage(new Image("/images/icons/navicons/navRepair.png"));
				changeActualPane(paneRepair);
				break;
			}
			case "cantine":
			{
				addNodesToKeeper(middle, paneCards);
				actual.setImage(new Image("/images/icons/navicons/navCantine.png"));
				changeActualPane(paneCantine);
				break;
			}
			case "food":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures);
				actual.setImage(new Image("/images/icons/navicons/navFood.png"));
				changeActualPane(paneFood);
				break;
			}
			case "armor":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures);
				actual.setImage(new Image("/images/icons/navicons/navArmor.png"));
				changeActualPane(paneArmor);
				break;
			}
			case "tech":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures);
				actual.setImage(new Image("/images/icons/navicons/navTech.png"));
				changeActualPane(paneTech);
				break;
			}
			case "mission":
			{
				addNodesToKeeper(middle);
				actual.setImage(new Image("/images/icons/navicons/navMission.png"));
				changeActualPane(paneMission);
				break;
			}
			case "medications":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures);
				actual.setImage(new Image("/images/icons/navicons/navMedications.png"));
				changeActualPane(paneMedications);
				break;
			}
			case "jewelry":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures);
				actual.setImage(new Image("/images/icons/navicons/navJewelry.png"));
				changeActualPane(paneJewelry);
				break;
			}
			case "fuel":
			{
				addNodesToKeeper(middle, paneInventory, paneFeatures);
				actual.setImage(new Image("/images/icons/navicons/navFuel.png"));
				changeActualPane(paneFuel);
				break;
			}
			case "charInfo":
			{
				charInfo.setVisible(false);
				itemInfo.setVisible(true);
				break;
			}
			case "itemInfo":
			{
				itemInfo.setVisible(false);
				charInfo.setVisible(true);
				break;
			}
			default:
			{
				changeActivePane(paneMainMenu);
			}
		}
	}

	private void addNodesToKeeper(Node... nodes)
	{
		paneMap.setVisible(false);
		paneActualNodesKeeper.clear();
		for (Node node : nodes)
			paneActualNodesKeeper.add(node);
		nodesVisibler(true);

	}

	private void nodesVisibler(boolean visible)
	{
		for (Node node : paneActualNodesKeeper)
			node.setVisible(visible);
	}

	private void characterVisibler(boolean visible)
	{
		paneGear.setVisible(visible);
		paneCards.setVisible(visible);
		paneInventory.setVisible(visible);
		paneFeatures.setVisible(visible);
		middle.setVisible(visible);
	}

	@FXML private void mainMenuClcs(Event event)
	{
		switch (idFromSource(event.toString()))
		{
			case "game1": // TODO načítání jednotlivých pozic
			{
				changeActivePane(paneCharacter);
				break;
			}
			case "game2":
			{
				changeActivePane(paneCharacter);
				break;
			}
			case "game3":
			{
				changeActivePane(paneCharacter);
				break;
			}
			case "trash1": // TODO mazání herních pozic
			{
				break;
			}
			case "trash2":
			{
				break;
			}
			case "trash3":
			{
				break;
			}
			case "options":
			{
				if (gameOptions.getResolution().equals("1600x900"))
					arrow.setLayoutY(330);
				else if (gameOptions.getResolution().equals("1280x720"))
					arrow.setLayoutY(410);
				else if (gameOptions.getResolution().equals("960x540"))
					arrow.setLayoutY(491);
				else
					arrow.setLayoutY(572);

				gameOptions.setNewResolution(gameOptions.getResolution());
				changeActivePane(paneOptions);
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
		switch (idFromSource(event.toString()))
		{
			case "res1":
			{
				gameOptions.setNewResolution("1600x900");
				arrow.setLayoutY(330);
				break;
			}
			case "res2":
			{
				gameOptions.setNewResolution("1280x720");
				arrow.setLayoutY(410);
				break;
			}
			case "res3":
			{
				gameOptions.setNewResolution("960x540");
				arrow.setLayoutY(491);
				break;
			}
			case "res4":
			{
				gameOptions.setNewResolution("640x360");
				arrow.setLayoutY(572);
				break;
			}
			case "cancel":
			{
				changeActivePane(paneMainMenu);
				break;
			}
			default:
			{
				gameOptions.setResolution(gameOptions.getNewResolution());
				changeActivePane(paneMainMenu);
			}
		}
	}

	private void changeActivePane(Pane pane)
	{
		gameOptions.getPaneActive().setVisible(false);
		gameOptions.setPaneActive(pane);
		gameOptions.getPaneActive().setVisible(true);
	}

	private void changeActualPane(Pane pane)
	{
		gameOptions.setPaneActual(pane);
		//actual.setImage(null);
		changeActivePane(pane);
	}

	private String idFromSource(String source)
	{
		int start = source.indexOf("id=") + 3;
		int end = source.indexOf(",", start);
		return source.substring(start, end);
	}

}