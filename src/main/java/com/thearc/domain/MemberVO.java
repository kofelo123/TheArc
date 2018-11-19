package com.thearc.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

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
public class MemberVO {

    private String userid;
    private String userpw;
    private String userName;
    private boolean enabled;

    private Date regDate;
    private Date updateDate;
    private List<AuthVO> authList;

}