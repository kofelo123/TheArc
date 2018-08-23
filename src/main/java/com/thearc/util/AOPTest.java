package com.thearc.util;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class AOPTest {

    private static final Logger logger = LoggerFactory.getLogger(AOPTest.class);

    public void printLog(JoinPoint jp){
        String method = jp.getSignature().getName();
        String className = jp.getSignature().getDeclaringTypeName();
        logger.info("[메소드 호출]: " + className + "."+ method );
    }

}
