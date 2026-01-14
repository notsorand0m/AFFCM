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
                    "Choose the one best of all directory path for this file "
                            + "from these: " + Arrays.toString(Arrays.stream(directories.toArray()).toArray())
                            + "Where should this file go: " + file.getName()
                            + "You must respond with exactly one value. Output ONLY a single absolute folder path. DO NOT include a file name. DO NOT include quotes, code blocks, explanations, formatting, whitespace, newlines, prefixes, or suffixes. DO NOT include commentary before or after the output. DO NOT invent, modify, or normalize paths. DO NOT output any path that is not explicitly listed. Choose ONE AND ONLY ONE path from the provided list. The output MUST match the chosen path exactly, character-for-character. If no valid path applies, output nothing at all (empty response). Any output other than a single valid listed absolute folder path is strictly forbidden."
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

