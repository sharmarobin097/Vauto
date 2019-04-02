package com.vauto.data;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class DataSetDAO {

    public DataSet getDataSet() {
        RestTemplate restTemplate =  new org.springframework.web.client.RestTemplate(new HttpComponentsClientHttpRequestFactory());
        DataSet dataSet = restTemplate.getForObject("http://vautointerview.azurewebsites.net/api/datasetId", DataSet.class);
        return dataSet;
    }
}
