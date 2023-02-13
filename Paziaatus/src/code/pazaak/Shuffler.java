package pazaak;

import java.util.ArrayList;
import java.util.Random;

public class Shuffler
{
    public static ArrayList<RealCard> shuffle(ArrayList<RealCard> deck)
    {
        ArrayList<RealCard> shuffledDeck = new ArrayList<>();
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
