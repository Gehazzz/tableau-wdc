package com.proteantec.tableauwdc.service;

import com.proteantec.tableauwdc.dto.QueryApprovalResponse;
import com.proteantec.tableauwdc.dto.QueryRequestForApproval;

public interface QueryValidationService {
    QueryApprovalResponse validate(QueryRequestForApproval queryRequestForApproval);
}
