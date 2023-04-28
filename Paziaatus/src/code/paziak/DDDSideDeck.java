package paziak;

import java.util.ArrayList;

public class DDDSideDeck
{
    private ArrayList<SideDeckCard> sideDeck;

    public DDDSideDeck()
    {
        this.sideDeck = new ArrayList<>();
    }

    public void fillSideDeck()
    {
        ArrayList<SideDeckCard> sideDeck = new ArrayList<>();

        sideDeck.add(new SideDeckCard(DDDCard.PLUS_ONE));
        sideDeck.add(new SideDeckCard(DDDCard.PLUS_TWO));
        sideDeck.add(new SideDeckCard(DDDCard.PLUS_THREE));
        sideDeck.add(new SideDeckCard(DDDCard.PLUS_FOUR));
        sideDeck.add(new SideDeckCard(DDDCard.PLUS_FIVE));
        sideDeck.add(new SideDeckCard(DDDCard.PLUS_SIX));

        sideDeck.add(new SideDeckCard(DDDCard.MINUS_ONE));
        sideDeck.add(new SideDeckCard(DDDCard.MINUS_TWO));
        sideDeck.add(new SideDeckCard(DDDCard.MINUS_THREE));
        sideDeck.add(new SideDeckCard(DDDCard.MINUS_FOUR));
        sideDeck.add(new SideDeckCard(DDDCard.MINUS_FIVE));
        sideDeck.add(new SideDeckCard(DDDCard.MINUS_SIX));

        sideDeck.add(new SideDeckCard(DDDCard.PLUS_MINUS_ONE));
        sideDeck.add(new SideDeckCard(DDDCard.PLUS_MINUS_TWO));
        sideDeck.add(new SideDeckCard(DDDCard.PLUS_MINUS_THREE));
        sideDeck.add(new SideDeckCard(DDDCard.PLUS_MINUS_FOUR));
        sideDeck.add(new SideDeckCard(DDDCard.PLUS_MINUS_FIVE));
        sideDeck.add(new SideDeckCard(DDDCard.PLUS_MINUS_SIX));

        sideDeck.add(new SideDeckCard(DDDCard.DOUBLE));
        sideDeck.add(new SideDeckCard(DDDCard.THREE_AND_SIX));
        sideDeck.add(new SideDeckCard(DDDCard.TWO_AND_FOUR));
        sideDeck.add(new SideDeckCard(DDDCard.PLUS_MINUS_ONE_TWO));

        this.sideDeck = sideDeck;
    }

    public ArrayList<SideDeckCard> getSideDeck()
    {
        return sideDeck;
    }
}
