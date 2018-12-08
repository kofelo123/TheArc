package com.thearc.domain;

import lombok.Data;

/**
 * @author 허정원
 * since 2018-12-02
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-12-02
 *
 * </pre>
 */

@Data
public class ConfigProfile {

    private String url;
    private String userName;
    private String password;

    private String ipAddress;
    private String uploadPath;
    private String mailPw;

    //naver sns login
    private String naverClientId;
    private String naverSecret;

    //google sns login
    private String googleClientId;
    private String googleSecret;

}


