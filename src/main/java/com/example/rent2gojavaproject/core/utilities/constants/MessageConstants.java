package com.example.rent2gojavaproject.core.utilities.constants;


public enum MessageConstants {

    BRAND("Brand"), COLOR("Color"), CAR("Car"), CUSTOMER("Customer"), RENTAL("Rental"),
    DISCOUNT("Discount"), EMPLOYEE("Employee"), MODEL("Model"), USER("User"), BILL("Bill"),PAYMENT("Payment"),

    ADD("The addition operation was successful!"),
    UPDATE("The update operation was successful!"),
    DELETE("The deletion operation was successful!"),
    GET_ALL("All records were listed!"),
    GET("The record was found!"),

    ID_NOT_FOUND("Id not found : "),
    NOT_FOUND(" not found"),
    ALREADY_EXISTS(" already exists"),
    EMAIL_ALREADY_EXISTS(" Email already exists"),
    ID_CARD_NUMBER_ALREADY_EXISTS(" Id card number already exists"),
    ID_CARD_INFORMATION_NOT_VALID(" Authentication is used in our system. Please enter your information correctly ! - :)- !"),
    PHONE_NUMBER_ALREADY_EXISTS(" Phone number already exists"),
    KILOMETER_ERROR("The last kilometer of the vehicle cannot be lower than the delivered mileage."),
    START_DATE_ERROR("Start date must be before rental end date"),
    MAX_RENTAL_DAYS_ERROR("Car can be rented for a maximum of 25 days.!"),

    PASSWORD_CHANGED("Password changed successfully"),
    EMAIL_NOT_FOUND("Email not found : "),
    FILE_UPLOAD_SUCCESS("File uploaded successfully"),

    USER_NOT_ENABLED("Please verify your account with your email address  "),
    INVALID_PASSWORD("Invalid password."),
    TOKEN_NOT_FOUND("Token not found!"),
    EMAIL_ALREADY_VERIFIED("Your email address has already been verified, please log in."),
    TOKEN_EXPIRED("Your email verification link is invalid or has expired. Please, sign up again."),
    EMAIL_VERIFICATION_SUCCESS("Email verification successful! Now you can log in to your account."),
    SIGNUP_SUCCESS("Success! Please, check your email to confirm your account."),
    DISCOUNT_NOT_UNIQUE("The discount code entered has been used before.");



    private final String message;

    MessageConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
