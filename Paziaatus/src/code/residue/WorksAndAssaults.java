package residue;

import beings.Player;

public class WorksAndAssaults
{
	public int hunter(Player player)
	{
		calculateFight(player);
		TearGear(player.getGear(), true);
		return 10;
	}

	public int archeologistTechnician(Player player, boolean archeologist, Database database)
	{
		int situationCode = Tools.getRandomNumber(10);
		boolean fight = false;

		switch (situationCode)
		{
			case 0:
			case 1:
			case 2:
			{
				calculateFight(player);
				if (archeologist)
					situationCode = 11;
				fight = true;
				break;
			}
			case 3:
			{
				if (player.isFreeSlotInInventory())
				{
					player.addItemIntoInventory(database.createArtifactOrImplant(archeologist));
					if (archeologist && (situationCode == 0 || situationCode == 1 || situationCode == 2))
						situationCode = 12;
				} else
				{
					situationCode = 4;
				}
			}
		}

		TearGear(player.getGear(), fight);

		return situationCode;
	}

	public void TearGear(Item[] gear, boolean fight)
	{
		for (Item item : gear)
		{
			if(item != null)
			{
				int newWearAndTear = item.getWearAndTear() - Tools.getRandomNumber(10) * (fight ? 2 : 1);
				if(newWearAndTear < 1)
					item = null;
				else 
					item.setWearAndTear(newWearAndTear);
			}
		}
	}

	public void calculateFight(Player player)
	{
		int planetMultiplier = player.planetMultiplier();

		boolean fight = true;
		int healthPointsOfEnemy = 70 + (planetMultiplier * 10);
		int playersStrenght = player.getStrenght();
		playersStrenght = playersStrenght < 5 ? 5 : playersStrenght;
		int strengthOfEnemy = (Tools.getRandomNumber(10) + 5 + (planetMultiplier * 5)) - (player.getDefense() / 6);
		strengthOfEnemy = strengthOfEnemy < 9 ? 9 : strengthOfEnemy;
		int playersEnergy = player.getEnergy();

		if (playersEnergy < 10)
			strengthOfEnemy = strengthOfEnemy + 3;
		else if (playersEnergy < 15)
			strengthOfEnemy = strengthOfEnemy + 2;
		else if (playersEnergy < 20)
			strengthOfEnemy = strengthOfEnemy + 1;

		while (fight)
		{
			healthPointsOfEnemy = healthPointsOfEnemy - playersStrenght;
			player.setHealth(player.getHealth() - strengthOfEnemy);

			if (player.getHealth() < 1 || healthPointsOfEnemy < 1)
				return;
		}
	}

	public int hunterDroid(Item droid)
	{
		return calculateDroidsFight(droid);
	}
	
	private int calculateDroidsFight(Item droid)
	{
		int newWearAndTear = droid.getWearAndTear() - 10 - Tools.getRandomNumber(20);
		
		
		if(newWearAndTear < 0)
		{
			droid.setWearAndTear(0);
			return 0;
		}
		else
		{
			droid.setWearAndTear(newWearAndTear);
			return 1;
		}
	}
	
	public int archeologistTechnicianDroid(Item droid)
	{
		int situationCode = Tools.getRandomNumber(10);
		switch (situationCode)
		{
			case 0:
			case 1:
			case 2:
	
				return calculateDroidsFight(droid);
		}

		return 1;
	}
}
