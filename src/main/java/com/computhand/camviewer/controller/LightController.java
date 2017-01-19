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
@RequestMapping(value = "/", produces="application/json;charset=UTF-8")
public class LightController {
    
    @Autowired
    private LightCamService parser;
    
    private Light light;
    
    private List<Light> lights;
    
    private static final Logger LOG = LoggerFactory.getLogger(LightController.class);
    
    public LightController(){
        LOG.info("have been scanned");
    }
    
    
    public Light getLight() {
        if(light == null){
            light = parser.getLight();
        }
        
        return light;
    }
    
    public void setLight(Light light){
        this.light = light;
    }
    
	/**
	 * @return the lights
	 */
	public List<Light> getLights() {
		
		if(lights == null){
            lights = parser.getLights();
        }
        
        return lights;
	}


	/**
	 * @param lights the lights to set
	 */
	public void setLights(List<Light> lights) {
		this.lights = lights;
	}
    
    @RequestMapping(value = "/geometry")
    public Geometry getGeometry(){
        return getLight().getGeometry();
    }
    
    @RequestMapping(value = "/geometries")
    public List<Geometry> getGeometries(){
    	
    	List<Geometry> geometries = new ArrayList<Geometry>();
    	
    	for(Light light : getLights()){
    		//if(light.getLightProperties().getBoroughNumber() == 1){
    			geometries.add(light.getGeometry());
    		//}
    	}
    	
        return geometries;
    }
    
    @RequestMapping(value = "/lightsinfo")
    public List<Light> getLightsInfo(){
    	
    	return getLights();
    }
    
    @RequestMapping(value = "/properties")
    public LightProperties getLightProperties(){
        
        LightProperties lightProperties = getLight().getLightProperties();
        
        setLight(null);
        
        return lightProperties;
    }
    
}
