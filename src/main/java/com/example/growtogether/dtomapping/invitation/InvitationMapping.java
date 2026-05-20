package com.example.growtogether.dtomapping.invitation;

import com.example.growtogether.dto.invitation.response.InviteResponseDto;
import com.example.growtogether.entity.Invitation;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvitationMapping {

    public InviteResponseDto invitationResponseDto(Invitation invitation){

        return InviteResponseDto.builder()
                .id(invitation.getInvitationId())
                .email(invitation.getEmailId())
                .fullName(invitation.getFullName())
                .roles(invitation.getRole())
                .status(invitation.getStatus())
                .sentOn(invitation.getSentOn())
                .expiresOn(invitation.getExpiresOn())
                .build();

    }

    public List<InviteResponseDto> invitationResponseDto(Page<Invitation> invitationList){

        return invitationList.stream()
                .map(this::invitationResponseDto).toList();

    }




}
