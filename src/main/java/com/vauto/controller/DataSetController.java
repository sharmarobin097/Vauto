package com.vauto.controller;

import com.vauto.data.*;
import com.vauto.service.AnswerService;
import com.vauto.service.DataSetService;
import com.vauto.service.DealerService;
import com.vauto.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@RequestMapping(value = "/")
public class DataSetController {

    @Autowired
    private DataSetService dataSetService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DealerService dealerService;

    @Autowired
    private AnswerService answerService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String getDataSet(Model model) {

        //get data set
        DataSet dataSet = this.dataSetService.getDataSet();
        String dataSetId = dataSet.getDatasetId();

        //set data set as an attribute for the view
        model.addAttribute("dataSet", dataSet);

        //call create answer to create DTO for answer and post the answer
        ResponseEntity responseEntity = this.answerService.saveAnswer(dataSetId, createAnswer(dataSet));

        //save the response entity as an attribute for the view
        model.addAttribute("responseEntity", responseEntity);

        //return template to view
        return "dataset";
    }

    // this method creates answer DTO
    public AnswerDTO createAnswer(DataSet dataSet) {

        List<Vehicle> vehicleList = this.vehicleService.getAllVehicles(dataSet);

        Set<Dealer> dealerSet;
        List<DealerDTO> dealerDTOList = new CopyOnWriteArrayList<>();
        AnswerDTO answerDTO = new AnswerDTO();

        /* Since there is no concurrent set, use a concurrent hashmap and extract set out of it so that parallel stream can
        *  be safely used add dealers to a set. Parallel stream improved speed by a factor of 5.
        */

        Map<Dealer, Dealer> dealerMap = new ConcurrentHashMap<>();
        vehicleList.parallelStream().forEach(
                (vehicle) -> {
                    Dealer dealer = this.dealerService.getDealer(dataSet.getDatasetId(), vehicle.getDealerId());
                    dealerMap.put(dealer, dealer);
                }
        );

        // For each dealer in dealer set find associated vehicles, add vehicle DTOs to dealer DTOs and finally dealer DTOs to Answer DTO
        dealerSet = dealerMap.keySet();
        dealerSet.parallelStream().forEach(
                (dealer) ->{
                    List<VehicleDTO> vehicleDTOList = new CopyOnWriteArrayList<>();
                    vehicleList.forEach(
                            (vehicle) ->{
                                if(vehicle.getDealerId() == dealer.getDealerId()){
                                    VehicleDTO vehicleDTO = new VehicleDTO();
                                    vehicleDTO.setMake(vehicle.getMake());
                                    vehicleDTO.setModel(vehicle.getModel());
                                    vehicleDTO.setVehicleId(vehicle.getVehicleId());
                                    vehicleDTO.setYear(vehicle.getYear());
                                    vehicleDTOList.add(vehicleDTO);
                                }
                            }
                    );
                    DealerDTO dealerDTO = new DealerDTO();
                    dealerDTO.setDealerId(dealer.getDealerId());
                    dealerDTO.setName(dealer.getName());
                    dealerDTO.setVehicles(vehicleDTOList.toArray(new VehicleDTO[vehicleDTOList.size()]));
                    dealerDTOList.add(dealerDTO);
                }
        );

        answerDTO.setDealers(dealerDTOList.toArray(new DealerDTO[0]));

        return answerDTO;
    }

}
