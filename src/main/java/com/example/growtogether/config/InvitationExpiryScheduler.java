package com.example.growtogether.config;

import com.example.growtogether.constants.InvitationStatus;
import com.example.growtogether.entity.Invitation;
import com.example.growtogether.repository.InvitationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InvitationExpiryScheduler {

    private final InvitationRepository invitationRepository;

    @Scheduled(fixedRate = 60000)
    public void expireInvitations(){

        List<Invitation> invitationList = invitationRepository
                .findByStatusAndExpiresOnBefore(InvitationStatus.PENDING, new Date());

        for (Invitation invitation : invitationList){
            invitation.setStatus(InvitationStatus.EXPIRED);
        }

        invitationRepository.saveAll(invitationList);
    }
}
