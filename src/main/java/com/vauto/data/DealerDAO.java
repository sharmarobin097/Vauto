package com.vauto.data;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class DealerDAO {

    public Dealer getDealer(String dataSetId, int dealerId){
        RestTemplate restTemplate = new org.springframework.web.client.RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String url = "http://vautointerview.azurewebsites.net/api/" + dataSetId + "/dealers/" + dealerId;
        Dealer dealer = restTemplate.getForObject(url, Dealer.class);
        return dealer;
    }
}
