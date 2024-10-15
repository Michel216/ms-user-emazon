package com.emazon.user.adapters.driving.http.dto.request;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationRequest {
    private String token;
}
