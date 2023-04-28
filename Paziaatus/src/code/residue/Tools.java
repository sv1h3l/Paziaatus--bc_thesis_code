package residue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Tools
{
	static Random random = new Random();

	public static String idFromSource(String source)
	{
		int start = source.indexOf("id=") + 3;
		int end = source.indexOf(",", start);
		return source.substring(start, end);
	}

	public static int getNumberFromString(String source, String removeThisFromSource)
	{
		source = source.replace(removeThisFromSource, "");
		return Integer.parseInt(source);
	}

	public static int getRandomNumber(int ceiling)
	{
		return random.nextInt(ceiling);
	}

	public static void shuffleDeck(ArrayList<?> deck)
    {
        Collections.shuffle(deck);
    }
}
