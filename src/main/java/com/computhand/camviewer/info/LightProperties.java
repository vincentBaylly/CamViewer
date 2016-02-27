/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computhand.camviewer.info;

import java.net.URL;

/**
 *
 * @author wallace
 */
public class LightProperties {

    private int numberId;
    private URL montrealLightSite;
    private String title;
    private int cameraId;
    private int boroughNumber;
    private String description;
    private String northSouthStreet;
    private String eastWestStreet;
    private URL urlImageLive;
    private URL urlImageNorth;
    private URL urlImageEast;
    private URL urlImageSouth;
    private URL urlImageWest;

    public int getNumberId() {
        return numberId;
    }

    public void setNumberId(int numberId) {
        this.numberId = numberId;
    }

    public URL getMontrealLightSite() {
        return montrealLightSite;
    }

    public void setMontrealLightSite(URL montrealLightSite) {
        this.montrealLightSite = montrealLightSite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public int getBoroughNumber() {
        return boroughNumber;
    }

    public void setBoroughNumber(int boroughNumber) {
        this.boroughNumber = boroughNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNorthSouthStreet() {
        return northSouthStreet;
    }

    public void setNorthSouthStreet(String northSouthStreet) {
        this.northSouthStreet = northSouthStreet;
    }

    public String getEastWestStreet() {
        return eastWestStreet;
    }

    public void setEastWestStreet(String eastWestStreet) {
        this.eastWestStreet = eastWestStreet;
    }

    public URL getUrlImageLive() {
        return urlImageLive;
    }

    public void setUrlImageLive(URL urlImageLive) {
        this.urlImageLive = urlImageLive;
    }

    public URL getUrlImageNorth() {
        return urlImageNorth;
    }

    public void setUrlImageNorth(URL urlImageNorth) {
        this.urlImageNorth = urlImageNorth;
    }

    public URL getUrlImageEast() {
        return urlImageEast;
    }

    public void setUrlImageEast(URL urlImageEast) {
        this.urlImageEast = urlImageEast;
    }

    public URL getUrlImageSouth() {
        return urlImageSouth;
    }

    public void setUrlImageSouth(URL urlImageSouth) {
        this.urlImageSouth = urlImageSouth;
    }

    public URL getUrlImageWest() {
        return urlImageWest;
    }

    public void setUrlImageWest(URL urlImageWest) {
        this.urlImageWest = urlImageWest;
    }

    @Override
    public String toString() {

        StringBuilder lightPropertiesValues = new StringBuilder();
        
        lightPropertiesValues.append("LightProperties{");
        lightPropertiesValues.append("numberId=");
        lightPropertiesValues.append(numberId);
        lightPropertiesValues.append(", montrealLightSite=");
        lightPropertiesValues.append(montrealLightSite);
        lightPropertiesValues.append(", title=");
        lightPropertiesValues.append(title);
        lightPropertiesValues.append(", cameraId=");
        lightPropertiesValues.append(cameraId);
        lightPropertiesValues.append(", boroughNumber=");
        lightPropertiesValues.append(boroughNumber);
        lightPropertiesValues.append(", description=");
        lightPropertiesValues.append(description);
        lightPropertiesValues.append(", northSouthStreet=");
        lightPropertiesValues.append(northSouthStreet);
        lightPropertiesValues.append(", eastWestStreet=");
        lightPropertiesValues.append(eastWestStreet);
        lightPropertiesValues.append(", urlImageLive=");
        lightPropertiesValues.append(urlImageLive);
        lightPropertiesValues.append(", urlImageNorth=");
        lightPropertiesValues.append(urlImageNorth);
        lightPropertiesValues.append(", urlImageEast=");
        lightPropertiesValues.append(urlImageEast);
        lightPropertiesValues.append(", urlImageSouth=");
        lightPropertiesValues.append(urlImageSouth);
        lightPropertiesValues.append(", urlImageWest=");
        lightPropertiesValues.append(urlImageWest);
        lightPropertiesValues.append("}");
        
        return lightPropertiesValues.toString();
    }

}
