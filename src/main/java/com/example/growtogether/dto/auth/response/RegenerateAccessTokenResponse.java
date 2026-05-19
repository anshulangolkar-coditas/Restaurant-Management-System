package com.example.growtogether.dto.auth.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegenerateAccessTokenResponse {

    private String accessToken;
    private String refreshToken;

}
