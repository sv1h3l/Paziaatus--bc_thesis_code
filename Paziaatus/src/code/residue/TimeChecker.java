package residue;

import main.GameModel;

public class TimeChecker implements Runnable
{
	private volatile boolean	isRunning	= true;
	private GameModel		gameModel;
	private String gameMode;
	
	public TimeChecker(GameModel gameModel, String gameMode)
	{
		this.gameModel = gameModel;
		this.gameMode = gameMode;
	}

	@Override public void run()
	{
		int delay = gameMode.equals("rychlý") ? 45000 : 60000;
		
		while (isRunning)
		{
			try
			{
				Thread.sleep(delay);
			} catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
			if (!isRunning)
				break;
			gameModel.updateNecessitiesOfLife(false);
		}
		this.stop();
	}

	public void stop()
	{
		isRunning = false;
	}
}