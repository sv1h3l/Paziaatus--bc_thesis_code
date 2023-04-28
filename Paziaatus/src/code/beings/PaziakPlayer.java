package beings;

import java.util.ArrayList;

import paziak.SideDeckCard;
import residue.Tools;

public class PaziakPlayer
{
	private static final int	NUMBER_OF_CARDS_IN_HAND	= 4;
	private ArrayList<SideDeckCard>	playersSpecialCards;
	public SideDeckCard				lockedOpponentsPlayedCard;

	public PaziakPlayer()
	{
		playersSpecialCards = null;
	}

	public void makeOpponentsSideDeck(/* String actualPlanet */ArrayList<SideDeckCard> sideDeck)
	{
		Tools.shuffleDeck(sideDeck);

		for (int i = 0; i < NUMBER_OF_CARDS_IN_HAND; i++)
			this.playersSpecialCards.add(sideDeck.get(i));
	}

	public ArrayList<SideDeckCard> getCardsForMatch()
	{
		return playersSpecialCards;
	}

	public void setCardsForMatch(ArrayList<SideDeckCard> cardsForMatch)
	{
		this.playersSpecialCards = cardsForMatch;
	}

	public SideDeckCard getCardForMatch(int i)
	{
		return playersSpecialCards.get(i);
	}

	public void removeCardForMatch(int i)
	{
		playersSpecialCards.remove(i);
	}
}
