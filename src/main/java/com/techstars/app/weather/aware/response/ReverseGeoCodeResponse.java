package com.techstars.app.weather.aware.response;

import com.techstars.app.weather.aware.CookieEater;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by m805958 on 8/4/15.
 *
 * Class gets Lat/Long
 *
 */
public class ReverseGeoCodeResponse extends AbstractResponse {

    private static HttpClient client = new DefaultHttpClient();

    public ReverseGeoCodeResponse(){}

    public JSONObject getGoogleMapsResponse() throws IOException, URISyntaxException {
        final URI url = new URI("https://maps.google.com/maps/api/geocode/json?latlng=" + CookieEater.parseLatLongFromCookie());
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

    public JSONObject getGoogleMapsResponse(double lat, double lon) throws IOException, URISyntaxException {
        final URI url = new URI("https://maps.google.com/maps/api/geocode/json?latlng=" + lat + "," + lon);
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

    public String getCity() throws IOException, URISyntaxException {
        // Makes array of strings from address (ex: [123 Fake St., Springfield, MA, USA, 94107]) and returns second index
        return getGoogleMapsResponse().getJSONArray("results").optJSONObject(0).get("formatted_address").toString().split(",")[1].trim();
    }

    public String getCityWithSetLatLong(double lat, double lon) throws IOException, URISyntaxException {
        return getGoogleMapsResponse(lat, lon).getJSONArray("results").optJSONObject(0).get("formatted_address").toString().split(",")[1].trim();
    }

}
