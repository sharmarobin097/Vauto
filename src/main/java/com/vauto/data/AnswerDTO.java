package com.vauto.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerDTO implements Serializable {

    private DealerDTO[] dealers;

    public DealerDTO[] getDealers() {
        return dealers;
    }

    public void setDealers(DealerDTO[] dealers) {
        this.dealers = dealers;
    }
}
