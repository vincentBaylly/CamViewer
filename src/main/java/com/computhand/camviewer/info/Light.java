/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computhand.camviewer.info;

/**
 *
 * @author wallace
 */
public class Light {
    
    private LightProperties lightProperties;
    private Geometry geometry;

    public LightProperties getLightProperties() {
        return lightProperties;
    }

    public void setLightProperties(LightProperties lightProperties) {
        this.lightProperties = lightProperties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
    
}
