package main.java.com.affcm.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.com.affcm.Data;
import main.resources.fxml.MainController;

import java.io.*;
import java.nio.file.Path;

public class JSONControl {

    public static void json_saver(Data data) throws Exception {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("data.json"))){
            gson.toJson(data, writer);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("logs.json"))){
            gson.toJson(data.log, writer);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }

        //Continue with saving properly logs into data.json file
    }

    public static Boolean json_checker(String field,String status){
        //Continue with reading and returning properly logs into data.json file
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        try(BufferedReader reader = new BufferedReader(new FileReader("data.json"))){
//            Data dataClass = gson.fromJson(reader, Data.class);
//
//            if(dataClass){
//                System.out.println("Exists");
//            } else if (dataClass.theme.equals(field) && dataClass.theme.equals()) {
//
//            }
//
//
//        }
//        catch(Exception e){
//
//        }
        return true;
    }
}
