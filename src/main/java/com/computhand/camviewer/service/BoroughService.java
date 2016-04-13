package com.computhand.camviewer.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computhand.camviewer.info.Borough;

public class BoroughService extends ServiceCaller{
	
	private static final Logger LOG = LoggerFactory.getLogger(BoroughService.class);

    /**
     * Get the position of the light to add it on the map.
     *
     * @return JSONArray
     */
    public List<Borough> getLight() {

        JSONParser parser = new JSONParser();

        StringBuilder json = callService("boroughURL");

        List<Borough> boroughs = new ArrayList<Borough>();

        Object limAdminInfo;
        try {
        	limAdminInfo = parser.parse(json.toString());
            JSONObject limAdminJSON = (JSONObject) limAdminInfo;

            JSONArray features = (JSONArray) limAdminJSON.get("features");
            LOG.debug(features.toString());
            
            for(Object feature : features){
            	
            	JSONObject boroughJson = (JSONObject) feature;
            	
            	Borough borough = new Borough();
            	
                JSONObject geometryValue = (JSONObject) boroughJson.get("geometry");
                LOG.debug(geometryValue.toString());
                
                JSONArray coordinates = (JSONArray)geometryValue.get("coordinates");
                borough.setCoordinates(new ArrayList<float[]>());
                for(Object coordinate: coordinates){
                	JSONArray coordinateJson = (JSONArray) coordinate;
                	float[] coordinateValue = {(Float) coordinateJson.get(0), (Float)coordinateJson.get(1)}; 
                	borough.getCoordinates().add(coordinateValue);
                }

                JSONObject propertiesValue = (JSONObject) boroughJson.get("properties");
                LOG.debug(propertiesValue.toString());
                borough.setNom((String) propertiesValue.get("NOM"));
                borough.setType((String) propertiesValue.get("TYPE"));
                borough.setCodeId((Integer) propertiesValue.get("CODEID"));
                borough.setAbrev((String) propertiesValue.get("ABREV"));
                borough.setNum((Integer) propertiesValue.get("NUM"));
                borough.setCodeMamrot((String) propertiesValue.get("CODEMAMROT"));
                borough.setAire((Float) propertiesValue.get("AIRE"));
                borough.setMunId((Integer) propertiesValue.get("MUNID"));
                borough.setPerim((Float) propertiesValue.get("PERIM"));
                
                boroughs.add(borough);
                
            }

        } catch (ParseException ex) {
            LOG.error("json parsing want wrong", ex);
        }

        return boroughs;
    }
}
