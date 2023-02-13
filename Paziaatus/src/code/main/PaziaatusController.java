package main;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.util.List;

import pazaak.CardsImages;
import pazaak.Pazaak;
import pazaak.RealCard;

public class PaziaatusController
{
    private Pazaak pazaak;

    @FXML private ImageView imgPlayersTable1;
    @FXML private ImageView imgPlayersTable2;
    @FXML private ImageView imgPlayersTable3;
    @FXML private ImageView imgPlayersTable4;
    @FXML private ImageView imgPlayersTable5;
    @FXML private ImageView imgPlayersTable6;
    @FXML private ImageView imgPlayersTable7;
    @FXML private ImageView imgPlayersTable8;
    @FXML private ImageView imgPlayersTable9;

    @FXML private ImageView imgOpponentsTable1;
    @FXML private ImageView imgOpponentsTable2;
    @FXML private ImageView imgOpponentsTable3;
    @FXML private ImageView imgOpponentsTable4;
    @FXML private ImageView imgOpponentsTable5;
    @FXML private ImageView imgOpponentsTable6;
    @FXML private ImageView imgOpponentsTable7;
    @FXML private ImageView imgOpponentsTable8;
    @FXML private ImageView imgOpponentsTable9;

    @FXML private ImageView imgPlayersHand1;
    @FXML private ImageView imgPlayersHand2;
    @FXML private ImageView imgPlayersHand3;
    @FXML private ImageView imgPlayersHand4;

    @FXML private ImageView imgOpponentsHand1;
    @FXML private ImageView imgOpponentsHand2;
    @FXML private ImageView imgOpponentsHand3;
    @FXML private ImageView imgOpponentsHand4;
    
    @FXML private ImageView point1Pl;
    @FXML private ImageView point2Pl;
    @FXML private ImageView point3Pl;
    @FXML private ImageView point1En;
    @FXML private ImageView point2En;
    @FXML private ImageView point3En;

    @FXML private Text playersScore;
    @FXML private Text opponentsScore;

    @FXML public Button btnStart;
    @FXML public Button btnStand;
    @FXML public Button btnEndTurn;

    @FXML public Button btnPlayersHandLeft1;
    @FXML public Button btnPlayersHandLeft2;
    @FXML public Button btnPlayersHandLeft3;
    @FXML public Button btnPlayersHandLeft4;

    @FXML public Button btnPlayersHandRight1;
    @FXML public Button btnPlayersHandRight2;
    @FXML public Button btnPlayersHandRight3;
    @FXML public Button btnPlayersHandRight4;

    public PauseTransition opponentPlayedCard =					new PauseTransition(Duration.millis(650));
    public PauseTransition visualizeWithDelayOpsTable =			new PauseTransition(Duration.millis(200));

    public PauseTransition visualizeWithShortDelayPlsTable =	new PauseTransition(Duration.millis(400));
    public PauseTransition visualizeWithDelayPlsTable =			new PauseTransition(Duration.millis(850));
    public PauseTransition opponentPlayedCardSoWait =			new PauseTransition(Duration.millis(1400));
    
    @FXML public void start() throws InterruptedException
    {
        pazaak = new Pazaak(this);
        pointsHider();
        btnStart.setVisible(false);
        btnStand.setDisable(false);
        btnEndTurn.setDisable(false);
        
        opponentPlayedCard.setOnFinished(event -> {pazaak.waitingForCardUse();});
        visualizeWithDelayOpsTable.setOnFinished(event -> {visualizationOpponentsSide();});
        
        visualizeWithShortDelayPlsTable.setOnFinished(event -> {visualizationPlayersSide();});
        visualizeWithDelayPlsTable.setOnFinished(event -> {visualizationPlayersSide();});
        opponentPlayedCardSoWait.setOnFinished(event -> {visualizationPlayersSide();});
        
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

	@FXML public void endTurn() throws InterruptedException
    {
        btnEndTurn.setDisable(true);
        btnStand.setDisable(true);
        pazaak.opponentsTurn();
    }

    @FXML public void stand() throws InterruptedException
    {
        pazaak.setPlayerStand(true);
        btnEndTurn.setDisable(true);
        btnStand.setDisable(true);
        darkenPlayersCards();
        pazaak.opponentsTurn();
    }

    @FXML public void firstCardClicked() throws InterruptedException
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(0).isUsed())
        {
            pazaak.setPlayersScore(pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(0), pazaak.getCardsOnPlayersTable()));
            pazaak.getPlayer().getCardsForMatch().get(0).used();
            visualizationPlayersSide();
            darkenPlayersHandCards(1);
        }
    }


    @FXML public void secondCardClicked() throws InterruptedException
    {
        if (!pazaak.getPlayer().getCardsForMatch().get(1).isUsed())
        {
        	pazaak.setPlayersScore(pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(1), pazaak.getCardsOnPlayersTable()));
            pazaak.getPlayer().getCardsForMatch().get(1).used();
            visualizationPlayersSide();
            darkenPlayersHandCards(2);
        }
    }

    @FXML public void thirdCardClicked() throws InterruptedException
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(2).isUsed())
        {
            pazaak.setPlayersScore(pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(2), pazaak.getCardsOnPlayersTable()));
            pazaak.getPlayer().getCardsForMatch().get(2).used();
            visualizationPlayersSide();
            darkenPlayersHandCards(3);
        }
    }

    @FXML public void fourthCardClicked() throws InterruptedException
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(3).isUsed())
        {
            pazaak.setPlayersScore(pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(3), pazaak.getCardsOnPlayersTable()));
            pazaak.getPlayer().getCardsForMatch().get(3).used();
            visualizationPlayersSide();
            darkenPlayersHandCards(4);
        }
    }

    @FXML public void clickPlayersHandLeft1() throws InterruptedException
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(0).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(0).makeLeftTurn();
            visualizationPlayersSide();
        }
    }

    @FXML public void clickPlayersHandLeft2() throws InterruptedException
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(1).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(1).makeLeftTurn();
            visualizationPlayersSide();
        }
    }

    @FXML public void clickPlayersHandLeft3() throws InterruptedException
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(2).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(2).makeLeftTurn();
            visualizationPlayersSide();
        }
    }

    @FXML public void clickPlayersHandLeft4() throws InterruptedException
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(3).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(3).makeLeftTurn();
            visualizationPlayersSide();
        }
    }

    @FXML public void clickPlayersHandRight1() throws InterruptedException
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(0).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(0).makeRightTurn();
            visualizationPlayersSide();
        }
    }

    @FXML public void clickPlayersHandRight2() throws InterruptedException
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(1).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(1).makeRightTurn();
            visualizationPlayersSide();
        }
    }

    @FXML public void clickPlayersHandRight3() throws InterruptedException
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(2).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(2).makeRightTurn();
            visualizationPlayersSide();
        }
    }

    @FXML public void clickPlayersHandRight4() throws InterruptedException
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(3).isUsed())
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

        for(i = 0; i < pazaak.getCardsOnOpponentsTable().size(); i++)
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

        for(i = 0; i < pazaak.getOpponent().getCardsForMatch().size(); i++)
        {
            switch (i)
            {
                case 0 -> {
                    if (!pazaak.getOpponent().getCardsForMatch().get(i).isUsed())
                        imgOpponentsHand1.setImage(new Image(String.valueOf(new File(CardsImages.BACK.getFirstImage()).toURI())));
                    else imgOpponentsHand1.setImage(null);
                }
                case 1 -> {
                    if (!pazaak.getOpponent().getCardsForMatch().get(i).isUsed())
                        imgOpponentsHand2.setImage(new Image(String.valueOf(new File(CardsImages.BACK.getFirstImage()).toURI())));
                    else imgOpponentsHand2.setImage(null);
                }
                case 2 -> {
                    if (!pazaak.getOpponent().getCardsForMatch().get(i).isUsed())
                        imgOpponentsHand3.setImage(new Image(String.valueOf(new File(CardsImages.BACK.getFirstImage()).toURI())));
                    else imgOpponentsHand3.setImage(null);
                }
                case 3 -> {
                    if (!pazaak.getOpponent().getCardsForMatch().get(i).isUsed())
                        imgOpponentsHand4.setImage(new Image(String.valueOf(new File(CardsImages.BACK.getFirstImage()).toURI())));
                    else imgOpponentsHand4.setImage(null);
                }
            }
        }
    }
    
    public void visualizationPlayersSide() 
    {
        int i;

        playersScore.setText(String.valueOf(pazaak.getPlayersScore()));

        for(i = 0; i < pazaak.getCardsOnPlayersTable().size(); i++)
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

        for(i = 0; i < pazaak.getPlayer().getCardsForMatch().size(); i++)
        {
            switch (i)
            {
                case 0 -> {
                    if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
                        imgPlayersHand1.setImage(whichSideOfCard(pazaak.getPlayer().getCardsForMatch(), i));
                    else
                    {
                        imgPlayersHand1.setImage(null);
                        btnPlayersHandLeft1.setVisible(false);
                        btnPlayersHandRight1.setVisible(false);
                    }
                }
                case 1 -> {
                    if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
                        imgPlayersHand2.setImage(whichSideOfCard(pazaak.getPlayer().getCardsForMatch(), i));
                    else
                    {
                        imgPlayersHand2.setImage(null);
                        btnPlayersHandLeft2.setVisible(false);
                        btnPlayersHandRight2.setVisible(false);
                    }
                }
                case 2 -> {
                    if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
                        imgPlayersHand3.setImage(whichSideOfCard(pazaak.getPlayer().getCardsForMatch(), i));
                    else
                    {
                        imgPlayersHand3.setImage(null);
                        btnPlayersHandLeft3.setVisible(false);
                        btnPlayersHandRight3.setVisible(false);
                    }
                }
                case 3 -> {
                    if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
                        imgPlayersHand4.setImage(whichSideOfCard(pazaak.getPlayer().getCardsForMatch(), i));
                    else
                    {
                        imgPlayersHand4.setImage(null);
                        btnPlayersHandLeft4.setVisible(false);
                        btnPlayersHandRight4.setVisible(false);
                    }
                }
            }
        }
        if (!pazaak.isEnd())
        	setButtonsActive();
    }

    private Image whichSideOfCard(List<RealCard> personsTable, int i)
    {
        Image image;
        if(personsTable.get(i).hasInactiveLeftTurn())
        {
            if(personsTable.get(i).hasInactiveRightTurn())
                image = new Image(String.valueOf(new File(personsTable.get(i).getCard().getImages().getFirstImage()).toURI()));
            else
                image = new Image(String.valueOf(new File(personsTable.get(i).getCard().getImages().getThirdImage()).toURI()));

        }
        else
        {
            if(personsTable.get(i).hasInactiveRightTurn())
                image = new Image(String.valueOf(new File(personsTable.get(i).getCard().getImages().getSecondImage()).toURI()));
            else
                image = new Image(String.valueOf(new File(personsTable.get(i).getCard().getImages().getFourthImage()).toURI()));
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
    	btnPlayersHandLeft1.setVisible(false);
    	btnPlayersHandLeft2.setVisible(false);
    	btnPlayersHandLeft3.setVisible(false);
    	btnPlayersHandLeft4.setVisible(false);
    	
    	btnPlayersHandRight1.setVisible(false);
    	btnPlayersHandRight2.setVisible(false);
    	btnPlayersHandRight3.setVisible(false);
    	btnPlayersHandRight4.setVisible(false);
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
        
        btnPlayersHandLeft1.setVisible(false);
        btnPlayersHandLeft2.setVisible(false);
        btnPlayersHandLeft3.setVisible(false);
        btnPlayersHandLeft4.setVisible(false);

        btnPlayersHandRight1.setVisible(false);
        btnPlayersHandRight2.setVisible(false);
        btnPlayersHandRight3.setVisible(false);
        btnPlayersHandRight4.setVisible(false);
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
    
    public void setButtonsActive()
    {
        btnEndTurn.setDisable(false);
        btnStand.setDisable(false);
    }

    public void activateHandButtons()
    {
        deactivateOldHandButtons();

        for (int i = 0; i < pazaak.getPlayer().getCardsForMatch().size(); i++)
        {
            switch (i)
            {
                case 0 -> {
                	if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
                	{
	                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getSecondImage().equals(""))
	                        btnPlayersHandLeft1.setVisible(true);
	                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getThirdImage().equals(""))
	                        btnPlayersHandRight1.setVisible(true);
                	}
                	else continue;
                }
                case 1 -> {
                	if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
                	{
		                if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getSecondImage().equals(""))
		                    btnPlayersHandLeft2.setVisible(true);
		                if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getThirdImage().equals(""))
		                    btnPlayersHandRight2.setVisible(true);
                	}
                	else continue;
                }
                case 2 -> {
                	if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
                	{
	                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getSecondImage().equals(""))
	                        btnPlayersHandLeft3.setVisible(true);
	                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getThirdImage().equals(""))
	                        btnPlayersHandRight3.setVisible(true);
                	}
                	else continue;
                }
                case 3 -> {
                	if (!pazaak.getPlayer().getCardsForMatch().get(i).isUsed())
                	{
	                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getSecondImage().equals(""))
	                        btnPlayersHandLeft4.setVisible(true);
	                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getThirdImage().equals(""))
	                        btnPlayersHandRight4.setVisible(true);
                	}
                	else continue;
                }
            }
        }
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
	    		imgPlayersHand1.setEffect(colorAdjust);
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
    
    public void brightenPlayersHandCards()
    {
    	imgPlayersHand1.setEffect(null);
    	imgPlayersHand2.setEffect(null);
    	imgPlayersHand3.setEffect(null);
    	imgPlayersHand4.setEffect(null);
    	imgPlayersHand1.setDisable(false);
    	imgPlayersHand2.setDisable(false);
    	imgPlayersHand3.setDisable(false);
    	imgPlayersHand4.setDisable(false);
    }

    private void deactivateOldHandButtons()
    {
        btnPlayersHandLeft1.setVisible(false);
        btnPlayersHandRight1.setVisible(false);
        btnPlayersHandLeft2.setVisible(false);
        btnPlayersHandRight2.setVisible(false);
        btnPlayersHandLeft3.setVisible(false);
        btnPlayersHandRight3.setVisible(false);
        btnPlayersHandLeft4.setVisible(false);
        btnPlayersHandRight4.setVisible(false);
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
}