package pazaak;

public class RealCard
{
    private Card card;
    private boolean inactiveLeftTurn;
    private boolean inactiveRightTurn;
    private boolean used;

    RealCard(Card card)
    {
        this.card = card;
        this.inactiveLeftTurn = true;
        this.inactiveRightTurn = true;
        this.used = false;
    }

    public Card getCard()
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

    public void setLeftTurnActive()
    {
        inactiveLeftTurn = false;
    }

    public void setRightTurnActive()
    {
        inactiveRightTurn = false;
    }

    public boolean isUsed()
    {
        return used;
    }

    public void used()
    {
        this.used = true;
    }

    public void makeLeftTurn()
    {
        inactiveLeftTurn = !inactiveLeftTurn;
    }

    public void makeRightTurn()
    {
        inactiveRightTurn = !inactiveRightTurn;
    }
}
