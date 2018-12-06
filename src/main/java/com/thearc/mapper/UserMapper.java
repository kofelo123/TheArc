package com.thearc.mapper;

import com.thearc.domain.LoginDTO;
import com.thearc.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Date;

public interface UserMapper {

/*    @Select("SELECT uid, upw, uname  " +
            "FROM tbl_user  " +
            "WHERE uid = #{uid}  " +
            "AND upw = #{upw}")
    public UserVO login(LoginDTO dto) throws Exception;*/

    @Select("SELECT uid, upw, uname  " +
            "FROM tbl_user  " +
            "WHERE uid = #{uid}  " +
            "AND upw = #{upw}")
    public UserVO testLogin(LoginDTO dto)throws Exception;

    @Update("UPDATE tbl_user  " +
            "SET sessionKey = #{sessionId}, sessionLimit = #{next}  " +
            "WHERE uid = #{uid}")
    public void keepLogin(@Param("uid") String uid, @Param("sessionid") String sessionId, @Param("date") Date next);

    @Select("SELECT *  " +
            "FROM tbl_user  " +
            "WHERE sessionKey = #{value}   " +
            "AND sessionlimit > now()")
    public UserVO checkUserWithSessionKey(String value);

    @Insert("INSERT INTO tbl_user(uid, upw, uname, email,address,phone) " +
            "VALUES(#{uid},#{upw},#{uname},#{email},#{addr1},#{phone})")
    public void joinPost(UserVO user);

    @Insert("INSERT INTO tbl_user_auth(uid,authority) VALUES (#{uid},'ROLE_MEMBER')")
    public void insertAuth(UserVO user);

    @Select("SELECT *  " +
            "FROM tbl_user  " +
            "WHERE uid= #{uid} ")
    public UserVO confirmId(UserVO uid);

//	public List<AddressVO> findzipnum(AddressVO address);

    // 아아디 비밀번호 찾기 로직관련
    @Select("SELECT uid,upw,uname  " +
            "FROM tbl_user  " +
            "WHERE email= #{email} ")
    public UserVO idfindofmail(UserVO user);

    @Select("SELECT uid  " +
            "FROM tbl_user  " +
            "WHERE upw = #{upw}  " +
            "AND uid = #{uid}")
    public UserVO hashbyid(UserVO user);

    @Update("UPDATE tbl_user  " +
            "SET upw = #{upw}  " +
            "WHERE uid = #{uid}")
    public void modifypw(UserVO user);

    public UserVO read(String uid);

    @Select("SELECT * " +
            "FROM tbl_user " +
            "WHERE email=#{email}")
    public UserVO mailCheck(UserVO userVO);

    @Select("SELECT uname " +
            "FROM tbl_user " +
            "WHERE uname=#{uname}")
    public String unameCheck(String uname);


    @Select("SELECT upw " +
            "FROM tbl_user " +
            "WHERE uid = #{uid}")
    public String getPw(String uid);
}
