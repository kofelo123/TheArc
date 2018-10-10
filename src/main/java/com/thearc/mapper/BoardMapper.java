package com.thearc.mapper;

import com.thearc.domain.BoardVO;
import com.thearc.domain.Criteria;
import com.thearc.domain.LikeVO;
import com.thearc.domain.SearchCriteria;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BoardMapper {

    @Insert("INSERT INTO tbl_board (title, content, writer, category) " +
            "VALUES( #{title}, #{content}, #{writer}, #{category})")
    public void create(BoardVO vo) throws Exception;


    public void create2(BoardVO vo) throws Exception;

    @Select("SELECT bno, title, content, writer, regdate, viewcnt, replycnt, countlike, category " +
            "FROM tbl_board WHERE bno = #{bno}")
    public BoardVO read(Integer bno) throws Exception;

    @Update("UPDATE tbl_board " +
            "SET title = #{title}, content = #{content} " +
            "WHERE bno = #{bno}")
    public void update(BoardVO vo) throws Exception;

    @Delete("DELETE FROM tbl_board " +
            "WHERE bno = #{bno}")
    public void delete(Integer bno) throws Exception;

    @Select("SELECT bno ,title, content, writer, regdate, viewcnt " +
            "FROM tbl_board " +
            "WHERE bno > 0 " +
            "ORDER BY bno DESC, regdate DESC")
    public List<BoardVO> listAll() throws Exception;

    public List<BoardVO> listPage(int page) throws Exception;

    @Select("SELECT bno, title, content, writer, regdate, viewcnt, replycnt " +
            "FROM tbl_board " +
            "WHERE bno > 0 " +
            "ORDER BY bno DESC, regdate DESC " +
            "LIMIT #{pageStart}, #{perPageNum}")
    public List<BoardVO> listCriteria(Criteria cri) throws Exception;


    public int countPaging(Criteria cri) throws Exception;


    public List<BoardVO> listSearch(@Param("cri") SearchCriteria cri, @Param("category") String category)throws Exception;

    public List<String> listThumnail(@Param("cri")SearchCriteria cri,@Param("category")String category) throws Exception;

    public int listSearchCount(@Param("cri") SearchCriteria cri,@Param("category") String category)throws Exception;


    public void updateReplyCnt(@Param("bno") Integer bno, @Param("amount") int amount)throws Exception;

    @Update("UPDATE tbl_board " +
            "SET viewcnt = viewcnt +1 " +
            "WHERE bno = #{bno}")
    public void updateViewCnt(Integer bno)throws Exception;

    @Insert("INSERT INTO tbl_attach(fullname, bno) " +
            "VALUES ( #{fullName}, LAST_INSERT_ID())")
    public void addAttach(String fullName)throws Exception;

    @Select("SELECT COUNT(fullName) " +
            "FROM tbl_Attach " +
            "WHERE bno = LAST_INSERT_ID()")
    public int countImgNum();

    @Update("UPDATE tbl_attach " +
            "SET imgnum = #{count} " +
            "WHERE fullName = #{fullName}")
    public void updateImgNum(@Param("count") int count, @Param("fullName") String fullName);

    public void addAttach2(String fullName)throws Exception;

    @Select("SELECT fullname " +
            "FROM tbl_attach " +
            "WHERE bno = #{bno} " +
            "ORDER BY regdate")
    public List<String> getAttach(Integer bno)throws Exception;

    @Select("SELECT fullname " +
            "FROM tbl_attach " +
            "WHERE bno = #{bno} " +
            "ORDER BY regdate " +
            "LIMIT 1")
    public String getAttachOne(Integer bno)throws Exception;//썸네일 게시판용

    @Delete("DELETE FROM tbl_attach " +
            "WHERE bno = #{bno}")
    public void deleteAttach(Integer bno)throws Exception;

    @Insert("INSERT INTO tbl_attach(fullname, bno) " +
            "VALUES ( #{fullName}, #{bno})")
    public void replaceAttach(@Param("fullName") String fullName, @Param("bno") Integer bno)throws Exception;

    @Update("UPDATE tbl_board " +
            "SET countlike = countlike + 1 " +
            "WHERE bno = #{bno}")
    public void addlike(int bno)throws Exception;

    @Update("UPDATE tbl_board " +
            "SET countlike = countlike - 1 " +
            "WHERE bno = #{bno}")
    public void sublike(int bno)throws Exception;

    @Select("SELECT * " +
            "FROM tbl_check " +
            "WHERE uid = #{uid} " +
            "AND bno = #{bno}")
    public LikeVO checklike(@Param("uid") String uid, @Param("bno") int bno)throws Exception;

    @Insert("INSERT INTO tbl_check(uid,bno) " +
            "VALUES ( #{uid}, #{bno})")
    public void insertlikedefault(@Param("uid") String uid, @Param("bno") int bno)throws Exception;

    @Update("UPDATE tbl_check " +
            "SET likecheck='y' " +
            "WHERE uid = #{uid} " +
            "AND bno = #{bno}")
    public void updatelikey(@Param("uid") String uid, @Param("bno") int bno)throws Exception;

    @Update("UPDATE tbl_check " +
            "SET likecheck='n' " +
            "WHERE uid = #{uid} " +
            "AND bno = #{bno}")
    public void updateliken(@Param("uid") String uid, @Param("bno") int bno)throws Exception;

}
