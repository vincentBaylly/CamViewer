package com.computhand.camviewer.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.computhand.camviewer.info.Borough;

/**
 * 
 * @author wallace
 *
 */
@Component
public class BoroughService extends ServiceCaller{
	
	private static final Logger LOG = LoggerFactory.getLogger(BoroughService.class);

    /**
     * Get the position of the light to add it on the map.
     *
     * @return JSONArray
     */
    public List<Borough> getBoroughs() {

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
                
                JSONArray firstArray = (JSONArray)geometryValue.get("coordinates");
                borough.setCoordinates(new ArrayList<double[]>());
                JSONArray secondArray = (JSONArray) firstArray.get(0);
                //JSONArray thridArray = (JSONArray) secondArray.get(0);
                JSONArray coordinateBorough = (JSONArray) secondArray.get(0);
                for(Object coordinate: coordinateBorough){
                	JSONArray coordinateJson = (JSONArray) coordinate;
                	double[] coordinateValue = {((Double)coordinateJson.get(0)).doubleValue(), ((Double)coordinateJson.get(1)).doubleValue()}; 
                	borough.getCoordinates().add(coordinateValue);
                }

                JSONObject propertiesValue = (JSONObject) boroughJson.get("properties");
                LOG.debug(propertiesValue.toString());
                borough.setNom((String) propertiesValue.get("NOM"));
                borough.setType((String) propertiesValue.get("TYPE"));
                borough.setCodeId((String) propertiesValue.get("CODEID"));
                borough.setAbrev((String) propertiesValue.get("ABREV"));
                borough.setNum(((Long)propertiesValue.get("NUM")).longValue());
                borough.setCodeMamrot((String) propertiesValue.get("CODEMAMROT"));
                borough.setAire(((Double)propertiesValue.get("AIRE")).doubleValue());
                borough.setMunId(((Long)propertiesValue.get("MUNID")).longValue());
                borough.setPerim(((Double)propertiesValue.get("PERIM")).doubleValue());
                
                boroughs.add(borough);
                
            }

        } catch (ParseException ex) {
            LOG.error("json parsing want wrong", ex);
        }

        return boroughs;
    }
}
