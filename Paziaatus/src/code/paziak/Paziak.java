package paziak;

import java.util.ArrayList;
import java.util.List;

import beings.PaziakPlayer;
import beings.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;
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

	private boolean	firstStandPlayer;
	private boolean	firstStandOpponent;

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

		playersScore = useCard(getAndRemoveCard(mainDeck), playersLaidCards, true);

		gameModel.paziakAnimatedVisualization(true, true, playersLaidCards, player.getSideDeck(), Integer.toString(playersScore));
		gameModel.paziakAnimatedVisualization(false, true, opponentsLaidCards, opponent.getSideDeck(), Integer.toString(opponentsScore));
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

		firstStandOpponent = false;
		firstStandPlayer = false;

		playersLaidCards = new ArrayList<>();
		opponentsLaidCards = new ArrayList<>();
	}

	public void opponentsTurn()
	{
		opponentPlayedCard = false;
		int temporaryScoreOfOpponentsCards = 0;

		if (!opponentStand)
		{
			opponentsScore = useCard(getAndRemoveCard(mainDeck), opponentsLaidCards, false);
			gameModel.paziakAnimatedVisualization(false, true, opponentsLaidCards, opponent.getSideDeck(), Integer.toString(opponentsScore));
			temporaryScoreOfOpponentsCards = opponentsScore;

			if (opponentConsidersUsingACard(opponent.getSideDeck()))
			{
				opponentPlayedCard = true;
				temporaryScoreOfOpponentsCards = calculateScore(opponentsLaidCards, true);

				waitingForCardUse();
			}

			if (temporaryScoreOfOpponentsCards == 20)
				firstStandCheckAndSetOfOpponent();
			else if (getPlayersScore() > 20)
				firstStandCheckAndSetOfOpponent();
			else if (temporaryScoreOfOpponentsCards > getPlayersScore() && playerStand)
				firstStandCheckAndSetOfOpponent();
			else if ((temporaryScoreOfOpponentsCards == 19 || temporaryScoreOfOpponentsCards == 18) && temporaryScoreOfOpponentsCards == getPlayersScore()
					&& playerStand)
				firstStandCheckAndSetOfOpponent();
			else if ((temporaryScoreOfOpponentsCards == 19 || temporaryScoreOfOpponentsCards == 18) && temporaryScoreOfOpponentsCards < getPlayersScore()
					&& playerStand)
				opponentStand = false;
			else if ((temporaryScoreOfOpponentsCards == 19 || temporaryScoreOfOpponentsCards == 18) && temporaryScoreOfOpponentsCards > getPlayersScore())
				firstStandCheckAndSetOfOpponent();
			else if (temporaryScoreOfOpponentsCards > 20)
				firstStandCheckAndSetOfOpponent();

		}
		if (opponentStand)
			gameModel.paziakDarkenDeck(false);

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
			firstStandCheckAndSetOfPlayer();
			gameModel.paziakDarkenDeck(true);
		}

		if (finalOpponentsScore > 20)
		{
			firstStandCheckAndSetOfOpponent();
			gameModel.paziakDarkenDeck(false);
		}

		if (playersLaidCards.size() > 8)
		{

			firstStandCheckAndSetOfPlayer();
			gameModel.paziakDarkenDeck(true);
		}

		if (opponentsLaidCards.size() > 8)
		{
			firstStandCheckAndSetOfOpponent();
			gameModel.paziakDarkenDeck(false);
		}

		if (playerStand && opponentStand)
		{
			if (finalOpponentsScore == getPlayersScore())
			{
				showDialogWithDelay(Constants.DRAW);
				newGameSet();
			} else if (getPlayersScore() > 20 && finalOpponentsScore > 20)
			{
				showDialogWithDelay(Constants.DRAW);
				newGameSet();
			} else if (getPlayersScore() > 20)
			{
				opponentsSets++;
				showDialogWithDelay(Constants.SET_OPPONENT);
				newGameSet();
			} else if (finalOpponentsScore > 20)
			{
				playersSets++;
				showDialogWithDelay(Constants.SET_PLAYER);
				newGameSet();
			} else if (finalOpponentsScore < getPlayersScore())
			{
				playersSets++;
				showDialogWithDelay(Constants.SET_PLAYER);
				newGameSet();
			} else
			{
				opponentsSets++;
				showDialogWithDelay(Constants.SET_OPPONENT);
				newGameSet();
			}

			if (playersSets == 3)
			{
				gameModel.paziakAnimatedVisualization(true, false, playersLaidCards, player.getSideDeck(), Integer.toString(playersScore));
				mainButtonCode = 2;
				showDialogWithDelay(Constants.GAME_PLAYER);
				end = true;
			} else if (opponentsSets == 3)
			{
				gameModel.paziakAnimatedVisualization(false, false, opponentsLaidCards, opponent.getSideDeck(), Integer.toString(opponentsScore));
				mainButtonCode = 2;
				showDialogWithDelay(Constants.GAME_OPPONENT);
				end = true;
			}

			if (end)
			{
				playersScore = calculateScore(playersLaidCards, false);
				opponentsScore = finalOpponentsScore;
			}
		}

		if (!end && !playerStand && !pause)
		{
			playersScore = useCard(getAndRemoveCard(mainDeck), playersLaidCards, true);
			gameModel.paziakAnimatedVisualization(true, true, playersLaidCards, player.getSideDeck(), Integer.toString(playersScore));
		} else if (end)
			gameModel.paziakAnimatedVisualization(true, false, playersLaidCards, player.getSideDeck(), Integer.toString(playersScore));

		if (playerStand)
			opponentsTurn();
	}

	public void waitingForCardUse()
	{
		opponent.getSideDeck().get(whichCardOpponentPlayed).useCard();
		opponentsScore = useCard(lockedOpponentsPlayedCard, opponentsLaidCards, false);
		gameModel.paziakAnimatedVisualization(false, true, playersLaidCards, player.getSideDeck(), Integer.toString(playersScore));
	}

	public int useCard(Card card, List<Card> personsCardsOnTable, boolean tryLuck)
	{
		if (tryLuck)
			card = tryLuck(card);

		personsCardsOnTable.add(card);
		return calculateScore(personsCardsOnTable, false);
	}

	private Card tryLuck(Card card)
	{
		int random = Tools.getRandomNumber(150);
		if(random <= player.getLuck() && (card.getValue() > mainDeck.get(0).getValue()))
		{
			Card newCard = mainDeck.get(0);
			mainDeck.set(0, card);
			return newCard;
		}
		return card;
	}

	public void newCard()
	{
		playersScore = useCard(getAndRemoveCard(mainDeck), playersLaidCards, true);
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
		int previousValue = 0;

		for (Card card : calculationList)
		{
			if (card.getCardTypeCode() == Constants.CARD_TYPE_DOUBLE)
				cardValues.add(previousValue * 2);
			else if (card.getCardTypeCode() == Constants.CARD_TYPE_2A4)
				cardValues = transformValues(cardValues, true);
			else if (card.getCardTypeCode() == Constants.CARD_TYPE_3A6)
				cardValues = transformValues(cardValues, false);
			else
				cardValues.add(card.getValue());

			previousValue = card.getValue();
		}

		calculatedScore = cardValues.stream().mapToInt(Integer::valueOf).sum();

		return calculatedScore;
	}

	private ArrayList<Integer> transformValues(List<Integer> cardValues, boolean twoAndFourCard)
	{
		ArrayList<Integer> transformedCardValues = new ArrayList<>();

		for (Integer value : cardValues)
		{
			if (twoAndFourCard && (value == 2 || value == 4))
				transformedCardValues.add(value * -1);
			else if (!twoAndFourCard && (value == 3 || value == 6))
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

	public boolean isFirstStandOpponent()
	{
		return firstStandOpponent;
	}

	public void firstStandCheckAndSetOfOpponent()
	{
		firstStandOpponent = isPlayerStand() ? false : true;

		opponentStand = true;
		gameModel.paziakDarkenOpponentsHandCards();
	}

	public boolean isPaused()
	{
		return pause;
	}

	private void showDialogWithDelay(String text)
	{
		pause();
		gameModel.paziakDarkenAllHandCards();
		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO), new KeyFrame(Duration.millis(firstStandPlayer ? 2400 : 1100)));

		timeline.setOnFinished(event -> {
			gameModel.paziakPointsVisibler();
			gameModel.paziakDarkenAllHandCards();
			gameModel.paziakSmallGeneralDialog(text);
			gameModel.setPaziakMainButton(mainButtonCode);
		});
		timeline.play();

	}

	public void stand()
	{
		firstStandCheckAndSetOfPlayer();
		opponentsTurn();
	}

	public void firstStandCheckAndSetOfPlayer()
	{
		firstStandPlayer = isOpponentStand() ? false : true;

		playerStand = true;
	}

	public boolean isOpponentStand()
	{
		return opponentStand;
	}

	public void paziakHandCardClicked(int numberOfSource)
	{

		if (!player.getSideDeck().get(numberOfSource).isCardUsed())
		{
			setPlayersScore(useCard(player.getSideDeck().get(numberOfSource), getPlayersLaidCards(), false));
			player.getSideDeck().get(numberOfSource).useCard();
			gameModel.paziakPointsVisibler();
		}
	}

	public boolean isPlayerStand()
	{
		return playerStand;
	}
}