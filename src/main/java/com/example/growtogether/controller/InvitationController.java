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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity<ApplicationResponse<List<InviteResponseDto>>> getAllInvitations(@RequestParam int page){

        List<InviteResponseDto> invitationsList = invitationService.getAllInvitations(page);

        ApplicationResponse<List<InviteResponseDto>> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "Fetched all invitations successfully",
                invitationsList
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }




}
