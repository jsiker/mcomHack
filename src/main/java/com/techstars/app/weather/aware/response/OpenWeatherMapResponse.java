package com.techstars.app.weather.aware.response;

import com.techstars.app.weather.aware.CookieEater;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by m805958 on 7/21/15.
 *
 * Class returns OpenWeatherMap response & 3-digit weather code
 * that corresponds with @WeatherCodeEnums
 *
 */
public class OpenWeatherMapResponse extends AbstractResponse {


    private static HttpClient client = new DefaultHttpClient();
    private CookieEater cookieEater = new CookieEater();

    /**
     * Returns a full weather object from OpenWeatherMap using lat/long from the MISCGCs
     * on Macy's homepage.
     *
     * @return a JSONObject, and null if HttpClient doesn't resolve or URI is malformed
     */
    public JSONObject getOWMResponse() throws IOException, URISyntaxException {
        String apiKey = getAPIKey("OpenWeatherMapAPIKey");
        final URI url = new URI("http://api.openweathermap.org/data/2.5/weather?lat=" + cookieEater.getLat() +
                "&lon=" + cookieEater.getLong() + "&APPID=" + apiKey);
        HttpGet request = new HttpGet(url);
        HttpResponse response;
        String result;
        try {
            response = client.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream inputStream = entity.getContent();
                result = convertStreamToString(inputStream);
                JSONObject object = new JSONObject(result);
                inputStream.close();
                return object;
            }
        } catch (ClientProtocolException c) {
            c.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return a 3-digit weather description code
     * @throws IOException
     * @throws URISyntaxException
     */
    public Integer getThreeDigitWeatherConditionCode() throws IOException, URISyntaxException {
        JSONObject obj = getOWMResponse();
        // the only part we're interested in, weather.id, a three digit code for various weather types
        return Integer.parseInt(obj.getJSONArray("weather").getJSONObject(0).get("id").toString());

    }

}