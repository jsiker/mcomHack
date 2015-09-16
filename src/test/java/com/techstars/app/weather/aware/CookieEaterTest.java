package com.techstars.app.weather.aware;

import com.techstars.app.weather.aware.response.ReverseGeoCodeResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.*;

/**
 * Created by m805958 on 8/4/15.
 */
public class CookieEaterTest {

    @Mock
    CookieEater mockedCookieEater;

    private CookieEater cookieEater;
    private ReverseGeoCodeResponse geoCode;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cookieEater = new CookieEater();
        geoCode = new ReverseGeoCodeResponse();
    }

    @Test
    public void testGetLocationCookieUsingCookieHandler() throws Exception {
        assertTrue(cookieEater.getCookieUsingCookieHandler().toString().contains("MISCGCs"));
    }

    @Test
    public void testParseLatLongCorrectly() throws Exception {
        assertNotNull(cookieEater.parseLatLongFromCookie());
        // FIXME: for sanity testing purposes only
        assertEquals(cookieEater.parseLatLongFromCookie(), "37.7795,-122.41953");
    }

    @Test
    public void testGetLat() {
        assertNotNull(cookieEater.getLat());
    }

    @Test
    public void testGetLong() {
        assertNotNull(cookieEater.getLong());
    }

}