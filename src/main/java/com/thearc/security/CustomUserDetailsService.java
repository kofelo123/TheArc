package com.thearc.security;

import com.thearc.domain.UserVO;
import com.thearc.mapper.UserMapper;
import com.thearc.security.domain.CustomUser;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

    @Setter(onMethod_ = { @Autowired })
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {

        log.warn("Load User By UserName : " + uid);

        // userName means userid
        UserVO vo = userMapper.read(uid);

        log.warn("queried by user mapper: " + vo);

        return vo == null ? null : new CustomUser(vo);
    }

}
