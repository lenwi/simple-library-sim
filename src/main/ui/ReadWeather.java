package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadWeather {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID="
                + "1cf7746e673b29fee70c1502cbaa77fc");
        System.out.println(json.toString());
        JSONArray arr = json.getJSONArray("weather");
        for (int i = 0; i < arr.length(); i++) {
            String d = arr.getJSONObject(i).getString("description");
            System.out.println("\nCurrent weather for Vancouver: " + d);
        }
        int c = json.getJSONObject("main").getInt("temp");
        System.out.println(c - 273 + " °C");
    }
}
