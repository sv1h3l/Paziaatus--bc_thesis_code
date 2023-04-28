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

	private Timeline	loadingBarTimeline;
	private Timeline	loadingIndicatorTimeline;

	private ImageView clickedImage;

	private Image			navigationActualImageKeeper;
	protected List<Node>	paneActualNodesKeeper;

	private String dialogText;

	protected GameView(GameController gameController)
	{
		this.gameController = gameController;
		paneActualNodesKeeper = new ArrayList<>();

		loadingIndicatorTimeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(gameController.loadingIndicator.progressProperty(), 0)),
				new KeyFrame(Duration.seconds(1), new KeyValue(gameController.loadingIndicator.progressProperty(), 1)));

		loadingIndicatorTimeline.setOnFinished(event -> {
			gameController.paneMap.setVisible(false);
			gameController.loadingIndicator.setVisible(false);
			gameController.actual.setImage(navigationActualImageKeeper);
			disableTravellingImages(false);
			keeperNodesVisibler(true);
		});

		loadingBarTimeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(gameController.loadingBar.progressProperty(), 0)),
				new KeyFrame(Duration.seconds(3), new KeyValue(gameController.loadingBar.progressProperty(), 1)));

		loadingBarTimeline.setOnFinished(event -> {
			gameController.paneLoading.setVisible(false);
			bigGeneralDialog(dialogText);
		});
	}

	protected void workDuration(int duration)
	{
		loadingIndicatorTimeline.getKeyFrames().set(1,
				new KeyFrame(Duration.seconds(duration), new KeyValue(gameController.loadingBar.progressProperty(), 1)));
	}

	protected void workClick(String workType, int workCode, int credits, int health)
	{
		List<String> dialogTexts = new ArrayList<>();
		dialogTexts.add("Během převozu laboratorního zařízení tě přepadli zloději.\n\nPřepadení jsi ");
		dialogTexts.add("Kvůli chybě v obvodu na tebe zaútočil laboratorní droyd.\n\nÚtok jsi ");
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
				dialogText = "Vrátil ses z práce.\n\n";
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
			dialogText = "Pro pokračování ve hře je nutné načíst poslední uloženou pozici, nebo si založit novou hru.";
		}

		this.dialogText = dialogText;

		gameController.paneLoading.setVisible(true);
		loadingBarTimeline.play();
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

	protected void changeFeatures(boolean featuresOfGear)
	{
		gameController.featuresSelect.setImage(getImg(featuresOfGear ? "features/char_info.png" : "features/item_info.png", false, false));
	}

	protected void characterNodesVisibler(boolean visible)
	{
		gameController.paneGear.setVisible(visible);
		gameController.paneCards.setVisible(visible);
		gameController.paneInventory.setVisible(visible);
		gameController.paneFeatures.setVisible(visible);
		gameController.middle.setVisible(visible);
	}

	protected void setImagesOfLoadedItems(Player player)
	{
		Item[] items = player.getInventory();

		for (int i = 0; i < Constants.PLAYERS_INVENTORY_SIZE; i++)
			if (items[i] != null)
			{
				ImageView inventoryImgView = (ImageView) gameController.paneInventory.getChildren().get(i + 1);
				inventoryImgView.setImage(getImg(items[i].getImg(), true, true));
			}

		items = player.getGear();
		for (int i = 0; i < Constants.PLAYERS_INVENTORY_SIZE; i++)
			if (items[i] != null)
			{
				ImageView gearImgView = (ImageView) gameController.paneGear.getChildren().get(i + 1);
				gearImgView.setImage(getImg(items[i].getImg(), true, true));
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

	private Image getImg(String partOfPath, boolean items, boolean png)
	{
		String itemsOrHud;
		if (items)
			itemsOrHud = "/items/";
		else
			itemsOrHud = "/hud/";

		return new Image(this.getClass().getResourceAsStream("/images/" + itemsOrHud + partOfPath + (png ? ".png" : "")));
	}

	protected void updateStats(Player player)
	{
		gameController.energy.setText(String.valueOf(player.getEnergy()));
		gameController.fullness.setText(String.valueOf(player.getFullness()));
		gameController.hydration.setText(String.valueOf(player.getHydration()));
		gameController.health.setText(String.valueOf(player.getHealth()));
		gameController.credits.setText(String.valueOf(player.getCredits()));
	}

	protected void setFieldVisible(String nameOfField, boolean visible)
	{
		gameController.getNodeThroughReflection(nameOfField).setVisible(visible);
	}

	protected void setShopsImages(Shop shop) // TODO
	{
		int i = 0;
		for (Item item : shop.getShopItems())
		{
			if (item != null)
				setImageOfNthSlot(i + 1, item.getImg(), true);
			else
				setImageOfNthSlot(i + 1, null, true);
			i++;
		}
	}

	private void setImageOfNthSlot(int nthSlot, String imgPath, boolean shop)
	{
		String fieldName;
		if (shop)
			fieldName = "shopSlot" + nthSlot;
		else
			fieldName = "invSlot" + nthSlot;

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
			fieldValue.setImage(getImg(imgPath, true, true));
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

	protected void setArrowPosition(int axisY)
	{
		gameController.arrow.setLayoutY(axisY);
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

	public void setShopsBanner(String shopType)
	{
		gameController.shopBanner.setImage(getImg("banners/banner_" + shopType, false, true));
	}

	protected void setEffect(ImageView image, boolean glow, double glowValue)
	{
		if (glow)
			image.setEffect(new Glow(glowValue));
		else
			image.setEffect(null);
	}

	protected void travellingClicks(String idOfSource)
	{
		gameController.loadingIndicator.setVisible(true);
		navigationActualImageKeeper = getImg("/icons/navigation/navigation_" + idOfSource, false, true);
		disableTravellingImages(true);

		loadingIndicatorTimeline.play();

	}

	protected void showFeatures(String partOfPath, String itemType, String[] values)
	{

		gameController.featuresTitles.setImage(getImg(partOfPath, false, false));

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
			inventoryImageView.setImage(null);
		} else
			inventoryImageView.setImage(gearImageView.getImage());
	}

	protected void smallGeneralDialog(String text)
	{
		gameController.smallDialogText.setText(text);
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
		return getImg("icons/gear/gear_" + partOfPath, false, true);
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
				gameController.mouseEnteredImgGlow03(event);
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

	public void ShopSlotRightClick(ImageView image, int nthSlot, String string)
	{
		if (image.getImage() != null)
			image.setImage(null);

		setImageOfNthSlot(nthSlot + 1, string, false);
	}

	public void sellItem(int nthSlot)
	{
		ImageView invImageView = (ImageView) gameController.paneInventory.getChildren().get(nthSlot + 1);
		invImageView.setImage(null);
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

		gameController.paneMainMenu.setVisible(true);
	}

	protected void setGameSetText(String info1, String info2, String info3)
	{
		gameController.gameText1.setText(info1);
		gameController.gameText2.setText(info2);
		gameController.gameText3.setText(info3);
	}

	protected void travelOnNextPlanet(boolean left)
	{
		if (left)
		{
			gameController.travelLeft.setVisible(false);
			gameController.priceLeft.setVisible(false);
		} else
		{
			gameController.travelRight.setVisible(false);
			gameController.priceRight.setVisible(false);
		}
	}

	protected void newGame()
	{
		gameController.paneNewGame.setVisible(true);
		gameController.specialization1.setImage(getImg("mainmenu/new_game_selected", false, true));
		gameController.specialization2.setImage(getImg("mainmenu/new_game_unselected", false, true));
		gameController.specialization3.setImage(getImg("mainmenu/new_game_unselected", false, true));
		gameController.mode1.setImage(getImg("mainmenu/new_game_selected", false, true));
		gameController.mode2.setImage(getImg("mainmenu/new_game_unselected", false, true));
		gameController.mode3.setImage(getImg("mainmenu/new_game_unselected", false, true));
	}

	protected void newGameChange(int id, boolean specialization)
	{
		if (specialization)
			switch (id)
			{
				case 1:
				{
					gameController.specialization1.setImage(getImg("mainmenu/new_game_selected", false, true));
					gameController.specialization2.setImage(getImg("mainmenu/new_game_unselected", false, true));
					gameController.specialization3.setImage(getImg("mainmenu/new_game_unselected", false, true));
					break;
				}
				case 2:
				{
					gameController.specialization1.setImage(getImg("mainmenu/new_game_unselected", false, true));
					gameController.specialization2.setImage(getImg("mainmenu/new_game_selected", false, true));
					gameController.specialization3.setImage(getImg("mainmenu/new_game_unselected", false, true));
					break;
				}
				case 3:
				{
					gameController.specialization1.setImage(getImg("mainmenu/new_game_unselected", false, true));
					gameController.specialization2.setImage(getImg("mainmenu/new_game_unselected", false, true));
					gameController.specialization3.setImage(getImg("mainmenu/new_game_selected", false, true));
					break;
				}
			}
		else
			switch (id)
			{
				case 1:
				{
					gameController.mode1.setImage(getImg("mainmenu/new_game_selectedg", false, true));
					gameController.mode2.setImage(getImg("mainmenu/new_game_unselected", false, true));
					gameController.mode3.setImage(getImg("mainmenu/new_game_unselected", false, true));
					break;
				}
				case 2:
				{
					gameController.mode1.setImage(getImg("mainmenu/new_game_unselected", false, true));
					gameController.mode2.setImage(getImg("mainmenu/new_game_selected", false, true));
					gameController.mode3.setImage(getImg("mainmenu/new_game_unselected", false, true));
					break;
				}
				case 3:
				{
					gameController.mode1.setImage(getImg("mainmenu/new_game_unselected", false, true));
					gameController.mode2.setImage(getImg("mainmenu/new_game_unselected", false, true));
					gameController.mode3.setImage(getImg("mainmenu/new_game_selected", false, true));
					break;
				}
			}

	}

	protected void quit()
	{
		List<Node> paneInventoryContent = gameController.paneInventory.getChildren();
		List<Node> paneCardsContent = gameController.paneCards.getChildren();
		List<Node> paneGearContent = gameController.paneGear.getChildren();

		int i = 1;
		for (; i <= Constants.PLAYERS_INVENTORY_SIZE; i++)
			((ImageView)paneInventoryContent.get(i)).setImage(null);
		i = 1;
		for (; i <= Constants.PLAYERS_CARDS_SIZE; i++)
			((ImageView)paneCardsContent.get(i)).setImage(null);
		i=1;
		for (; i <= Constants.PLAYERS_GEAR_SIZE; i++)
			((ImageView)paneGearContent.get(i)).setImage(getGearDefaultImage(i-1));
	}
}
