package com.techstars.app.weather.aware;

import java.net.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by m805958 on 8/4/15.
 */
public class CookieEater {

    public static HttpCookie getCookieUsingCookieHandler() {
        try {
            // Instantiate CookieManager;
            // make sure to set CookiePolicy
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);

            // get content from URLConnection;
            // cookies are set by web site
            URL url = new URL("http://www.macys.com");
            URLConnection connection = url.openConnection();
            connection.getContent();

            // get cookies from underlying
            // CookieStore
            CookieStore cookieJar = manager.getCookieStore();
            List<HttpCookie> cookies =
                    cookieJar.getCookies();
            return cookies.get(1);
        } catch (Exception e) {
            System.out.println("Unable to get cookie using CookieHandler");
            e.printStackTrace();
        }
        return null;
    }

    public static String parseLatLongFromCookie() {
        final String location = getCookieUsingCookieHandler().toString();

        String result = "";
        Pattern p = Pattern.compile("-?\\d+\\.\\d+");
        Matcher m = p.matcher(location);
        while (m.find()) {
            result += m.group()+",";
        }
        return result.substring(0, result.length()-1);
    }

    public static String getLat() {
        String fullCoords = parseLatLongFromCookie();
        return fullCoords.substring(0, fullCoords.indexOf(","));
    }

    public static String getLong() {
        String fullCoords = parseLatLongFromCookie();
        return fullCoords.substring(fullCoords.indexOf(",")+1, fullCoords.length());
    }

}
