package com.affcm.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import com.affcm.Data;
import com.affcm.service.ModelService;
import com.affcm.service.UserService;
import com.affcm.service.JSONControl;

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

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.awt.event.MouseAdapter;
import java.io.*;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.security.spec.ECField;
import java.security.spec.KeySpec;
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

    private static final int SALT_LENGTH = 16; // bytes
    private static final int IV_LENGTH = 12; // bytes
    private static final int ITERATIONS = 100_000;
    private static final int KEY_LENGTH = 256; // AES-256

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
    @FXML
    TextArea passwordToCheck;
    @FXML
    TextArea passwordToEncrypt;
    @FXML
    Button OpenDecryptedFileLocation;

    File rawFile;
    File encryptedFile;
    File finalEncryptedFile;
    File finalFile;

    // @FXML
    // TextArea passwordToEncrypt;

    public static Path oldDirectory;
    public static Path newDirectory;

    @FXML
    public void initialize() throws Exception{

        try(BufferedReader reader = new BufferedReader(new FileReader(Paths.get(System.getProperty("user.home"), "AFFCM", "logs.json").toFile()))){
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
        // historyText.setText(UserService.getData1().log);
        JSONControl.json_saver(UserService.getData1());
    }

    public void savePassiveAI() throws Exception{
        UserService.setData1org_level(passiveAI.getText());
        UserService.setData1log("Changed organization level to passive | " + LocalDateTime.now());
        // historyText.setText(UserService.data.log);
        JSONControl.json_saver(UserService.getData1());
    }

    public void savePassiveAI_2() throws Exception{
        UserService.setData1org_level(passiveAI.getText());
        UserService.setData1log("Changed organization level to passive | " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());

        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/out/com/affcm/fxml/Main.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    public void saveAutonomousAI_2() throws Exception{
        UserService.setData1org_level(passiveAI.getText());
        UserService.setData1log("Changed organization level to autonomous | " + LocalDateTime.now());
        JSONControl.json_saver(UserService.getData1());

        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("out/com/affcm/fxml/Main.fxml")));
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

    public List<String> findDirectoriesDownloads(File downloads) throws Exception{

        // File downloads = new File("/Users/" + OSName + "/Downloads");

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

                //service.submit(task);
                //counter--;



            while (counter > 0) {
                for (int i = 0; i < contentDownloads.length; i++) {
                    if (contentDownloads[i].isDirectory()) {
                        directoriesDownloads.add(contentDownloads[i].toString());
                    }
                    counter--;
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
        try {
            // For debugging
//            System.out.println(oldDirectory);
//            System.out.println(newDirectory);
            Files.move(oldDirectory, newDirectory);
        }
        catch (Exception ignored){

        }
        if(UserService.getData1().theme.equals("Light")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/MainLite.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else if(UserService.getData1().theme.equals("Dark")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Main.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else{
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Main.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
    }

    @FXML
    public void setRecommendedFolderDeclined(ActionEvent event) throws Exception{
        if(UserService.getData1().theme.equals("Light")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/MainLite.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else if(UserService.getData1().theme.equals("Dark")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Main.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else{
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Main.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }

    }

    @FXML
    public void UploadFile(ActionEvent event) throws Exception {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");
        Stage stage = (Stage) rootPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        oldDirectory = Path.of(file.getAbsolutePath());

        // For debugging
        System.out.println(oldDirectory.toString());

        File downloads = Paths.get(System.getProperty("user.home"), "Downloads").toFile();

        Task<String> task1 = new Task<>() {
            @Override
            protected String call() throws Exception {
                if(file.isFile()) {
//                    UserService.getData1().tempFolder = file.toPath().toString();
//                    JSONControl.json_saver(UserService.getData1());
                    List<String> listDownloads = findDirectoriesDownloads(downloads);

                    if(!UserService.getData1().AIModel.isEmpty()){
                        folder = ModelService.call(listDownloads, file);
                        newDirectory = Path.of(folder, file.getName());

                        // For debugging
                        System.out.println(newDirectory);

                        setFolder = folder;
                    }
                    else{
                        if(UserService.getData1().theme.equals("Light")){
                            OpenChooseModelPage(new ActionEvent());
                        }
                        else if(UserService.getData1().theme.equals("Dark")){
                            OpenChooseModelPage(new ActionEvent());
                        }
                        else{
                            OpenChooseModelPage(new ActionEvent());
                        }
                    }
                }

                return folder;
            }
        };

        task1.setOnFailed(e -> {
            throw new RuntimeException();
        });

        task1.setOnSucceeded((evnt) -> {

            FXMLLoader loader;
            if(UserService.getData1().org_level.equals("Passive")) {
                // Loads .fxml file
                if(UserService.getData1().theme.equals("Dark")){
                    loader = new FXMLLoader(getClass().getResource("/com/affcm/fxml/Uploaded.fxml"));
                }
                else if(UserService.getData1().theme.equals("Light")){
                    loader = new FXMLLoader(getClass().getResource("/com/affcm/fxml/UploadedLite.fxml"));
                }
                else{
                    loader = new FXMLLoader(getClass().getResource("/com/affcm/fxml/Uploaded.fxml"));
                }

                try {
                    Parent window = loader.load();

                    // Creates new instance of the controller
                    MainController controller = loader.getController();
                    controller.recommended_folder.setText("Recommended location: " + folder);

                    // Shows the windows with the new instance of MainController :)
                    rootPane.getChildren().setAll(window);



                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }
            }
            else if(UserService.getData1().org_level.equals("Autonomous")){
                try {
                    setRecommendedFolderAccepted(new ActionEvent());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                try {
                    OpenOrgLevelNotSet(new ActionEvent());
                } catch (Exception e) {
                    throw new RuntimeException(e);
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
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/ChooseFolderToSort.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public SecretKey extractKey(char[] pass, byte[] salt) throws Exception{
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(pass, salt, ITERATIONS, KEY_LENGTH);
        SecretKey key = factory.generateSecret(spec);
        return new SecretKeySpec(key.getEncoded(), "AES");
    }

    @FXML
    public void encrypt(File file, File finalFile, char[] pass) throws Exception{
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        // Derive key
        SecretKey key = extractKey(pass, salt);

        // Generate random IV
        byte[] iv = new byte[IV_LENGTH];
        random.nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);

        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(finalFile)) {

            // Write salt + IV at the beginning
            fos.write(salt);
            fos.write(iv);

            // Stream encryption
            try (CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {
                byte[] buffer = new byte[16 * 1024 * 1024]; // 16 MB buffer
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    cos.write(buffer, 0, bytesRead);
                }
            }
        }
    }

    @FXML
    public void decrypt(File file, File finalFile, char[] pass) throws Exception{
        try (FileInputStream fis = new FileInputStream(file)) {

            // Read salt
            byte[] salt = new byte[SALT_LENGTH];
            if (fis.read(salt) != SALT_LENGTH) throw new IllegalStateException("Invalid salt length");

            // Read IV
            byte[] iv = new byte[IV_LENGTH];
            if (fis.read(iv) != IV_LENGTH) throw new IllegalStateException("Invalid IV length");

            // Derive key
            SecretKey key = extractKey(pass, salt);
            GCMParameterSpec spec = new GCMParameterSpec(128, iv);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key, spec);

            // Stream decryption
            try (CipherInputStream cis = new CipherInputStream(fis, cipher);
                 FileOutputStream fos = new FileOutputStream(finalFile)) {

                byte[] buffer = new byte[16 * 1024 * 1024]; // 16 MB buffer
                int bytesRead;
                while ((bytesRead = cis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }
        }
    }

    @FXML
    public void BrowseFileToEncrypt(ActionEvent event) throws Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");
        Stage stage = (Stage) rootPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);


        if(file.isFile()){
            // rawFile = file;
            UserService.setRawFile(file.getAbsolutePath());
            JSONControl.json_saver(UserService.getData1());
            System.out.println(file);
            System.out.println(UserService.getData1().rawFile);

            if(UserService.getData1().theme.equals("Light")){
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/EncryptLite.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            }
            else if(UserService.getData1().theme.equals("Dark")){
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Encrypt.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            }
            else{
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Encrypt.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            }
        }

        // Change so the choose file dialog is a task
        // nd when finished the aes encrypt enter password opens
        // then when save as clicked its checked if the text area is not empty
        // then it saves the encrypted file
    }

    @FXML
    public void SaveEncryptedFileAs(ActionEvent event) throws Exception{
        try{
            File rawFilePacked = new File(UserService.getData1().rawFile);
            finalEncryptedFile = new File(rawFilePacked.getParentFile().getAbsolutePath() + "/Encrypted" + rawFilePacked.getName() + ".enc");
            encrypt(rawFilePacked, finalEncryptedFile, passwordToEncrypt.getText().toCharArray());

            if(UserService.getData1().theme.equals("Light")){
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/FileEncryptedSuccsessfullyLite.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            }
            else if(UserService.getData1().theme.equals("Dark")){
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/FileEncryptedSuccsessfully.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            }
            else{
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/FileEncryptedSuccsessfully.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void OpenEncryptedLocation(ActionEvent event) throws Exception{
        File rawFilePacked = new File(UserService.getData1().rawFile);
        finalEncryptedFile = new File(rawFilePacked.getParentFile().getAbsolutePath() + rawFilePacked.getName() + ".enc");
        new ProcessBuilder("open", rawFilePacked.getParentFile().getAbsolutePath()).start();
    }

    public static String removeAllExtensions(String filename) {
        String name = Paths.get(filename).getFileName().toString();

        int firstDot = name.indexOf('.');
        if (firstDot <= 0) {
            return name; // no dot or hidden file like ".gitignore"
        }

        return name.substring(0, firstDot);
    }

    public static String removeLastExtension(String filename) {
        String name = Paths.get(filename).getFileName().toString();

        int lastDot = name.lastIndexOf('.');
        if (lastDot <= 0) {
            return name; // no dot or hidden file like ".gitignore"
        }

        return name.substring(0, lastDot);
    }

    @FXML
    public void BrowseFileToDecrypt(ActionEvent event) throws Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");
        Stage stage = (Stage) rootPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if(file.isFile()) {
            // rawFile = file;
            UserService.setRawFile(file.getAbsolutePath());
            JSONControl.json_saver(UserService.getData1());

            System.out.println(file);
            System.out.println(UserService.getData1().rawFile);

            if (UserService.getData1().theme.equals("Light")) {
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/RequirePasswordLite.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            } else if (UserService.getData1().theme.equals("Dark")) {
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/RequirePassword.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            } else {
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/RequirePassword.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            }
        }
    }

    @FXML
    public void checkUserPassword(ActionEvent event) throws Exception{
        try{

            File rawFilePacked = new File(UserService.getData1().rawFile);
            finalEncryptedFile = new File(rawFilePacked.getParentFile().getAbsolutePath() + "/decrypted" + removeLastExtension(rawFilePacked.getName()));
            decrypt(rawFilePacked, finalEncryptedFile, passwordToCheck.getText().toCharArray());

            if(UserService.getData1().theme.equals("Light")){
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/FileDecryptedSuccsessfullyLite.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            }
            else if(UserService.getData1().theme.equals("Dark")){
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/FileDecryptedSuccsessfully.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            }
            else{
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/FileDecryptedSuccsessfully.fxml ")));
                rootPane.getChildren().setAll(fxmlLoader);
            }

        } catch (Exception e){
            System.out.println(e);

            if(UserService.getData1().theme.equals("Light")){
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/WrongPasswordLite.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            }
            else if(UserService.getData1().theme.equals("Dark")){
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/WrongPassword.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            }
            else{
                Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/WrongPassword.fxml")));
                rootPane.getChildren().setAll(fxmlLoader);
            }

        }
    }

    @FXML
    public void OpenDecryptedLocation(ActionEvent event) throws Exception{
        File rawFilePacked = new File(UserService.getData1().rawFile);
        finalEncryptedFile = new File(rawFilePacked.getParentFile().getAbsolutePath() + removeLastExtension(rawFilePacked.getName()));
        new ProcessBuilder("open", rawFilePacked.getParentFile().getAbsolutePath()).start();
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

                DirectoryChooser dirChooser = new DirectoryChooser();
                dirChooser.setTitle("Choose a folder");
                Stage stage = new Stage();
                File downloads2 = dirChooser.showDialog(stage);

                folder = ModelService.call(findDirectoriesDownloads(downloads2), contentDownloads[i]);
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

                DirectoryChooser dirChooser = new DirectoryChooser();
                dirChooser.setTitle("Choose a folder");
                Stage stage = new Stage();
                File downloads3 = dirChooser.showDialog(stage);

                folder = ModelService.call(findDirectoriesDownloads(downloads3), contentDownloads[i]);
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

                DirectoryChooser dirChooser = new DirectoryChooser();
                dirChooser.setTitle("Choose a folder");
                Stage stage = new Stage();
                File downloads4 = dirChooser.showDialog(stage);

                folder = ModelService.call(findDirectoriesDownloads(downloads4), contentDownloads[i]);
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

                DirectoryChooser dirChooser = new DirectoryChooser();
                dirChooser.setTitle("Choose a folder");
                Stage stage5 = new Stage();
                File downloads5 = dirChooser.showDialog(stage5);

                folder = ModelService.call(findDirectoriesDownloads(downloads5), contentDownloads[i]);
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

        UserService.getData1().tempFolder = file.toPath().toString();
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/affcm/fxml/History.fxml"));
        try {
            Parent window = loader.load();

            // Creates new instance of the controller
            MainController controller = loader.getController();
            // controller.historyText.setText(UserService.getData1().log);

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
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/ChooseModel.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenPluginPage(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Plugin.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenSettingsPage(ActionEvent event) throws Exception{
        if(UserService.getData1().theme.equals("Light")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/SettingsLite.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else if(UserService.getData1().theme.equals("Dark")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Settings.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else{
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Settings.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }

    }

    @FXML
    public void OpenOrgLevelNotSet(ActionEvent event) throws Exception{
        if(UserService.getData1().theme.equals("Light")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/OrganizationLevelNotSetLite.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else if(UserService.getData1().theme.equals("Dark")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/OrganizationLevelNotSet.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else{
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/OrganizationLevelNotSet.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
    }

    @FXML
    public void OpenChooseModelPage(ActionEvent event) throws Exception{
        if(UserService.getData1().theme.equals("Light")) {
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/ChooseModelLite.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else if(UserService.getData1().theme.equals("Dark")) {
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/ChooseModel.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else {
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/ChooseModel.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
    }

    @FXML
    public void OpenAIOrganizePage(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/AI_Organize.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenHomePage(ActionEvent event) throws Exception{
        if(UserService.getData1().theme.equals("Light")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/MainLite.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else if(UserService.getData1().theme.equals("Dark")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Main.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else{
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Main.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }

    }

    @FXML
    public void OpenEncryptDecryptPage(ActionEvent event) throws Exception{
        if(UserService.getData1().theme.equals("Light")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/EncryptDecryptLite.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else if(UserService.getData1().theme.equals("Dark")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/EncryptDecrypt.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else{
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/EncryptDecrypt.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
    }

    @FXML
    public void OpenPerformanceModePage(ActionEvent evnet) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/PerformanceMode.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenArchive(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Archive.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenHistory(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/History.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenCloudSync(ActionEvent event) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/CloudSync.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenLoadingScreen() throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/LoadingScreen.fxml")));
        rootPane.getChildren().setAll(fxmlLoader);
    }

    @FXML
    public void OpenViewUploaded() throws Exception{

    }

    @FXML
    public void OpenUploaded() throws Exception{
        if(UserService.getData1().theme.equals("Light")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/UploadedLite.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else if(UserService.getData1().theme.equals("Dark")){
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Uploaded.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }
        else{
            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/affcm/fxml/Uploaded.fxml")));
            rootPane.getChildren().setAll(fxmlLoader);
        }

    }

}
