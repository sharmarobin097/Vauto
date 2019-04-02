package com.vauto.service;

import com.vauto.data.Dealer;
import com.vauto.data.DealerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealerService {

    @Autowired
    private DealerDAO dealerDAO;

    public Dealer getDealer(String dataSetId, int dealerId){
        return this.dealerDAO.getDealer(dataSetId, dealerId);
    }
}
