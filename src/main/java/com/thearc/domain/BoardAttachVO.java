package com.thearc.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author 허정원
 * since 2018-10-16
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-10-16
 *
 * </pre>
 */

//불필요한 파일 삭제 - task 작업때 파일들 불러오기 위해서 생성
@Data
public class BoardAttachVO {

    private String fullName;
    private int bno;
    private Date regdate;
    private int imgnum;
    private boolean fileType;
}
