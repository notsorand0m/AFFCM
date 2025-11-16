package main.java.com.affcm.service;

import de.kherud.llama.ModelParameters;
import de.kherud.llama.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import main.resources.fxml.MainController.*;

public class ModelService {

    public static String call(List<String> directories, File file) {
        ModelParameters modelParams = new ModelParameters()
                .setModel("models/" + UserService.getData1().AIModel)
                .setGpuLayers(45)
                .skipWarmup();


        try (LlamaModel model = new LlamaModel(modelParams)) {
            InferenceParameters inferParams = new InferenceParameters(
                    "Choose the one best of all directory path for this file "
                            + "from these: " + Arrays.toString(Arrays.stream(directories.toArray()).toArray())
                            + "Where should this file go: " + file.getName()
                            + "Output ONLY the absolute path. Don't include the file name. Include ONLY the path to the folder. Don't include non existing paths from the listed. CHOOSE ONLY FROM THE GIVEN PATHS, DONT OUTPUT ANYTHING ELSE, nothing before, nothing after "
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

