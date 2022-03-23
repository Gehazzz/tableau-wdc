package com.proteantec.tableauwdc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueryExecution {
    private String query;
    private long executionTime;
}
