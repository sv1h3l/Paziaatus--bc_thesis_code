package residue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database
{
	private Connection connection;

	private String specialization;

	public Database(String specialization)
	{
		try
		{
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:./database");

		} catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		this.specialization = specialization;
	}

	public void stop()
	{
		try
		{
			connection.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void createShopItems(Shop[] shops, String actualPlanet)
	{
		for (Shop shop : shops)
		{
			int numberOfShopSlots, planetCode;
			if (actualPlanet.equals("Tarrys"))
			{
				numberOfShopSlots = shop.getCountOfShopSlotsFirstPlanet();
				planetCode = 1;
			} else if (actualPlanet.equals("Narr Sheyda"))
			{
				numberOfShopSlots = shop.getCountOfShopSlotsSecondPlanet();
				planetCode = 2;
			} else
			{
				numberOfShopSlots = shop.getCountOfShopSlotsThirdPlanet();
				planetCode = 3;
			}

			if (shop.getShopType().equals("cards"))
				createCardsShopItems(shop, actualPlanet, numberOfShopSlots, planetCode);
			else
				createSpecificShopItems(shop, actualPlanet, numberOfShopSlots, planetCode);
		}
	}

	private void createCardsShopItems(Shop shop, String actualPlanet, int numberOfShopSlots, int planetCode)
	{
		try
		{
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs;
			int random;
			String[] itemTypesOfShop = shop.getItemTypesOfShop();

			shop.clearShopItems();

			for (int i = 0; i < numberOfShopSlots; i++)
			{
				random = Tools.getRandomNumber(itemTypesOfShop.length);
				rs = stmt.executeQuery("SELECT * FROM " + itemTypesOfShop[random] + " WHERE planet = " + planetCode);
				String itemType = getItemType(itemTypesOfShop[random]);

				rs.last();
				random = Tools.getRandomNumber(rs.getRow()) + 1;
				rs.absolute(random);

				int primaryFeature = rs.getInt("primary_feature");
				int secondaryFeature = rs.getInt("secondary_feature");
				int ternaryFeature = rs.getInt("ternary_feature");
				int weight = 1;
				int wearAndTear = Constants.NO_VALUE;
				int maxRepairPossibleUse = Constants.NO_VALUE;
				int price = 1000 * planetCode;
				String active = rs.getString("active");
				String img = rs.getString("img");
				
				shop.addItemIntoShop(
						new Item(itemType, primaryFeature, secondaryFeature, ternaryFeature, price, weight, wearAndTear, maxRepairPossibleUse, active, img));
			}
			stmt.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	private void createSpecificShopItems(Shop shop, String actualPlanet, int numberOfShopSlots, int planetCode)
	{
		try
		{
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs;
			int random;
			String[] itemTypesOfShop = shop.getItemTypesOfShop();

			shop.clearShopItems();

			for (int i = 0; i < numberOfShopSlots; i++)
			{
				random = Tools.getRandomNumber(itemTypesOfShop.length);
				rs = stmt.executeQuery("SELECT * FROM " + itemTypesOfShop[random] + " WHERE planet = " + planetCode);
				String itemType = getItemType(itemTypesOfShop[random]);

				rs.last();
				random = Tools.getRandomNumber(rs.getRow()) + 1;
				rs.absolute(random);

				int primaryFeature = rs.getInt("primary_feature") + Tools.getRandomNumber(4);
				int secondaryFeature = rs.getInt("secondary_feature") == Constants.NO_VALUE ? Constants.NO_VALUE : rs.getInt("secondary_feature") + Tools.getRandomNumber(4);
				int ternaryFeature = rs.getInt("ternary_feature") == Constants.NO_VALUE ? Constants.NO_VALUE : rs.getInt("ternary_feature") + Tools.getRandomNumber(4);
				int weight = rs.getInt("weight") == 0 ? 0 : rs.getInt("weight") + Tools.getRandomNumber(8);
				int wearAndTear = 100;
				int maxRepairPossibleUse = rs.getInt("maxrepair");
				int price;
				String img = rs.getString("img");

				if (itemType.equals("jídlo"))
					price = (secondaryFeature + (ternaryFeature / 2)) * (40 * planetCode);
				else if (itemType.equals("pití"))
					price = (ternaryFeature + (secondaryFeature / 2)) * (40 * planetCode);
				else if (itemType.equals("léčivo"))
					price = primaryFeature * (50 * planetCode);
				else if (itemType.equals("nástroj"))
					price = (primaryFeature + maxRepairPossibleUse) * 50 * planetCode;
				else if (itemType.equals("droid") || itemType.equals("vznášedlo"))
					price = primaryFeature * 30 * planetCode;
				else
					price = (primaryFeature + (maxRepairPossibleUse == Constants.NO_VALUE ? 0 : maxRepairPossibleUse) + secondaryFeature + ternaryFeature)
							* (100 * planetCode);

				if (price < 0)
					price = price * -1;

				if (itemType.equals("světelný meč") && (Tools.getNumberFromString(img, "weapons/") > 13))
				{
					itemType = "laserová puška";
					if (specialization.equals("lovec odměn"))
						primaryFeature = primaryFeature + 5;
				}

				if (itemType.equals("světelný meč") && specialization.equals("šedý válečník"))
					primaryFeature = primaryFeature + 10;

				shop.addItemIntoShop(
						new Item(itemType, primaryFeature, secondaryFeature, ternaryFeature, price, weight, wearAndTear, maxRepairPossibleUse, "", img));
			}

			stmt.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	private String getItemType(String itemType)
	{
		switch (itemType)
		{
			case "GLOVES":
				return "rukavice";
			case "BELTS":
				return "opasek";
			case "BOOTS":
				return "boty";
			case "HELMETS":
				return "helma";
			case "WEARS":
				return "oděv";
			case "WEAPONS":
				return "světelný meč"; //TODO laserová puška
			case "TOOLS":
				return "nástroj";
			case "SPEEDERS":
				return "vznášedlo";
			case "DROIDS":
				return "droid";
			case "RINGS":
				return "prsten";
			case "NECKLACES":
				return "náhrdelník";
			case "DRINKS":
				return "pití";
			case "FOOD":
				return "jídlo";
			case "MEDICATIONS":
				return "léčivo";
			case "CARDS":
				return "karta";
		}
		return "";
	}

	protected Item createArtifactOrImplant(boolean artifact)
	{
		String active, image;
		try
		{
			ResultSet rs;
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String itemType = artifact ? "artifacts" : "implants";
			rs = stmt.executeQuery("SELECT * FROM " + itemType);

			rs.last();
			int random = Tools.getRandomNumber(rs.getRow());
			rs.absolute(random);

			stmt.close();

			active = rs.getString("active");
			image = rs.getString("img");
			int noValue = Constants.NO_VALUE;
			return new Item((artifact ? "artefakt" : "implantát"), noValue, noValue, noValue, 1000, 12, 30, noValue, active, image);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
