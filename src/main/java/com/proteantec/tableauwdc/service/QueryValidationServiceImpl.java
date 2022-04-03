package com.proteantec.tableauwdc.service;

import com.proteantec.tableauwdc.dto.QueryApprovalResponse;
import com.proteantec.tableauwdc.dto.QueryRequestForApproval;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryValidationServiceImpl implements QueryValidationService {
    @Override
    public QueryApprovalResponse validate(QueryRequestForApproval queryRequestForApproval) {

        final String VALIDATE_TYPE = "customer_name";

        List<String> queryRequest = Arrays.stream(queryRequestForApproval.getQuery().split(" "))
                .collect(Collectors.toList());
        List<String> queryFilter = new ArrayList<>();

//                -- FIRST SOLUTION --
        for (int i = 0; i < queryRequest.size(); i++){
            if (queryRequest.get(i).equals("WHERE") || queryRequest.get(i).equals("AND"))
                queryFilter.add(queryRequest.get(i+1));
        }
        boolean isApproved = queryFilter.contains("customer_name");

//        //        -- SECOND SOLUTION --
//        int start = queryRequest.indexOf("WHERE");
//        int end = queryRequest.lastIndexOf("AND");
//        queryFilter = queryRequest.subList(start, end != -1 ? end+2 : start+2);
//        boolean isApproved = queryFilter.contains("customer_name");


        //        -- THIRD SOLUTION --
//        boolean isApproved = false;
//        Iterator<String> itr = queryRequest.iterator();
//        List<String> arr = List.of("WHERE", "AND");
//        while (itr.hasNext()) {
//            if (arr.contains(itr.next())) {
//                if (itr.next().equals(VALIDATE_TYPE)){
//                    isApproved=true;
//                    break;
//                }
//            }
//        }

        return QueryApprovalResponse.builder()
                .isApproved(isApproved)
                .query(queryRequestForApproval.getQuery())
                .message("Query execution denied because customer_name not found")
                .build();
    }
}
