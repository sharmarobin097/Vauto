package com.vauto.data;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class VehicleDAO {

    public List<Vehicle> getAllVehicles(DataSet dataSet){
        List<Vehicle> vehicleList = new ArrayList<>();
        ID ids = getAllVehicleIdsForDataSet(dataSet);

        //List<Integer> listOfVehicleIds = new ArrayList<>();
        // Use thread safe implementation of list instead of array list so that using parallel stream to add to the list will be okay
        List<Integer> listOfVehicleIds = new CopyOnWriteArrayList<>();

        // API returns vehicle ids in an array format. Convert array to List since Arrays.asList does not work with int[]
        for (int i : ids.getVehicleIds())
        {
            listOfVehicleIds.add(i);
        }

        //using Java 8 for each on parallel stream improves speed by a factor of 5 compared to using Java 7 for-each to make API calls
        listOfVehicleIds.parallelStream().forEach(
                ( id ) ->{
                    RestTemplate restTemplate = new org.springframework.web.client.RestTemplate(new HttpComponentsClientHttpRequestFactory());
                    String url = "http://vautointerview.azurewebsites.net/api/" + dataSet.getDatasetId() + "/vehicles/" + id;
                    Vehicle vehicle = restTemplate.getForObject(url, Vehicle.class);
                    vehicleList.add(vehicle);
                }
        );

        /* for(Integer id: ids.getVehicleIds()){
            RestTemplate restTemplate = new org.springframework.web.client.RestTemplate(new HttpComponentsClientHttpRequestFactory());
            String url = "http://vautointerview.azurewebsites.net/api/" + dataSet.getDatasetId() + "/vehicles/" + id;
            Vehicle vehicle = restTemplate.getForObject(url, Vehicle.class);
            vehicleList.add(vehicle);
        } */
        return vehicleList;
    }

    public ID getAllVehicleIdsForDataSet(DataSet dataSet){
        RestTemplate restTemplate = new org.springframework.web.client.RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String url = "http://vautointerview.azurewebsites.net/api/" + dataSet.getDatasetId()+ "/vehicles";
        ID id = restTemplate.getForObject(url, ID.class);
        return id;
    }
}
