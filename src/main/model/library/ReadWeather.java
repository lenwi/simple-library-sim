package model.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;


public class ReadWeather {

    public static void main(String[] args) throws MalformedURLException, IOException, JSONException {

        BufferedReader br = null;

        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID="
                    + "1cf7746e673b29fee70c1502cbaa77fc");
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            JSONObject obj = new JSONObject(url.openStream());
            JSONArray arr = obj.getJSONArray("weather");
            for (int i = 0; i < arr.length(); i++) {
                String d = arr.getJSONObject(i).getString("description");
                System.out.println("Current weather for Vancouver: " + d);
            }
            int c = obj.getJSONObject("main").getInt("temp");
            System.out.println("\n" + c + " C");
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}
