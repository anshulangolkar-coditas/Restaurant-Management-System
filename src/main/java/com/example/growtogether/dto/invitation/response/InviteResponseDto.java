package com.example.growtogether.dto.invitation.response;

import com.example.growtogether.constants.InvitationStatus;
import com.example.growtogether.constants.Role;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InviteResponseDto {

    private Long id;
    private String email;
    private String fullName;
    private Role roles;
    private InvitationStatus status;
    private Date sentOn;
    private Date expiresOn;

}
