package com.vauto.service;

import com.vauto.data.DataSet;
import com.vauto.data.DataSetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSetService {

    @Autowired
    private DataSetDAO dataSetDAO;

    public DataSet getDataSet(){
        return dataSetDAO.getDataSet();
    }
}
