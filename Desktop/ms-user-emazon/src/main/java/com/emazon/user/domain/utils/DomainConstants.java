package com.emazon.user.domain.utils;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        LASTNAME,
        EMAIL,
        PHONE_NUMBER,
        BIRTH_DATE,
        IDENTITY_DOCUMENT,
        PASSWORD
    }


    public static final String USER_EMAIL_ALREADY_EXISTS_MESSAGE = "User with email %s already exists";
    public static final String USER_DOCUMENT_ALREADY_EXISTS_MESSAGE = "User with document %s already exists";

    public static final String EMPTY_FIELD_MESSAGE = "Field '%s' cannot be empty";


    public static final String UNDER_AGE_MESSAGE = "The user cannot less that 18 years old";


    public static final String EMAIL_REGEX_RFC5322 = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
                + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String PHONE_NUMBER_REGEX = "^(\\+\\d{2})?\\d{10}$";
    public static final String IDENTITY_DOCUMENT_REGEX = "^\\d{6,16}";

    public static final String WAREHOUSE_ASSISTANT_REGISTERED_MESSAGE = "Warehouse Assistant registered successfully";

    // Empty
    public static final String EMPTY_NAME_MESSAGE = "'name' field cannot be empty";
    public static final String EMPTY_LASTNAME_MESSAGE = "'lastname' field cannot be empty";
    public static final String EMPTY_EMAIL_MESSAGE = "'email' field cannot be empty";
    public static final String EMPTY_PHONE_NUMBER_MESSAGE = "'phone' field cannot be empty";
    public static final String EMPTY_BIRTH_DATE_MESSAGE = "'birthdate' field cannot be empty";
    public static final String EMPTY_IDENTITY_DOCUMENT_MESSAGE = "'identityDocument' field cannot be empty";
    public static final String EMPTY_PASSWORD_MESSAGE = "'password' field cannot be empty";


    public static final String OUT_OF_BOUNDS_NAME_MESSAGE = "'name' size must be between 2 and 64";
    public static final String OUT_OF_BOUNDS_LASTNAME_MESSAGE = "'lastname' size must be between 2 and 64";
    public static final String OUT_OF_BOUNDS_EMAIL_MESSAGE = "'email' size must be between 8 and 128";
    public static final String OUT_OF_BOUNDS_IDENTITY_DOCUMENT_MESSAGE = "'identityDocument' size must be between 6 and 16";
    public static final String OUT_OF_BOUNDS_PHONE_NUMBER_MESSAGE = "'phone' size must be between 10 and 13";
    public static final String OUT_OF_BOUNDS_PASSWORD_MESSAGE = "'password' size must be between 8 and 64";
    public static final String FUTURE_BIRTH_DATE_MESSAGE = "'birthdate' field cannot be a future date";

    public static final String NOT_VALID_EMAIL_MESSAGE = "invalid email";
    public static final String NOT_VALID_PHONE_NUMBER_MESSAGE = "invalid phone number";
    public static final String NOT_NUMERIC_IDENTITY_DOCUMENT_MESSAGE = "Identity document must be numerical only";

    public static final String USER_WITH_EMAIL_NOT_FOUND_MESSAGE = "User with email '%s' not found";
}
