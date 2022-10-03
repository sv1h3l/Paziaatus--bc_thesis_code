package pcg;

public enum CardsImages
{
    BACK("src/resources/pcg/images/cards/b.png", "", "", ""),

    ONE("src/resources/pcg/images/cards/g_1.png", "", "", ""),
    TWO("src/resources/pcg/images/cards/g_2.png", "", "", ""),
    THREE("src/resources/pcg/images/cards/g_3.png", "", "", ""),
    FOUR("src/resources/pcg/images/cards/g_4.png", "", "", ""),
    FIVE("src/resources/pcg/images/cards/g_5.png", "", "", ""),
    SIX("src/resources/pcg/images/cards/g_6.png", "", "", ""),
    SEVEN("src/resources/pcg/images/cards/g_7.png", "", "", ""),
    EIGHT("src/resources/pcg/images/cards/g_8.png", "", "", ""),
    NINE("src/resources/pcg/images/cards/g_9.png", "", "", ""),
    TEN("src/resources/pcg/images/cards/g_10.png", "", "", ""),

    PLUS_ONE("src/resources/pcg/images/cards/b_+1.png", "", "", ""),
    PLUS_TWO("src/resources/pcg/images/cards/b_+2.png", "", "", ""),
    PLUS_THREE("src/resources/pcg/images/cards/b_+3.png", "", "", ""),
    PLUS_FOUR("src/resources/pcg/images/cards/b_+4.png", "", "", ""),
    PLUS_FIVE("src/resources/pcg/images/cards/b_+5.png", "", "", ""),
    PLUS_SIX("src/resources/pcg/images/cards/b_+6.png", "", "", ""),

    MINUS_ONE("src/resources/pcg/images/cards/r_-1.png", "", "", ""),
    MINUS_TWO("src/resources/pcg/images/cards/r_-2.png", "", "", ""),
    MINUS_THREE("src/resources/pcg/images/cards/r_-3.png", "", "", ""),
    MINUS_FOUR("src/resources/pcg/images/cards/r_-4.png", "", "", ""),
    MINUS_FIVE("src/resources/pcg/images/cards/r_-5.png", "", "", ""),
    MINUS_SIX("src/resources/paziaatus/images/cards/r_-6.png", "", "", ""),

    PLUS_MINUS_ONE("src/resources/pcg/images/cards/br_+1.png","src/resources/pcg/images/cards/br_-1.png", "", ""),
    PLUS_MINUS_TWO("src/resources/pcg/images/cards/br_+2.png","src/resources/pcg/images/cards/br_-2.png", "", ""),
    PLUS_MINUS_THREE("src/resources/pcg/images/cards/br_+3.png","src/resources/pcg/images/cards/br_-3.png", "", ""),
    PLUS_MINUS_FOUR("src/resources/pcg/images/cards/br_+4.png","src/resources/pcg/images/cards/br_-4.png", "", ""),
    PLUS_MINUS_FIVE("src/resources/pcg/images/cards/br_+5.png","src/resources/pcg/images/cards/br_-5.png", "", ""),
    PLUS_MINUS_SIX("src/resources/pcg/images/cards/br_+6.png","src/resources/pcg/images/cards/br_-6.png", "", ""),

    TWO_AND_FOUR("src/resources/pcg/images/cards/s_2&4.png","", "", ""),
    THREE_AND_SIX("src/resources/pcg/images/cards/s_3&6.png","", "", ""),
    DOUBLE("src/resources/pcg/images/cards/s_d.png", "", "", ""),
    PLUS_MINUS_ONE_TWO("src/resources/pcg/images/cards/s_1.png","src/resources/pcg/images/cards/s_2.png","src/resources/pcg/images/cards/s_-1.png","src/resources/pcg/images/cards/s_-2.png");

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