package residue;

import main.GameProperties;

public class TimeChecker implements Runnable
{
	private volatile boolean	isRunning	= true;
	private GameProperties		gameProperties;

	public TimeChecker(GameProperties gameProperties)
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
			gameProperties.updateNecessitiesOfLife(false);
		}
	}

	public void stop()
	{
		isRunning = false;
	}
}