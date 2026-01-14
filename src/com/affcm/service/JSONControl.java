package com.affcm.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.affcm.Data;
import com.affcm.controller.MainController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONControl {

    public static void json_saver(Data data) throws Exception {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(System.getProperty("user.home"), "AFFCM", "data.json").toFile()))){
            gson.toJson(data, writer);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(System.getProperty("user.home"), "AFFCM", "logs.json").toFile()))){
            gson.toJson(data.log, writer);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

}
