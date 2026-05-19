package com.example.growtogether.controller;

import com.example.growtogether.dto.invitation.request.InviteRequest;
import com.example.growtogether.dto.invitation.response.InviteResponseDto;
import com.example.growtogether.entity.Users;
import com.example.growtogether.response.ApplicationResponse;
import com.example.growtogether.service.InvitationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invitation")
@RequiredArgsConstructor
public class InvitationController {

    private final InvitationService invitationService;

    @PostMapping("/")
    public ResponseEntity<ApplicationResponse<InviteResponseDto>> sendInvite(@Valid @RequestBody InviteRequest request, @AuthenticationPrincipal Users user){

        InviteResponseDto invite = invitationService.sendInvite(request, user);

        ApplicationResponse<InviteResponseDto> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "Invite Sent Successfully",
                invite
        );

        return new ResponseEntity<>(response, HttpStatus.OK);

    }




}
