package com.example.growtogether.constants;

import com.example.growtogether.exception.InvalidRoleException;

public enum Role {

    ADMIN,
    OWNER,
    MANAGER,
    STAFF_WAITER,
    STAFF_KITCHEN;

    public static Role toValue(String role){

        for(Role r : Role.values()){
            if(r.name().equalsIgnoreCase(role)){
                return r;
            }
        }
        throw new InvalidRoleException(ExceptionMessages.INVALID_ROLE);
    }

}
