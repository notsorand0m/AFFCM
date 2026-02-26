package com.affcm.service;

import de.kherud.llama.ModelParameters;
import de.kherud.llama.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ModelService {

    public static String call(List<String> directories, File file) {
        ModelParameters modelParams = new ModelParameters()
                .setModel(UserService.getData1().tempFolder)
                .setGpuLayers(45)
                .skipWarmup();


        try (LlamaModel model = new LlamaModel(modelParams)) {
            InferenceParameters inferParams = new InferenceParameters(
                    "<s>[INST] Pick the best directory for the file: " + file.getName() + "\n"
                            + "Choices: " + directories.toString() + "\n"
                            + "Output ONLY the absolute path. [/INST]"
            )
                    .setTemperature(0.1f)
                    .setNPredict(30);

            StringBuilder promptBuilder = new StringBuilder();
            for (LlamaOutput output : model.generate(inferParams)) {
                System.out.print(output.text);
                promptBuilder.append(output.text);
            }

            String result = promptBuilder.toString().trim();
            System.out.println("\nResponse: " + result);

            return result;

        } catch (Exception e) {
            System.out.println(e);
        }

        return "Error";
    }

}

