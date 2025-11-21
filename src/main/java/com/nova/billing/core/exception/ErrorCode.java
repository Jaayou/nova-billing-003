package com.nova.billing.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common Errors (C001 ~ C099)
    INVALID_INPUT_VALUE("C001", "Invalid Input Date"),
    INTERNAL_SERVER_ERROR("C999", "Internal Server Error"),

    // Billing Logic Errors (B001 ~ B099)
    NO_BILLING_TARGET("B001", "Billing Target Not Exists"),
    INVALID_BILLING_PERIOD("B002", "Billing Period Invalid"),
    REFERENCE_DATA_MISSING("B003", "Mandatory Info Not Found");

    private final String code;
    private final String message;
    
}
