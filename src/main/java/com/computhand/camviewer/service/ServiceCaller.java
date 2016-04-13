package com.computhand.camviewer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computhand.camviewer.properties.PropertiesLoader;

public abstract class ServiceCaller {
	
	private static final Logger LOG = LoggerFactory.getLogger(BoroughService.class);
	
    /**
     * Call the borough finder service.
     *
     * @return StringBuilder the JSON service output.
     */
    public static StringBuilder callService(String serviceUrl) throws RuntimeException {

    	PropertiesLoader propertiesLoader = new PropertiesLoader();
        String camInfoURL = propertiesLoader.readInPropertiesFile(serviceUrl);

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
	
}
