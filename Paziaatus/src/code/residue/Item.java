package residue;

public class Item
{
	private String	itemType;
	private int		primaryFeature;		// strenghtDefenseSkillHealthSpeedFunction
	private int		secondaryFeature;	// appearanceFulnessFuel
	private int		ternaryFeature;		// luckHydration

	private int	price;
	private int	weight;
	private int	wearAndTear;
	private int	maxRepairPossibleUse;

	private String	img;
	private String	active;

	public Item(String itemType, int primaryFeature, int secondaryFeature, int ternaryFeature, int price, int weight, int wearAndTear,
			int maxRepairPossibleUse, String active, String img)
	{
		this.itemType = itemType;
		this.primaryFeature = primaryFeature;
		this.secondaryFeature = secondaryFeature;
		this.ternaryFeature = ternaryFeature;

		this.price = price;
		this.weight = weight;
		this.wearAndTear = wearAndTear;
		this.maxRepairPossibleUse = maxRepairPossibleUse;

		this.active = active;
		this.img = img;
	}

	public String[] getContentOfFieldsLikeString()
	{
		String[] values = new String[8];

		if(itemType.equals("karta"))
		{
			values[0] = "";
			values[1] = "";
			values[2] = "";
			values[3] = String.valueOf(price);
			values[4] = String.valueOf(weight);
			values[5] = "";
			values[6] = "";
			values[7] = String.valueOf(active);
		}
		else {
		values[0] = String.valueOf(primaryFeature);
		values[1] = secondaryFeature != Constants.NO_VALUE ? String.valueOf(secondaryFeature) : "";
		values[2] = ternaryFeature != Constants.NO_VALUE ? String.valueOf(ternaryFeature) : "";
		values[3] = String.valueOf(price);
		values[4] = String.valueOf(weight);
		values[5] = wearAndTear != Constants.NO_VALUE ? String.valueOf(wearAndTear) : "";
		values[6] = maxRepairPossibleUse != Constants.NO_VALUE ? String.valueOf(maxRepairPossibleUse) : "";
		values[7] = "";
		}

		return values;
	}

	public String getItemType()
	{
		return itemType;
	}

	public void setItemType(String itemType)
	{
		this.itemType = itemType;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public int getPrimaryFeature()
	{
		return primaryFeature;
	}

	public void setPrimaryFeature(int primaryFeature)
	{
		this.primaryFeature = primaryFeature;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public int getWearAndTear()
	{
		return wearAndTear;
	}

	public void setWearAndTear(int wearAndTear)
	{
		this.wearAndTear = wearAndTear;
	}

	public int getMaxRepairPossibleUse()
	{
		return maxRepairPossibleUse;
	}

	public void setMaxRepairPossibleUse(int maxRepairPossibleUse)
	{
		this.maxRepairPossibleUse = maxRepairPossibleUse;
	}

	public int getSecondaryFeature()
	{
		return secondaryFeature;
	}

	public void setSecondaryFeature(int secondaryFeature)
	{
		this.secondaryFeature = secondaryFeature;
	}

	public int getTernaryFeature()
	{
		return ternaryFeature;
	}

	public void setTernaryFeature(int ternaryFeature)
	{
		this.ternaryFeature = ternaryFeature;
	}

	public String getImg()
	{
		return img;
	}

	public void setImg(String img)
	{
		this.img = img;
	}

	public String getActive()
	{
		return active;
	}

	public void setActive(String active)
	{
		this.active = active;
	}

}
