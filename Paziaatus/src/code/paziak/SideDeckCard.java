package paziak;

public class SideDeckCard
{
	private DDDCard	card;	// Item card
	private boolean	inactiveLeftTurn;
	private boolean	inactiveRightTurn;
	private boolean	used;

	SideDeckCard(DDDCard card)
	{
		this.card = card;
		this.inactiveLeftTurn = true;
		this.inactiveRightTurn = true;
		this.used = false;
	}

	public DDDCard getCard()
	{
		return card;
	}

	public boolean hasInactiveLeftTurn()
	{
		return inactiveLeftTurn;
	}

	public boolean hasInactiveRightTurn()
	{
		return inactiveRightTurn;
	}

	public void resetLeftTurn()
	{
		inactiveLeftTurn = true;
	}

	public void resetRightTurn()
	{
		inactiveRightTurn = true;
	}

	public void setLeftTurnActive()
	{
		inactiveLeftTurn = false;
	}

	public void setRightTurnActive()
	{
		inactiveRightTurn = false;
	}

	public boolean isCardUsed()
	{
		return used;
	}

	public void useCard()
	{
		this.used = true;
	}

	public void changeValue(boolean first)
	{
		if (first)
			inactiveLeftTurn = !inactiveLeftTurn;
		else
			inactiveRightTurn = !inactiveRightTurn;
	}
}
