package com.vauto.data;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class AnswerDAO {

    public ResponseEntity saveAnswer(String dataSetId, AnswerDTO answerDTO){

        RestTemplate restTemplate = new org.springframework.web.client.RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String url = "http://vautointerview.azurewebsites.net/api/" + dataSetId + "/answer";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AnswerDTO> entity = new HttpEntity<AnswerDTO>(answerDTO,headers);
        ResponseEntity responseEntity = restTemplate .exchange(url, HttpMethod.POST, entity, String.class);
        return responseEntity;
    }
}
