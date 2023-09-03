package load_from_json;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LoaderHelper {
    public static GraphDto graphDto() {
        URL resource = LoaderHelper.class.getResource("/graph.json");

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(resource.getPath(), StandardCharsets.UTF_8);
            Gson gson = new Gson();
            return gson.fromJson(fileReader, GraphDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
