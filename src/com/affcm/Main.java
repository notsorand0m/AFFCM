package com.affcm;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.affcm.service.ModelService;
import com.affcm.controller.MainController;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

import javafx.application.Application;

public class Main extends Application{
    public static void main(String[] args) throws Exception {

        String filename = "";
        String[] directories = {};
        Stream<String> dir_stream = Arrays.stream(directories);

        launch(args);

        // ModelService.call("Answer with exactly ONE lowercase WORD: either 'icons' or ‘images’ or ‘videos’. When should this file go: mama.svg. Without explanation. DON'T give me any other word you want, just the given ones you can choose from!");
        // ModelService.call(new String[] {"iconnnnn", "videooo", "photoooooo"}, new File("mama.jpg"));

        // Launch preview UI
        // Later update to FXML
        // Application.launch(MainController.class, args);

    }
    @Override
    public void start(Stage stage) throws Exception {
        try {
            // 1. Check if the FXML file actually exists before loading
            java.net.URL fxmlLocation = getClass().getResource("/com/affcm/fxml/Main.fxml");
            if (fxmlLocation == null) {
                throw new Exception("CRITICAL: FXML file not found! Check your build path.");
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            // This will catch the error and print it so you can see it in logs
            e.printStackTrace();

            // Bonus: Show a popup so you know WHY it's not starting
            javax.swing.JOptionPane.showMessageDialog(null, "Error Launching: " + e.getMessage());
            System.exit(1);
        }
    }

}



