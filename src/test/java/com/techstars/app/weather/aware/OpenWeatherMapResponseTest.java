package com.techstars.app.weather.aware;

import com.techstars.app.weather.aware.response.OpenWeatherMapResponse;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by m805958 on 7/22/15.
 *
 */
public class OpenWeatherMapResponseTest {

    private static OpenWeatherMapResponse openWeatherMapResponse;

    @Before
    public void setUp() {
        openWeatherMapResponse = new OpenWeatherMapResponse();
    }

    @Test
    public void testGetConditions() throws Exception {
        JSONObject object = openWeatherMapResponse.getOWMResponse();
        assertNotNull(object);
    }

    @Test
    public void testThreeCodeConditionID() throws Exception {
        assertTrue(openWeatherMapResponse.getThreeDigitWeatherConditionCode() > 199);
        assertTrue(openWeatherMapResponse.getThreeDigitWeatherConditionCode() < 907);
        assertTrue(String.valueOf(openWeatherMapResponse.getThreeDigitWeatherConditionCode()).length() == 3);
    }
}