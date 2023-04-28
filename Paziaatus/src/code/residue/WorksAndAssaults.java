package residue;

import beings.Player;

public class WorksAndAssaults
{

	public void assault(Player playe)
	{

	}

	public void hunter(Player player)
	{

	}

	public int archeologistTechnician(Player player, boolean archeologist, Database database)
	{
		int situationCode = Tools.getRandomNumber(10);

		switch (situationCode)
		{
			case 0:
			case 1:
			case 2:
			{
				calculateFight(player);
				if(archeologist)
					situationCode = 11;
				break;
			}
			case 3:
			{
				if(player.isFreeSlotInInventory())
				{
					player.addItemIntoInventory(database.createArtifactOrImplant(archeologist));
					if(archeologist && (situationCode == 0 || situationCode == 1 || situationCode == 2))
						situationCode = 12;
				}
				else {
					situationCode = 4;
				}
			}
		}

		return situationCode;
	}

	private void calculateFight(Player player)
	{
		int planetMultiplier = player.planetMultiplier();

		boolean fight = true;
		int healthPointsOfEnemy = 70 + (planetMultiplier * 10);
		int playersStrenght = player.getStrenght();
		playersStrenght = playersStrenght < 5 ? 5 : playersStrenght;
		int strengthOfEnemy = (Tools.getRandomNumber(10) + 5 + (planetMultiplier * 5)) - (player.getDefense()/3);
		strengthOfEnemy = strengthOfEnemy < 9 ? 9 : strengthOfEnemy;
		int playersEnergy = player.getEnergy();

		if (playersEnergy < 10)
			strengthOfEnemy = strengthOfEnemy + 3;
		else if (playersEnergy < 15)
			strengthOfEnemy = strengthOfEnemy + 2;
		else if (playersEnergy < 20)
			strengthOfEnemy = strengthOfEnemy + 1;

		while(fight)
		{
			healthPointsOfEnemy = healthPointsOfEnemy - playersStrenght;
			player.setHealth(player.getHealth() - strengthOfEnemy);

			if (player.getHealth() < 1 || healthPointsOfEnemy < 1)
				return;
		}
	}
}
