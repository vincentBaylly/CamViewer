
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wallace
 */
public class OpenDataParserTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(OpenDataParserTest.class);
    
    public static void main(String[] args){
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

        LOG.info(output.toString());
    }
}
