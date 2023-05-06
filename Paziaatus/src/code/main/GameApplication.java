package main;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameApplication extends Application
{
	@Override public void start(Stage stage) throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("/game.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("Paziaatus");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/hud/residue/logo.png")));

		Image image = new Image("/images/hud/residue/cursor.png");
		scene.setCursor(new ImageCursor(image));

		GameController controller = fxmlLoader.getController();

		String path = this.getClass().getResource("").toString();
		File lockFile = new File(path.substring("file:".length()) + "/game.lock");
		if (lockFile.exists())
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Hra je již spuštěna");
			alert.setContentText("Jiná instance hry je již spuštěna.\nNelze spustit najednou více instancí hry.");
			alert.showAndWait();
			Platform.exit();
			return;
		} else
		{
			lockFile.createNewFile();
		}

		controller.setStage(stage, lockFile);

		stage.show();
	}

	public static void main(String[] args)
	{
		launch();
	}
}