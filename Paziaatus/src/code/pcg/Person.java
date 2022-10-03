package pcg;

import java.util.ArrayList;

public class Person
{
    private static final int NUMVER_OF_CARDS_IN_HAND = 4;
    protected ArrayList<RealCard> cardsForMatch;

    public Person()
    {
        cardsForMatch = null;
    }

    public void makeCardsForMatch(ArrayList<RealCard> sideDeck)
    {
        ArrayList<RealCard> duplicatedSideDeck = new ArrayList<>(sideDeck);
        ArrayList<RealCard> cardsForMatch = new ArrayList<>();

        duplicatedSideDeck = Shuffler.shuffle(duplicatedSideDeck);

        for(int i = 0; i < NUMVER_OF_CARDS_IN_HAND; i++)
        {
            cardsForMatch.add(duplicatedSideDeck.get(i));
        }
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
