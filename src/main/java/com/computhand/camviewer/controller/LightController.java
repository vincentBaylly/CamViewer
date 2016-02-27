/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computhand.camviewer.controller;

import com.computhand.camviewer.info.Geometry;
import com.computhand.camviewer.info.Light;
import com.computhand.camviewer.info.LightProperties;
import com.computhand.camviewer.parser.OpenDataParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wallace
 */
@RestController
@RequestMapping(value = "/", produces="application/json;charset=UTF-8")
public class LightController {
    
    @Autowired
    private OpenDataParser parser;
    
    private Light light;
    
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
    
    @RequestMapping(value = "/geometry")
    public Geometry getGeometry(){
        return getLight().getGeometry();
    }
    
    @RequestMapping(value = "/properties")
    public LightProperties getLightProperties(){
        
        LightProperties lightProperties = getLight().getLightProperties();
        
        setLight(null);
        
        return lightProperties;
    }
    
}
