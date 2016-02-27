/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computhand.camviewer.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author wallace
 */
@Component
public class OpenDataParser {

    private static final Logger LOG = LoggerFactory.getLogger(OpenDataParser.class);

    public OpenDataParser() {
        LOG.info("have been scanned");
    }

    /**
     * Call the camera information service for Montreal open data.
     *
     * @return StringBuilder the JSON service output.
     */
    public static StringBuilder callService() throws RuntimeException {

        //TODO add to properties file
        String camInfoURL = "http://ville.montreal.qc.ca/circulation/sites/ville.montreal.qc.ca.circulation/files/cameras-de-circulation.json";

        StringBuilder output = null;

        try {

            URL url = new URL(camInfoURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            output = new StringBuilder();
            String out;
            LOG.debug("Output from Server .... \n");
            while ((out = br.readLine()) != null) {
                output.append(out);
                LOG.debug(out);
            }

            conn.disconnect();

        } catch (MalformedURLException ex) {

            LOG.error("the url to call is not valid : " + camInfoURL, ex);

        } catch (IOException ex) {

            LOG.error("json reading want wrong", ex);

        }

        return output;

    }

    /**
     * Get the position of the light to add it on the map.
     *
     * @return JSONArray
     */
    public JSONObject getLightPosition() {

        JSONParser parser = new JSONParser();

        StringBuilder json = callService();

        JSONObject geometry = null;

        Object trafficCamInfo;
        try {
            trafficCamInfo = parser.parse(json.toString());
            JSONObject trafficCamInfoJSON = (JSONObject) trafficCamInfo;

            //LOG.debug(trafficCamInfoJSON.get("features").toString());
            JSONArray features = (JSONArray) trafficCamInfoJSON.get("features");

            //LOG.debug(features.get(1).toString());        
            JSONObject feature1 = (JSONObject) features.get(randInt(0, 1000));

            LOG.debug(feature1.get("geometry").toString());
            geometry = (JSONObject) feature1.get("geometry");

            LOG.debug(geometry.get("coordinates").toString());
            JSONArray coordinates = (JSONArray) geometry.get("coordinates");

            LOG.debug("Latitude : " + coordinates.get(0).toString() + ", Longitude : " + coordinates.get(1).toString());

        } catch (ParseException ex) {
            LOG.error("json parsing want wrong", ex);
        }

        return geometry;
    }

    /**
     * Returns a psuedo-random number between min and max, inclusive. The
     * difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimim value
     * @param max Maximim value. Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
