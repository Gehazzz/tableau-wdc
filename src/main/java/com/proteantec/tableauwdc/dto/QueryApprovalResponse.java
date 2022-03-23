package com.proteantec.tableauwdc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueryApprovalResponse {
    private String query;
    private boolean isApproved;
    private String message;
}
