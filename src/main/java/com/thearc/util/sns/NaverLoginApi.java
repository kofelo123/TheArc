package com.thearc.util.sns;

import com.github.scribejava.core.builder.api.DefaultApi20;

/**
 * @author 허정원
 * since 2018-11-25
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-11-25
 *
 * </pre>
 */

public class NaverLoginApi extends DefaultApi20{

    protected NaverLoginApi(){
    }

    private static class InstanceHolder{
        private static final NaverLoginApi INSTANCE = new NaverLoginApi();
    }


    public static NaverLoginApi instance(){
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://nid.naver.com/oauth2.0/authorize";
    }

}
