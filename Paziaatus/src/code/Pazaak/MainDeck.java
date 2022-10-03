package Pazaak;

import java.util.ArrayList;

public class MainDeck
{
    private ArrayList<RealCard> mainDeck;

    public MainDeck()
    {
        this.mainDeck = null;
    }

    public void fillAndShuffleMainDeck()
    {
        ArrayList<RealCard> mainDeck = new ArrayList<>();
        for (int i = 0; i < 4; ++i)
        {
            mainDeck.add(new RealCard(Card.ONE));
            mainDeck.add(new RealCard(Card.TWO));
            mainDeck.add(new RealCard(Card.THREE));
            mainDeck.add(new RealCard(Card.FOUR));
            mainDeck.add(new RealCard(Card.FIVE));
            mainDeck.add(new RealCard(Card.SIX));
            mainDeck.add(new RealCard(Card.SEVEN));
            mainDeck.add(new RealCard(Card.EIGHT));
            mainDeck.add(new RealCard(Card.NINE));
            mainDeck.add(new RealCard(Card.TEN));
        }
        this.mainDeck = Shuffler.shuffle(mainDeck);
    }

    public RealCard getAndRemoveCard()
    {
        RealCard card = mainDeck.get(0);
        mainDeck.remove(0);
        return card;
    }
}