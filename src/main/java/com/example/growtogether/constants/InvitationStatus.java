package com.example.growtogether.constants;

import com.example.growtogether.exception.InvalidInvitationStatusException;

public enum InvitationStatus {

    PENDING,
    ACCEPTED,
    EXPIRED,
    CANCELLED;

    public static InvitationStatus toValue(String status){

        for(InvitationStatus invitationStatus : InvitationStatus.values()){
            if(invitationStatus.name().equalsIgnoreCase(status)){
                return invitationStatus;
            }
        }
        throw new InvalidInvitationStatusException(ExceptionMessages.INVALID_INVITATION_STATUS_TYPE);
    }
}
