package dk.kvalitetsit.hello.controller;

import dk.kvalitetsit.hello.controller.exception.AbstractApiException;
import jakarta.servlet.http.HttpServletRequest;
import org.openapitools.model.DetailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(AbstractApiException.class)
    public ResponseEntity<DetailedError> handleApiException(AbstractApiException e, HttpServletRequest request) {
        logger.debug("Handling ApiException: {}", e.getHttpStatus());
        var error = new DetailedError()
                .path(request.getRequestURI())
                .timestamp(OffsetDateTime.now())
                .status(e.getHttpStatus().value())
                .error(e.getHttpStatus().getReasonPhrase())
                .detailedErrorCode(e.getDetailedErrorCode())
                .detailedError(e.getDetailedError());

        return ResponseEntity.status(e.getHttpStatus().value()).body(error);
    }
}
