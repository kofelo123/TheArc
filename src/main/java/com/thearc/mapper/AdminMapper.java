package com.thearc.mapper;

import com.thearc.domain.BoardVO;
import com.thearc.domain.UserVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 허정원
 * since 2018-10-09
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-10-09
 *
 * </pre>
 */


public interface AdminMapper {

    @Select("SELECT * " +
            "FROM tbl_user")
    public List<UserVO> listuser()throws Exception;

    @Select("SELECT uid,upw " +
            "FROM tbl_user " +
            "WHERE uid=#{uid} " +
            "AND upw=#{upw}")
    public UserVO adminlogin(UserVO user) throws Exception;

    @Update("UPDATE tbl_user " +
            "SET authority = #{authority} " +
            "WHERE uid = #{uid}")
    public void authmodify(UserVO user) throws Exception;


    @Delete("DELETE FROM tbl_check " +
            "WHERE uid = #{uid}")
    public void userDropCheck(UserVO user) throws Exception;

    @Delete("DELETE FROM tbl_message " +
            "WHERE sender = #{uid}")
    public void userDropMessage(UserVO user) throws Exception;

    @Delete("DELETE FROM tbl_user " +
            "WHERE uid = #{uid}")
    public void userDropUser(UserVO user) throws Exception;


    public List<String> dayBoard() throws Exception;

    public List<String> dayReply() throws Exception;

    public List<String> cateBoardview() throws Exception;

    public List<String> weekcateBoard() throws Exception;

    public List<String> weekReplyCount() throws Exception;


    @Delete("DELETE FROM tbl_reply " +
            "WHERE bno = #{bno}")
    public void boardDrop1(BoardVO board) throws Exception;

    @Delete("DELETE FROM tbl_attach " +
            "WHERE bno = #{bno}")
    public void boardDrop2(BoardVO board) throws Exception;

    @Delete("DELETE FROM tbl_board " +
            "WHERE bno = #{bno}")
    public void boardDrop3(BoardVO board) throws Exception;


}
