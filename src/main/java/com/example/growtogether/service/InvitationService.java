package com.example.growtogether.service;

import com.example.growtogether.dto.invitation.request.InviteRequest;
import com.example.growtogether.dto.invitation.response.InviteResponseDto;
import com.example.growtogether.entity.Users;
import jakarta.validation.Valid;

public interface InvitationService {
    InviteResponseDto sendInvite(@Valid InviteRequest request, Users user);
}
