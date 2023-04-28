package residue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Properties;

import com.google.gson.Gson;

import beings.Player;

public class GameSaverLoader
{
	public String saveGameState(Player player, int gameState)
	{
		Gson gson = new Gson();
		String jsonString = gson.toJson(player);

		String path = this.getClass().getResource("/gamestate" + gameState + ".json").toString();
		File file = new File(path.substring("file:".length()));

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
		{
			writer.write(jsonString);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		LocalDateTime localDateTime = LocalDateTime.now();
		String info = String.valueOf(localDateTime.getHour()) + " : " + String.valueOf(localDateTime.getMinute()) + " - " + String.valueOf(
				localDateTime.getDayOfMonth() + ". " + String.valueOf(localDateTime.getMonthValue()) + ". " + String.valueOf(localDateTime.getYear()));

		return info;
	}

	public Player loadGameState(int gameState)
	{
		/*-
		 * String path = this.getClass().getResource("/gamestate" + gameState + ".json").toString();
		File file = new File(path.substring("file:".length()));
		 */
		
		String path = this.getClass().getResource("/gamestate" + gameState + ".json").toString();
		File file = new File(path.substring("file:".length()));

		String jsonString = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			String line;
			while ((line = reader.readLine()) != null)
			{
				jsonString += line;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		Gson gson = new Gson();
		return gson.fromJson(jsonString, Player.class);
	}

	public void saveGameProperties(int saveCode, String content)
	{
		Properties properties = new Properties();
		File file = null;
		try
		{
			file = new File(this.getClass().getResource("/game.properties").toURI());
			FileInputStream in = new FileInputStream(file);
			properties.load(in);

			in.close();
		} catch (URISyntaxException | IOException e)
		{
			e.printStackTrace();
		}

		String resolution = properties.getProperty("resolution");
		String info1 = properties.getProperty("info1");
		String info2 = properties.getProperty("info2");
		String info3 = properties.getProperty("info3");

		switch (saveCode)
		{
			case 0:
			{
				resolution = content;
				break;
			}
			case 1:
			{
				info1 = content;
				break;
			}
			case 2:
			{
				info2 = content;
				break;
			}
			case 3:
				info3 = content;
		}

		try
		{
			FileOutputStream out = new FileOutputStream(file);
			properties.setProperty("resolution", resolution);
			properties.setProperty("info1", info1);
			properties.setProperty("info2", info2);
			properties.setProperty("info3", info3);
			properties.store(out, null);
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String[] loadGameProperties()
	{
		Properties properties = new Properties();
		try (InputStream is = getClass().getClassLoader().getResourceAsStream("game.properties")) {
			properties.load(is);
		} catch (IOException e) {
		    // handle the exception
		}
		
		/*Properties properties = new Properties();
		String path = this.getClass().getResource("/game.properties").toString();
		File file = new File(path.substring("file:".length()));
		try
		{
			FileInputStream in = new FileInputStream(file);
			properties.load(in);
			in.close();
		} catch ( IOException e)
		{
			e.printStackTrace();
		}*/
		String info1 = properties.getProperty("info1").equals("Nova hra") ? "Nová hra" : properties.getProperty("info1");
		String info2 = properties.getProperty("info2").equals("Nova hra") ? "Nová hra" : properties.getProperty("info2");
		String info3 = properties.getProperty("info3").equals("Nova hra") ? "Nová hra" : properties.getProperty("info3");

		String[] values = { properties.getProperty("resolution"), info1, info2, info3 };
		return values;
	}

	public void removeGameState(int saveCode)
	{
		saveGameProperties(saveCode, "Nova hra");

		try
		{
			File file = new File(this.getClass().getResource("/gamestate" + saveCode + ".json").toURI());
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException | URISyntaxException e)
		{
		}
	}

}
