package com.vauto.service;

import com.vauto.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VehicleService {

    @Autowired
    private VehicleDAO vehicleDAO;

    public ID getAllVehicleIdsForDataSet(DataSet dataSet){
        return this.vehicleDAO.getAllVehicleIdsForDataSet(dataSet);
    }

    public List<Vehicle> getAllVehicles(DataSet dataSet){
        return vehicleDAO.getAllVehicles(dataSet);
    }
}
