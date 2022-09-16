package UCNDiscordBot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class GiphyAPI {
    private static String giphyKey = new GetAPIKey().getGiphyKey();

    // Find gif from giphy using search term
    public static String getGif(String searchTerm) throws ParseException, IOException, InterruptedException {
        // Make restful call to giphy api
        searchTerm = searchTerm.replaceAll(" ", "+");
        String url = "http://api.giphy.com/v1/gifs/search?api_key=" + giphyKey + "&q=" + searchTerm;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        String gif = jsonParse(response.body());
        return gif;
    }

    // Find random gif from giphy
    public static Object getRandomGif() throws InterruptedException, IOException, ParseException {
        // Make restful call to giphy api
        String url = "http://api.giphy.com/v1/gifs/random?api_key=" + giphyKey;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        String gif = randomGifParse(response.body());
        return gif;
    }

    public static String randomGifParse(String input) throws ParseException {
        // Parse response
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(input);
        JSONObject data = (JSONObject) json.get("data");
        JSONObject images = (JSONObject) data.get("images");
        JSONObject original = (JSONObject) images.get("original");
        String gif = (String) original.get("url");
        return gif;
    }

    public static String jsonParse(String input) throws ParseException {
        // Parse response
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(input);
        JSONArray data = (JSONArray) json.get("data");
        Random random = new Random();
        JSONObject gif = (JSONObject) data.get(random.nextInt(data.size()));
        JSONObject images = (JSONObject) gif.get("images");
        JSONObject original = (JSONObject) images.get("original");
        String gifUrl = (String) original.get("url");
        return gifUrl;
    }
}
