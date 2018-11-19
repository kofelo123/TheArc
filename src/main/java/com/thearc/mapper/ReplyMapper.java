package com.thearc.mapper;

import com.thearc.domain.Criteria;
import com.thearc.domain.ReplyVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ReplyMapper {

    @Select("SELECT * " +
            "FROM tbl_reply " +
            "WHERE bno =  #{bno} " +
            "ORDER BY rno DESC")
    public List<ReplyVO> list(Integer bno) throws Exception;

    @Insert("INSERT INTO tbl_reply (bno, replytext, replyer) " +
            "VALUES (#{bno},#{replytext},#{replyer})")
    public void create(ReplyVO vo) throws Exception;

    @Update("UPDATE tbl_reply " +
            "SET replytext = #{replytext}, updatedate = now() " +
            "WHERE rno = #{rno}")
    public void update(ReplyVO vo) throws Exception;

    @Delete("DELETE FROM tbl_reply " +
            "WHERE rno =#{rno}")
    public void delete(Integer rno) throws Exception;

    @Select("SELECT *" +
            "FROM tbl_reply " +
            "WHERE bno =#{bno} " +
            "ORDER BY rno DESC " +
            "LIMIT #{cri.pageStart}, #{cri.perPageNum}")
    public List<ReplyVO> listPage(@Param("bno") Integer bno, @Param("cri") Criteria cri) throws Exception;

    @Select("SELECT count(bno) " +
            "FROM tbl_reply " +
            "WHERE bno =#{bno}")
    public int count(Integer bno) throws Exception;

    @Select("SELECT bno " +
            "FROM tbl_reply " +
            "WHERE rno = #{rno}")
    public int getBno(Integer rno) throws Exception;
}
