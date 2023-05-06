package main;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import beings.Player;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.util.Duration;
import residue.Constants;
import residue.Item;
import residue.Shop;
import residue.Tools;

public class GameView
{
	private GameController gameController;

	private Timeline	loadingBarWorkTimeline;
	private Timeline	loadingBarSleepAndTravelTimeline;
	private Timeline	loadingIndicatorTimeline;

	private ImageView clickedImage;

	private Image			navigationActualImageKeeper;
	protected List<Node>	paneActualNodesKeeper;

	private String dialogText;

	protected int sleepOrTravelCode;

	protected GameView(GameController gameController)
	{
		this.gameController = gameController;
		paneActualNodesKeeper = new ArrayList<>();
	}
	
	public void initialize()
	{
		characterNodesVisibler(true, true);
		gameController.actual.setImage(getImg("icons/navigation/navigation_sleep", false));

		gameController.trashDisablerAndEnabler();
		
		paneActualNodesKeeper.add(gameController.middle);
		paneActualNodesKeeper.add(gameController.paneSleep);
	}

	

	protected void setSleepAndTravelTimeline(int sleepTravelCode, String gameMode)
	{
		loadingBarSleepAndTravelTimeline = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(gameController.loadingBarSleepAndTravel.progressProperty(), 0)),
				new KeyFrame(Duration.seconds(gameMode.equals("rychlý") ? 4 : 8), new KeyValue(gameController.loadingBarSleepAndTravel.progressProperty(), 1)));

		switch (sleepTravelCode)
		{
			case 1:
			{
				dialogText = "Při příletu na Narr Sheyda si cestující odchytl gang Seroko, který po všech vymáhá kredity, jelikož přistáli na přistávací ploše v sektoru ovládaném tímto gangem. Protože nemáš dostatečné množství kreditů na zaplacení, jsi zbit a okraden o většinu výbavy. Nezaplacením sis znepřátelil gang Seroko a tak je tvá činnost na této planetě občas narušena přepadením. Jediným východiskem, jak zabránit neustálým přepadením, je odcestovat na planetu Kerusant.";
				break;
			}
			case 2:
			{
				dialogText = "Odletěl jsi na planetu Kerusant. Nyní už jsi daleko od působení gangu Seroko a tak nebudeš během svých aktivit náhodně přepadáván.";
				break;
			}
			case 3:
			{
				dialogText = "Během spánku jsi byl přepaden a bohužel jsi nepřežil.\n\nPro pokračování ve hře je nutné načíst poslední uloženou pozici, nebo si založit novou hru.";
				break;
			}
			case 4:
				dialogText = "Během spánku jsi byl přepaden, ale naštěstí jsi přežil.\n\n";
		}

		loadingBarSleepAndTravelTimeline.setOnFinished(event -> {
			gameController.paneLoadingSleepAndTravel.setVisible(false);
			if (sleepTravelCode != 0)
				bigGeneralDialog(dialogText);
		});

		gameController.paneLoadingSleepAndTravel.setVisible(true);
		
		loadingBarSleepAndTravelTimeline.play();
	}

	protected void setWorkTimeline(int duration)
	{
		loadingBarWorkTimeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(gameController.loadingBar.progressProperty(), 0)),
				new KeyFrame(Duration.seconds(duration), new KeyValue(gameController.loadingBar.progressProperty(), 1)));

		loadingBarWorkTimeline.setOnFinished(event -> {
			gameController.paneLoading.setVisible(false);
			bigGeneralDialog(dialogText);
		});

		loadingBarWorkTimeline.play();
	}

	protected void setIndicatorTimeline(int duration)
	{
		loadingIndicatorTimeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(gameController.loadingIndicator.progressProperty(), 0)),
				new KeyFrame(Duration.seconds(duration), new KeyValue(gameController.loadingIndicator.progressProperty(), 1)));

		loadingIndicatorTimeline.setOnFinished(event -> {
			gameController.paneMap.setVisible(false);
			gameController.loadingIndicator.setVisible(false);
			gameController.actual.setImage(navigationActualImageKeeper);
			disableTravellingImages(false);
			keeperNodesVisibler(true);
		});

		loadingIndicatorTimeline.play();
	}

	protected void workClick(String workType, int workCode, int credits, int health, int duration)
	{
		List<String> dialogTexts = new ArrayList<>();
		dialogTexts.add("Během převozu laboratorního zařízení tě přepadli zloději.\n\nPřepadení jsi ");
		dialogTexts.add("Kvůli chybě v obvodu na tebe zaútočil laboratorní droid.\n\nÚtok jsi ");
		dialogTexts.add("Na laboratoř zaútočil gang Yako-Ho.\n\nÚtok jsi ");
		dialogTexts.add("Během vykonávání práce tě odhalil lovec odměn a napadl tě.\n\nNapadení jsi "); //3

		dialogTexts.add("Na vykopávkách na tebe zaútočila krvelačná trukata.\n\nÚtok jsi ");
		dialogTexts.add("Na nalezišti krystalů tě napadl jedovatý kynratt.\n\nNapadení jsi ");
		dialogTexts.add("Při těžbě krystalů na tebe zaútočil obrovský wyrm.\n\nÚtok jsi ");

		dialogTexts.add("V laboratoři jsi vytvořil aplikovatelný implantát.\n\n");

		dialogTexts.add("Během těžby v jeskyni jsi objevil artefakt.\n\n");

		dialogTexts.add("Cílem lovu byl člověk, který přeprodával zbraně.\n\nLov jsi "); //9
		dialogTexts.add("Cílem lovu byl člověk, jenž obchodoval s drogami.\n\nLov jsi ");
		dialogTexts.add("Cílem lovu byl člověk, který byl členem místního gangu.\n\nLov jsi ");
		dialogTexts.add("Cílem lovu byl ytoriaan, dlužící obrovské množství kreditů.\n\nLov jsi ");
		dialogTexts.add("Cílem lovu byl sabrak, obchodující s laboratorními implantáty.\n\nLov jsi ");
		dialogTexts.add("Cílem lovu byl sidh, který prodával padělané dokumenty.\n\nLov jsi ");
		dialogTexts.add("Cílem lovu bylo zvíře wyrm, které ohrožovalo místní obyvatele.\n\nLov jsi ");
		dialogTexts.add("Cílem lovu bylo zvíře trukata, které lidem jedlo úrodu.\n\nLov jsi ");
		dialogTexts.add("Cílem lovu bylo zvíře kynratt, které lidem zabíjelo dobytek.\n\nLov jsi ");

		String dialogText;

		switch (workCode)
		{
			case 0:
			case 1:
			case 2:
			{
				dialogText = "Vrátil ses z práce technika.\n\n" + dialogTexts.get(Tools.getRandomNumber(4));
				break;
			}
			case 3:
			{
				dialogText = "Vrátil ses z práce technika.\n\n" + dialogTexts.get(7);
				break;
			}
			case 10:
			{
				dialogText = "Vrátil ses z práce lovce.\n\n" + dialogTexts.get(9 + Tools.getRandomNumber(9));
				break;
			}
			case 11:
			{
				dialogText = "Vrátil ses z práce archeologa.\n\n" + dialogTexts.get(3 + Tools.getRandomNumber(4));
				break;
			}
			case 12:
			{
				dialogText = "Vrátil ses z práce archeologa.\n\n" + dialogTexts.get(8);
				break;
			}
			default:
				dialogText = "Vrátil ses z práce";
				switch (workType)
				{
					case "hunter":
					{
						dialogText = dialogText + " lovce.\n\n";
						break;
					}
					case "archeologist":
					{
						dialogText = dialogText + " archeologa.\n\n";
						break;
					}
					case "technician":
					{
						dialogText = dialogText + " technika.\n\n";
						break;
					}
					default:
						dialogText = dialogText + ".\n\n";
				}
		}

		switch (workCode)
		{
			case 0:
			case 1:
			case 2:
			case 10:
			case 11:
			{
				if (health > 0)
					dialogText = dialogText + "přežil.\n\n";
				else
				{
					dialogText = dialogText + "nepřežil.\n\n";
				}

			}
		}

		if (health > 0)
			dialogText = dialogText + "Vydělal sis\n" + credits + " kreditů";
		else
		{
			quit();
			dialogText = dialogText + "Pro pokračování ve hře je nutné načíst poslední uloženou pozici, nebo si založit novou hru.";
		}

		this.dialogText = dialogText;

		gameController.paneLoading.setVisible(true);

		setWorkTimeline(duration);
	}

	private void disableTravellingImages(boolean disable)
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);

		List<Node> children = gameController.paneMap.getChildren();

		for (int i = 0; i < children.size() - 1; i++)
		{
			children.get(i).setEffect(disable ? colorAdjust : null);
			children.get(i).setDisable(disable);
		}

		gameController.actual.setEffect(disable ? colorAdjust : null);
		gameController.character.setEffect(disable ? colorAdjust : null);
		gameController.map.setEffect(disable ? colorAdjust : null);
		gameController.exit.setEffect(disable ? colorAdjust : null);

		gameController.actual.setDisable(disable);
		gameController.character.setDisable(disable);
		gameController.map.setDisable(disable);
		gameController.exit.setDisable(disable);
	}

	protected void keeperNodesVisibler(boolean visible)
	{
		for (Node node : paneActualNodesKeeper)
			node.setVisible(visible);
	}

	protected void changeFeatures(boolean featuresOfGear, Player player)
	{
		if (player == null)
			gameController.featuresSelect.setImage(getImg(featuresOfGear ? "features/item_info" : "features/item_info", false));
		else
			gameController.featuresSelect.setImage(getImg(featuresOfGear ? "features/char_info" : "features/item_info", false));

		if (featuresOfGear)
		{
			gameController.paneFeaturesGear.setVisible(true);
			gameController.featuresGear1.setText(Integer.toString(player.getStrenght()));
			gameController.featuresGear2.setText(Integer.toString(player.getDefense()));
			gameController.featuresGear3.setText(Integer.toString(player.getSkill()));
			gameController.featuresGear4.setText(Integer.toString(player.getAppearance()));
			gameController.featuresGear5.setText(Integer.toString(player.getLuck()));
			gameController.featuresGear6.setText(Integer.toString(player.getWeight()));
		} else
			gameController.paneFeaturesGear.setVisible(false);

	}

	protected void characterNodesVisibler(boolean visible, boolean featuresOfGear)
	{
		gameController.paneGear.setVisible(visible);
		gameController.paneDeck.setVisible(visible);
		gameController.paneInventory.setVisible(visible);
		gameController.paneFeatures.setVisible(visible);
		if (featuresOfGear)
			gameController.paneFeaturesGear.setVisible(visible);
		gameController.middle.setVisible(visible);
		gameController.featuresSelect.setVisible(visible);
	}

	protected void setImagesOfLoadedItems(Player player)
	{
		Item[] items = player.getInventory();

		for (int i = 0; i < Constants.PLAYERS_INVENTORY_SIZE; i++)
			if (items[i] != null)
			{
				ImageView inventoryImgView = (ImageView) gameController.paneInventory.getChildren().get(i + 1);
				inventoryImgView.setImage(getImg(items[i].getImg(), true));
			}

		items = player.getGear();
		for (int i = 0; i < Constants.PLAYERS_INVENTORY_SIZE; i++)
			if (items[i] != null)
			{
				ImageView gearImgView = (ImageView) gameController.paneGear.getChildren().get(i + 1);
				gearImgView.setImage(getImg(items[i].getImg(), true));
			}
		
		items = player.getDeck();
		for (int i = 0; i < Constants.PLAYERS_DECK_SIZE; i++)
			if (items[i] != null)
			{
				ImageView gearImgView = (ImageView) gameController.paneDeck.getChildren().get(i + 1);
				gearImgView.setImage(getImg(items[i].getImg(), true));
			}
	}

	protected void changeResolution(String resolution)
	{
		String[] parts = resolution.split("x");
		double width = Integer.parseInt(parts[0]), height = Integer.parseInt(parts[1]);
		double scale = width / 1600;

		gameController.paneMain.setScaleX(scale);
		gameController.paneMain.setScaleY(scale);

		gameController.paneMain.setTranslateX(((width - 1600) / 2));
		gameController.paneMain.setTranslateY(((height - 900) / 2));

		gameController.stage.setWidth(width + 16);
		gameController.stage.setHeight(height + 38);

		gameController.paneOptions.setVisible(false);

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		gameController.stage.setX((screenBounds.getWidth() - gameController.stage.getWidth()) / 2);
		gameController.stage.setY((screenBounds.getHeight() - gameController.stage.getHeight()) / 2);
	}

	private Image getImg(String partOfPath, boolean items)
	{
		String itemsOrHud;
		if (items)
			itemsOrHud = "/items/";
		else
			itemsOrHud = "/hud/";

		return new Image(this.getClass().getResourceAsStream("/images/" + itemsOrHud + partOfPath + ".png"));
	}

	protected synchronized void updateStats(Player player)
	{
		gameController.energy.setText(String.valueOf(player.getEnergy()));
		gameController.fullness.setText(String.valueOf(player.getFullness()));
		gameController.hydration.setText(String.valueOf(player.getHydration()));
		gameController.health.setText(String.valueOf(player.getHealth()));
		gameController.credits.setText(String.valueOf(player.getCredits()));
	}

	protected void updateCredits(int credits)
	{

		gameController.credits.setText(String.valueOf(credits));
	}

	protected void setFieldVisible(String nameOfField, boolean visible)
	{
		gameController.getNodeThroughReflection(nameOfField).setVisible(visible);
	}

	protected void setShopsImages(Shop shop, String actualPlanet)
	{
		int i = 1;
		for (; i <= 10; i++)
			setImageOfNthSlot(i, null, 1);

		int ceiling;
		switch (actualPlanet)
		{
			case "Tarrys":
			{
				ceiling = shop.getCountOfShopSlotsFirstPlanet();
				break;
			}
			case "Narr Sheyda":
			{
				ceiling = shop.getCountOfShopSlotsSecondPlanet();
				break;
			}
			default:
				ceiling = shop.getCountOfShopSlotsThirdPlanet();
		}

		i = 1;
		for (Item item : shop.getShopItems())
		{
			if (item != null)
				setImageOfNthSlot(i, item.getImg(), 1);
			if (i == ceiling)
				return;
			i++;
		}
	}

	protected void setImageOfNthSlot(int nthSlot, String imgPath, int paneCode)
	{
		String fieldName;
		if (paneCode == 0)
			fieldName = "invSlot" + nthSlot;
		else if (paneCode == 1)
			fieldName = "shopSlot" + nthSlot;
		else if(paneCode == 2)
			fieldName = "deckSlot" + nthSlot;
		else
			fieldName = "cardSlot" + nthSlot;

		ImageView fieldValue = null;
		try
		{
			Field field = gameController.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			fieldValue = (ImageView) field.get(gameController);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
		{
			e.printStackTrace();
		}

		if (imgPath == null)
			fieldValue.setImage(null);
		else
		{
			fieldValue.setImage(getImg(imgPath, true));
		}
	}

	public void yesOrNoDialog(String dialogText)
	{
		gameController.smallDialogText.setText(dialogText);
		gameController.smallDialogNo.setVisible(true);
		gameController.smallDialogYes.setVisible(true);
		gameController.smallDialogOk.setVisible(false);

		showDialog(true);
	}

	protected void setArrowPosition(int nthResolution)
	{
		gameController.arrow.setLayoutY(Constants.ARROW_POSITION[nthResolution]);
	}

	private void showDialog(boolean smallDialog)
	{
		GaussianBlur blurEffect = new GaussianBlur(15);
		gameController.blurGroup.setEffect(blurEffect);

		if (smallDialog)
			gameController.paneSmallDialog.setVisible(true);
		else
			gameController.paneBigDialog.setVisible(true);
	}

	public void setShopsBannerAndSlots(int numberOfShopSlots, String shopType)
	{
		gameController.shopSlots.setImage(getImg("shops/shop_slots_" + numberOfShopSlots, false));
		gameController.shopBanner.setImage(getImg("banners/banner_" + shopType, false));
	}

	protected void setEffect(ImageView image, boolean glow)
	{
		if (glow)
			image.setEffect(new Glow(0.5));
		else
			image.setEffect(null);
	}

	protected void travellingClicks(String idOfSource, int duration)
	{
		gameController.loadingIndicator.setVisible(true);
		navigationActualImageKeeper = getImg("/icons/navigation/navigation_" + idOfSource, false);
		disableTravellingImages(true);

		setIndicatorTimeline(duration);
	}

	protected void showFeatures(String partOfPath, String itemType, String[] values)
	{
		gameController.featuresTitles.setImage(getImg(partOfPath, false));

		gameController.featuresName.setText(itemType);
		gameController.features1.setText(values[0]);
		gameController.features2.setText(values[1]);
		gameController.features3.setText(values[2]);
		gameController.features4.setText(values[3]);
		gameController.features5.setText(values[4]);
		gameController.features6.setText(values[5]);
		gameController.features7.setText(values[6]);
	}

	protected void takeOffGear(int nthGearSlot, int nthInventorySlot)
	{
		ImageView gearImageView = (ImageView) gameController.paneGear.getChildren().get(nthGearSlot + 1);
		ImageView inventoryImageView = (ImageView) gameController.paneInventory.getChildren().get(nthInventorySlot + 1);
		setImage(gearImageView, inventoryImageView, false);
		gearImageView.setImage(getGearDefaultImage(nthGearSlot));

		gearImageView.setEffect(null);
		gearImageView.setOnMouseEntered(null);
		gearImageView.setOnMouseExited(null);
	}

	private void swapImages(ImageView gearImageView, ImageView inentoryvImageView)
	{
		Image tmpImg = gearImageView.getImage();
		gearImageView.setImage(inentoryvImageView.getImage());
		inentoryvImageView.setImage(tmpImg);
	}

	private void setImage(ImageView gearImageView, ImageView inventoryImageView, boolean setGearImageView)
	{
		if (setGearImageView)
		{
			gearImageView.setImage(inventoryImageView.getImage());
			addListener(gearImageView);
			inventoryImageView.setImage(null);
		} else
			inventoryImageView.setImage(gearImageView.getImage());
	}
	
	protected void setNthDeckImage(int nthDeckSlot, int nthInventorySlot, String imagePath)
	{
		((ImageView) gameController.getNodeThroughReflection("deckSlot" + nthDeckSlot)).setImage(getImg(imagePath, true));
		((ImageView) gameController.getNodeThroughReflection("invSlot" + nthInventorySlot)).setImage(null);
	}


	protected void smallGeneralDialog(String text)
	{
		gameController.smallDialogText.setText(text);
		gameController.smallDialogNo.setVisible(false);
		gameController.smallDialogYes.setVisible(false);
		gameController.smallDialogOk.setVisible(true);

		showDialog(true);
	}

	protected void notEnoughMoney()
	{
		gameController.smallDialogText.setText("Pro uskutečnění této akce nemáš dostatečné mžnožství kreditů.");
		gameController.smallDialogNo.setVisible(false);
		gameController.smallDialogYes.setVisible(false);
		gameController.smallDialogOk.setVisible(true);

		showDialog(true);
	}

	protected void bigGeneralDialog(String text)
	{
		gameController.bigDialogText.setText(text);
		gameController.bigDialogOk.setVisible(true);

		showDialog(false);
	}

	private Image getGearDefaultImage(int nthGearSlot)
	{
		String partOfPath;
		switch (nthGearSlot)
		{
			case 0:
				partOfPath = "implant";
				break;
			case 1:
				partOfPath = "helmet";
				break;
			case 2:
				partOfPath = "necklace";
				break;
			case 3:
				partOfPath = "artifact";
				break;
			case 5:
				partOfPath = "wear";
				break;
			case 6:
				partOfPath = "gloves";
				break;
			case 7:
				partOfPath = "ring";
				break;
			case 8:
				partOfPath = "speeder";
				break;
			case 9:
				partOfPath = "boots";
				break;
			case 10:
				partOfPath = "belt";
				break;
			case 11:
				partOfPath = "droid";
				break;
			default:
				partOfPath = "hand";
		}
		return getImg("icons/gear/gear_" + partOfPath, false);
	}

	protected void gearSlotClick(int nthSlot)
	{
		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.WHITE);

		ImageView gearImageView = (ImageView) gameController.paneGear.getChildren().get(nthSlot + 1);
		gearImageView.setEffect(dropShadow);
	}

	protected void swapEffectsOfImages(ImageView newImage)
	{
		ImageView oldImage = clickedImage;
		if (oldImage != null)
		{
			oldImage.setEffect(null);
			oldImage.setOnMouseEntered(event -> {
				gameController.mouseEnteredImgGlow(event);
			});
			oldImage.setOnMouseExited(event -> {
				gameController.mouseExitedImg(event);
			});
		}

		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.WHITE);

		newImage.setEffect(dropShadow);
		newImage.setOnMouseExited(null);
		newImage.setOnMouseEntered(null);
		clickedImage = newImage;
	}

	public void ShopSlotRightClick(ImageView image, int nthSlot, String string, Player player, int paneCode)
	{
		if (image.getImage() != null)
			image.setImage(null);

		gameController.credits.setText(Integer.toString(player.getCredits()));

		setImageOfNthSlot(nthSlot + 1, string, paneCode);
	}

	public void sellItem(int nthSlot, Player player)
	{
		ImageView invImageView = (ImageView) gameController.paneInventory.getChildren().get(nthSlot + 1);
		invImageView.setImage(null);
		gameController.credits.setText(Integer.toString(player.getCredits()));
	}

	public void changeGear(int nthInvSlot, int nthGearSlot, Item gear)
	{
		ImageView gearImgView = (ImageView) gameController.paneGear.getChildren().get(nthGearSlot + 1);
		ImageView inventoryImageView = (ImageView) gameController.paneInventory.getChildren().get(nthInvSlot + 1);

		if (gear != null)
		{
			swapImages(gearImgView, inventoryImageView);
		} else
		{
			setImage(gearImgView, inventoryImageView, true);
		}
	}

	public void useItem(int nthInvSlot)
	{
		ImageView inventoryImageView = (ImageView) gameController.paneInventory.getChildren().get(nthInvSlot + 1);
		inventoryImageView.setImage(null);
	}

	protected void dialogVisibleFalse()
	{
		gameController.paneSmallDialog.setVisible(false);
		gameController.paneBigDialog.setVisible(false);
		gameController.blurGroup.setEffect(null);
	}

	public void changeGameText(String info, int gameState)
	{
		switch (gameState)
		{
			case 1:
			{
				gameController.gameText1.setText(info);
				break;
			}
			case 2:
			{
				gameController.gameText2.setText(info);
				break;
			}
			case 3:
			{
				gameController.gameText3.setText(info);
			}
		}
	}

	protected void paneMainMenuShow()
	{
		gameController.paneMainMenu.setVisible(true);
	}

	protected void setGameSetText(String info1, String info2, String info3)
	{
		gameController.gameText1.setText(info1);
		gameController.gameText2.setText(info2);
		gameController.gameText3.setText(info3);
		
		trashDisablerAndEnabler();
	}

	protected void newGame()
	{
		gameController.paneNewGame.setVisible(true);
		gameController.specialization1.setImage(getImg("mainmenu/new_game_selected", false));
		gameController.specialization2.setImage(getImg("mainmenu/new_game_unselected", false));
		gameController.specialization3.setImage(getImg("mainmenu/new_game_unselected", false));
		gameController.mode1.setImage(getImg("mainmenu/new_game_selected", false));
		gameController.mode2.setImage(getImg("mainmenu/new_game_unselected", false));
		gameController.mode3.setImage(getImg("mainmenu/new_game_unselected", false));
	}

	protected void newGameChange(String name, boolean specialization)
	{
		if (specialization)
			switch (name)
			{
				case "šedý válečník":
				{
					gameController.specialization1.setImage(getImg("mainmenu/new_game_selected", false));
					gameController.specialization2.setImage(getImg("mainmenu/new_game_unselected", false));
					gameController.specialization3.setImage(getImg("mainmenu/new_game_unselected", false));
					break;
				}
				case "lovec odměn":
				{
					gameController.specialization1.setImage(getImg("mainmenu/new_game_unselected", false));
					gameController.specialization2.setImage(getImg("mainmenu/new_game_selected", false));
					gameController.specialization3.setImage(getImg("mainmenu/new_game_unselected", false));
					break;
				}
				case "civilista":
				{
					gameController.specialization1.setImage(getImg("mainmenu/new_game_unselected", false));
					gameController.specialization2.setImage(getImg("mainmenu/new_game_unselected", false));
					gameController.specialization3.setImage(getImg("mainmenu/new_game_selected", false));
				}
			}
		else
			switch (name)
			{
				case "klasický":
				{
					gameController.mode1.setImage(getImg("mainmenu/new_game_selected", false));
					gameController.mode2.setImage(getImg("mainmenu/new_game_unselected", false));
					gameController.mode3.setImage(getImg("mainmenu/new_game_unselected", false));
					break;
				}
				case "rychlý":
				{
					gameController.mode1.setImage(getImg("mainmenu/new_game_unselected", false));
					gameController.mode2.setImage(getImg("mainmenu/new_game_selected", false));
					gameController.mode3.setImage(getImg("mainmenu/new_game_unselected", false));
					break;
				}
				case "realistický":
				{
					gameController.mode1.setImage(getImg("mainmenu/new_game_unselected", false));
					gameController.mode2.setImage(getImg("mainmenu/new_game_unselected", false));
					gameController.mode3.setImage(getImg("mainmenu/new_game_selected", false));
				}
			}
	}

	protected void quit()
	{
		List<Node> paneInventoryContent = gameController.paneInventory.getChildren();
		List<Node> paneDeckContent = gameController.paneDeck.getChildren();
		List<Node> paneGearContent = gameController.paneGear.getChildren();

		int i = 1;
		for (; i <= Constants.PLAYERS_INVENTORY_SIZE; i++)
			((ImageView) paneInventoryContent.get(i)).setImage(null);
		i = 1;
		for (; i <= Constants.PLAYERS_DECK_SIZE; i++)
			((ImageView) paneDeckContent.get(i)).setImage(null);
		i = 1;
		for (; i <= Constants.PLAYERS_GEAR_SIZE; i++)
			((ImageView) paneGearContent.get(i)).setImage(getGearDefaultImage(i - 1));

		gameController.paneMap.setVisible(false);
		keeperNodesVisibler(false);
		
		paneMainMenuShow();
	}

	protected void updateUserInterfaceForSpecificPlanet(String actualPlanet)
	{
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.4);
		if (actualPlanet.equals("Tarrys"))
		{
			gameController.blue.setVisible(false);
			gameController.brown.setVisible(false);
			gameController.red.setVisible(false);

			gameController.priceLeft.setVisible(true);
			gameController.travelLeft.setVisible(true);
			gameController.priceRight.setVisible(false);
			gameController.travelRight.setVisible(false);
			gameController.narrSheyda.setEffect(null);
			gameController.kerusant.setEffect(colorAdjust);

			gameController.cardShop.setImage(getImg("cantine/card_shop4", false));

			gameController.leftHotel.setImage(getImg("hotels/left_hotel_tarrys", false));
			gameController.rightHotel.setImage(getImg("hotels/right_hotel_tarrys", false));
			gameController.priceLeftHotel.setText("1950");
			gameController.priceRightHotel.setText("4120");

			gameController.legend.setImage(getImg("map/legend_tarrys_background", false));
			gameController.mapBackground.setImage(getImg("map/map_tarrys", false));

			gameController.repairer.setVisible(false);
			gameController.technique.setVisible(false);
			gameController.fuel.setVisible(false);

			colorClick("blue");
		} else if (actualPlanet.equals("Narr Sheyda"))
		{
			gameController.blue.setVisible(true);
			gameController.brown.setVisible(true);
			gameController.red.setVisible(false);

			gameController.priceLeft.setVisible(false);
			gameController.travelLeft.setVisible(false);
			gameController.priceRight.setVisible(true);
			gameController.travelRight.setVisible(true);
			gameController.narrSheyda.setEffect(colorAdjust);
			gameController.kerusant.setEffect(null);

			gameController.cardShop.setImage(getImg("cantine/card_shop6", false));

			gameController.leftHotel.setImage(getImg("hotels/left_hotel_narr_sheyda", false));
			gameController.rightHotel.setImage(getImg("hotels/right_hotel_narr_sheyda", false));
			gameController.priceLeftHotel.setText("3900");
			gameController.priceRightHotel.setText("8240");

			gameController.legend.setImage(getImg("map/legend_background", false));
			gameController.mapBackground.setImage(getImg("map/map_narr_sheyda", false));

			gameController.repairer.setVisible(true);
			gameController.technique.setVisible(true);
			gameController.fuel.setVisible(true);

			colorClick("brown");
		} else
		{
			gameController.blue.setVisible(true);
			gameController.brown.setVisible(true);
			gameController.red.setVisible(true);

			gameController.priceLeft.setVisible(false);
			gameController.travelLeft.setVisible(false);
			gameController.priceRight.setVisible(false);
			gameController.travelRight.setVisible(false);
			gameController.narrSheyda.setEffect(colorAdjust);
			gameController.kerusant.setEffect(colorAdjust);

			gameController.cardShop.setImage(getImg("cantine/card_shop8", false));

			gameController.leftHotel.setImage(getImg("hotels/left_hotel_kerusant", false));
			gameController.rightHotel.setImage(getImg("hotels/right_hotel_kerusant", false));
			gameController.priceLeftHotel.setText("5850");
			gameController.priceRightHotel.setText("12360");

			gameController.legend.setImage(getImg("map/legend_background", false));
			gameController.mapBackground.setImage(getImg("map/map_kerusant", false));

			gameController.repairer.setVisible(true);
			gameController.technique.setVisible(true);
			gameController.fuel.setVisible(true);

			colorClick("red");
		}

		moveMapIcons(actualPlanet);
	}

	private void moveMapIcons(String actualPlanet)
	{
		if (actualPlanet.equals("Tarrys"))
		{
			gameController.migration.setLayoutX(956);
			gameController.migration.setLayoutY(417);

			gameController.sleep.setLayoutX(993);
			gameController.sleep.setLayoutY(186);

			gameController.weapons.setLayoutX(593);
			gameController.weapons.setLayoutY(298);

			gameController.cantine.setLayoutX(857);
			gameController.cantine.setLayoutY(502);

			gameController.grocery.setLayoutX(808);
			gameController.grocery.setLayoutY(646);

			gameController.armor.setLayoutX(1153);
			gameController.armor.setLayoutY(559);

			gameController.work.setLayoutX(569);
			gameController.work.setLayoutY(708);

			gameController.medications.setLayoutX(1245);
			gameController.medications.setLayoutY(202);

			gameController.jewelry.setLayoutX(1298);
			gameController.jewelry.setLayoutY(723);
		} else if (actualPlanet.equals("Narr Sheyda"))
		{
			gameController.migration.setLayoutX(1157);
			gameController.migration.setLayoutY(751);

			gameController.sleep.setLayoutX(719);
			gameController.sleep.setLayoutY(684);

			gameController.weapons.setLayoutX(1262);
			gameController.weapons.setLayoutY(384);

			gameController.cantine.setLayoutX(1083);
			gameController.cantine.setLayoutY(517);

			gameController.grocery.setLayoutX(731);
			gameController.grocery.setLayoutY(391);

			gameController.armor.setLayoutX(1220);
			gameController.armor.setLayoutY(644);

			gameController.work.setLayoutX(724);
			gameController.work.setLayoutY(291);

			gameController.medications.setLayoutX(876);
			gameController.medications.setLayoutY(751);

			gameController.jewelry.setLayoutX(945);
			gameController.jewelry.setLayoutY(289);

			gameController.repairer.setLayoutX(1125);
			gameController.repairer.setLayoutY(206);

			gameController.technique.setLayoutX(957);
			gameController.technique.setLayoutY(459);

			gameController.fuel.setLayoutX(635);
			gameController.fuel.setLayoutY(538);
		} else
		{
			gameController.migration.setLayoutX(882);
			gameController.migration.setLayoutY(206);

			gameController.sleep.setLayoutX(1058);
			gameController.sleep.setLayoutY(372);

			gameController.weapons.setLayoutX(696);
			gameController.weapons.setLayoutY(538);

			gameController.cantine.setLayoutX(1274);
			gameController.cantine.setLayoutY(546);

			gameController.grocery.setLayoutX(1120);
			gameController.grocery.setLayoutY(202);

			gameController.armor.setLayoutX(785);
			gameController.armor.setLayoutY(436);

			gameController.work.setLayoutX(1285);
			gameController.work.setLayoutY(241);

			gameController.medications.setLayoutX(1001);
			gameController.medications.setLayoutY(696);

			gameController.jewelry.setLayoutX(667);
			gameController.jewelry.setLayoutY(428);

			gameController.repairer.setLayoutX(873);
			gameController.repairer.setLayoutY(715);

			gameController.technique.setLayoutX(519);
			gameController.technique.setLayoutY(497);

			gameController.fuel.setLayoutX(519);
			gameController.fuel.setLayoutY(212);
		}
	}

	protected void colorClick(String idFromSource)
	{
		switch (idFromSource)
		{
			case "blue":
			{
				gameController.blue.setImage(getImg("residue/selected_blue", false));
				gameController.brown.setImage(getImg("residue/unselected_brown", false));
				gameController.red.setImage(getImg("residue/unselected_red", false));
				gameController.background.setImage(getImg("residue/blue_background", false));
				break;
			}
			case "brown":
			{
				gameController.blue.setImage(getImg("residue/unselected_blue", false));
				gameController.brown.setImage(getImg("residue/selected_brown", false));
				gameController.red.setImage(getImg("residue/unselected_red", false));
				gameController.background.setImage(getImg("residue/brown_background", false));
				break;
			}
			default:
			{
				gameController.blue.setImage(getImg("residue/unselected_blue", false));
				gameController.brown.setImage(getImg("residue/unselected_brown", false));
				gameController.red.setImage(getImg("residue/selected_red", false));
				gameController.background.setImage(getImg("residue/red_background", false));
			}
		}
	}

	public void clearFeatures()
	{
		gameController.featuresTitles.setImage(null);

		gameController.featuresName.setText("");
		gameController.features1.setText("");
		gameController.features2.setText("");
		gameController.features3.setText("");
		gameController.features4.setText("");
		gameController.features5.setText("");
		gameController.features6.setText("");
		gameController.features7.setText("");

	}

	public void setFuelVisible(boolean fuelClick)
	{
		if (fuelClick)
		{
			gameController.fuelOrRepairBanner.setImage(getImg("repairfuel/bannerFuel", false));
			gameController.fuelOrRepairClick.setImage(getImg("repairfuel/refill", false));
		} else
		{
			gameController.fuelOrRepairBanner.setImage(getImg("repairfuel/bannerRepair", false));
			gameController.fuelOrRepairClick.setImage(getImg("repairfuel/repair", false));
		}
	}

	public void setFuelOrRepairSlotImage(String img)
	{
		gameController.fuelOrRepairSlot.setImage(getImg(img, true));
	}

	public void clearFuelOrRepairSlotImage()
	{
		gameController.fuelOrRepairSlot.setImage(null);
	}

	public void checkDestroyedGear(Item[] gear)
	{
		for (int i = 0; i < Constants.PLAYERS_GEAR_SIZE; i++)
			if (gear[i] == null)
				((ImageView) gameController.paneGear.getChildren().get(i + 1)).setImage(getGearDefaultImage(i));
	}

	public void paneWorkDisable(boolean disable)
	{
		if (disable)
		{
			gameController.droidIndicator.setVisible(true);
			gameController.paneWork.setDisable(true);
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setBrightness(-0.4);
			gameController.paneWork.setEffect(colorAdjust);
		} else
		{
			gameController.droidIndicator.setVisible(false);
			gameController.paneWork.setDisable(false);
			gameController.paneWork.setEffect(null);
		}
	}

	public void setDroidIndicator(int primaryFeatureOfDroid, Player player, int droidsWorkCode, int droidsCredits)
	{
		int duration = 4000 / primaryFeatureOfDroid;
		if(player.getGameMode().equals("rychlý"))
			duration = duration / 2;
				
		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(gameController.droidIndicator.progressProperty(), 0)),
				new KeyFrame(Duration.seconds(duration), new KeyValue(gameController.droidIndicator.progressProperty(), 1)));
		
		timeline.setOnFinished(event -> {
			if (droidsWorkCode == 0)
			{
				smallGeneralDialog("Droid byl během práce zničen.");
				player.getGear()[11] = null;
				checkDestroyedGear(player.getGear());
			} else
			{
				smallGeneralDialog("Droid se vrátil z práce.\n\nVydělal\n" + droidsCredits + " kreditů.");
				player.addOrRemoveCredits(droidsCredits, true);
				updateStats(player);
			}
			paneWorkDisable(false);
		});

		timeline.play();
	}

	public boolean droidIsWorking()
	{
		return gameController.droidIndicator.isVisible();
	}

	public void addListener(ImageView image)
	{
		image.setEffect(null);
		image.setOnMouseEntered(event -> {
			gameController.mouseEnteredImgGlow(event);
		});
		image.setOnMouseExited(event -> {
			gameController.mouseExitedImg(event);
		});

	}

	public void setNewGameListeneres(Item[] gear)
	{
		addListener(gameController.gearWear);
		addListener(gameController.gearHand);
		addListener(gameController.gearBelt);
		addListener(gameController.gearBoots);
		
	}

	protected void setBet(int paziakBet)
	{
		gameController.paziakBet.setText(Integer.toString(paziakBet));
	}

	public void removeCardImage(ImageView image)
	{
		image.setImage(null);
	}

	public void setCantineCardsImages(Shop shop, String actualPlanet)
	{
		int i = 1;
		for (; i <= 8; i++)
			setImageOfNthSlot(i, null, 1);

		int ceiling;
		switch (actualPlanet)
		{
			case "Tarrys":
			{
				ceiling = shop.getCountOfShopSlotsFirstPlanet();
				break;
			}
			case "Narr Sheyda":
			{
				ceiling = shop.getCountOfShopSlotsSecondPlanet();
				break;
			}
			default:
				ceiling = shop.getCountOfShopSlotsThirdPlanet();
		}

		i = 1;
		for (Item item : shop.getShopItems())
		{
			if (item != null)
				setImageOfNthSlot(i, item.getImg(), 3);
			if (i == ceiling)
				return;
			i++;
		}
		
	}

	public void trashDisablerAndEnabler()
	{
		gameController.trashDisablerAndEnabler();
	}

	public void assault(boolean sleep, int health)
	{
		String dialogText;
		if(sleep)
			dialogText = "Během spánku jsi byl přepaden.\n\nPřepadení jsi\n";
		else 
			dialogText = "Během cestování jsi byl přepaden členem gangu Seroko.\n\nPřepadení jsi\n";
		
		boolean survived = true;
		if(health < 1)
		{
			survived = false;
			dialogText = dialogText + "nepřežil.\n\nPro pokračování ve hře je nutné načíst poslední uloženou pozici, nebo si založit novou hru.";
		}
		else 
			dialogText = dialogText + "přežil";
		
		if(survived)
			smallGeneralDialog(dialogText);
		else 
			bigGeneralDialog(dialogText);
	}
}
