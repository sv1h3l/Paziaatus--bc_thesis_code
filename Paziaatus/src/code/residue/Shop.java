package residue;

public class Shop
{
	private int	countOfShopSlotsFirstPlanet;
	private int	countOfShopSlotsSecondPlanet;
	private int	countOfShopSlotsThirdPlanet;

	private String[] itemTypesOfShop;

	private Item[] shopItems;

	public Shop(String shopType, int countOfShopSlotsFirstPlanet, int countOfShopSlotsSecondPlanet, int countOfShopSlotsThirdPlanet)
	{
		this.countOfShopSlotsFirstPlanet = countOfShopSlotsFirstPlanet;
		this.countOfShopSlotsSecondPlanet = countOfShopSlotsSecondPlanet;
		this.countOfShopSlotsThirdPlanet = countOfShopSlotsThirdPlanet;

		shopItems = new Item[10];
		setItemTypesOfShop(shopType);
	}

	private void setItemTypesOfShop(String shopType)
	{
		switch (shopType)
		{
			case "armor":
			{
				itemTypesOfShop = new String[] { "GLOVES", "BELTS", "BOOTS", "HELMETS", "WEARS" };
				break;
			}
			case "weapons":
			{
				itemTypesOfShop = new String[] { "WEAPONS" };
				break;
			}
			case "tech":
			{
				itemTypesOfShop = new String[] { "TOOLS", "SPEEDERS", "DROIDS" };
				break;
			}
			case "jewelry":
			{
				itemTypesOfShop = new String[] { "RINGS", "NECKLACES" };
				break;
			}
			case "grocery":
			{
				itemTypesOfShop = new String[] { "DRINKS", "FOOD" };
				break;
			}
			case "medications":
			{
				itemTypesOfShop = new String[] { "MEDICATIONS" };
				break;
			}
			case "cards":
				itemTypesOfShop = new String[] { "CARDS" };
		}
	}

	protected String[] getItemTypesOfShop()
	{
		return itemTypesOfShop;
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

	public void addItemIntoShop(Item item/* , String Planet */)
	{
		for (int i = 0; i < countOfShopSlotsThirdPlanet; i++)

			if (shopItems[i] == null)
			{
				shopItems[i] = item;
				return;
			}
	}
}
