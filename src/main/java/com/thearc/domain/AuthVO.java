package com.thearc.domain;

import lombok.Data;

/**
 * @author 허정원
 * since 2018-10-19
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-10-19
 *
 * </pre>
 */


@Data
public class AuthVO {

    private String uid;
    private String authority;

}