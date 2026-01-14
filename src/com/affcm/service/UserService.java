package com.affcm.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.affcm.Data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserService {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static BufferedReader reader;
    static final Path dataPath = Paths.get(System.getProperty("user.home"), "AFFCM", "data.json");
    static final Path logsPath = Paths.get(System.getProperty("user.home"), "AFFCM", "logs.json");

    static {
        try {

            // Ensure folders exists
            if (!Files.exists(dataPath.getParent())) {
                Files.createDirectories(dataPath.getParent());
            }
            if (!Files.exists(logsPath.getParent())) {
                Files.createDirectories(dataPath.getParent());
            }

            // If file does not exist, create default JSON's
            if (!Files.exists(dataPath)) {
                try (BufferedWriter writer = Files.newBufferedWriter(dataPath, StandardCharsets.UTF_8)) {
                    gson.toJson(new Data(), writer);
                }
            }
            if (!Files.exists(logsPath)) {
                try (BufferedWriter writer = Files.newBufferedWriter(dataPath, StandardCharsets.UTF_8)) {
                    gson.toJson(new Data(), writer);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            reader = new BufferedReader(new FileReader(Paths.get(System.getProperty("user.home"), "AFFCM", "data.json").toFile()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Data data = gson.fromJson(reader, Data.class);


    public UserService() throws FileNotFoundException {

    }

    public static void setData1log(String log) throws Exception {
        data.log += "\n";
        data.log += log;
    }
    public static void clearData1log() throws Exception{
        data.log = "";
    }
    public static void setData1theme(String theme) throws Exception{
        data.theme = theme;
    }
    public static void setAIModel(String AIModelStr) throws Exception{
        data.AIModel = AIModelStr;
    }
    public static void setData1org_level(String org_level) throws Exception{
        data.org_level = org_level;
    }
    public static void setData1archive_interval(String archive_interval) throws Exception {
        data.archive_interval = archive_interval;
    }
    public static void setMultithreading(String multithreadingStatus) throws Exception {
        data.multithreading = multithreadingStatus;
    }
    public static void setSleepControl(String SleepControlStatus) throws Exception {
        data.autoSleepControl = SleepControlStatus;
    }
    public static void setThermalOptimization(String status) throws Exception {
        data.thermalOptimization = status;
    }
    public static void setFileView(String fileView){
        data.fileView = fileView;
    }
    public static void setAutomaticBackup(String status){
        data.automaticBackup = status;
    }
    public static void setFileVault(String fileVault){
        data.fileVault = fileVault;
    }
    public static void setTempFolder(String tempFolder){
        data.tempFolder = tempFolder;
    }

    public static Data getData1(){
        return data;
    }
}
