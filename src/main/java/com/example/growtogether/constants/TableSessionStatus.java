package com.example.growtogether.constants;

import com.example.growtogether.exception.InvalidRoleException;
import com.example.growtogether.exception.InvalidTableSessionStatusException;

public enum TableSessionStatus {

    OPEN,
    BILLED,
    CLOSED;

    public static TableSessionStatus toValue(String status){

        for(TableSessionStatus tableStatus : TableSessionStatus.values()){
            if(tableStatus.name().equalsIgnoreCase(status)){
                return tableStatus;
            }
        }
        throw new InvalidTableSessionStatusException(ExceptionMessages.INVALID_TABLE_SESSION_STATUS);
    }

}
