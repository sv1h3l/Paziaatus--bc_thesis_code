package main;

import beings.Player;
import javafx.scene.layout.Pane;

public class GameOptions
{
	private String	resolution;
	private String	newResolution;

	private Pane	paneActive;
	private Pane	paneActual;

	private Player player;
	
	public GameOptions(String resolution, Pane paneActive, Pane paneActual, Player player)
	{
		this.resolution = resolution;
		this.paneActive = paneActive;
		this.paneActual = paneActual;
		this.player = player;
	}

	protected String getResolution()
	{
		return resolution;
	}

	protected void setResolution(String resolution)
	{
		this.resolution = resolution;
	}

	protected String getNewResolution()
	{
		return newResolution;
	}

	protected void setNewResolution(String newResolution)
	{
		this.newResolution = newResolution;
	}

	protected Pane getPaneActive()
	{
		return paneActive;
	}

	protected void setPaneActive(Pane paneActive)
	{
		this.paneActive = paneActive;
	}

	protected Pane getPaneActual()
	{
		return paneActual;
	}

	protected void setPaneActual(Pane paneActual)
	{
		this.paneActual = paneActual;
	}
}
