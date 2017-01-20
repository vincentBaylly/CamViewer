/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computhand.camviewer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.computhand.camviewer.info.Borough;
import com.computhand.camviewer.service.BoroughService;

/**
 *
 * @author wallace
 */
@RestController
@RequestMapping(value = "/borough", produces="application/json;charset=UTF-8")
public class BoroughController {
    
    @Autowired
    private BoroughService parser;
    
    private List<Borough> boroughs;
    
    private static final Logger LOG = LoggerFactory.getLogger(BoroughController.class);
    
    /**
     * constructor
     */
    public BoroughController(){
        LOG.info("have been scanned");
    }
    
	/**
	 * @return the boroughs
	 */
	public List<Borough> getBoroughs() {
		
		if(boroughs == null){
			boroughs = parser.getBoroughs();
        }
        
        return boroughs;
	}


	/**
	 * @param boroughs the boroughs to set
	 */
	public void setBoroughs(List<Borough> boroughs) {
		this.boroughs = boroughs;
	}

    
    /**
     * Get the all the boroughs informations.
     * 
     * @return List<Borough>
     */
    @RequestMapping(value = "/infos")
    public List<Borough> getBoroughsInfo(){
    	
    	return getBoroughs();
    }

    
}
