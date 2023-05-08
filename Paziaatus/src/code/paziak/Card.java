package paziak;

public class Card
{
	private int cardTypeCode;

	private int	primaryValue;
	private int	secondaryValue;

	private String	firstImage;
	private String	secondImage;
	private String	thirdImage;
	private String	fourthImage;

	private boolean	turnedLeft;
	private boolean	turnedRight;
	private boolean	used;

	public Card(int cardTypeCode, int primaryValue, int secondaryValue, String firstImage, String secondImage,
			String thirdImage, String fourthImage)
	{
		this.cardTypeCode = cardTypeCode;

		this.primaryValue = primaryValue;
		this.secondaryValue = secondaryValue;

		this.firstImage = firstImage;
		this.secondImage = secondImage;
		this.thirdImage = thirdImage;
		this.fourthImage = fourthImage;

		this.turnedLeft = false;
		this.turnedRight = false;
		this.used = false;
	}

	public int getCardTypeCode()
	{
		return cardTypeCode;
	}

	public int getPrimaryValue()
	{
		return primaryValue;
	}

	public int getSecondaryValue()
	{
		return secondaryValue;
	}

	public String getFirstImage()
	{
		return firstImage;
	}

	public String getFourthImage()
	{
		return fourthImage;
	}

	public boolean isTurnedLeft()
	{
		return turnedLeft;
	}

	public boolean isTurnedRight()
	{
		return turnedRight;
	}

	public boolean isUsed()
	{
		return used;
	}

	public String getSecondImage()
	{
		return secondImage;
	}

	public String getThirdImage()
	{
		return thirdImage;
	}

	public void resetLeftTurn()
	{
		turnedLeft = false;
	}

	public void resetRightTurn()
	{
		turnedRight = false;
	}

	public void setLeftTurnActive()
	{
		turnedLeft = true;
	}

	public void setRightTurnActive()
	{
		turnedRight = true;
	}

	public boolean isCardUsed()
	{
		return used;
	}

	public void useCard()
	{
		this.used = true;
	}

	public int getValue() //TODO check values returning
	{
		if (turnedLeft && turnedRight)
			return -2;
		else if (turnedLeft)
			return secondaryValue;
		else if (turnedRight)
			return -1;
		else
			return primaryValue;
	}
	
	public String getImage()
	{
		if (turnedLeft && turnedRight)
			return fourthImage;
		else if (turnedLeft)
			return secondImage;
		else if (turnedRight)
			return thirdImage;
		else
			return firstImage;
	}

	public void changeValue(boolean first)
	{
		if (first)
			turnedLeft = !turnedLeft;
		else
			turnedRight = !turnedRight;
	}

}
