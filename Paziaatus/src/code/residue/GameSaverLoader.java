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
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Properties;

import com.google.gson.Gson;

import beings.Player;
import main.GameApplication;

public class GameSaverLoader
{
	public String saveGameState(Player player, int gameState)
	{
		Gson gson = new Gson();
		String jsonString = gson.toJson(player);

		String jarPath;
		String jarDirPath = "";
		try
		{
			jarPath = GameApplication.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			jarDirPath = new File(jarPath).getParent();
		} catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		
		File file =  new File(jarDirPath+"/gamestate" + gameState + ".json");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
		{
			writer.write(jsonString);
		} catch (IOException e)
		{
			e.printStackTrace();
		}	
		
		/*-String path = this.getClass().getResource("/gamestate" + gameState + ".json").toString();
		File file = new File(path.substring("file:".length()));

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
		{
			writer.write(jsonString);
		} catch (IOException e)
		{
			e.printStackTrace();
		}*/

		LocalDateTime localDateTime = LocalDateTime.now();
		String info = String.valueOf(localDateTime.getHour()) + " : " + String.valueOf(localDateTime.getMinute()) + " - " + String.valueOf(
				localDateTime.getDayOfMonth() + ". " + String.valueOf(localDateTime.getMonthValue()) + ". " + String.valueOf(localDateTime.getYear()));

		return info;
	}

	public Player loadGameState(int gameState)
	{
/*-		String path = this.getClass().getResource("/gamestate" + gameState + ".json").toString();
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
		}*/
		
		String jarPath;
		String jarDirPath = "";
		String jsonString = "";
		
		try
		{
			jarPath = GameApplication.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			jarDirPath = new File(jarPath).getParent();
		} catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		
		File file =  new File(jarDirPath+"/gamestate" + gameState + ".json");
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
		/*-Properties properties = new Properties();
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
		}*/
		
		Properties properties = new Properties();

		String jarPath;
		String jarDirPath = "";
		try
		{
			jarPath = GameApplication.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			jarDirPath = new File(jarPath).getParent();
		} catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		
		File file =  new File(jarDirPath+"/game.properties");
		try (InputStream inputStream = new FileInputStream(file) /* classLoader.getResourceAsStream("game.properties")*/)
		{
			properties.load(inputStream);
		} catch (IOException e)
		{
			e.printStackTrace();
		}	

		String resolution = properties.getProperty("gameResolution");
		String info1 = properties.getProperty("gameStateInfo1");
		String info2 = properties.getProperty("gameStateInfo2");
		String info3 = properties.getProperty("gameStateInfo3");

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
			properties.setProperty("gameResolution", resolution);
			properties.setProperty("gameStateInfo1", info1);
			properties.setProperty("gameStateInfo2", info2);
			properties.setProperty("gameStateInfo3", info3);
			properties.store(out, null);
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String[] loadGameProperties()
	{
		/*-Properties properties = new Properties();
		try (InputStream is = getClass().getClassLoader().getResourceAsStream("game.properties"))
		{
			properties.load(is);
		} catch (IOException e)
		{
			// handle the exception
		}*/
	
		
		Properties properties = new Properties();

		String jarPath;
		String jarDirPath = "";
		try
		{
			jarPath = GameApplication.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			jarDirPath = new File(jarPath).getParent();
		} catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		
		File file =  new File(jarDirPath+"/game.properties");
		try (InputStream inputStream = new FileInputStream(file) /* classLoader.getResourceAsStream("game.properties")*/)
		{
			properties.load(inputStream);
		} catch (IOException e)
		{
			e.printStackTrace();
		}	
		
		String info1 = properties.getProperty("gameStateInfo1").equals("Nova hra") ? "Nová hra" : properties.getProperty("gameStateInfo1");
		String info2 = properties.getProperty("gameStateInfo2").equals("Nova hra") ? "Nová hra" : properties.getProperty("gameStateInfo2");
		String info3 = properties.getProperty("gameStateInfo3").equals("Nova hra") ? "Nová hra" : properties.getProperty("gameStateInfo3");

		String[] values = { properties.getProperty("gameResolution"), info1, info2, info3 };
		return values;
	}

	public void removeGameState(int saveCode)
	{
		saveGameProperties(saveCode, "Nova hra");

		String jarPath;
		String jarDirPath = "";
		try
		{
			jarPath = GameApplication.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			jarDirPath = new File(jarPath).getParent();
		} catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			File file = new File(jarDirPath+"/gamestate" + saveCode + ".json");
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}	
		
		/*-try
		{
			File file = new File(this.getClass().getResource("/gamestate" + saveCode + ".json").toURI());
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException | URISyntaxException e)
		{
		}*/
	}

	public static boolean isGameAlreadyRunning()
	{
		String propertyValue = "false";
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		
		try (InputStream inputStream = classLoader.getResourceAsStream("game.properties"))
		{
			Properties properties = new Properties();
			properties.load(inputStream);
			propertyValue = properties.getProperty("gameIsAlreadyRunning");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		if (propertyValue.equals("true"))
			return true;
		else
			return false;
	}

	public static void gameIsAlreadyRunning(boolean running)
	{
		//ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Properties properties = new Properties();

		String jarPath;
		String jarDirPath = "";
		try
		{
			jarPath = GameApplication.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			jarDirPath = new File(jarPath).getParent();
		} catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		
		File file =  new File(jarDirPath+"/game.properties");
		try (InputStream inputStream = new FileInputStream(file) /* classLoader.getResourceAsStream("game.properties")*/)
		{
			properties.load(inputStream);
		} catch (IOException e)
		{
			e.printStackTrace();
		}		

		String runningString = running ? "true" : "false";
		String resolution = properties.getProperty("gameResolution");
		String info1 = properties.getProperty("gameStateInfo1");
		String info2 = properties.getProperty("gameStateInfo2");
		String info3 = properties.getProperty("gameStateInfo3");
		
		//properties.store(outputStream, "Updated properties");
		
		try (OutputStream outputStream = new FileOutputStream(file))
		{
			properties = new Properties();
			properties.setProperty("gameIsAlreadyRunning", runningString);
			properties.setProperty("gameResolution", resolution);
			properties.setProperty("gameStateInfo1", info1);
			properties.setProperty("gameStateInfo2", info2);
			properties.setProperty("gameStateInfo3", info3);
			properties.store(outputStream, null);
		} catch (IOException e)
		{
			e.printStackTrace();
		}	
	}

}
