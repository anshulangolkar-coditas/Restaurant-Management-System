package com.example.growtogether.service;

import com.example.growtogether.constants.ExceptionMessages;
import com.example.growtogether.constants.InvitationStatus;
import com.example.growtogether.constants.Role;
import com.example.growtogether.dto.invitation.request.InviteRequest;
import com.example.growtogether.dto.invitation.response.InviteResponseDto;
import com.example.growtogether.dtomapping.invitation.InvitationMapping;
import com.example.growtogether.entity.Invitation;
import com.example.growtogether.entity.Owner;
import com.example.growtogether.entity.Users;
import com.example.growtogether.exception.InvitationAlreadyAcceptedException;
import com.example.growtogether.exception.InvitationExpiredException;
import com.example.growtogether.exception.InvitationStillValidException;
import com.example.growtogether.repository.InvitationRepository;
import com.example.growtogether.repository.OwnerRepository;
import com.example.growtogether.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

@Service
@Transactional
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService{

    private final InvitationRepository invitationRepository;
    private final OwnerRepository ownerRepository;
    private final StaffRepository staffRepository;
    private final EmailService emailService;
    private final InvitationMapping invitationMapping;


    @Override
    public InviteResponseDto sendInvite(InviteRequest request, Users user) {

        if(invitationRepository.existsByEmailId(request.getEmail())){
            Invitation inv = invitationRepository.findByEmailId(request.getEmail());
            if(inv.getStatus().equals(InvitationStatus.PENDING)){
                throw new InvitationStillValidException(ExceptionMessages.INVITATION_VALID);
            }
            if(inv.getStatus().equals(InvitationStatus.ACCEPTED)){
                throw new InvitationAlreadyAcceptedException(ExceptionMessages.INVITATION_ACCEPTED);
            }
            if(inv.getStatus().equals(InvitationStatus.EXPIRED)){
                  throw new InvitationExpiredException(ExceptionMessages.INVITATION_EXPIRED);
            }
        }


        String uniqueKey = emailService.sendInvitation(request.getEmail());

        Invitation invitation = Invitation.builder()
                .emailId(request.getEmail())
                .fullName(request.getFullName())
                .role(Role.toValue(request.getRole()))
                .uniqueKey(uniqueKey)
                .sentBy(user)
                .build();

        Invitation savedInvitation =  invitationRepository.save(invitation);

        return invitationMapping.invitationResponseDto(savedInvitation);

    }
}
