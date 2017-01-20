/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computhand.camviewer.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.computhand.camviewer.info.Geometry;
import com.computhand.camviewer.info.Light;
import com.computhand.camviewer.info.LightProperties;
import com.computhand.camviewer.service.LightCamService;

/**
 *
 * @author wallace
 */
@RestController
@RequestMapping(value = "/light", produces="application/json;charset=UTF-8")
public class LightController {
    
    @Autowired
    private LightCamService parser;
    
    private List<Light> lights;
    
    private static final Logger LOG = LoggerFactory.getLogger(LightController.class);
    
    /**
     * constructor
     */
    public LightController(){
        LOG.info("have been scanned");
    }
    
    /**
     * Get the light with the numberId pass in parameter.
     * 
     * @param lightId
     * @return |Light the retrieved light
     */
    public Light getLight(int lightId) {
    	
    	Light lightRetrieved = null;
    	
    	for(Light light : getLights()){
    		if(light.getLightProperties().getNumberId() == lightId){
    			lightRetrieved = light;
    			break;
    		}
    	}
        
        return lightRetrieved;
    }
    
	/**
	 * Retrieve the list of all lights.
	 * 
	 * @return the lights
	 */
	public List<Light> getLights() {
		
		if(lights == null){
            lights = parser.getLights();
        }
        
        return lights;
	}


	/**
	 * Set the list of lights.
	 * 
	 * @param lights the lights to set
	 */
	public void setLights(List<Light> lights) {
		this.lights = lights;
	}
    
	/**
	 * Get the position of the light with the numberId pass in parameter.
	 * 
	 * @param lightId
	 * @return Geometry the geometry of the light
	 */
    @RequestMapping(value = "/geometry")
    public Geometry getGeometry(@PathVariable int lightId){
        return getLight(lightId).getGeometry();
    }
    
    /**
     * Get the positions of all lights.
     * 
     * @return List<Geometry> the position list of all lights
     */
    @RequestMapping(value = "/geometries")
    public List<Geometry> getGeometries(){
    	
    	List<Geometry> geometries = new ArrayList<Geometry>();
    	
    	for(Light light : getLights()){
    		geometries.add(light.getGeometry());
    	}
    	
        return geometries;
    }
    
    /**
     * Get the all the lights informations.
     * 
     * @return List<Light>
     */
    @RequestMapping(value = "/infos")
    public List<Light> getLightsInfo(){
    	
    	return getLights();
    }
    
    /**
     * Get the properties of the light with the numberId pass in parameter.
     * 
     * @param lightId
     * @return LightProperties the light properties
     */
    @RequestMapping(value = "/properties")
    public LightProperties getLightProperties(@PathVariable int lightId){
        
        LightProperties lightProperties = getLight(lightId).getLightProperties();
        
        return lightProperties;
    }
    
}
