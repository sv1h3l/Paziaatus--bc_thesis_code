package residue;

public class Shop
{
	private int	countOfShopSlotsFirstPlanet;
	private int	countOfShopSlotsSecondPlanet;
	private int	countOfShopSlotsThirdPlanet;

	private Item[] shopItems;

	public Shop(int countOfShopSlotsFirstPlanet, int countOfShopSlotsSecondPlanet, int countOfShopSlotsThirdPlanet)
	{
		this.countOfShopSlotsFirstPlanet = countOfShopSlotsFirstPlanet;
		this.countOfShopSlotsSecondPlanet = countOfShopSlotsSecondPlanet;
		this.countOfShopSlotsThirdPlanet = countOfShopSlotsThirdPlanet;

		shopItems = new Item[10];
	}

	protected int getCountOfShopSlotsFirstPlanet()
	{
		return countOfShopSlotsFirstPlanet;
	}

	protected void setCountOfShopSlotsFirstPlanet(int countOfShopSlotsFirstPlanet)
	{
		this.countOfShopSlotsFirstPlanet = countOfShopSlotsFirstPlanet;
	}

	protected int getCountOfShopSlotsSecondPlanet()
	{
		return countOfShopSlotsSecondPlanet;
	}

	protected void setCountOfShopSlotsSecondPlanet(int countOfShopSlotsSecondPlanet)
	{
		this.countOfShopSlotsSecondPlanet = countOfShopSlotsSecondPlanet;
	}

	protected int getCountOfShopSlotsThirdPlanet()
	{
		return countOfShopSlotsThirdPlanet;
	}

	protected void setCountOfShopSlotsThirdPlanet(int countOfShopSlotsThirdPlanet)
	{
		this.countOfShopSlotsThirdPlanet = countOfShopSlotsThirdPlanet;
	}

	public Item getNthShopItem(int nthItem)
	{
		return shopItems[nthItem];
	}

	protected void setShopItems(Item[] shopItems)
	{
		this.shopItems = shopItems;
	}
	
	public Item[] getShopItems()
	{
		return shopItems;
	}

	public void addItemIntoShop(Item item)
	{
		
		for (int i = 0; i < countOfShopSlotsThirdPlanet; i++)
		{
			if (shopItems[i] == null)
			{
				shopItems[i] = item;
				return;
			}
		}
	}
}
