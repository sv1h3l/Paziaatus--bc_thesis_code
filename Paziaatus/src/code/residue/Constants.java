package residue;

public final class Constants
{
	private Constants()
	{
	}

	/*- When changing the values of these constants, there is no need to change the logic of the game. */
	public static final String	SET_PLAYER		= "Vyhrál jsi set.";
	public static final String	SET_OPPONENT	= "Protihráč vyhrál set.";
	public static final String	GAME_PLAYER		= "Vyhrál jsi karetní partii.";
	public static final String	GAME_OPPONENT	= "Protihráč vyhrál karetní partii.";
	public static final String	DRAW			= "Nastala remíza.";

	/*- When changing the values of these constants, it is necessary to change the logic of the game. */
	public static final String[] RESOLUTIONS = {"1600x900", "1440x810", "1280x720", "1120x630", "960x540", "800x450", "640x360"};
	public static final int[] ARROW_POSITION = {232, 309, 385, 462, 540, 617, 693};
	public static final int NO_VALUE = -420;
	public static final String NO_VALUE_STRING = "-420";
	public static final int PLAYERS_INVENTORY_SIZE = 12;
	public static final int PLAYERS_GEAR_SIZE = 12;
	public static final int PLAYERS_DECK_SIZE = 10;
}
