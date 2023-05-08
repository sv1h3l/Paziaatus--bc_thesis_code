package paziak;

import java.util.ArrayList;

public class DDDSideDeck
{
    private ArrayList<Card> sideDeck;

    public DDDSideDeck()
    {
        this.sideDeck = new ArrayList<>();
    }

    /*public void fillSideDeck()
    {
        ArrayList<Card> sideDeck = new ArrayList<>();

        sideDeck.add(new Card(DDDCard.PLUS_ONE));
        sideDeck.add(new Card(DDDCard.PLUS_TWO));
        sideDeck.add(new Card(DDDCard.PLUS_THREE));
        sideDeck.add(new Card(DDDCard.PLUS_FOUR));
        sideDeck.add(new Card(DDDCard.PLUS_FIVE));
        sideDeck.add(new Card(DDDCard.PLUS_SIX));

        sideDeck.add(new Card(DDDCard.MINUS_ONE));
        sideDeck.add(new Card(DDDCard.MINUS_TWO));
        sideDeck.add(new Card(DDDCard.MINUS_THREE));
        sideDeck.add(new Card(DDDCard.MINUS_FOUR));
        sideDeck.add(new Card(DDDCard.MINUS_FIVE));
        sideDeck.add(new Card(DDDCard.MINUS_SIX));

        sideDeck.add(new Card(DDDCard.PLUS_MINUS_ONE));
        sideDeck.add(new Card(DDDCard.PLUS_MINUS_TWO));
        sideDeck.add(new Card(DDDCard.PLUS_MINUS_THREE));
        sideDeck.add(new Card(DDDCard.PLUS_MINUS_FOUR));
        sideDeck.add(new Card(DDDCard.PLUS_MINUS_FIVE));
        sideDeck.add(new Card(DDDCard.PLUS_MINUS_SIX));

        sideDeck.add(new Card(DDDCard.DOUBLE));
        sideDeck.add(new Card(DDDCard.THREE_AND_SIX));
        sideDeck.add(new Card(DDDCard.TWO_AND_FOUR));
        sideDeck.add(new Card(DDDCard.PLUS_MINUS_ONE_TWO));

        this.sideDeck = sideDeck;
    }*/

    public ArrayList<Card> getSideDeck()
    {
        return sideDeck;
    }
}
