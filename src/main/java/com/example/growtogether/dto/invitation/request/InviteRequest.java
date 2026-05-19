package com.example.growtogether.dto.invitation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InviteRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    private String role;

}
