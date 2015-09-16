package com.techstars.app.weather.aware;

import com.techstars.app.weather.aware.response.ReverseGeoCodeResponse;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by m805958 on 8/4/15.
 */
public class ReverseGeoCodeResponseTest {

    private ReverseGeoCodeResponse reverseGeoCodeResponse;
    private JSONObject object;

    @Before
    public void setUp() throws Exception {
        reverseGeoCodeResponse = new ReverseGeoCodeResponse();
        object = reverseGeoCodeResponse.getGoogleMapsResponse();
    }

    // Return 200 from google geocode
    @Test
    public void googleApiResponseWorks() throws IOException, URISyntaxException {

        System.out.println(object);
        assertFalse(object.getJSONArray("results").isEmpty());
    }

    // Return expected lat & long
    @Test
    public void googleApiResponseLocationSameAsMine() throws IOException, URISyntaxException {
        System.out.println(CookieEater.getCookieUsingCookieHandler());
        assertTrue(object.getJSONArray("results").toString().contains(CookieEater.getLat().substring(0, 4))
                && object.getJSONArray("results").toString().contains(CookieEater.getLong().substring(0, 4)));
    }

    @Test
    public void googleApiResponseCityIsSameAsMine() throws IOException, URISyntaxException {
        assertEquals("San Francisco", reverseGeoCodeResponse.getCity());
    }

    @Test
    public void testShouldBeMKE() throws IOException, URISyntaxException {
        assertEquals("Milwaukee", reverseGeoCodeResponse.getCityWithSetLatLong(43.0500, -87.9500));
    }
}