package paziak;

public enum DDDCard
{
	BACK(0, 0, DDDCardsImages.BACK),

	ONE(1, 0, DDDCardsImages.ONE), TWO(2, 0, DDDCardsImages.TWO), THREE(3, 0, DDDCardsImages.THREE),
	FOUR(4, 0, DDDCardsImages.FOUR), FIVE(5, 0, DDDCardsImages.FIVE), SIX(6, 0, DDDCardsImages.SIX),
	SEVEN(7, 0, DDDCardsImages.SEVEN), EIGHT(8, 0, DDDCardsImages.EIGHT), NINE(9, 0, DDDCardsImages.NINE),
	TEN(10, 0, DDDCardsImages.TEN),

	PLUS_ONE(1, 0, DDDCardsImages.PLUS_ONE), PLUS_TWO(2, 0, DDDCardsImages.PLUS_TWO),
	PLUS_THREE(3, 0, DDDCardsImages.PLUS_THREE), PLUS_FOUR(4, 0, DDDCardsImages.PLUS_FOUR),
	PLUS_FIVE(5, 0, DDDCardsImages.PLUS_FIVE), PLUS_SIX(6, 0, DDDCardsImages.PLUS_SIX),

	MINUS_ONE(1, 0, DDDCardsImages.MINUS_ONE), MINUS_TWO(2, 0, DDDCardsImages.MINUS_TWO),
	MINUS_THREE(3, 0, DDDCardsImages.MINUS_THREE), MINUS_FOUR(4, 0, DDDCardsImages.MINUS_FOUR),
	MINUS_FIVE(5, 0, DDDCardsImages.MINUS_FIVE), MINUS_SIX(6, 0, DDDCardsImages.MINUS_SIX),

	PLUS_MINUS_ONE(1, 0, DDDCardsImages.PLUS_MINUS_ONE), PLUS_MINUS_TWO(2, 0, DDDCardsImages.PLUS_MINUS_TWO),
	PLUS_MINUS_THREE(3, 0, DDDCardsImages.PLUS_MINUS_THREE), PLUS_MINUS_FOUR(4, 0, DDDCardsImages.PLUS_MINUS_FOUR),
	PLUS_MINUS_FIVE(5, 0, DDDCardsImages.PLUS_MINUS_FIVE), PLUS_MINUS_SIX(6, 0, DDDCardsImages.PLUS_MINUS_SIX),

	THREE_AND_SIX(0, 0, DDDCardsImages.THREE_AND_SIX), TWO_AND_FOUR(0, 0, DDDCardsImages.TWO_AND_FOUR),
	DOUBLE(0, 0, DDDCardsImages.DOUBLE), PLUS_MINUS_ONE_TWO(1, 2, DDDCardsImages.PLUS_MINUS_ONE_TWO);

	private final int			firstValue;
	private final int			secondValue;
	private final DDDCardsImages	images;

	DDDCard(int firstValue, int secondValue, DDDCardsImages images)
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

	public DDDCardsImages getImages()
	{
		return images;
	}

	public boolean isItMinusCard()
	{
		return this.equals(DDDCard.MINUS_ONE) || this.equals(MINUS_TWO) || this.equals(MINUS_THREE)
				|| this.equals(MINUS_FOUR) || this.equals(MINUS_FIVE) || this.equals(MINUS_SIX);
	}

	public boolean isItDoubleCard()
	{
		return this.equals(DDDCard.DOUBLE);
	}

	public boolean isItThreeAndSixCard()
	{
		return this.equals(DDDCard.THREE_AND_SIX);
	}

	public boolean isItTwoAndFourCard()
	{
		return this.equals(DDDCard.TWO_AND_FOUR);
	}
}