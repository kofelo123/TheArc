package com.thearc.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.social.InternalServerErrorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @author 허정원
 * since 2018-12-21
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-12-21
 *
 * </pre>
 */

@ControllerAdvice
@Log4j
public class AdviceController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String error404(Exception ex)   {

        log.error("Exception"+ex.getMessage());
        return "error/error404";
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public String error400(Exception ex)   {

        log.error("Exception"+ex.getMessage());
        return "error/error400";
    }

    //?
    @ExceptionHandler(IllegalArgumentException.class)
    public String error500(Exception ex)   {

        log.error("Exception"+ex.getMessage());
        return "error/error500";
    }
/*    @ExceptionHandler(Exception.class)
    public String error500(Exception ex)   {

        log.error("Exception"+ex.getMessage());
        return "error/error500";
    }*/



}
