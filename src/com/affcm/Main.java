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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/affcm/fxml/Main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}



