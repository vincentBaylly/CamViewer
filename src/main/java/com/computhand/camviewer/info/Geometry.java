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
public class Geometry {

    private String type;
    private float latitude;
    private float longitude;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {

        StringBuilder geometryValues = new StringBuilder();
        
        geometryValues.append("Geometry{");
        geometryValues.append("type= ");
        geometryValues.append(type);
        geometryValues.append(", latitude= ");
        geometryValues.append(latitude);
        geometryValues.append(", longitude=");
        geometryValues.append(longitude);
        geometryValues.append("}");

        return geometryValues.toString();
    }
}
