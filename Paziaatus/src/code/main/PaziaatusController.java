package main;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import pazaak.CardsImages;
import pazaak.Pazaak;
import pazaak.RealCard;

import java.io.File;
import java.util.List;

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

    @FXML private Circle leftGreen;
    @FXML private Circle middleGreen;
    @FXML private Circle rightGreen;
    @FXML private Circle leftRed;
    @FXML private Circle middleRed;
    @FXML private Circle rightRed;

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

    @FXML public void start() throws InterruptedException
    {
        pazaak = new Pazaak(this);
        setCirclesGrey();
        btnStart.setVisible(false);
        btnStand.setDisable(false);
        btnEndTurn.setDisable(false);
        pazaak.newGame();
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
        pazaak.opponentsTurn();
    }

    @FXML public void firstCardClicked()
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(0).isUsed())
        {
            pazaak.setPlayersScore(pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(0), pazaak.getCardsOnPlayersTable()));
            pazaak.getPlayer().getCardsForMatch().get(0).used();
            visualization();
        }
    }


    @FXML public void secondCardClicked()
    {
        if (!pazaak.getPlayer().getCardsForMatch().get(1).isUsed())
        {
        	pazaak.setPlayersScore(pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(1), pazaak.getCardsOnPlayersTable()));
            pazaak.getPlayer().getCardsForMatch().get(1).used();
            visualization();
        }
    }

    @FXML public void thirdCardClicked()
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(2).isUsed())
        {
            pazaak.setPlayersScore(pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(2), pazaak.getCardsOnPlayersTable()));
            pazaak.getPlayer().getCardsForMatch().get(2).used();
            visualization();
        }
    }

    @FXML public void fourthCardClicked()
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(3).isUsed())
        {
            pazaak.setPlayersScore(pazaak.useCard(pazaak.getPlayer().getCardsForMatch().get(3), pazaak.getCardsOnPlayersTable()));
            pazaak.getPlayer().getCardsForMatch().get(3).used();
            visualization();
        }
    }

    @FXML public void clickPlayersHandLeft1()
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(0).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(0).makeLeftTurn();
            visualization();
        }
    }

    @FXML public void clickPlayersHandLeft2()
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(1).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(1).makeLeftTurn();
            visualization();
        }
    }

    @FXML public void clickPlayersHandLeft3()
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(2).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(2).makeLeftTurn();
            visualization();
        }
    }

    @FXML public void clickPlayersHandLeft4()
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(3).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(3).makeLeftTurn();
            visualization();
        }
    }

    @FXML public void clickPlayersHandRight1()
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(0).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(0).makeRightTurn();
            visualization();
        }
    }

    @FXML public void clickPlayersHandRight2()
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(1).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(1).makeRightTurn();
            visualization();
        }
    }

    @FXML public void clickPlayersHandRight3()
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(2).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(2).makeRightTurn();
            visualization();
        }
    }

    @FXML public void clickPlayersHandRight4()
    {
        if(!pazaak.getPlayer().getCardsForMatch().get(3).isUsed())
        {
            pazaak.getPlayer().getCardsForMatch().get(3).makeRightTurn();
            visualization();
        }
    }

    public void dialogDraw()
    {
        Dialog<String> dialog = new Dialog<>();

        dialog.setContentText("The set is ted.");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);

        dialog.showAndWait();
    }

    public void dialogPlayerWinsTheSet()
    {
        Dialog<String> dialog = new Dialog<>();

        dialog.setContentText("You win the set");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);

        dialog.showAndWait();
    }

    public void dialogOpponentWinsTheSet()
    {
        Dialog<String> dialog = new Dialog<>();

        dialog.setContentText("The opponent wins the set");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);

        dialog.showAndWait();
    }

    public void dialogOpponentWinsTheGame()
    {
        Dialog<String> dialog = new Dialog<>();

        dialog.setContentText("The opponent wins the game");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);

        dialog.showAndWait();
    }

    public void dialogPlayerWinsTheGame()
    {
        Dialog<String> dialog = new Dialog<>();

        dialog.setContentText("You wins the game");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);

        dialog.showAndWait();
    }

    public void visualization()
    {
        int i;

        playersScore.setText(String.valueOf(pazaak.getPlayersScore()));
        opponentsScore.setText(pazaak.getOpponentsScore());

        circleColorizer(pazaak.getPlayersSets(), false);
        circleColorizer(pazaak.getOpponentsSets(), true);

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

    private void circleColorizer(int sets, boolean opponent)
    {
        if (opponent)
        {
            if (sets == 3)
                rightRed.setFill(Color.RED);
            else if (sets == 2)
                middleRed.setFill(Color.RED);
            else if (sets == 1)
                leftRed.setFill(Color.RED);
        }
        else
        {
            if (sets == 3)
                rightGreen.setFill(Color.LIME);
            else if (sets == 2)
                middleGreen.setFill(Color.LIME);
            else if (sets == 1)
                leftGreen.setFill(Color.LIME);
        }
    }

    protected void setCirclesGrey()
    {
        rightRed.setFill(Color.GREY);
        middleRed.setFill(Color.GREY);
        leftRed.setFill(Color.GREY);

        rightGreen.setFill(Color.GREY);
        middleGreen.setFill(Color.GREY);
        leftGreen.setFill(Color.GREY);
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
                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getSecondImage().equals(""))
                        btnPlayersHandLeft1.setVisible(true);
                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getThirdImage().equals(""))
                        btnPlayersHandRight1.setVisible(true);
                }
                case 1 -> {
                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getSecondImage().equals(""))
                        btnPlayersHandLeft2.setVisible(true);
                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getThirdImage().equals(""))
                        btnPlayersHandRight2.setVisible(true);
                }
                case 2 -> {
                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getSecondImage().equals(""))
                        btnPlayersHandLeft3.setVisible(true);
                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getThirdImage().equals(""))
                        btnPlayersHandRight3.setVisible(true);
                }
                case 3 -> {
                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getSecondImage().equals(""))
                        btnPlayersHandLeft4.setVisible(true);
                    if(!pazaak.getPlayer().getCardsForMatch().get(i).getCard().getImages().getThirdImage().equals(""))
                        btnPlayersHandRight4.setVisible(true);
                }
            }
        }
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
}