package paziak;

public enum DDDCardsImages
{
	BACK("/images/1600x900/items/cards/b.png", "", "", ""),

	ONE("/images/1600x900/items/cards/g_1.png", "", "", ""), TWO("/images/1600x900/items/cards/g_2.png", "", "", ""),
	THREE("/images/1600x900/items/cards/g_3.png", "", "", ""), FOUR("/images/1600x900/items/cards/g_4.png", "", "", ""),
	FIVE("/images/1600x900/items/cards/g_5.png", "", "", ""), SIX("/images/1600x900/items/cards/g_6.png", "", "", ""),
	SEVEN("/images/1600x900/items/cards/g_7.png", "", "", ""), EIGHT("/images/1600x900/items/cards/g_8.png", "", "", ""),
	NINE("/images/1600x900/items/cards/g_9.png", "", "", ""), TEN("/images/1600x900/items/cards/g_10.png", "", "", ""),

	PLUS_ONE("/images/1600x900/items/cards/b_+1.png", "", "", ""), PLUS_TWO("/images/1600x900/items/cards/b_+2.png", "", "", ""),
	PLUS_THREE("/images/1600x900/items/cards/b_+3.png", "", "", ""), PLUS_FOUR("/images/1600x900/items/cards/b_+4.png", "", "", ""),
	PLUS_FIVE("/images/1600x900/items/cards/b_+5.png", "", "", ""), PLUS_SIX("/images/1600x900/items/cards/b_+6.png", "", "", ""),

	MINUS_ONE("/images/1600x900/items/cards/r_-1.png", "", "", ""), MINUS_TWO("/images/1600x900/items/cards/r_-2.png", "", "", ""),
	MINUS_THREE("/images/1600x900/items/cards/r_-3.png", "", "", ""), MINUS_FOUR("/images/1600x900/items/cards/r_-4.png", "", "", ""),
	MINUS_FIVE("/images/1600x900/items/cards/r_-5.png", "", "", ""), MINUS_SIX("/images/1600x900/items/cards/r_-6.png", "", "", ""),

	PLUS_MINUS_ONE("/images/1600x900/items/cards/br_+1.png", "/images/1600x900/items/cards/br_-1.png", "", ""),
	PLUS_MINUS_TWO("/images/1600x900/items/cards/br_+2.png", "/images/1600x900/items/cards/br_-2.png", "", ""),
	PLUS_MINUS_THREE("/images/1600x900/items/cards/br_+3.png", "/images/1600x900/items/cards/br_-3.png", "", ""),
	PLUS_MINUS_FOUR("/images/1600x900/items/cards/br_+4.png", "/images/1600x900/items/cards/br_-4.png", "", ""),
	PLUS_MINUS_FIVE("/images/1600x900/items/cards/br_+5.png", "/images/1600x900/items/cards/br_-5.png", "", ""),
	PLUS_MINUS_SIX("/images/1600x900/items/cards/br_+6.png", "/images/1600x900/items/cards/br_-6.png", "", ""),

	TWO_AND_FOUR("/images/1600x900/items/cards/s_2&4.png", "", "", ""), THREE_AND_SIX("/images/1600x900/items/cards/s_3&6.png", "", "", ""),
	DOUBLE("/images/1600x900/items/cards/s_d.png", "", "", ""), PLUS_MINUS_ONE_TWO("/images/1600x900/items/cards/s_1.png", "/images/1600x900/items/cards/s_2.png",
			"/images/1600x900/items/cards/s_-1.png", "/images/1600x900/items/cards/s_-2.png");

	private final String	firstImagesPath;
	private final String	secondImagesPath;
	private final String	thirdImagesPath;
	private final String	fourthImagesPath;

	DDDCardsImages(String firstImage, String secondImage, String thirdImage, String fourthImage)
	{
		this.firstImagesPath = firstImage;
		this.secondImagesPath = secondImage;
		this.thirdImagesPath = thirdImage;
		this.fourthImagesPath = fourthImage;
	}

	public String getFirstImage()
	{
		return firstImagesPath;
	}

	public String getSecondImage()
	{
		return secondImagesPath;
	}

	public String getThirdImage()
	{
		return thirdImagesPath;
	}

	public String getFourthImage()
	{
		return fourthImagesPath;
	}
}