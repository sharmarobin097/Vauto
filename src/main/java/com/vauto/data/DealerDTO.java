package com.vauto.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DealerDTO implements Serializable {

    private int dealerId;
    private String name;
    private VehicleDTO[] vehicles;

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleDTO[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(VehicleDTO[] vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public int hashCode(){
        return this.getDealerId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DealerDTO other = (DealerDTO) obj;
        if (dealerId != other.dealerId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Dealer : " + dealerId;
    }
}
