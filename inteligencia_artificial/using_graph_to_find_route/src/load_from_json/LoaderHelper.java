package load_from_json;

import com.google.gson.Gson;
import graph.Graph;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoaderHelper {
    public static GraphDto graphDto() {
        URL resource = LoaderHelper.class.getResource("/graph-romenia.json");

        try (FileReader fileReader = new FileReader(resource.getPath(), StandardCharsets.UTF_8)){

            Gson gson = new Gson();
            return gson.fromJson(fileReader, GraphDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveAsJson(GraphDto graph) {
        URL resource = LoaderHelper.class.getResource("/");

        try {
            Path path = Paths.get(resource.toURI()).resolve("graph-romenia.json");

            Gson gson = new Gson();
            String gsonJson = gson.toJson(graph, GraphDto.class);

            FileWriter fileWriter = new FileWriter(path.toAbsolutePath().toString());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(gsonJson);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
