package beings;

import java.util.ArrayList;
import java.util.List;

import paziak.Card;
import residue.Constants;
import residue.Item;
import residue.Tools;

public class PaziakPlayer
{
	public ArrayList<Card> sideDeck;

	public PaziakPlayer()
	{
	}

	public void makeSideDeck(Item[] deck)
	{
		sideDeck = new ArrayList<>();
		ArrayList<Card> sideDeck = createSideDeck(deck);
		Tools.shuffleDeck(sideDeck);

		for (int i = 0; i < Constants.MAX_SIDE_DECK_CARDS; i++)
			this.sideDeck.add(sideDeck.get(i));
	}

	public ArrayList<Card> createSideDeck(Item[] deck)
	{
		ArrayList<Card> sideDeck = new ArrayList<>();

		for (Item card : deck)
		{
			String[] images = getSpecialCardImages(card);
			sideDeck.add(
					new Card(card.getTernaryFeature(), card.getPrimaryFeature(), card.getSecondaryFeature(), images[0], images[1], images[2], images[3]));
		}

		return sideDeck;
	}

	private String[] getSpecialCardImages(Item card)
	{
		String pathPrefix = "cards/";
		switch (card.getTernaryFeature())
		{
			case 3:
				return new String[] { pathPrefix+card.getImg() + "_1", pathPrefix+card.getImg() + "_2", "", "" };
			case 4:
				return new String[] { pathPrefix+card.getImg() + "_1", pathPrefix+card.getImg() + "_2", pathPrefix+card.getImg() + "_3", pathPrefix+card.getImg() + "_4" };
			default:
				return new String[] { pathPrefix+card.getImg(), "", "", "" };
		}
	}

	public ArrayList<Card> getSideDeck()
	{
		return sideDeck;
	}

	public void setSideDeck(ArrayList<Card> cardsForMatch)
	{
		this.sideDeck = cardsForMatch;
	}

	public Card getCardFromSideDeck(int nth)
	{
		return sideDeck.get(nth);
	}

	public void removeCardFromSideDeck(int nth)
	{
		sideDeck.remove(nth);
	}
}
