package residue;

import main.GameModel;

public class TimeChecker implements Runnable
{
	private volatile boolean	isRunning	= true;
	private GameModel		gameProperties;

	public TimeChecker(GameModel gameProperties)
	{
		this.gameProperties = gameProperties;
	}

	@Override public void run()
	{
		while (isRunning)
		{

			try
			{
				Thread.sleep(20000);
			} catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
			if (!isRunning)
				break;
			gameProperties.updateNecessitiesOfLife(false);
		}
		this.stop(); // TODO zjistit, jestli to pomohlo
	}

	public void stop()
	{
		isRunning = false;
	}
}