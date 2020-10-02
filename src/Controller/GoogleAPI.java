package Controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import javazoom.jl.player.Player;

public class GoogleAPI {

    /**
     * Use Google Translate to translate.
     */
    public static String trans(String from, String to, String word) throws Exception {
        URL url = new URL("https://translate.googleapis.com/translate_a/single?" +
                "client=gtx&" +
                "sl=" + from +
                "&tl=" + to +
                "&dt=t&q=" + URLEncoder.encode(word, "UTF-8"));
        URLConnection urlCon = url.openConnection();
        urlCon.setRequestProperty("User-Agent", "Mozilla/5");
        BufferedReader data = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "utf8"));
        String s;
        StringBuffer save = new StringBuffer();
        while ((s = data.readLine()) != null) {
            save.append(s);
        }
        data.close();
        JSONArray a = new JSONArray(save.toString());
        JSONArray b = (JSONArray) a.get(0);
        System.out.println(b.get(0));
        JSONArray c = (JSONArray) b.get(0);
        return c.get(0).toString();
    }

    /**
     * Use Google Translate to read a text.
     */
    public static void read(String text, String from) throws Exception {
        URL url = new URL("http://translate.google.com/translate_tts?ie=UTF-8&tl=" + from + "&client=tw-ob&q="
                + text.replace(" ", "%20"));
        URLConnection urlCon = url.openConnection();
        urlCon.addRequestProperty("User-Agent", "Mozilla/5");
        InputStream data = urlCon.getInputStream();
        Player sound = new Player(data);
        sound.play();
    }

//    public static void main(String[] args) throws Exception {
//        String s = trans("en","vi","wind tells me where to go");
//        read("Trí đẹp trai.", "vi");
//    }
}