package pazaak;

public enum CardsImages
{
    BACK("src/resources/images/b.png", "", "", ""),

    ONE("src/resources/images/g_1.png", "", "", ""),
    TWO("src/resources/images/g_2.png", "", "", ""),
    THREE("src/resources/images/g_3.png", "", "", ""),
    FOUR("src/resources/images/g_4.png", "", "", ""),
    FIVE("src/resources/images/g_5.png", "", "", ""),
    SIX("src/resources/images/g_6.png", "", "", ""),
    SEVEN("src/resources/images/g_7.png", "", "", ""),
    EIGHT("src/resources/images/g_8.png", "", "", ""),
    NINE("src/resources/images/g_9.png", "", "", ""),
    TEN("src/resources/images/g_10.png", "", "", ""),

    PLUS_ONE("src/resources/images/b_+1.png", "", "", ""),
    PLUS_TWO("src/resources/images/b_+2.png", "", "", ""),
    PLUS_THREE("src/resources/images/b_+3.png", "", "", ""),
    PLUS_FOUR("src/resources/images/b_+4.png", "", "", ""),
    PLUS_FIVE("src/resources/images/b_+5.png", "", "", ""),
    PLUS_SIX("src/resources/images/b_+6.png", "", "", ""),

    MINUS_ONE("src/resources/images/r_-1.png", "", "", ""),
    MINUS_TWO("src/resources/images/r_-2.png", "", "", ""),
    MINUS_THREE("src/resources/images/r_-3.png", "", "", ""),
    MINUS_FOUR("src/resources/images/r_-4.png", "", "", ""),
    MINUS_FIVE("src/resources/images/r_-5.png", "", "", ""),
    MINUS_SIX("src/resources/images/r_-6.png", "", "", ""),

    PLUS_MINUS_ONE("src/resources/images/br_+1.png","src/resources/images/br_-1.png", "", ""),
    PLUS_MINUS_TWO("src/resources/images/br_+2.png","src/resources/images/br_-2.png", "", ""),
    PLUS_MINUS_THREE("src/resources/images/br_+3.png","src/resources/images/br_-3.png", "", ""),
    PLUS_MINUS_FOUR("src/resources/images/br_+4.png","src/resources/images/br_-4.png", "", ""),
    PLUS_MINUS_FIVE("src/resources/images/br_+5.png","src/resources/images/br_-5.png", "", ""),
    PLUS_MINUS_SIX("src/resources/images/br_+6.png","src/resources/images/br_-6.png", "", ""),

    TWO_AND_FOUR("src/resources/images/s_2&4.png","", "", ""),
    THREE_AND_SIX("src/resources/images/s_3&6.png","", "", ""),
    DOUBLE("src/resources/images/s_d.png", "", "", ""),
    PLUS_MINUS_ONE_TWO("src/resources/images/s_1.png","src/resources/images/s_2.png","src/resources/images/s_-1.png","src/resources/images/s_-2.png");

    private final String firstImagesPath;
    private final String secondImagesPath;
    private final String thirdImagesPath;
    private final String fourthImagesPath;

    CardsImages(String firstImage, String secondImage, String thirdImage, String fourthImage)
    {
        this.firstImagesPath    = firstImage;
        this.secondImagesPath   = secondImage;
        this.thirdImagesPath    = thirdImage;
        this.fourthImagesPath   = fourthImage;
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