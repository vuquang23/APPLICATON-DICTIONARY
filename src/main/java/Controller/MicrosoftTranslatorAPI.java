package Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;

public class MicrosoftTranslatorAPI {
    private static String subscriptionKey = ("f2fba7565a694a6ab9bec851c55a83f9");
    private static String endpoint = ("https://api.cognitive.microsofttranslator.com/");
    static String url = endpoint + "/translate?api-version=3.0&to=vi";
    static OkHttpClient client = new OkHttpClient();

    /* translate a text from english to vietnamese. */
    public static String Post(String translate) throws IOException {
        String content = "[{\n\t\"Text\": \"" + translate + "\"\n}]";
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(url).post(body)
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .addHeader("Ocp-Apim-Subscription-Region", "eastasia")
                .addHeader("Content-type", "application/json").build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /* get String from JSON ARRAY. */
    public static String Translate(String Word) {
        try {
            String json_text = Post(Word);
            JsonParser parser = new JsonParser();
            Object obj = parser.parse(json_text);
            JsonObject obj2 = (JsonObject) (((JsonArray) obj).get(0));
            JsonArray x = (JsonArray) (obj2.get("translations"));
            JsonObject obj3 = (JsonObject) x.get(0);
            String ret = String.valueOf(obj3.get("text"));
            ret = ret.substring(1, ret.length() - 1);
            return ret;
        } catch (IOException e) {
            return "-1";
        }
    }

}