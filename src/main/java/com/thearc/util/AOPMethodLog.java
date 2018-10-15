package com.thearc.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author 허정원
 * since 2018-08-23
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-08-23      허정원         AOP - 로깅
 *
 * </pre>
 */

@Slf4j
@Component
@Aspect
public class AOPMethodLog {

    @Before("execution(* com.thearc.controller.*.*(..))")
    public void printLog(JoinPoint jp){
        String method = jp.getSignature().getName();
        String className = jp.getSignature().getDeclaringTypeName();
        log.info("[메소드 호출]: " + className + "."+ method );
    }

}
/*

@Slf4j
public class AOPMethodLog {

    public void printLog(JoinPoint jp){
        String method = jp.getSignature().getName();
        String className = jp.getSignature().getDeclaringTypeName();
        log.info("[메소드 호출]: " + className + "."+ method );
    }

}
*/
