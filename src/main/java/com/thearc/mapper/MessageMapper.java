package com.thearc.mapper;

import com.thearc.domain.MessageVO;
import com.thearc.domain.SearchCriteria;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MessageMapper {

    @Insert("INSERT INTO tbl_message (sender,targetid,title,message) " +
            "VALUES( #{sender}, #{targetid}, #{title}, #{message})")
    public void create(MessageVO message) throws Exception;

    @Update("UPDATE tbl_message " +
            "SET opendate = now()" +
            "WHERE mid= #{mid}")
    public void updateState(Integer mid) throws Exception;

    public void addCountList() throws Exception;

//    public List<MessageVO> listmail(MessageVO vo) throws Exception;


    public List<MessageVO> listSearch(@Param("cri") SearchCriteria cri, @Param("targetid") String targetid) throws Exception;

    public int listSearchCount(@Param("cri") SearchCriteria cri) throws Exception;

    @Select("SELECT * FROM tbl_message " +
            "WHERE mid = #{mid}")
    public MessageVO readMessage(Integer mid) throws Exception;

    @Update("UPDATE tbl_message " +
            "SET readcheck = 'y' " +
            "WHERE mid = #{mid}")
    public void updateReadCheck(Integer mid) throws Exception;
}
