package com.vauto.service;

import com.vauto.data.AnswerDAO;
import com.vauto.data.AnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerDAO answerDAO;

    public ResponseEntity saveAnswer(String dataSetId, AnswerDTO answerDTO){
        return this.answerDAO.saveAnswer(dataSetId, answerDTO);
    }
}
