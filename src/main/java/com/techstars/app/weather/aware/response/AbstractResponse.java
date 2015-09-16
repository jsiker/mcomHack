package com.techstars.app.weather.aware.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by m805958 on 8/4/15.
 */
public abstract class AbstractResponse {

    private static InputStream in;

    protected String getAPIKey(String propName) throws IOException {
        Properties properties = new Properties();
        in = getClass().getResourceAsStream("/secret.properties");
        properties.load(in);
        final String API_KEY = properties.getProperty(propName);
        in.close();
        return API_KEY;
    }

    protected static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
