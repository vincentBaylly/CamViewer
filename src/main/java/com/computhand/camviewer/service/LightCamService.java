/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computhand.camviewer.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.computhand.camviewer.info.Geometry;
import com.computhand.camviewer.info.Light;
import com.computhand.camviewer.info.LightProperties;
import com.computhand.camviewer.utils.CamViewerUtils;

/**
 *
 * @author wallace
 */
@Component
public class LightCamService extends ServiceCaller{

    private static final Logger LOG = LoggerFactory.getLogger(LightCamService.class);

    public LightCamService() {
        LOG.info("have been scanned");
    }

    /**
     * Get the position of the light to add it on the map.
     *
     * @return JSONArray
     */
    public Light getLight() {

        JSONParser parser = new JSONParser();

        StringBuilder json = callService("camInfoURL");

        Light light = null;

        Object trafficCamInfo;
        try {
            trafficCamInfo = parser.parse(json.toString());
            JSONObject trafficCamInfoJSON = (JSONObject) trafficCamInfo;

            JSONArray features = (JSONArray) trafficCamInfoJSON.get("features");
            LOG.debug(features.toString());

            int lightNumber = CamViewerUtils.randInt(0, 303);

            JSONObject feature = (JSONObject) features.get(lightNumber);
            LOG.debug(feature.toString());

            JSONObject geometryValue = (JSONObject) feature.get("geometry");
            LOG.debug(geometryValue.toString());

            JSONObject propertiesValue = (JSONObject) feature.get("properties");
            LOG.debug(propertiesValue.toString());

            //Populate Light Values
            light = new Light();

            light.setGeometry(populateGeometry(geometryValue));

            light.setLightProperties(populateLightProperties(propertiesValue));

        } catch (ParseException ex) {
            LOG.error("json parsing want wrong", ex);
        }

        return light;
    }

    /**
     * Populate Geometry Object.
     *
     * @param geometryValue
     * @return Geometry
     */
    private Geometry populateGeometry(JSONObject geometryValue) {

        JSONArray coordinates = (JSONArray) geometryValue.get("coordinates");
        LOG.debug("Latitude : " + coordinates.get(0).toString() + ", Longitude : " + coordinates.get(1).toString());

        Geometry geometry = new Geometry();
        geometry.setType(geometryValue.get("type").toString());
        geometry.setLatitude(Float.valueOf(coordinates.get(1).toString()));
        geometry.setLongitude(Float.valueOf(coordinates.get(0).toString()));

        return geometry;
    }

    /**
     * Populate LightProperties Object.
     *
     * @param propertiesValue
     * @return LightProperties
     */
    private LightProperties populateLightProperties(JSONObject propertiesValue) {

        LightProperties lightProperties = new LightProperties();

        try {

            lightProperties.setNumberId(Integer.valueOf(propertiesValue.get("nid").toString()));

//            lightProperties.setMontrealLightSite(new URL(propertiesValue.get("url").toString()));

            lightProperties.setTitle(propertiesValue.get("titre").toString());
            lightProperties.setCameraId(Integer.valueOf(propertiesValue.get("id-camera").toString()));
            lightProperties.setBoroughNumber(Integer.valueOf(propertiesValue.get("id-arrondissement").toString()));
            //lightProperties.setDescription(propertiesValue.get("description").toString());
            lightProperties.setNorthSouthStreet(propertiesValue.get("axe-routier-nord-sud").toString());
            lightProperties.setEastWestStreet(propertiesValue.get("axe-routier-est-ouest").toString());
            lightProperties.setUrlImageLive(new URL(propertiesValue.get("url-image-en-direct").toString()));
            lightProperties.setUrlImageNorth(new URL(propertiesValue.get("url-image-direction-nord").toString()));
            lightProperties.setUrlImageEast(new URL(propertiesValue.get("url-image-direction-est").toString()));
            lightProperties.setUrlImageSouth(new URL(propertiesValue.get("url-image-direction-sud").toString()));
            lightProperties.setUrlImageWest(new URL(propertiesValue.get("url-image-direction-ouest").toString()));

        } catch (MalformedURLException ex) {
            LOG.error("an error occured during populate Light Properties", ex);
        } catch (Exception ex) {
            LOG.error("an error occured during populate Light Properties", ex);
        }

        return lightProperties;

    }
}
