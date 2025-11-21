package com.nova.billing.core.exception;

import lombok.Getter;

@Getter
public class BillingException extends RuntimeException {

    private final ErrorCode errorCode;

    public BillingException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BillingException(ErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage() + " (" + detailMessage + ") ");
        this.errorCode = errorCode;
    }

}
