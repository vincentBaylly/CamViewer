package com.computhand.camviewer.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesLoader {

    private static final Logger LOG = LoggerFactory
            .getLogger(PropertiesLoader.class);

    private static final String PROP_FILE = "/home/dev/git/CamViewer/src/main/resources/serviceinfo.properties";

    private Properties prop = new Properties();
    private InputStream input;

    public PropertiesLoader() {

        try {
            input = new FileInputStream(PROP_FILE);

        } catch (FileNotFoundException e) {
            LOG.error("Properties file not Found", e);
        }

    }

    public String readInPropertiesFile(String propertiesName) {
    	
    	String propertiesValue = "";
    	
        try {

            // load a properties file
            prop.load(input);
            propertiesValue = (String) prop.get(propertiesName);
            

        } catch (IOException ex) {
            LOG.error("Can not read the properties file", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.error("Can not close the properties file after reading", e);
                }
            }
        }
        
        return propertiesValue;
    }

}
