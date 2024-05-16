package com.yb.wealth.care.ui.exception;

import com.yb.wealth.care.ui.constant.ErrorMessages;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionHandler {

    public static Throwable handle(Throwable error) {
        if (error instanceof NoResultException) {
            return new NotFoundException(ErrorMessages.ERROR_NO_PERMISSION_NOT_EXIST);
        } else {
            log.error(ErrorMessages.UNKNOWN_ERROR, error);
            return new WebApplicationException(ErrorMessages.UNKNOWN_ERROR);
        }
    }
}
