package com.affcm.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.affcm.Data;
import com.affcm.controller.MainController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSONControl {

    public static void json_saver(Data data) throws Exception {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        try(BufferedWriter writer = new BufferedWriter(new FileWriter("out/com/affcm//fxml/data.json"))){
            gson.toJson(data, writer);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("out/com/affcm/fxml/logs.json"))){
            gson.toJson(data.log, writer);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

}
