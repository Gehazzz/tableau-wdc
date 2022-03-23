package com.proteantec.tableauwdc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueryRequestForApproval {
    private String query;
}
