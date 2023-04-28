package paziak;

import java.util.ArrayList;
import java.util.List;

import beings.PaziakPlayer;
import main.GameController;

public class Paziak
{
	private GameController	controller;
	private int					playersSets;
	private int					opponentsSets;
	private int					playersScore;
	private int					opponentsScore;
	private List<SideDeckCard>		cardsOnPlayersTable;
	private List<SideDeckCard>		cardsOnOpponentsTable;
	private boolean				playerStand;
	private boolean				opponentStand;
	private DDDMainDeck			mainDeck;
	private DDDSideDeck			sideDeck;
	private PaziakPlayer		player;
	private PaziakPlayer		opponent;
	private boolean				end;
	private boolean				opponentPlayedCard;
	private int					whichCardOpPlayed;

	public Paziak(GameController controller)
	{
		this.controller = controller;
		this.playersSets = 0;
		this.opponentsSets = 0;
		this.playersScore = 0;
		this.opponentsScore = 0;
		this.cardsOnPlayersTable = new ArrayList<>();
		this.cardsOnOpponentsTable = new ArrayList<>();
		this.playerStand = false;
		this.opponentStand = false;
		this.mainDeck = new DDDMainDeck();
		this.sideDeck = new DDDSideDeck();
		this.player = new PaziakPlayer();
		this.opponent = new PaziakPlayer();
		this.end = false;
	}

	public void newGame() throws InterruptedException
	{
		mainDeck.fillAndShuffleMainDeck();

		sideDeck.fillSideDeck();
		player.makeOpponentsSideDeck(sideDeck.getSideDeck());
		sideDeck.fillSideDeck();
		opponent.makeOpponentsSideDeck(sideDeck.getSideDeck());

		controller.showOrHidehandCards(true);
		controller.activateHandButtons();
		controller.visualizationOfTableCards(true);

		playersScore = useCard(mainDeck.getAndRemoveCard(), cardsOnPlayersTable);

		controller.visualizeWithShortDelayPlsTable.play();
		controller.visualizationOfTableCards(false);
	}

	public void opponentsTurn() throws InterruptedException
	{
		opponentPlayedCard = false;
		int temporaryScoreOfOpponentsCards = 0;

		if (!opponentStand)
		{
			opponentsScore = useCard(mainDeck.getAndRemoveCard(), cardsOnOpponentsTable);
			controller.visualizeWithDelayOpsTable.play();
			temporaryScoreOfOpponentsCards = opponentsScore;

			if (opponentConsidersUsingACard(opponent.getCardsForMatch()))
			{
				opponentPlayedCard = true;
				temporaryScoreOfOpponentsCards = calculateScore(cardsOnOpponentsTable, true);

				controller.opponentPlayedCard.play();
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
				controller.darkenCards(false);
		}

		if (!opponentPlayedCard)
			temporaryScoreOfOpponentsCards = 0;

		endOfTurn(temporaryScoreOfOpponentsCards);
	}

	public boolean opponentConsidersUsingACard(List<SideDeckCard> cards)
	{
		int scoreIfCardIsUsed[] = new int[4], index = 0;

		List<SideDeckCard> opponentConsidersToUseOneOfTheseCards = new ArrayList<>();

		for (SideDeckCard card : cards)
		{
			card.resetLeftTurn();
			card.resetRightTurn();

			if (card.getCard().isItDoubleCard() && (opponentsScore == 10 || opponentsScore == 9))
			{
				scoreIfCardIsUsed[index++] = opponentsScore * 2;
				opponentConsidersToUseOneOfTheseCards.add(card);
			}

			else if (card.getCard().getSecondValue() == 0)
			{
				if (!card.getCard().isItMinusCard())
				{
					if (opponentsScore + card.getCard().getFirstValue() == 20 || opponentsScore + card.getCard().getFirstValue() == 19)
					{
						scoreIfCardIsUsed[index++] = opponentsScore + card.getCard().getFirstValue();
						opponentConsidersToUseOneOfTheseCards.add(card);
					}
				} else
				{
					if (opponentsScore > 20 && opponentsScore - card.getCard().getFirstValue() < 21)
					{
						card.setLeftTurnActive();
						scoreIfCardIsUsed[index++] = opponentsScore - card.getCard().getFirstValue();
						opponentConsidersToUseOneOfTheseCards.add(card);
					}
				}
			}

			else if (card.getCard().getFirstValue() == 1 && card.getCard().getSecondValue() == 2)
			{
				if (opponentsScore == 18 || opponentsScore == 17 || opponentsScore == 16)
				{
					card.setLeftTurnActive();
					scoreIfCardIsUsed[index++] = opponentsScore + 2;
					opponentConsidersToUseOneOfTheseCards.add(card);
				} else if (opponentsScore == 19)
				{
					scoreIfCardIsUsed[index++] = 20;
					opponentConsidersToUseOneOfTheseCards.add(card);
				} else if (opponentsScore == 21)
				{
					card.setRightTurnActive();
					scoreIfCardIsUsed[index++] = 20;
					opponentConsidersToUseOneOfTheseCards.add(card);
				} else if (opponentsScore == 22)
				{
					card.setLeftTurnActive();
					card.setRightTurnActive();
					scoreIfCardIsUsed[index++] = 20;
					opponentConsidersToUseOneOfTheseCards.add(card);
				}
			}

			else if (card.getCard().equals(DDDCard.TWO_AND_FOUR) && opponentsScore > 20)
			{
				int countOf2 = 0, countOf4 = 0;
				for (SideDeckCard cardFromTable : cardsOnOpponentsTable)
				{
					if (cardFromTable.getCard().getFirstValue() == 2)
						countOf2++;
					else if (cardFromTable.getCard().getFirstValue() == 4)
						countOf4++;
				}
				if (opponentsScore - ((countOf2 * 2 + countOf4 * 4) * 2) < 21)
				{
					scoreIfCardIsUsed[index++] = opponentsScore - ((countOf2 * 2 + countOf4 * 4) * 2);
					opponentConsidersToUseOneOfTheseCards.add(card);
				}
			}

			else if (card.getCard().equals(DDDCard.THREE_AND_SIX) && opponentsScore > 20)
			{
				int countOf3 = 0, countOf6 = 0;
				for (SideDeckCard cardFromTable : cardsOnOpponentsTable)
				{
					if (cardFromTable.getCard().getFirstValue() == 3)
						countOf3++;
					else if (cardFromTable.getCard().getFirstValue() == 6)
						countOf6++;
				}
				if (opponentsScore - ((countOf3 * 3 + countOf6 * 6) * 2) < 21)
				{
					scoreIfCardIsUsed[index++] = opponentsScore - ((countOf3 * 3 + countOf6 * 6) * 2);
					opponentConsidersToUseOneOfTheseCards.add(card);
				}
			}

			else
			{
				if (opponentsScore < 20)
				{
					if ((card.getCard().equals(DDDCard.PLUS_ONE) || card.getCard().equals(DDDCard.PLUS_TWO) || card.getCard().equals(DDDCard.PLUS_THREE)
							|| card.getCard().equals(DDDCard.PLUS_FOUR) || card.getCard().equals(DDDCard.PLUS_FIVE) || card.getCard().equals(DDDCard.PLUS_SIX))
							&& (card.getCard().getFirstValue() + opponentsScore == 19 || card.getCard().getFirstValue() + opponentsScore == 20))
					{
						scoreIfCardIsUsed[index++] = opponentsScore + card.getCard().getFirstValue();
						opponentConsidersToUseOneOfTheseCards.add(card);
					}
				} else if ((card.getCard().equals(DDDCard.MINUS_ONE) || card.getCard().equals(DDDCard.MINUS_TWO) || card.getCard().equals(DDDCard.MINUS_THREE)
						|| card.getCard().equals(DDDCard.MINUS_FOUR) || card.getCard().equals(DDDCard.MINUS_FIVE) || card.getCard().equals(DDDCard.MINUS_SIX))
						&& opponentsScore - card.getCard().getFirstValue() < 21)
				{
					card.setLeftTurnActive();
					scoreIfCardIsUsed[index++] = opponentsScore - card.getCard().getFirstValue();
					opponentConsidersToUseOneOfTheseCards.add(card);
				}
			}
		}
		int score = scoreIfCardIsUsed[0], nth = 0;
		if (scoreIfCardIsUsed[1] > score)
		{
			score = scoreIfCardIsUsed[1];
			nth = 1;
		}
		if (scoreIfCardIsUsed[2] > score)
		{
			score = scoreIfCardIsUsed[2];
			nth = 2;
		}
		if (scoreIfCardIsUsed[3] > score)
		{
			score = scoreIfCardIsUsed[3];
			nth = 3;
		}

		if (playerStand && opponentsScore > playersScore && opponentsScore <= 20)
			return false;
		else if ((score == 18 || score == 19 || score == 20) && score >= playersScore && !cards.get(nth).isCardUsed())
		{
			opponent.lockedOpponentsPlayedCard = cards.get(nth);
			whichCardOpPlayed = nth;
			return true;
		} else
			return false;
	}

	private void endOfTurn(int valueOfTemporaryUsedCard) throws InterruptedException
	{
		int finalOpponentsScore;

		if (valueOfTemporaryUsedCard == 0)
			finalOpponentsScore = opponentsScore;
		else
			finalOpponentsScore = valueOfTemporaryUsedCard;

		if (getPlayersScore() > 20)
		{
			playerStand = true;
			controller.darkenCards(true);
		}

		if (finalOpponentsScore > 20)
		{
			opponentStand = true;
			controller.darkenCards(false);
		}

		/*-if (playerStand && opponentStand)
		{
			if (finalOpponentsScore == getPlayersScore())
			{
				controller.generalDialog(Constants.DRAW);
				newGameSet();
			} else if (getPlayersScore() > 20 && finalOpponentsScore > 20)
			{
				controller.generalDialog(Constants.DRAW);
				newGameSet();
			} else if (getPlayersScore() > 20)
			{
				opponentsSets++;
				controller.generalDialog(Constants.SET_OPPONENT);
				newGameSet();
			} else if (finalOpponentsScore > 20)
			{
				playersSets++;
				controller.generalDialog(Constants.SET_PLAYER);
				newGameSet();
			} else if (finalOpponentsScore < getPlayersScore())
			{
				playersSets++;
				controller.generalDialog(Constants.SET_PLAYER);
				newGameSet();
			} else
			{
				opponentsSets++;
				controller.generalDialog(Constants.SET_OPPONENT);
				newGameSet();
			}

			if (playersSets == 3)
			{
				controller.visualizeWithDelayPlsTable.play();
				controller.generalDialog(Constants.GAME_PLAYER);
				end = true;
			} else if (opponentsSets == 3)
			{
				controller.visualizeWithDelayOpsTable.play();
				controller.generalDialog(Constants.GAME_OPPONENT);
				end = true;
			}

			if (end)
			{
				controller.showOrHidehandCards(false);
				controller.nextSetStartLeaveGame.setVisible(true);
				controller.disableNextAndStandButtons(true);
			}
		}*/

		if (!end && !playerStand)
		{
			playersScore = useCard(mainDeck.getAndRemoveCard(), cardsOnPlayersTable);
			if (opponentPlayedCard)
				controller.opponentPlayedCardSoWait.play();
			else
				controller.visualizeWithDelayPlsTable.play();
		} else if (opponentPlayedCard)
			controller.opponentPlayedCardSoWait.play();
		else if (end)
			controller.visualizeWithShortDelayPlsTable.play();
		else
			controller.visualizeWithDelayPlsTable.play();

		if (playerStand)
			opponentsTurn();
	}

	private void newGameSet()
	{
		playerStand = false;
		opponentStand = false;
		playersScore = 0;
		opponentsScore = 0;
		controller.resetScore();
		mainDeck.fillAndShuffleMainDeck();
		cardsOnPlayersTable = new ArrayList<>();
		cardsOnOpponentsTable = new ArrayList<>();
		controller.pointVisibler(playersSets, opponentsSets);
		controller.hideAllHandButtons();
		controller.activateHandButtons();
		controller.clearImages();
	}

	public void waitingForCardUse()
	{
		opponent.getCardsForMatch().get(whichCardOpPlayed).useCard();
		opponentsScore = useCard(opponent.lockedOpponentsPlayedCard, cardsOnOpponentsTable);
		controller.visualizationOfTableCards(false);
	}

	public int useCard(SideDeckCard card, List<SideDeckCard> personsCardsOnTable)
	{
		personsCardsOnTable.add(card);
		return calculateScore(personsCardsOnTable, false);
	}

	private int calculateScore(List<SideDeckCard> personsCardsOnTable, boolean preCalculation)
	{
		List<SideDeckCard> calculationList = new ArrayList<>(personsCardsOnTable);

		if (preCalculation)
			calculationList.add(opponent.lockedOpponentsPlayedCard);

		List<Integer> cardValues = new ArrayList<>();
		int calculatedScore;

		for (SideDeckCard card : calculationList)
		{
			if (card.getCard().equals(DDDCard.PLUS_MINUS_ONE_TWO))
			{
				if (card.hasInactiveLeftTurn())
				{
					if (card.hasInactiveRightTurn())
						cardValues.add(getCardsValue(card.getCard().getFirstValue(), false));
					else
						cardValues.add(getCardsValue(card.getCard().getFirstValue(), true));
				} else
				{
					if (card.hasInactiveRightTurn())
						cardValues.add(getCardsValue(card.getCard().getSecondValue(), false));
					else
						cardValues.add(getCardsValue(card.getCard().getSecondValue(), true));
				}
			} else if (card.hasInactiveLeftTurn())
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
			} else
			{
				if (card.hasInactiveRightTurn())
					cardValues.add(getCardsValue(card.getCard().getFirstValue(), true));
				else
					cardValues.add(getCardsValue(card.getCard().getSecondValue(), true));
			}
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

	public List<SideDeckCard> getCardsOnPlayersTable()
	{
		return cardsOnPlayersTable;
	}

	public List<SideDeckCard> getCardsOnOpponentsTable()
	{
		return cardsOnOpponentsTable;
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

}