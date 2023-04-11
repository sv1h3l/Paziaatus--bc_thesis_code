package residue;

public class Item
{
	private String	itemType;
	private int		strenghtDefenseSkillHealthSpeedFunction;
	private int		appearanceFulnessFuel;
	private int		luckHydration;

	private int	price;
	private int	weight;
	private int	wearAndTear;
	private int	maxRepairPossibleUse;

	private String	img;
	private String	active;

	public Item(String itemType, int strenghtDefenseSkillHealthSpeedFunction, int appearanceFulnessFuel, int luckHydration, int price, int weight,
			int wearAndTear, int maxRepairPossibleUse, String active, String img)
	{
		this.itemType = itemType;
		this.strenghtDefenseSkillHealthSpeedFunction = strenghtDefenseSkillHealthSpeedFunction;
		this.appearanceFulnessFuel = appearanceFulnessFuel;
		this.luckHydration = luckHydration;

		this.price = price;
		this.weight = weight;
		this.wearAndTear = wearAndTear;
		this.maxRepairPossibleUse = maxRepairPossibleUse;

		this.active = active;
		this.img = img;
	}

	public String[] getContentOfFieldsLikeString()
	{
		String[] values = new String[7];

		values[0] = String.valueOf(strenghtDefenseSkillHealthSpeedFunction);
		values[1] = appearanceFulnessFuel != Constants.NO_VALUE ? String.valueOf(appearanceFulnessFuel) : "";
		values[2] = luckHydration != Constants.NO_VALUE ? String.valueOf(luckHydration) : "";
		values[3] = String.valueOf(price);
		values[4] = String.valueOf(weight);
		values[5] = wearAndTear != Constants.NO_VALUE ? String.valueOf(wearAndTear) : "";
		values[6] = maxRepairPossibleUse != Constants.NO_VALUE ? String.valueOf(maxRepairPossibleUse) : "";

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

	public int getStrenghtDefenseSkillHealthSpeedFunction()
	{
		return strenghtDefenseSkillHealthSpeedFunction;
	}

	public void setStrenghtDefenseSkillHealthSpeedFunction(int strenghtDefenseSkillHealthSpeedFunction)
	{
		this.strenghtDefenseSkillHealthSpeedFunction = strenghtDefenseSkillHealthSpeedFunction;
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

	public int getAppearanceFulnessFuel()
	{
		return appearanceFulnessFuel;
	}

	public void setAppearanceFulnessFuel(int appearanceFulnessFuel)
	{
		this.appearanceFulnessFuel = appearanceFulnessFuel;
	}

	public int getLuckHydration()
	{
		return luckHydration;
	}

	public void setLuckHydration(int luckHydration)
	{
		this.luckHydration = luckHydration;
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
