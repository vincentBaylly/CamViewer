/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computhand.camviewer.controller;

import com.computhand.camviewer.parser.OpenDataParser;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author wallace
 */
@Controller
@RequestMapping(value = "/data", produces="application/json;charset=UTF-8")
public class LightController {
    
    @Autowired
    private OpenDataParser parser;
    
    private static final Logger LOG = LoggerFactory.getLogger(LightController.class);
    
    public LightController(){
        LOG.info("have been scanned");
    }
    
    @RequestMapping(value = "/light")
    @ResponseBody
    public String getLightPosition() {
        
        String ligther = parser.getLightPosition().toJSONString();
        
        return ligther;
    }
    
}
