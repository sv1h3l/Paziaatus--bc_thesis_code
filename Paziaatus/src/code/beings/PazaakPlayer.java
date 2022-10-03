package beings;

import java.util.ArrayList;

import Pazaak.RealCard;
import Pazaak.Shuffler;

public class PazaakPlayer
{
    private static final int NUMBER_OF_CARDS_IN_HAND = 4;
    private ArrayList<RealCard> cardsForMatch;
    /*private Item artifact;
    private Item implant;*/

    public PazaakPlayer()
    {
        cardsForMatch = null;
    }

    public void makeCardsForMatch(ArrayList<RealCard> sideDeck)
    {
        ArrayList<RealCard> duplicatedSideDeck = new ArrayList<>(sideDeck);
        ArrayList<RealCard> cardsForMatch = new ArrayList<>();

        duplicatedSideDeck = Shuffler.shuffle(duplicatedSideDeck);

        for(int i = 0; i < NUMBER_OF_CARDS_IN_HAND; i++)
        {
            cardsForMatch.add(duplicatedSideDeck.get(i));
        }
        this.cardsForMatch = cardsForMatch;
    }
    
    public ArrayList<RealCard> getCardsForMatch()
	{
		return cardsForMatch;
	}

	public void setCardsForMatch(ArrayList<RealCard> cardsForMatch)
	{
		this.cardsForMatch = cardsForMatch;
	}

	public RealCard getCardForMatch(int i)
    {
        return cardsForMatch.get(i);
    }

    public void removeCardForMatch(int i)
    {
        cardsForMatch.remove(i);
    }
}
