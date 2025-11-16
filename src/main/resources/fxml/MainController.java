package main.resources.fxml;

import javafx.scene.text.Text;
import main.java.com.affcm.Data;
import main.java.com.affcm.service.ModelService;
import main.java.com.affcm.service.UserService;
import main.java.com.affcm.service.JSONControl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDateTime;
import java.awt.Desktop;

public class MainController{

    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    // Chatgpt
    private final String OSName = System.getProperty("user.name");

    @FXML
    public BorderPane rootPane;

    @FXML
    public Button text5min;

    @FXML
    public Button text30min;

    @FXML
    public Button text2hours;

    @FXML
    public Button text2days;

    @FXML
    public Button lightTheme;

    @FXML
    public Button darkTheme;

    @FXML
    public Button passiveAI;

    @FXML
    public Button assistiveAI;

    @FXML
    public Button autonomousAI;

    @FXML
    public Button multithreadingOn;

    @FXML
    public Button multithreadingOff;

    @FXML
    public Button sleepControlOn;

    @FXML
    public Button sleepControlOff;

    @FXML
    public Button thermalOptimizationOn;

    @FXML
    public Button thermalOptimizationOff;

    @FXML
    public Button fileViewGrid;

    @FXML
    public Button fileViewList;

    @FXML
    public Button fileViewGallery;

    @FXML
    public Button automaticBackupDaily;

    @FXML
    public Button automaticBackupMonthly;

    @FXML
    public Button automaticBackupCustom;

    @FXML
    public Button fileVaultOn;

    @FXML
    public Button fileVaultOff;

    @FXML
    public Button upload;

    public String folder;

    @FXML
    public Button recommended_folder;

    @FXML
    public Button fileName;

    @FXML
    public Button RecommendedFolderAccepted;

    @FXML
    public Button RecommendedFolderDeclined;

    @FXML
    public Button RecommendedFolderTryAgain;

    public String setFolder = "";

    @FXML
    public Button OrganizeDesktopID;

    @FXML
    public Button OrganizeDocumentsID;

    @FXML
    public Button OrganizeDownloadsID;

    @FXML
    public Button OrganizeCustomID;

    @FXML
    public Button AIModelButton;

    public String AIModelName;

    public String multithreadingState;

    public ExecutorService service;

    public int oldTotal;

    @FXML
    public Text historyText;

    public static Path oldDirectory;
    public static Path newDirectory;

    @FXML
    public void initialize() throws Exception{

        try(BufferedReader reader = new BufferedReader(new FileReader("data.json"))){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Data data = gson.fromJson(reader, Data.class);

            multithreadingState = UserService.getData1().multithreading;
            service = Executors.newFixedThreadPool(2);

            if(multithreadingState.equals("On")){
                System.out.println("initialize: multithreading turned on");
            }

        }
        catch (Exception ignored){
        }

    }

    public void getText5min() throws Exception {
        UserService.setData1archive_interval(text5min.getText());
        UserService.setData1log("Changed automatic archiving period to 5 minutes | " + LocalDateTime.now());
        historyText.setText(UserService.getData1().log);
        JSONControl.json_saver(UserService.getData1());
    }

    public void getText30min() throws Exception {
        UserService.setData1archive_interval(text30min.getText());
        UserService.setData1log("Changed automatic archiving period to 30 minutes | " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void getText2hours() throws Exception {
        UserService.setData1archive_interval(text2hours.getText());
        UserService.setData1log("Changed automatic archiving period to 2 hours | " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void getText2days() throws Exception {
        UserService.setData1archive_interval(text2days.getText());
        UserService.setData1log("Changed automatic archiving period to 2 days | " + LocalDateTime.now() );
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveLightTheme() throws Exception{
        UserService.setData1theme(lightTheme.getText());
        UserService.setData1log("Changed theme to light | " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveDarkTheme() throws Exception{
        UserService.setData1theme(darkTheme.getText());
        UserService.setData1log("Changed theme to dark | " + LocalDateTime.now());
        historyText.setText(UserService.getData1().log);
        JSONControl.json_saver(UserService.getData1());
    }

    public void savePassiveAI() throws Exception{
        UserService.setData1org_level(passiveAI.getText());
        UserService.setData1log("Changed organization level to passive | " + LocalDateTime.now());
        historyText.setText(UserService.getData1().log);
        JSONControl.json_saver(UserService.getData1());
    }

    public void savePassiveAI_2() throws Exception{
        UserService.setData1org_level(passiveAI.getText());
        UserService.setData1log("Changed organization level to passive | " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());

        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    public void saveAutonomousAI_2() throws Exception{
        UserService.setData1org_level(passiveAI.getText());
        UserService.setData1log("Changed organization level to autonomous | " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());

        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    public void saveAssistiveAI() throws Exception{
        UserService.setData1org_level(assistiveAI.getText());
        UserService.setData1log("Changed organization level to assistive | " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveAutonomousAI() throws Exception{
        UserService.setData1org_level(autonomousAI.getText());
        UserService.setData1log("Changed organization level to autonomous | " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveMultithreadingOn() throws Exception{
        UserService.setMultithreading(multithreadingOn.getText());
        UserService.setData1log("Changed multithreading state to on | " + LocalDateTime.now() );
        JSONControl.json_saver(UserService.getData1());

    }

    public void saveMultithreadingOff() throws Exception{
        UserService.setMultithreading(multithreadingOff.getText());
        UserService.setData1log("Changed multithreading state to on | " + LocalDateTime.now() );
        JSONControl.json_saver(UserService.getData1());

    }

    public void saveSleepControlOn() throws Exception{
        UserService.setSleepControl(sleepControlOn.getText());
        UserService.setData1log("Sleep control was changed to on" + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveSleepControlOff() throws Exception{
        UserService.setSleepControl(sleepControlOff.getText());
        UserService.setData1log("Sleep control was changed to off" + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveThermalOptimizationOn() throws Exception{
        UserService.setThermalOptimization(thermalOptimizationOn.getText());
        UserService.setData1log("Thermal optimization was changed to on" + LocalDateTime.now() );
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveThermalOptimizationOff() throws Exception{
        UserService.setThermalOptimization(thermalOptimizationOff.getText());
        UserService.setData1log("Thermal optimization was changed to off" + LocalDateTime.now() );
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveFileViewGrid() throws Exception{
        UserService.setFileView(fileViewGrid.getText());
        UserService.setData1log("File view was changed to grid" + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveFileViewList() throws Exception{
        UserService.setFileView(fileViewList.getText());
        UserService.setData1log("File view was changed to list" + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveFileViewGallery() throws Exception{
        UserService.setFileView(fileViewGallery.getText());
        UserService.setData1log("File view was changed to gallery" + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveAutomaticBackupDaily() throws Exception{
        UserService.setAutomaticBackup(automaticBackupDaily.getText());
        UserService.setData1log("Automatic backup was changed to daily " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveAutomaticBackupMonthly() throws Exception{
        UserService.setAutomaticBackup(automaticBackupMonthly.getText());
        UserService.setData1log("Automatic backup was changed to monthly " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveAutomaticBackupCustom() throws Exception{
        UserService.setAutomaticBackup(automaticBackupCustom.getText());
        UserService.setData1log("Automatic backup was changed to custom " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveFileVaultOn() throws Exception{
        UserService.setFileVault(fileVaultOn.getText());
        UserService.setData1log("File vault status was changed to on " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public void saveFileVaultOff() throws Exception{
        UserService.setFileVault(fileVaultOff.getText());
        UserService.setData1log("File vault status was changed to off " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    public List<String> findDirectoriesDocuments() throws Exception{

        File downloads = new File("/Users/" + OSName + "/Documents");

        File[] contentDocuments = downloads.listFiles();

        int counter = contentDocuments.length;
        List<String> directoriesDocuments = new ArrayList<>();

        Runnable task = () -> {
            for (int i = 0; i < contentDocuments.length; i++) {
                if (contentDocuments[i].isDirectory()) {
                    directoriesDocuments.add(contentDocuments[i].toString());
                }
            }
        };

        if(multithreadingState.equals("On")){
            while(counter > 0){
                service.submit(task);
                counter--;
            }
        }
        else {
            while (counter > 0) {
                for (int i = 0; i < contentDocuments.length; i++) {
                    if (contentDocuments[i].isDirectory()) {
                        directoriesDocuments.add(contentDocuments[i].toString());
                    }
                    counter--;
                }
            }
        }

        directoriesDocuments.remove("/Users/simeon");

        for(int i = 0; i < directoriesDocuments.toArray().length; i++){
            System.out.println(directoriesDocuments.get(i));
        }

        return directoriesDocuments;
    }

    public List<String> findDirectoriesDownloads() throws Exception{

        File downloads = new File("/Users/" + OSName + "/Downloads");

        File[] contentDownloads = downloads.listFiles();

        int counter = contentDownloads.length;
        List<String> directoriesDownloads = new ArrayList<>();

        Runnable task = () -> {
                for (int i = 0; i < contentDownloads.length; i++) {
                    if (contentDownloads[i].isDirectory()) {
                        directoriesDownloads.add(contentDownloads[i].toString());
                    }
            }
        };

        if(multithreadingState.equals("On")){
            while(counter > 0){
                service.submit(task);
                counter--;
            }
        }
        else {
            while (counter > 0) {
                for (int i = 0; i < contentDownloads.length; i++) {
                    if (contentDownloads[i].isDirectory()) {
                        directoriesDownloads.add(contentDownloads[i].toString());
                    }
                    counter--;
                }
            }
        }

        for(int i = 0; i < directoriesDownloads.toArray().length; i++){
            System.out.println(directoriesDownloads.get(i));
        }

        return directoriesDownloads;
    }

    public List<String> findDirectoriesDesktop() throws Exception{

        File downloads = new File("/Users/" + OSName + "/Desktop");

        File[] contentDesktop = downloads.listFiles();

        int counter = contentDesktop.length;
        List<String> directoriesDesktop = new ArrayList<>();

        Runnable task = () -> {
            for (int i = 0; i < contentDesktop.length; i++) {
                if (contentDesktop[i].isDirectory()) {
                    directoriesDesktop.add(contentDesktop[i].toString());
                }
            }
        };

        if(multithreadingState.equals("On")){
            while(counter > 0){
                service.submit(task);
                counter--;
            }
        }
        else {
            while (counter > 0) {
                for (int i = 0; i < contentDesktop.length; i++) {
                    if (contentDesktop[i].isDirectory()) {
                        directoriesDesktop.add(contentDesktop[i].toString());
                    }
                    counter--;
                }
            }
        }

        for(int i = 0; i < directoriesDesktop.toArray().length; i++){
            System.out.println(directoriesDesktop.get(i));
        }

        return directoriesDesktop;
    }

    public List<String> findDirectoriesPictures() throws Exception{

        File downloads = new File("/Users/" + OSName + "/Pictures");

        File[] contentPictures = downloads.listFiles();

        int counter = contentPictures.length;
        List<String> directoriesPictures = new ArrayList<>();

        Runnable task = () -> {
            for (int i = 0; i < contentPictures.length; i++) {
                if (contentPictures[i].isDirectory()) {
                    directoriesPictures.add(contentPictures[i].toString());
                }
            }
        };

        if(multithreadingState.equals("On")){
            while(counter > 0){
                service.submit(task);
                counter--;
            }
        }
        else {
            while (counter > 0) {
                for (int i = 0; i < contentPictures.length; i++) {
                    if (contentPictures[i].isDirectory()) {
                        directoriesPictures.add(contentPictures[i].toString());
                    }
                    counter--;
                }
            }
        }

        for(int i = 0; i < directoriesPictures.toArray().length; i++){
            System.out.println(directoriesPictures.get(i));
        }

        return directoriesPictures;
    }

    @FXML
    public void setRecommendedFolderAccepted(ActionEvent event) throws Exception{
        try{
            System.out.println(oldDirectory);
            System.out.println(newDirectory);
            Files.move(oldDirectory, newDirectory);
        }
        catch (Exception ignored){

        }
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void setRecommendedFolderDeclined(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void UploadFile(ActionEvent event) throws Exception {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        oldDirectory = Path.of(file.getAbsolutePath());
        System.out.println(oldDirectory.toString());

        Task<String> task1 = new Task<>() {
            @Override
            protected String call() throws Exception {
                if(file.isFile()) {
                    folder = ModelService.call(findDirectoriesDownloads(), file);
                    newDirectory = Path.of(folder, file.getName());
                    System.out.println(newDirectory);
                    setFolder = folder;
                }

                return folder;
            }
        };

        task1.setOnSucceeded((evnt) -> {

            if(UserService.getData1().org_level.equals("Passive")) {
                // Loads .fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Uploaded.fxml"));
                try {
                    Parent window = loader.load();

                    // Creates new instance of the controller
                    MainController controller = loader.getController();
                    controller.recommended_folder.setText("Recommended location: " + folder);

                    // Shows the windows with the new instance of MainController :)
                    rootPane.getChildren().setAll(window);



                } catch (Exception ignored) {

                }
            }
            else if(UserService.getData1().org_level.equals("Autonomous")){
                try {
                    setRecommendedFolderAccepted(new ActionEvent());
                } catch (Exception ignored) {

                }
            }
            else{
                    try {
                        Parent loader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrganizationLevelNotSet.fxml")));

                        // Shows the windows with the new instance of MainController :)
                        rootPane.getChildren().setAll(loader);

                        // Loads home page


                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }


        });
        multithreadingState = UserService.getData1().multithreading;
        if(multithreadingState.equals("On")){
            service.submit(task1);
        }
        else{
            ExecutorService service1 = Executors.newFixedThreadPool(2);
            service1.submit(task1);
        }


    }

    @FXML
    public void CreateFolder(ActionEvent event) throws Exception{
        Runtime.getRuntime().exec(new String[]{"open", "/Users"});
    }

    @FXML
    public void AIFileSorting(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ChooseFolderToSort.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OrganizeDesktop(ActionEvent event) throws Exception{
        File downloads = new File("/Users/" + OSName + "/Desktop");

        File[] contentDownloads = downloads.listFiles();

        int counter = contentDownloads.length;
        List<String> directoriesDownloads = new ArrayList<>();

        for (int i = 0; i < contentDownloads.length; i++) {
            if (contentDownloads[i].isFile()) {
                oldDirectory = Path.of(contentDownloads[i].getAbsolutePath());
                folder = ModelService.call(findDirectoriesDownloads(), contentDownloads[i]);
                newDirectory = Path.of(folder, contentDownloads[i].getName());

                System.out.println(contentDownloads[i] + " is going to: " + folder);
                System.out.println("old is: " + oldDirectory);
                System.out.println(newDirectory);
                Files.move(oldDirectory, newDirectory);
                counter--;
            }
            else{
                counter--;
            }
        }


        for(int i = 0; i < directoriesDownloads.toArray().length; i++){
            System.out.println(directoriesDownloads.get(i));
        }

    }

    @FXML
    public void OrganizeDocuments(ActionEvent event) throws Exception{
        File downloads = new File("/Users/" + OSName + "/Documents");

        File[] contentDownloads = downloads.listFiles();

        int counter = contentDownloads.length;
        List<String> directoriesDownloads = new ArrayList<>();

        for (int i = 0; i < contentDownloads.length; i++) {
            if (contentDownloads[i].isFile()) {
                oldDirectory = Path.of(contentDownloads[i].getAbsolutePath());
                folder = ModelService.call(findDirectoriesDownloads(), contentDownloads[i]);
                newDirectory = Path.of(folder, contentDownloads[i].getName());

                System.out.println(contentDownloads[i] + " is going to: " + folder);
                System.out.println("old is: " + oldDirectory);
                System.out.println(newDirectory);
                Files.move(oldDirectory, newDirectory);
                counter--;
            }
            else{
                counter--;
            }
        }


        for(int i = 0; i < directoriesDownloads.toArray().length; i++){
            System.out.println(directoriesDownloads.get(i));
        }
    }

    @FXML
    public void OrganizeDownloads(ActionEvent event) throws Exception{
        File downloads = new File("/Users/" + OSName + "/Downloads");

        File[] contentDownloads = downloads.listFiles();

        int counter = contentDownloads.length;
        List<String> directoriesDownloads = new ArrayList<>();

            for (int i = 0; i < contentDownloads.length; i++) {
                if (contentDownloads[i].isFile()) {
                    oldDirectory = Path.of(contentDownloads[i].getAbsolutePath());
                    folder = ModelService.call(findDirectoriesDownloads(), contentDownloads[i]);
                    newDirectory = Path.of(folder, contentDownloads[i].getName());

                    System.out.println(contentDownloads[i] + " is going to: " + folder);
                    System.out.println("old is: " + oldDirectory);
                    System.out.println(newDirectory);
                    Files.move(oldDirectory, newDirectory);
                    counter--;
                }
                else{
                    counter--;
                }
            }


        for(int i = 0; i < directoriesDownloads.toArray().length; i++){
            System.out.println(directoriesDownloads.get(i));
        }
    }

    @FXML
    public void OrganizeCustom(ActionEvent event) throws Exception{
        DirectoryChooser folderChooser = new DirectoryChooser();

        Stage stage = new Stage();

        File selectedFolder = folderChooser.showDialog(stage);
        System.out.println("Selected folder is: " + selectedFolder.toString());

        File[] contentDownloads = selectedFolder.listFiles();

        int counter = contentDownloads.length;
        List<String> directoriesDownloads = new ArrayList<>();

        for (int i = 0; i < contentDownloads.length; i++) {
            if (contentDownloads[i].isFile()) {
                oldDirectory = Path.of(contentDownloads[i].getAbsolutePath());
                folder = ModelService.call(findDirectoriesDownloads(), contentDownloads[i]);
                newDirectory = Path.of(folder, contentDownloads[i].getName());

                System.out.println(contentDownloads[i] + " is going to: " + folder);
                System.out.println("old is: " + oldDirectory);
                System.out.println(newDirectory);
                Files.move(oldDirectory, newDirectory);
                counter--;
            }
            else{
                counter--;
            }
        }


        for(int i = 0; i < directoriesDownloads.toArray().length; i++){
            System.out.println(directoriesDownloads.get(i));
        }
    }

    public void SortFolder(File selectedFolder) throws Exception{
        File[] contentDownloads = selectedFolder.listFiles();

        int counter = contentDownloads.length;
        List<String> directoriesDownloads = new ArrayList<>();

        while(counter > 0) {
            for (int i = 0; i < contentDownloads.length; i++) {
                if (contentDownloads[i].isDirectory()) {
                    directoriesDownloads.add(contentDownloads[i].toString());
                    counter--;
                }
                else{
                    folder = ModelService.call(directoriesDownloads, contentDownloads[i]);
                    System.out.println("Sorting...");
                    System.out.println(folder);
                    counter--;
                }
            }


        }

        for(int i = 0; i < directoriesDownloads.toArray().length; i++){
            System.out.println(directoriesDownloads.get(i));
        }
    }

    @FXML
    public void ChooseAIModel(ActionEvent event) throws Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("GGUF files (*.gguf)", "*.gguf"));
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        AIModelName = file.getName();
        UserService.setAIModel(AIModelName);
        UserService.setData1log("Changed AI Model | " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
        System.out.println("Successfully chose and saved to .json AI Model | " + LocalDateTime.now());
    }

    @FXML
    public void DefaultAIModel(ActionEvent event) throws Exception{
        UserService.setAIModel("Mistral-Nemo-Instruct-2407-Q4_K_S.gguf");
        UserService.setData1log("Changed AI Model to default | " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());
    }

    @FXML
    public void ClearJsonContents(ActionEvent event) throws Exception{
        UserService.setData1log("");
        UserService.setData1theme("");
        UserService.setAIModel("");
        UserService.setData1org_level("");
        UserService.setFileView("");
        UserService.setAutomaticBackup("");
        UserService.setFileVault("");
        UserService.setData1archive_interval("");
        UserService.setMultithreading("");
        UserService.setSleepControl("");
        UserService.setThermalOptimization("");
        UserService.setTempFolder("");
        JSONControl.json_saver(UserService.getData1());
    }

    @FXML
    public void ClearJsonLog(ActionEvent event) throws Exception{
        UserService.clearData1log();
        JSONControl.json_saver(UserService.getData1());
    }

    @FXML
    public void setFileName(ActionEvent event) throws Exception{
        fileName.setText("File: ");
    }

    @FXML
    public void AutomaticBackup(ActionEvent event) throws Exception{

    }

    @FXML
    public void UpdateHistory(ActionEvent event) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("History.fxml"));
        try {
            Parent window = loader.load();

            // Creates new instance of the controller
            MainController controller = loader.getController();
            controller.historyText.setText(UserService.getData1().log);

            // Shows the windows with the new instance of MainController :)
            rootPane.getChildren().setAll(window);



        } catch (Exception ignored) {

        }
    }

    @FXML
    public void ScanFolders(ActionEvent event) throws Exception {

        // Scans Downloads Folder
        File downloads = new File("/Users/" + OSName + "/Downloads");

        File[] contentDownloads = downloads.listFiles();

        int counter = contentDownloads.length;
        List<String> directoriesDownloads = new ArrayList<>();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < contentDownloads.length; i++) {
                    if (contentDownloads[i].isDirectory()) {
                        directoriesDownloads.add(contentDownloads[i].toString());
                    } else if (contentDownloads[i].isFile()) {
                        System.out.println("Found not sorted file: " + contentDownloads[i]);
                        final File ii = contentDownloads[i];
                        Task<Void> tempTask = new Task<Void>() {

                            @Override
                            protected Void call() throws Exception {
                                try {
                                    // FIX
                                    ModelService.call(directoriesDownloads, ii);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                return null;
                            }
                        };
                        if(tempTask.isDone()){
                            tempTask.run();
                        }

                    }
                }
                return null;
            }
        };

        if (multithreadingState.equals("On")) {
            while (counter > 0) {
                service.submit(task);
                counter--;
            }
        } else {

        }

        System.out.println();

        // Scanning Documents Folder
        File documents = new File("/Users/" + OSName + "/Documents");

        File[] contentDocuments = documents.listFiles();

        int counter2 = contentDocuments.length;
        List<String> directoriesDocuments = new ArrayList<>();

        Runnable task2 = () -> {
            for (int i = 0; i < contentDocuments.length; i++) {
                if (contentDocuments[i].isDirectory()) {
                    directoriesDocuments.add(contentDocuments[i].toString());
                }
            }
        };

        if (multithreadingState.equals("On")) {
            while (counter2 > 0) {
                service.submit(task2);
                counter2--;
            }
        } else {
            while (counter2 > 0) {
                for (int i = 0; i < contentDocuments.length; i++) {
                    if (contentDocuments[i].isDirectory()) {
                        directoriesDocuments.add(contentDocuments[i].toString());
                    } else if (contentDocuments[i].isFile()) {
                        System.out.println("Found not sorted file: " + contentDocuments[i]);
                    }
                    counter2--;
                }
            }
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("folders.txt"))){
            for(int i = 0; i < contentDownloads.length; i++){
                writer.write(String.valueOf(contentDownloads[i]));
                writer.newLine();
            }
            writer.newLine();
            for(int i = 0; i < contentDocuments.length; i++){
                writer.write(String.valueOf(contentDocuments[i]));
                writer.newLine();
            }
            Desktop desktop = Desktop.getDesktop();
            File file = new File("folders.txt");
            Task<Void> task3 = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    desktop.open(file);
                    return null;
                }
            };

            if(multithreadingState.equals("On")){
                service.submit(task3);
            }
            else {
                desktop.open(file);
            }

        }
        catch(Exception ignored){

        }
    }

    @FXML
    public void ScanCustomFolder(ActionEvent event) throws Exception{
        // Scans Downloads Folder
        DirectoryChooser folderChooser = new DirectoryChooser();

        Stage stage = new Stage();

        File selectedFolder = folderChooser.showDialog(stage);
        System.out.println(selectedFolder);

        File[] contentDownloads = selectedFolder.listFiles();

        int counter = contentDownloads.length;
        List<String> directoriesDownloads = new ArrayList<>();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < contentDownloads.length; i++) {
                    if (contentDownloads[i].isDirectory()) {
                        directoriesDownloads.add(contentDownloads[i].toString());
                    } else if (contentDownloads[i].isFile()) {
                        System.out.println("Found not sorted file: " + contentDownloads[i]);
                        final File ii = contentDownloads[i];
                        Task<Void> tempTask = new Task<Void>() {

                            @Override
                            protected Void call() throws Exception {
                                try {
                                    ModelService.call(directoriesDownloads, ii);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                return null;
                            }
                        };
                        if(tempTask.isDone()){
                            tempTask.run();
                        }

                    }
                }
                return null;
            }
        };

        if (multithreadingState.equals("On")) {
            while (counter > 0) {
                service.submit(task);
                counter--;
            }
        } else {

        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("folders.txt"))){
            for(int i = 0; i < contentDownloads.length; i++){
                writer.write(String.valueOf(contentDownloads[i]));
                writer.newLine();
            }

            Desktop desktop = Desktop.getDesktop();
            File file = new File("folders.txt");
            Task<Void> task3 = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    desktop.open(file);
                    return null;
                }
            };

            if(multithreadingState.equals("On")){
                service.submit(task3);
            }
            else {
                desktop.open(file);
            }

        }
        catch(Exception ignored){

        }
    }

    @FXML
    public void OpenAIModelPage(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ChooseModel.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenPluginPage(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Plugin.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenSettingsPage(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Settings.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);

    }

    @FXML
    public void OpenAIOrganizePage(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AI_Organize.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenHomePage(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenPerformanceModePage(ActionEvent evnet) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PerformanceMode.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenArchive(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Archive.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenHistory(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("History.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenCloudSync(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CloudSync.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenLoadingScreen() throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoadingScreen.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenViewUploaded() throws Exception{

    }

    @FXML
    public void OpenUploaded() throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Uploaded.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

}
