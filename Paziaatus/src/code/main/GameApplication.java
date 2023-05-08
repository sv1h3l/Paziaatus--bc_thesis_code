package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import residue.GameSaverLoader;

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

		if (GameSaverLoader.isGameAlreadyRunning())
		{

			Alert dialog = new Alert(AlertType.INFORMATION);
			
			Stage dialogIcon = (Stage) dialog.getDialogPane().getScene().getWindow();
			dialogIcon.getIcons().add(new Image(this.getClass().getResource("/images/hud/residue/logo.png").toString()));
			
			dialog.setTitle("Paziaatus");
			dialog.setHeaderText(null);
			dialog.setContentText("Jiná instance hry je již spuštěna.\nNelze spustit najednou více instancí hry.");
			
			dialog.showAndWait();
			
			Platform.exit();
			return;
		}
		
		GameController controller = fxmlLoader.getController();
		controller.setStage(stage);
		GameSaverLoader.gameIsAlreadyRunning(true);

		stage.show();
	}

	public static void main(String[] args)
	{
		launch();
	}
}