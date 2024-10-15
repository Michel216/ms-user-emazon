package com.emazon.user.adapters.driving.http.dto.request;

import com.emazon.user.domain.utils.DomainConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    @NotNull(message = DomainConstants.EMPTY_NAME_MESSAGE)
    @Size(min = 2, max = 64, message = DomainConstants.OUT_OF_BOUNDS_NAME_MESSAGE)
    private String name;

    @NotNull(message = DomainConstants.EMPTY_LASTNAME_MESSAGE)
    @Size(min = 2, max = 64, message = DomainConstants.OUT_OF_BOUNDS_LASTNAME_MESSAGE)
    private String lastname;

    @NotNull(message = DomainConstants.EMPTY_IDENTITY_DOCUMENT_MESSAGE)
    @Pattern(regexp = DomainConstants.IDENTITY_DOCUMENT_REGEX, message = DomainConstants.NOT_NUMERIC_IDENTITY_DOCUMENT_MESSAGE)
    @Size(min = 6, max = 16, message = DomainConstants.OUT_OF_BOUNDS_IDENTITY_DOCUMENT_MESSAGE)
    private String identityDocument;

    @Past(message = DomainConstants.FUTURE_BIRTH_DATE_MESSAGE)
    @NotNull(message = DomainConstants.EMPTY_BIRTH_DATE_MESSAGE)
    private LocalDateTime birthdate;

    @NotNull(message = DomainConstants.EMPTY_PHONE_NUMBER_MESSAGE)
    @Pattern(regexp = DomainConstants.PHONE_NUMBER_REGEX, message = DomainConstants.NOT_VALID_PHONE_NUMBER_MESSAGE)
    @Size(min = 10, max = 13, message = DomainConstants.OUT_OF_BOUNDS_PHONE_NUMBER_MESSAGE)
    private String phone;

    @NotNull(message = DomainConstants.EMPTY_EMAIL_MESSAGE)
    @Pattern(regexp = DomainConstants.EMAIL_REGEX_RFC5322, message = DomainConstants.NOT_VALID_EMAIL_MESSAGE)
    @Size(min = 8, max = 128, message = DomainConstants.OUT_OF_BOUNDS_EMAIL_MESSAGE)
    private String email;

    @NotNull(message = DomainConstants.EMPTY_PASSWORD_MESSAGE)
    @Size(min = 8, max = 64, message = DomainConstants.OUT_OF_BOUNDS_PASSWORD_MESSAGE)
    private String password;
}
