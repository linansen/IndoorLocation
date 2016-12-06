package com.sysu.zigbee.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("domain")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "/Users/linansen/Documents/workspace/IndoorZigBee/wav_storage_file/";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
