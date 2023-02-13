package main;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class PaziaatusApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(PaziaatusApplication.class.getResource("/Pazaak.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pazaak");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/logo.png")));
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}