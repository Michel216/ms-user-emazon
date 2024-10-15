package com.emazon.user.domain.exceptions;

import com.emazon.user.domain.utils.DomainConstants;

public class UnderAgeException extends RuntimeException {
    public UnderAgeException() {
        super(DomainConstants.UNDER_AGE_MESSAGE);
    }
}
