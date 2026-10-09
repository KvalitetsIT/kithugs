package dk.kvalitetsit.hello.controller.exception;

import org.openapitools.model.DetailedError;
import org.springframework.http.HttpStatus;

public class AbstractApiException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final DetailedError.DetailedErrorCodeEnum detailedErrorCodeEnum;
    private final String detailedError;

    public AbstractApiException(HttpStatus httpStatus, DetailedError.DetailedErrorCodeEnum detailedErrorCodeEnum, String detailedErrorCode) {
        this.httpStatus = httpStatus;
        this.detailedErrorCodeEnum = detailedErrorCodeEnum;
        this.detailedError = detailedErrorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public DetailedError.DetailedErrorCodeEnum getDetailedErrorCode() {
        return detailedErrorCodeEnum;
    }

    public String getDetailedError() {
        return detailedError;
    }
}
