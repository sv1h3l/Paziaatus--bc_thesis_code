package pazaak;

public enum Card
{
	BACK(0, 0, CardsImages.BACK),

	ONE(1, 0, CardsImages.ONE), TWO(2, 0, CardsImages.TWO), THREE(3, 0, CardsImages.THREE),
	FOUR(4, 0, CardsImages.FOUR), FIVE(5, 0, CardsImages.FIVE), SIX(6, 0, CardsImages.SIX),
	SEVEN(7, 0, CardsImages.SEVEN), EIGHT(8, 0, CardsImages.EIGHT), NINE(9, 0, CardsImages.NINE),
	TEN(10, 0, CardsImages.TEN),

	PLUS_ONE(1, 0, CardsImages.PLUS_ONE), PLUS_TWO(2, 0, CardsImages.PLUS_TWO),
	PLUS_THREE(3, 0, CardsImages.PLUS_THREE), PLUS_FOUR(4, 0, CardsImages.PLUS_FOUR),
	PLUS_FIVE(5, 0, CardsImages.PLUS_FIVE), PLUS_SIX(6, 0, CardsImages.PLUS_SIX),

	MINUS_ONE(1, 0, CardsImages.MINUS_ONE), MINUS_TWO(2, 0, CardsImages.MINUS_TWO),
	MINUS_THREE(3, 0, CardsImages.MINUS_THREE), MINUS_FOUR(4, 0, CardsImages.MINUS_FOUR),
	MINUS_FIVE(5, 0, CardsImages.MINUS_FIVE), MINUS_SIX(6, 0, CardsImages.MINUS_SIX),

	PLUS_MINUS_ONE(1, 0, CardsImages.PLUS_MINUS_ONE), PLUS_MINUS_TWO(2, 0, CardsImages.PLUS_MINUS_TWO),
	PLUS_MINUS_THREE(3, 0, CardsImages.PLUS_MINUS_THREE), PLUS_MINUS_FOUR(4, 0, CardsImages.PLUS_MINUS_FOUR),
	PLUS_MINUS_FIVE(5, 0, CardsImages.PLUS_MINUS_FIVE), PLUS_MINUS_SIX(6, 0, CardsImages.PLUS_MINUS_SIX),

	THREE_AND_SIX(0, 0, CardsImages.THREE_AND_SIX), TWO_AND_FOUR(0, 0, CardsImages.TWO_AND_FOUR),
	DOUBLE(0, 0, CardsImages.DOUBLE), PLUS_MINUS_ONE_TWO(1, 2, CardsImages.PLUS_MINUS_ONE_TWO);

	private final int			firstValue;
	private final int			secondValue;
	private final CardsImages	images;

	Card(int firstValue, int secondValue, CardsImages images)
	{
		this.firstValue = firstValue;
		this.secondValue = secondValue;
		this.images = images;
	}

	public int getFirstValue()
	{
		return this.firstValue;
	}

	public int getSecondValue()
	{
		return this.secondValue;
	}

	public CardsImages getImages()
	{
		return images;
	}

	public boolean isItMinusCard()
	{
		return this.equals(Card.MINUS_ONE) || this.equals(MINUS_TWO) || this.equals(MINUS_THREE)
				|| this.equals(MINUS_FOUR) || this.equals(MINUS_FIVE) || this.equals(MINUS_SIX);
	}

	public boolean isItDoubleCard()
	{
		return this.equals(Card.DOUBLE);
	}

	public boolean isItThreeAndSixCard()
	{
		return this.equals(Card.THREE_AND_SIX);
	}

	public boolean isItTwoAndFourCard()
	{
		return this.equals(Card.TWO_AND_FOUR);
	}
}