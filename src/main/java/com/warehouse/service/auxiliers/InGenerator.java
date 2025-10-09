package com.warehouse.service.auxiliers;

import java.util.List;

public class InGenerator {

    public static String genIn(List<Integer> values, int amountToGet){
        int listSize = values.size();
        StringBuilder inQuery = new StringBuilder("IN(?");

        for(int i = 0; i < listSize; i++){
            inQuery.append(",?");
        }

        inQuery.append(")");
        return inQuery.toString();
    }

}
