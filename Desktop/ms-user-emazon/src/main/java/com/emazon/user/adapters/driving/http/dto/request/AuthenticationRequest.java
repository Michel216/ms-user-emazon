package com.emazon.user.adapters.driving.http.dto.request;

import com.emazon.user.domain.utils.DomainConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    @NotNull(message = DomainConstants.EMPTY_EMAIL_MESSAGE)
    @Pattern(regexp = DomainConstants.EMAIL_REGEX_RFC5322, message = DomainConstants.NOT_VALID_EMAIL_MESSAGE)
    private String email;

    @NotNull(message = DomainConstants.EMPTY_EMAIL_MESSAGE)
    @Size(min = 8, max = 64, message = DomainConstants.OUT_OF_BOUNDS_PASSWORD_MESSAGE)
    private String password;
}
