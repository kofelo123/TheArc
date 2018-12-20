package com.thearc.config;

import com.thearc.security.CustomAccessDeniedHandler;
import com.thearc.security.CustomLoginSuccessHandler;
import com.thearc.security.CustomUserDetailsService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.sql.DataSource;

/**
 * @author 허정원
 * since 2018-12-15
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-12-15
 *
 * </pre>
 */

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Setter(onMethod_ = {@Autowired})
    private DataSource dataSource;

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        log.info("configure.............");
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
        auth.inMemoryAuthentication()
                .withUser("member").password("{noop}member").roles("MEMBER");
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserService()).
                passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(){
        return new CustomLoginSuccessHandler();
    }

    @Override
    public void configure(HttpSecurity http)throws Exception{

//        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("**/callback/**","POST");
//        http.csrf().requireCsrfProtectionMatcher(antPathRequestMatcher);

//        http.csrf().ignoringAntMatchers("**/addrlink/**");



//        http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/addrlink/**","POST"));



//        http.csrf().requireCsrfProtectionMatcher(csrfMatcher());
//        http.requestMatcher(new AntPathRequestMatcher("**/addrlink/**")).csrf().disable();

        //        http.csrf().disable();


        /*http.authorizeRequests().antMatchers("/sample/all")
                .permitAll()
                .antMatchers("/sample/admin")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/sample/member")
                .access("hasRole('ROLE_MEMBER')");*/

        http.authorizeRequests().antMatchers("/sboard/list/supporter")
                                .access("hasRole('ROLE_SUPPORTER') and hasRole('ROLE_ADMIN')");

        http.exceptionHandling().accessDeniedHandler(customAccessDenied());

        http.csrf().ignoringAntMatchers("/user/jusoPopup","/login");


/*        http.formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/sboard/main");
////                .loginProcessingUrl("/login")
////                .successHandler(loginSuccessHandler());*/

//loginProcessingUrl  꼭있어야함.
        http.formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/sboard/main");
//                .successHandler(loginSuccessHandler());



        http.logout()
                .logoutUrl("/customLogout")
                .logoutSuccessUrl("/sboard/main")
                .invalidateHttpSession(true)
                .deleteCookies("remember-me","JSESSION_ID");

        http.rememberMe()
                .key("thearc")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(604800);


    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService customUserService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDenied(){
        return new CustomAccessDeniedHandler();
    }

//    @Bean
//    public OrRequestMatcher csrfMatcher(){
//        OrRequestMatcher matcher = new OrRequestMatcher(antPathRequestMatcher());
//        return matcher;
//    }
//
//    @Bean
//    public AntPathRequestMatcher antPathRequestMatcher(){
//        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("**/callback/**","POST");
//        return antPathRequestMatcher;
//    }

}
