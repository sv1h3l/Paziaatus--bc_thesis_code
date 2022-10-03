package main;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PaziaatusApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(PaziaatusApplication.class.getResource("/FXML/Pazaak.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 855, 782);
        stage.setTitle("Pazaak");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}