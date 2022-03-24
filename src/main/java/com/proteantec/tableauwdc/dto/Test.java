package com.proteantec.tableauwdc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Test {
    private int some;

    public Test(){}

    public Test(int some) {
        this.some = some;
    }
    public int getSome() {
        return some;
    }

    public void setSome(int some) {
        this.some = some;
    }
}
