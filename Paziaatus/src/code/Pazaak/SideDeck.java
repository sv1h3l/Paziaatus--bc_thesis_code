package Pazaak;

import java.util.ArrayList;

public class SideDeck
{
    private ArrayList<RealCard> sideDeck;

    public SideDeck()
    {
        this.sideDeck = new ArrayList<>();
    }

    public void fillSideDeck()
    {
        ArrayList<RealCard> sideDeck = new ArrayList<>();

        sideDeck.add(new RealCard(Card.PLUS_ONE));
        sideDeck.add(new RealCard(Card.PLUS_TWO));
        sideDeck.add(new RealCard(Card.PLUS_THREE));
        sideDeck.add(new RealCard(Card.PLUS_FOUR));
        sideDeck.add(new RealCard(Card.PLUS_FIVE));
        sideDeck.add(new RealCard(Card.PLUS_SIX));

        sideDeck.add(new RealCard(Card.MINUS_ONE));
        sideDeck.add(new RealCard(Card.MINUS_TWO));
        sideDeck.add(new RealCard(Card.MINUS_THREE));
        sideDeck.add(new RealCard(Card.MINUS_FOUR));
        sideDeck.add(new RealCard(Card.MINUS_FIVE));
        sideDeck.add(new RealCard(Card.MINUS_SIX));

        sideDeck.add(new RealCard(Card.PLUS_MINUS_ONE));
        sideDeck.add(new RealCard(Card.PLUS_MINUS_TWO));
        sideDeck.add(new RealCard(Card.PLUS_MINUS_THREE));
        sideDeck.add(new RealCard(Card.PLUS_MINUS_FOUR));
        sideDeck.add(new RealCard(Card.PLUS_MINUS_FIVE));
        sideDeck.add(new RealCard(Card.PLUS_MINUS_SIX));

        sideDeck.add(new RealCard(Card.DOUBLE));
        sideDeck.add(new RealCard(Card.THREE_AND_SIX));
        sideDeck.add(new RealCard(Card.TWO_AND_FOUR));
        sideDeck.add(new RealCard(Card.PLUS_MINUS_ONE_TWO));

        this.sideDeck = sideDeck;
    }

    public ArrayList<RealCard> getSideDeck()
    {
        return sideDeck;
    }
}
