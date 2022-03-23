package com.proteantec.tableauwdc.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TablesToHide {
    private List<String> hiddenTables;
}
