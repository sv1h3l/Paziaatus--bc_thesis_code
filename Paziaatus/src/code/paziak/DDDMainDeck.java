package paziak;

import java.util.ArrayList;

public class DDDMainDeck
{
    private ArrayList<SideDeckCard> mainDeck;

    public DDDMainDeck()
    {
        this.mainDeck = null;
    }

    public void fillAndShuffleMainDeck()
    {
        ArrayList<SideDeckCard> mainDeck = new ArrayList<>();
        for (int i = 0; i < 4; ++i)
        {
            mainDeck.add(new SideDeckCard(DDDCard.ONE));
            mainDeck.add(new SideDeckCard(DDDCard.TWO));
            mainDeck.add(new SideDeckCard(DDDCard.THREE));
            mainDeck.add(new SideDeckCard(DDDCard.FOUR));
            mainDeck.add(new SideDeckCard(DDDCard.FIVE));
            mainDeck.add(new SideDeckCard(DDDCard.SIX));
            mainDeck.add(new SideDeckCard(DDDCard.SEVEN));
            mainDeck.add(new SideDeckCard(DDDCard.EIGHT));
            mainDeck.add(new SideDeckCard(DDDCard.NINE));
            mainDeck.add(new SideDeckCard(DDDCard.TEN));
        }
        this.mainDeck = DDDShuffler.shuffle(mainDeck);
    }

    public SideDeckCard getAndRemoveCard()
    {
        SideDeckCard card = mainDeck.get(0);
        mainDeck.remove(0);
        return card;
    }
}