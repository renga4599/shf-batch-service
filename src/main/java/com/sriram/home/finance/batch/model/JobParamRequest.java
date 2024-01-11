package com.sriram.home.finance.batch.model;

import lombok.Data;

@Data
public class JobParamRequest {
    private String key;
    private String paramValue;
    private Class paramClassType = String.class;
}
