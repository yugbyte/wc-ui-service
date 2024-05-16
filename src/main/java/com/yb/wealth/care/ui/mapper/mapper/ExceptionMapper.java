package com.yb.wealth.care.ui.mapper.mapper;

import com.yb.wealth.care.ui.exception.BadRequestException;
import com.yb.wealth.care.ui.exception.NotFoundException;
import com.yb.wealth.care.ui.resource.dto.ErrorDto;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;


@Slf4j
public class ExceptionMapper {

    @ServerExceptionMapper
    public RestResponse<ErrorDto> mapBadRequestException(BadRequestException x) {
        return RestResponse.status(Response.Status.BAD_REQUEST, ErrorDto.builder().message(x.getMessage()).build());
    }

    @ServerExceptionMapper
    public RestResponse<ErrorDto> mapNotFoundException(NotFoundException x) {
        return RestResponse.status(Response.Status.FORBIDDEN,  ErrorDto.builder().message(x.getMessage()).build());
    }

    @ServerExceptionMapper
    public RestResponse<ErrorDto> constraintException(ConstraintViolationException x) {
        log.error("CONTRATINT ERRR");
        return RestResponse.status(Response.Status.BAD_REQUEST,  ErrorDto.builder().message(x.getMessage()).build());
    }

    @ServerExceptionMapper
    public RestResponse<ErrorDto> internalServerError(WebApplicationException x) {
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR,  ErrorDto.builder().message(x.getMessage()).build());
    }
}
