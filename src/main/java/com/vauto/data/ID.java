package com.vauto.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ID implements Serializable {

    private int[] vehicleIds;

    public int[] getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(int[] vehicleIds) {
        this.vehicleIds = vehicleIds;
    }
}
