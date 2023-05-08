package paziak;

import java.util.ArrayList;
import java.util.Random;

public class DDDShuffler
{
    public static ArrayList<Card> shuffle(ArrayList<Card> deck)
    {
        ArrayList<Card> shuffledDeck = new ArrayList<>();
        Random random = new Random();
        int randomIndex;
        int originalSize = deck.size();

        for (int i = 0; i < originalSize; i++)
        {
            randomIndex = random.nextInt(deck.size());
            shuffledDeck.add(deck.get(randomIndex));
            deck.remove(randomIndex);
        }
        return shuffledDeck;
    }
}
