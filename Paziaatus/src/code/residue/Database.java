package residue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database
{
	private Connection connection;

	public Database()
	{
		try
		{
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:./database");

		} catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
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
			createSpecificShopItems(shop, actualPlanet);
	}

	private void createSpecificShopItems(Shop shop, String actualPlanet)
	{
		int numberOfShopSlots, planetCode;
		if (actualPlanet.equals("Tarys"))
		{
			numberOfShopSlots = shop.getCountOfShopSlotsFirstPlanet();
		planetCode = 1;
		}
		else if (actualPlanet.equals("Narr Sheyda"))
		{
			numberOfShopSlots = shop.getCountOfShopSlotsSecondPlanet();
			planetCode = 2;
		}
		else
		{
			numberOfShopSlots = shop.getCountOfShopSlotsThirdPlanet();
			planetCode = 3;
		}

		if (shop.getItemTypesOfShop()[0] == "CARDS") // TODO doplnit karty do
			return;

		try
		{
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs;
			int random;
			String[] itemTypesOfShop = shop.getItemTypesOfShop();

			for (int i = 0; i < numberOfShopSlots; i++)
			{
				random = Tools.getRandomNumber(itemTypesOfShop.length);
				rs = stmt.executeQuery("SELECT * FROM " + itemTypesOfShop[random] + " WHERE planet = " + planetCode);
				String itemType = getItemType(itemTypesOfShop[random]);

				rs.last();
				random = Tools.getRandomNumber(rs.getRow());
				rs.absolute(random+1);

				int primaryFeature = rs.getInt("primary_feature");
				int secondaryFeature = rs.getInt("secondary_feature");
				int ternaryFeature = rs.getInt("ternary_feature");
				int weight = rs.getInt("weight");
				int wearAndTear = 100;
				int maxRepairPossibleUse = 3;
				int price = primaryFeature+secondaryFeature+ternaryFeature+maxRepairPossibleUse*200;
				String img = rs.getString("img");

				shop.addItemIntoShop(new Item(itemType, primaryFeature, secondaryFeature, ternaryFeature, price, weight, wearAndTear, maxRepairPossibleUse, "", img));
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
				return "laserový meč"; //TODO laserová puška
			case "TOOLS":
				return "nástroj";
			case "SPEEDERS":
				return "vznášedlo";
			case "DROIDS":
				return "droyd";
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
