package com.proteantec.tableauwdc.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ColumnsToHide {
    private List<String> hiddenColumns;
}
