package paziak;

import java.util.ArrayList;
import java.util.List;

import beings.PaziakPlayer;
import beings.Player;
import javafx.fxml.FXML;
import main.GameController;
import main.GameModel;
import residue.Constants;
import residue.Database;
import residue.Tools;

public class Paziak
{
	private GameModel gameModel;

	private ArrayList<Card> mainDeck;

	private Player			player;
	private boolean			playerStand;
	private int				playersSets;
	private int				playersScore;
	private ArrayList<Card>	playersLaidCards;

	private PaziakPlayer	opponent;
	private boolean			opponentStand;
	private int				opponentsSets;
	private int				opponentsScore;
	private ArrayList<Card>	opponentsLaidCards;

	private boolean	opponentPlayedCard;
	private Card	lockedOpponentsPlayedCard;
	private int		whichCardOpponentPlayed;

	private boolean	end;
	private boolean	pause;
	private int		mainButtonCode;

	public Paziak(GameModel gameModel, Player player)
	{
		this.gameModel = gameModel;

		this.player = player;
		this.playerStand = false;
		this.playersSets = 0;
		this.playersScore = 0;
		this.playersLaidCards = new ArrayList<>();

		this.opponent = new PaziakPlayer();
		this.opponentStand = false;
		this.opponentsSets = 0;
		this.opponentsScore = 0;
		this.opponentsLaidCards = new ArrayList<>();

		this.pause = false;
		this.end = false;
		mainButtonCode = 0;
	}

	public void mainButtonClick(Database database)
	{
		switch (mainButtonCode)
		{
			case 0:
			{
				newGame(database);
				gameModel.mainButtonClick(0);
				mainButtonCode = 1; // TODO
				break;
			}
			case 1:
			{
				gameModel.paziakNewGameSet();
				gameModel.mainButtonClick(1);
				break;
			}
			case 2:
			{
				gameModel.mainButtonClick(2);
			}
		}

	}

	public void newGame(Database database)
	{
		createMainDeck();
		Tools.shuffleDeck(mainDeck);

		player.makeSideDeck(player.getDeck());
		getOpponent().makeSideDeck(database.createDeckForOpponent(player.planetMultiplier()));

		playersScore = useCard(getAndRemoveCard(mainDeck), playersLaidCards);

		gameModel.paziakVsualizationOfTableCards(true);
		gameModel.paziakVsualizationOfTableCards(false);
	}

	private Card getAndRemoveCard(ArrayList<Card> mainDeck)
	{
		Card card = mainDeck.get(0);
		mainDeck.remove(0);
		return card;
	}

	public void createMainDeck()
	{
		ArrayList<Card> mainDeck = new ArrayList<Card>();
		int noValue = Constants.NO_VALUE;
		String pathPrefix = "cards/cards/";

		for (int i = 0; i < 4; ++i)
		{
			mainDeck.add(new Card(0, 1, noValue, pathPrefix + "m1", "", "", ""));
			mainDeck.add(new Card(0, 2, noValue, pathPrefix + "m2", "", "", ""));
			mainDeck.add(new Card(0, 3, noValue, pathPrefix + "m3", "", "", ""));
			mainDeck.add(new Card(0, 4, noValue, pathPrefix + "m4", "", "", ""));
			mainDeck.add(new Card(0, 5, noValue, pathPrefix + "m5", "", "", ""));
			mainDeck.add(new Card(0, 6, noValue, pathPrefix + "m6", "", "", ""));
			mainDeck.add(new Card(0, 7, noValue, pathPrefix + "m7", "", "", ""));
			mainDeck.add(new Card(0, 8, noValue, pathPrefix + "m8", "", "", ""));
			mainDeck.add(new Card(0, 9, noValue, pathPrefix + "m9", "", "", ""));
			mainDeck.add(new Card(0, 10, noValue, pathPrefix + "m10", "", "", ""));
		}

		this.mainDeck = mainDeck;
	}

	private void newGameSet()
	{
		playerStand = false;
		opponentStand = false;
		playersScore = 0;
		opponentsScore = 0;

		createMainDeck();
		Tools.shuffleDeck(mainDeck);

		playersLaidCards = new ArrayList<>();
		opponentsLaidCards = new ArrayList<>();
	}

	public void opponentsTurn()
	{
		opponentPlayedCard = false;
		int temporaryScoreOfOpponentsCards = 0;

		if (!opponentStand)
		{
			opponentsScore = useCard(getAndRemoveCard(mainDeck), opponentsLaidCards);
			gameModel.paziakVsualizationOfTableCards(false);
			temporaryScoreOfOpponentsCards = opponentsScore;

			if (opponentConsidersUsingACard(opponent.getSideDeck()))
			{
				opponentPlayedCard = true;
				temporaryScoreOfOpponentsCards = calculateScore(opponentsLaidCards, true);

				waitingForCardUse();
			}

			if (temporaryScoreOfOpponentsCards == 20)
				opponentStand = true;
			else if (getPlayersScore() > 20)
				opponentStand = true;
			else if (temporaryScoreOfOpponentsCards > getPlayersScore() && playerStand)
				opponentStand = true;
			else if ((temporaryScoreOfOpponentsCards == 19 || temporaryScoreOfOpponentsCards == 18) && temporaryScoreOfOpponentsCards == getPlayersScore()
					&& playerStand)
				opponentStand = true;
			else if ((temporaryScoreOfOpponentsCards == 19 || temporaryScoreOfOpponentsCards == 18) && temporaryScoreOfOpponentsCards < getPlayersScore()
					&& playerStand)
				opponentStand = false;
			else if ((temporaryScoreOfOpponentsCards == 19 || temporaryScoreOfOpponentsCards == 18) && temporaryScoreOfOpponentsCards > getPlayersScore())
				opponentStand = true;
			else if (temporaryScoreOfOpponentsCards > 20)
				opponentStand = true;

			if (opponentStand)
				gameModel.paziakDarkenCards(false);
		}

		if (!opponentPlayedCard)
			temporaryScoreOfOpponentsCards = 0;

		endOfTurn(temporaryScoreOfOpponentsCards);
	}

	public boolean opponentConsidersUsingACard(List<Card> cards)
	{
		if (playerStand && opponentsScore > playersScore && opponentsScore <= 20)
			return false;

		int scoreIfCardIsUsed[] = new int[Constants.MAX_SIDE_DECK_CARDS], index = 0;

		List<Card> opponentConsidersToUseOneOfTheseCards = new ArrayList<>();

		for (Card card : cards)
		{
			card.resetLeftTurn();
			card.resetRightTurn();
			int cardType = card.getCardTypeCode();

			if (cardType == Constants.CARD_TYPE_DOUBLE && (opponentsLaidCards.get(opponentsLaidCards.size() - 1).getPrimaryValue() * 2 == 20
					|| opponentsLaidCards.get(opponentsLaidCards.size() - 1).getPrimaryValue() * 2 == 19))
			{
				scoreIfCardIsUsed[index] = opponentsScore + opponentsLaidCards.get(opponentsLaidCards.size() - 1).getPrimaryValue() * 2;
				opponentConsidersToUseOneOfTheseCards.add(card);
			}

			else if (cardType == Constants.CARD_TYPE_BOTH)
			{
				if (opponentsScore + card.getPrimaryValue() == 20 || opponentsScore + card.getPrimaryValue() == 19)
				{
					scoreIfCardIsUsed[index] = opponentsScore + card.getPrimaryValue();
					opponentConsidersToUseOneOfTheseCards.add(card);
				} else if (opponentsScore > 20 && opponentsScore + card.getSecondaryValue() < 21)
				{
					card.setLeftTurnActive();
					scoreIfCardIsUsed[index] = opponentsScore + card.getSecondaryValue();
					opponentConsidersToUseOneOfTheseCards.add(card);
				}

			}

			else if (cardType == Constants.CARD_TYPE_1A2)
			{
				if (opponentsScore == 18 || opponentsScore == 17 || opponentsScore == 16)
				{
					card.setLeftTurnActive();
					scoreIfCardIsUsed[index] = opponentsScore + 2;
					opponentConsidersToUseOneOfTheseCards.add(card);
				} else if (opponentsScore == 19)
				{
					scoreIfCardIsUsed[index] = 20;
					opponentConsidersToUseOneOfTheseCards.add(card);
				} else if (opponentsScore == 21)
				{
					card.setRightTurnActive();
					scoreIfCardIsUsed[index] = 20;
					opponentConsidersToUseOneOfTheseCards.add(card);
				} else if (opponentsScore == 22)
				{
					card.setLeftTurnActive();
					card.setRightTurnActive();
					scoreIfCardIsUsed[index] = 20;
					opponentConsidersToUseOneOfTheseCards.add(card);
				}
			}

			else if (cardType == Constants.CARD_TYPE_2A4 && opponentsScore > 20)
			{
				int countOf2 = 0, countOf4 = 0;
				for (Card cardFromTable : opponentsLaidCards)
				{
					if (cardFromTable.getPrimaryValue() == 2)
						countOf2++;
					else if (cardFromTable.getPrimaryValue() == 4)
						countOf4++;
				}
				if (opponentsScore - ((countOf2 * 2 + countOf4 * 4) * 2) < 21)
				{
					scoreIfCardIsUsed[index] = opponentsScore - ((countOf2 * 2 + countOf4 * 4) * 2);
					opponentConsidersToUseOneOfTheseCards.add(card);
				}
			}

			else if (cardType == Constants.CARD_TYPE_3A6 && opponentsScore > 20)
			{
				int countOf3 = 0, countOf6 = 0;
				for (Card cardFromTable : opponentsLaidCards)
				{
					if (cardFromTable.getPrimaryValue() == 3)
						countOf3++;
					else if (cardFromTable.getPrimaryValue() == 6)
						countOf6++;
				}
				if (opponentsScore - ((countOf3 * 3 + countOf6 * 6) * 2) < 21)
				{
					scoreIfCardIsUsed[index] = opponentsScore - ((countOf3 * 3 + countOf6 * 6) * 2);
					opponentConsidersToUseOneOfTheseCards.add(card);
				}
			}

			else if (cardType == Constants.CARD_TYPE_INCREASE
					&& (card.getPrimaryValue() + opponentsScore == 19 || card.getPrimaryValue() + opponentsScore == 20))
			{
				scoreIfCardIsUsed[index] = opponentsScore + card.getPrimaryValue();
				opponentConsidersToUseOneOfTheseCards.add(card);

			} else if (cardType == Constants.CARD_TYPE_DECREASE && opponentsScore + card.getPrimaryValue() < 21
					&& opponentsScore + card.getPrimaryValue() > 17)
			{
				scoreIfCardIsUsed[index] = opponentsScore + card.getPrimaryValue();
				opponentConsidersToUseOneOfTheseCards.add(card);
			}

			index++;
		}

		for (int nth = 0; nth < Constants.MAX_SIDE_DECK_CARDS; nth++)
			if (playerStand && scoreIfCardIsUsed[nth] >= playersScore && scoreIfCardIsUsed[nth] < 21 && !cards.get(nth).isCardUsed())
			{
				lockedOpponentsPlayedCard = cards.get(nth);
				whichCardOpponentPlayed = nth;
				return true;
			}

		int nth = -1, wantedValue = 20;

		for (; wantedValue > 17; wantedValue--)
		{
			if (scoreIfCardIsUsed[0] == wantedValue)
			{
				nth = 0;
				break;
			} else if (scoreIfCardIsUsed[1] == wantedValue)
			{
				nth = 1;
				break;
			} else if (scoreIfCardIsUsed[2] == wantedValue)
			{
				nth = 2;
				break;
			} else if (scoreIfCardIsUsed[3] == wantedValue)
			{
				nth = 3;
				break;
			}
		}

		if (nth == -1)
			return false;
		else if (wantedValue >= playersScore && !cards.get(nth).isCardUsed() && wantedValue < 21 && !playerStand)
		{
			lockedOpponentsPlayedCard = cards.get(nth);
			whichCardOpponentPlayed = nth;
			return true;
		} else
			return false;
	}

	private void endOfTurn(int valueOfTemporaryUsedCard)
	{
		int finalOpponentsScore;

		if (valueOfTemporaryUsedCard == 0)
			finalOpponentsScore = opponentsScore;
		else
			finalOpponentsScore = valueOfTemporaryUsedCard;

		if (getPlayersScore() > 20)
		{
			playerStand = true;
			gameModel.paziakDarkenCards(true);
		}

		if (finalOpponentsScore > 20)
		{
			opponentStand = true;
			gameModel.paziakDarkenCards(false);
		}

		if (playerStand && opponentStand)
		{
			if (finalOpponentsScore == getPlayersScore())
			{
				gameModel.paziakSmallGeneralDialog(Constants.DRAW);
				gameModel.setPaziakMainButton(1);
				newGameSet();
			} else if (getPlayersScore() > 20 && finalOpponentsScore > 20)
			{
				gameModel.paziakSmallGeneralDialog(Constants.DRAW);
				newGameSet();
				gameModel.setPaziakMainButton(1);
			} else if (getPlayersScore() > 20)
			{
				opponentsSets++;
				gameModel.paziakSmallGeneralDialog(Constants.SET_OPPONENT);
				newGameSet();
				gameModel.setPaziakMainButton(1);
			} else if (finalOpponentsScore > 20)
			{
				playersSets++;
				gameModel.paziakSmallGeneralDialog(Constants.SET_PLAYER);
				newGameSet();
				gameModel.setPaziakMainButton(1);
			} else if (finalOpponentsScore < getPlayersScore())
			{
				playersSets++;
				gameModel.paziakSmallGeneralDialog(Constants.SET_PLAYER);
				newGameSet();
				gameModel.setPaziakMainButton(1);
			} else
			{
				opponentsSets++;
				gameModel.paziakSmallGeneralDialog(Constants.SET_OPPONENT);
				newGameSet();
				gameModel.setPaziakMainButton(1);
			}

			if (playersSets == 3)
			{
				gameModel.paziakVsualizationOfTableCards(true);
				gameModel.paziakSmallGeneralDialog(Constants.GAME_PLAYER);
				end = true;
			} else if (opponentsSets == 3)
			{
				gameModel.paziakVsualizationOfTableCards(false);
				gameModel.paziakSmallGeneralDialog(Constants.GAME_OPPONENT);
				end = true;
			}

			if (end)
			{
				mainButtonCode = 2;
				gameModel.setPaziakMainButton(2);
			}
		}

		if (!end && !playerStand && !pause)
		{
			playersScore = useCard(getAndRemoveCard(mainDeck), playersLaidCards);
			gameModel.paziakVsualizationOfTableCards(true);
		} else if (opponentPlayedCard)
			gameModel.paziakVsualizationOfTableCards(true);
		else if (end)
			gameModel.paziakVsualizationOfTableCards(true);

		if (playerStand)
			opponentsTurn();
	}

	public void waitingForCardUse()
	{
		opponent.getSideDeck().get(whichCardOpponentPlayed).useCard();
		opponentsScore = useCard(lockedOpponentsPlayedCard, opponentsLaidCards);
		gameModel.paziakVsualizationOfTableCards(false);
	}

	public int useCard(Card card, List<Card> personsCardsOnTable)
	{
		personsCardsOnTable.add(card);
		return calculateScore(personsCardsOnTable, false);
	}

	public void pause()
	{
		this.pause = true;
	}

	public void unpause()
	{
		this.pause = false;
	}

	private int calculateScore(List<Card> personsCardsOnTable, boolean preCalculation)
	{
		List<Card> calculationList = new ArrayList<>(personsCardsOnTable);

		if (preCalculation)
			calculationList.add(lockedOpponentsPlayedCard);

		List<Integer> cardValues = new ArrayList<>();
		int calculatedScore;

		for (Card card : calculationList)
		{
			cardValues.add(card.getValue());
		}

		calculatedScore = cardValues.stream().mapToInt(Integer::valueOf).sum();

		return calculatedScore;
	}

	private int getCardsValue(int value, boolean dec)
	{
		if (!dec)
			return value;
		else
			return value * -1;
	}

	private ArrayList<Integer> transformValues(List<Integer> cardValues, boolean doubleCard, boolean twoAndFourCard)
	{
		ArrayList<Integer> transformedCardValues = new ArrayList<>();

		for (Integer value : cardValues)
		{
			if (doubleCard)
				transformedCardValues.add(value * 2);
			else if (twoAndFourCard && (value == 2 || value == 4))
				transformedCardValues.add(value * -1);
			else if (value == 3 || value == 6)
				transformedCardValues.add(value * -1);
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

	public ArrayList<Card> getPlayersLaidCards()
	{
		return playersLaidCards;
	}

	public ArrayList<Card> getOpponentsLaidCards()
	{
		return opponentsLaidCards;
	}

	public PaziakPlayer getPlayer()
	{
		return player;
	}

	public PaziakPlayer getOpponent()
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

	public boolean isEnd()
	{
		return end;
	}

	public void nextTurn()
	{
		opponentsTurn();
	}

	public void stand()
	{
		playerStand = true;
		opponentsTurn();
	}

	public void paziakHandCardClicked(int numberOfSource)
	{

		if (!player.getSideDeck().get(numberOfSource).isCardUsed())
		{
			setPlayersScore(useCard(player.getSideDeck().get(numberOfSource), getPlayersLaidCards()));
			player.getSideDeck().get(numberOfSource).useCard();
		}
	}

	public void newCard()
	{
		playersScore = useCard(getAndRemoveCard(mainDeck), playersLaidCards);
	}
}