package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PaziaatusApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(PaziaatusApplication.class.getResource("/Paziaatus.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Paziaatus");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/1600x900/hud/residue/logo.png")));
        
        Image image = new Image("/images/1600x900/hud/residue/cursor.png");
        scene.setCursor(new ImageCursor(image));
        
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}