package Pazaak;

import java.util.ArrayList;
import java.util.List;

import beings.*;
import main.PaziaatusController;

public class Pazaak
{
    private PaziaatusController controller;
    private int playersSets;
    private int opponentsSets;
    private int playersScore;
    private int opponentsScore;
    private List<RealCard> cardsOnPlayersTable;
    private List<RealCard> cardsOnOpponentsTable;
    private boolean playerStand;
    private boolean opponentStand;
    private MainDeck mainDeck;
    private SideDeck sideDeck;
    private PazaakPlayer player;
    private PazaakPlayer opponent;
    private boolean end;

    public Pazaak(PaziaatusController controller)
    {
        this.controller         	= controller;
        this.playersSets            = 0;
        this.opponentsSets          = 0;
        this.playersScore           = 0;
        this.opponentsScore         = 0;
        this.cardsOnPlayersTable    = new ArrayList<>();
        this.cardsOnOpponentsTable  = new ArrayList<>();
        this.playerStand            = false;
        this.opponentStand          = false;
        this.mainDeck               = new MainDeck();
        this.sideDeck               = new SideDeck();
        this.player                 = new PazaakPlayer();
        this.opponent               = new PazaakPlayer();
        this.end                    = false;
    }

    public void newGame()
    {
        mainDeck.fillAndShuffleMainDeck();

        sideDeck.fillSideDeck();
        player.makeCardsForMatch(sideDeck.getSideDeck());
        sideDeck.fillSideDeck();
        opponent.makeCardsForMatch(sideDeck.getSideDeck());

        controller.activateHandButtons();

        playersScore = useCard(mainDeck.getAndRemoveCard(), cardsOnPlayersTable);

        controller.visualization();
    }

    
    
    public void opponentsTurn()
    {
        if (!opponentStand)
        {
            opponentsScore = useCard(mainDeck.getAndRemoveCard(), cardsOnOpponentsTable);

            if(getPlayersScore() > 20)
            {
                opponentStand = true;
            }

            if(opponentsScore < 20 && opponentsScore > getPlayersScore() && playerStand)
            {
                opponentStand = true;
            }

            else
            {
                for (int i = 0; i < opponent.getCardsForMatch().size(); i++)
                {
                    if (opponentConsidersUsingACard(opponent.getCardForMatch(i)))
                    {
                        opponent.getCardsForMatch().get(i).used();
                    }
                }
                if (opponentsScore == 18 || opponentsScore == 19 || opponentsScore == 20)
                {
                    opponentStand = true;
                }
            }
        }
        controller.visualization();
        endOfTurn();
    }

    public boolean opponentConsidersUsingACard(RealCard card)
    {
        if (card.isUsed())
            return false;

        if (card.getCard().isItDoubleCard() &&
                (opponentsScore == 10 || opponentsScore == 9))
        {
            opponentsScore = useCard(card, cardsOnOpponentsTable);
            return true;
        }

        else if (card.getCard().getSecondValue() == 0)
        {
            if (!card.getCard().isItMinusCard())
            {
                if (opponentsScore + card.getCard().getFirstValue() == 20 ||
                        opponentsScore + card.getCard().getFirstValue() == 19)
                {
                    opponentsScore = useCard(card, cardsOnOpponentsTable);
                    return true;
                }
            }
            else
            {
                if (opponentsScore > 20 &&
                        opponentsScore - card.getCard().getFirstValue() < 21)
                {
                    opponentsScore = useCard(card, cardsOnOpponentsTable);
                    return true;
                }
            }
        }

        else if (card.getCard().getFirstValue() == 1
                && card.getCard().getSecondValue() == 2)
        {
            if (opponentsScore == 18 ||
                    opponentsScore == 17)
            {
                card.setLeftTurnActive();
                opponentsScore = useCard(card, cardsOnOpponentsTable);
                return true;
            }
            else if (opponentsScore == 19)
            {
                opponentsScore = useCard(card, cardsOnOpponentsTable);
                return true;
            }
            else if (opponentsScore == 21)
            {
                card.setRightTurnActive();
                opponentsScore = useCard(card, cardsOnOpponentsTable);
                return true;
            }
            else if (opponentsScore == 22)
            {
                card.setLeftTurnActive();
                card.setRightTurnActive();
                return true;
            }
        }

        else if (card.getCard().equals(Card.TWO_AND_FOUR) && opponentsScore > 20)
        {
            int countOf2 = 0, countOf4 = 0;
            for (RealCard cardFromTable : cardsOnOpponentsTable)
            {
                if (cardFromTable.getCard().getFirstValue() == 2)
                    countOf2++;
                else if ( cardFromTable.getCard().getFirstValue() == 4)
                    countOf4++;
            }
            if (opponentsScore-((countOf2*2+countOf4*4)*2) < 21 )
            {
                opponentsScore = useCard(card, cardsOnOpponentsTable);
                return true;
            }
        }

        else if (card.getCard().equals(Card.THREE_AND_SIX) && opponentsScore > 20)
        {
            int countOf3 = 0, countOf6 = 0;
            for (RealCard cardFromTable : cardsOnOpponentsTable)
            {
                if (cardFromTable.getCard().getFirstValue() == 3)
                    countOf3++;
                else if ( cardFromTable.getCard().getFirstValue() == 6)
                    countOf6++;
            }
            if (opponentsScore-((countOf3*3+countOf6*6)*2) < 21 )
            {
                opponentsScore = useCard(card, cardsOnOpponentsTable);
                return true;
            }
        }

        else
        {
            if (opponentsScore < 20)
            {
                if (card.getCard().getFirstValue() + opponentsScore == 19 ||
                        card.getCard().getFirstValue() + opponentsScore == 20)
                {
                    opponentsScore = useCard(card, cardsOnOpponentsTable);
                    return true;
                }
            }
            else if (opponentsScore - card.getCard().getFirstValue() < 21)
            {
                card.setLeftTurnActive();
                opponentsScore = useCard(card, cardsOnOpponentsTable);
                return true;
            }
        }
        return false;
    }

    private void endOfTurn()
    {
        if(getPlayersScore()>20)
        {
            playerStand = true;
        }

        if(opponentsScore>20)
        {
            opponentStand = true;
        }

        if(playerStand && opponentStand)
        {
            if (opponentsScore == getPlayersScore())
            {
                controller.dialogDraw();
                newGameSet();
            }
            else if(getPlayersScore() > 20 && opponentsScore > 20)
            {
                controller.dialogDraw();
                newGameSet();
            }
            else if(getPlayersScore() > 20)
            {
                opponentsSets++;
                controller.dialogOpponentWinsTheSet();
                newGameSet();
            }
            else if(opponentsScore > 20)
            {
                playersSets++;
                controller.dialogPlayerWinsTheSet();
                newGameSet();
            }
            else if (opponentsScore < getPlayersScore())
            {
                playersSets++;
                controller.dialogPlayerWinsTheSet();
                newGameSet();
            }
            else
            {
                opponentsSets++;
                controller.dialogOpponentWinsTheSet();
                newGameSet();
            }

            if (playersSets == 3)
            {
                controller.visualization();
                controller.dialogPlayerWinsTheGame();
                end = true;
            }
            else if (opponentsSets == 3)
            {
                controller.visualization();
                controller.dialogOpponentWinsTheGame();
                end = true;
            }

            if (end)
            {
                controller.btnStart.setVisible(true);
                controller.btnStand.setDisable(true);
                controller.btnEndTurn.setDisable(true);
            }
        }

        if (!end && !playerStand)
        {
            playersScore = useCard(mainDeck.getAndRemoveCard(), cardsOnPlayersTable);
            playerIsOnTurn();
        }
        controller.visualization();

        if(playerStand)
            opponentsTurn();
    }

    private void playerIsOnTurn()
    {
        controller.setButtonsActive();
    }

    private void newGameSet()
    {
        playerStand = false;
        opponentStand = false;
        playersScore = 0;
        opponentsScore = 0;
        mainDeck.fillAndShuffleMainDeck();
        cardsOnPlayersTable = new ArrayList<>();
        cardsOnOpponentsTable = new ArrayList<>();
        controller.hideAllHandButtons();
        controller.activateHandButtons();
        controller.clearImages();
    }

    public int useCard(RealCard card, List<RealCard> personsCardsOnTable)
    {
        personsCardsOnTable.add(card);
        return calculateScore(personsCardsOnTable);
    }

    private int calculateScore(List<RealCard> personsCardsOnTable)
    {
        List<Integer> cardValues = new ArrayList<>();
        int calculatedScore;

        for (RealCard card : personsCardsOnTable)
        {
            if(card.getCard().equals(Card.PLUS_MINUS_ONE_TWO))
            {
                if (card.hasInactiveLeftTurn())
                {
                    if (card.hasInactiveRightTurn())
                        cardValues.add(getCardsValue(card.getCard().getFirstValue(), false));
                    else
                        cardValues.add(getCardsValue(card.getCard().getFirstValue(), true));
                }
                else
                {
                    if (card.hasInactiveRightTurn())
                        cardValues.add(getCardsValue(card.getCard().getSecondValue(),false));
                    else
                        cardValues.add(getCardsValue(card.getCard().getSecondValue(), true));
                }
            }
            else if (card.hasInactiveLeftTurn())
            {
                if (card.getCard().isItDoubleCard())
                    cardValues = new ArrayList<>(transformValues(cardValues, true, false));
                else if (card.getCard().isItThreeAndSixCard())
                    cardValues = new ArrayList<>(transformValues(cardValues, false, false));
                else if (card.getCard().isItTwoAndFourCard())
                    cardValues = new ArrayList<>(transformValues(cardValues, false, true));
                else if (card.hasInactiveRightTurn())
                    cardValues.add(getCardsValue(card.getCard().getFirstValue(), card.getCard().isItMinusCard()));
                else
                    cardValues.add(getCardsValue(card.getCard().getSecondValue(), card.getCard().isItMinusCard()));
            }
            else
            {
                if (card.hasInactiveRightTurn())
                    cardValues.add(getCardsValue(card.getCard().getFirstValue(), true));
                else
                    cardValues.add(getCardsValue(card.getCard().getSecondValue(), true));
            }
        }

        calculatedScore = cardValues.stream()
                .mapToInt(Integer::valueOf)
                .sum();

        return calculatedScore;
    }

    private int getCardsValue(int value, boolean dec)
    {
        if (!dec)
            return value;
        else
            return value*-1;
    }

    private ArrayList<Integer> transformValues(List<Integer> cardValues, boolean doubleCard, boolean twoAndFourCard)
    {
        ArrayList<Integer> transformedCardValues = new ArrayList<>();

        for (Integer value : cardValues)
        {
            if (doubleCard)
                transformedCardValues.add(value*2);
            else if (twoAndFourCard && (value == 2 || value == 4))
                transformedCardValues.add(value*-1);
            else if (value == 3 || value == 6)
                transformedCardValues.add(value*-1);
            else
                transformedCardValues.add(value);
        }

        return transformedCardValues;
    }

    public int getPlayersSets()
    {
        return playersSets;
    }

    public int getOpponentsSets()
    {
        return opponentsSets;
    }

    public int getPlayersScore()
    {
        return playersScore;
    }

    public String getOpponentsScore()
    {
        return String.valueOf(opponentsScore);
    }

	public List<RealCard> getCardsOnPlayersTable()
	{
		return cardsOnPlayersTable;
	}

	public List<RealCard> getCardsOnOpponentsTable()
	{
		return cardsOnOpponentsTable;
	}

	public PazaakPlayer getPlayer()
	{
		return player;
	}

	public PazaakPlayer getOpponent()
	{
		return opponent;
	}

	public void setPlayerStand(boolean playerStand)
	{
		this.playerStand = playerStand;
	}

	public void setPlayersScore(int playersScore)
	{
		this.playersScore = playersScore;
	}

	
}