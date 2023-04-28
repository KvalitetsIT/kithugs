package dk.kvalitetsit.hello.controller.exception;

import org.openapitools.model.DetailedError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BadRequestException extends AbstractApiException {
    public BadRequestException(DetailedError.DetailedErrorCodeEnum errorCode, String errorText) {
        super(HttpStatus.BAD_REQUEST, errorCode, errorText);
    }
}
