package paziak;

import java.util.ArrayList;

public class DDDMainDeck
{
    private ArrayList<Card> mainDeck;

    public DDDMainDeck()
    {
        this.mainDeck = null;
    }

    public void fillAndShuffleMainDeck()
    {
        ArrayList<Card> mainDeck = new ArrayList<>();
        for (int i = 0; i < 4; ++i)
        {
            /*mainDeck.add(new Card(DDDCard.ONE));
            mainDeck.add(new Card(DDDCard.TWO));
            mainDeck.add(new Card(DDDCard.THREE));
            mainDeck.add(new Card(DDDCard.FOUR));
            mainDeck.add(new Card(DDDCard.FIVE));
            mainDeck.add(new Card(DDDCard.SIX));
            mainDeck.add(new Card(DDDCard.SEVEN));
            mainDeck.add(new Card(DDDCard.EIGHT));
            mainDeck.add(new Card(DDDCard.NINE));
            mainDeck.add(new Card(DDDCard.TEN));*/
        }
        this.mainDeck = DDDShuffler.shuffle(mainDeck);
    }


}