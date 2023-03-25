package pazaak;

public enum CardsImages
{
	BACK("/images/cards/b.png", "", "", ""),

	ONE("/images/cards/g_1.png", "", "", ""), TWO("/images/cards/g_2.png", "", "", ""),
	THREE("/images/cards/g_3.png", "", "", ""), FOUR("/images/cards/g_4.png", "", "", ""),
	FIVE("/images/cards/g_5.png", "", "", ""), SIX("/images/cards/g_6.png", "", "", ""),
	SEVEN("/images/cards/g_7.png", "", "", ""), EIGHT("/images/cards/g_8.png", "", "", ""),
	NINE("/images/cards/g_9.png", "", "", ""), TEN("/images/cards/g_10.png", "", "", ""),

	PLUS_ONE("/images/cards/b_+1.png", "", "", ""), PLUS_TWO("/images/cards/b_+2.png", "", "", ""),
	PLUS_THREE("/images/cards/b_+3.png", "", "", ""), PLUS_FOUR("/images/cards/b_+4.png", "", "", ""),
	PLUS_FIVE("/images/cards/b_+5.png", "", "", ""), PLUS_SIX("/images/cards/b_+6.png", "", "", ""),

	MINUS_ONE("/images/cards/r_-1.png", "", "", ""), MINUS_TWO("/images/cards/r_-2.png", "", "", ""),
	MINUS_THREE("/images/cards/r_-3.png", "", "", ""), MINUS_FOUR("/images/cards/r_-4.png", "", "", ""),
	MINUS_FIVE("/images/cards/r_-5.png", "", "", ""), MINUS_SIX("/images/cards/r_-6.png", "", "", ""),

	PLUS_MINUS_ONE("/images/cards/br_+1.png", "/images/cards/br_-1.png", "", ""),
	PLUS_MINUS_TWO("/images/cards/br_+2.png", "/images/cards/br_-2.png", "", ""),
	PLUS_MINUS_THREE("/images/cards/br_+3.png", "/images/cards/br_-3.png", "", ""),
	PLUS_MINUS_FOUR("/images/cards/br_+4.png", "/images/cards/br_-4.png", "", ""),
	PLUS_MINUS_FIVE("/images/cards/br_+5.png", "/images/cards/br_-5.png", "", ""),
	PLUS_MINUS_SIX("/images/cards/br_+6.png", "/images/cards/br_-6.png", "", ""),

	TWO_AND_FOUR("/images/cards/s_2&4.png", "", "", ""), THREE_AND_SIX("/images/cards/s_3&6.png", "", "", ""),
	DOUBLE("/images/cards/s_d.png", "", "", ""), PLUS_MINUS_ONE_TWO("/images/cards/s_1.png", "/images/cards/s_2.png",
			"/images/cards/s_-1.png", "/images/cards/s_-2.png");

	private final String	firstImagesPath;
	private final String	secondImagesPath;
	private final String	thirdImagesPath;
	private final String	fourthImagesPath;

	CardsImages(String firstImage, String secondImage, String thirdImage, String fourthImage)
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