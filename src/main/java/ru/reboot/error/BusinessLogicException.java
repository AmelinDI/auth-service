package ru.reboot.error;

public class BusinessLogicException extends RuntimeException {

    private final BusinessExceptionCode code;

    public BusinessLogicException(String message, BusinessExceptionCode code) {
        super(message);
        this.code = code;
    }

    public BusinessExceptionCode getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.format("BusinessLogicException{message=%s code=%s}", getMessage(), getCode());
    }
}
