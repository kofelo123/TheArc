package com.thearc.controller;

import com.thearc.domain.UserVO;
import com.thearc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 허정원
 * since 2018-12-08
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-12-08
 *
 * </pre>
 */

@Controller
public class GoogleLoginController {

    /* GoogleLogin */
    @Autowired
    private GoogleConnectionFactory googleConnectionFactory;
    @Autowired
    private OAuth2Parameters googleOAuth2Parameters;

    private OAuth2Operations oauthOperations;

    @Autowired
    private UserService service;

    // 로그인 첫 화면 요청 메소드
    @RequestMapping(value = "/googleLogin", method = { RequestMethod.GET, RequestMethod.POST })
    public String login(Model model, HttpSession session) {

        /* 구글code 발행 */
        OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
        String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);

        System.out.println("구글:" + url);

//        model.addAttribute("google_url", url);

        /* 생성한 인증 URL을 View로 전달 */
        return "redirect:"+url;
    }

    // 구글 Callback호출 메소드
    @RequestMapping(value = "/googleSignInCallback", method = { RequestMethod.GET, RequestMethod.POST })
    public String googleCallback(Model model, @RequestParam String code, HttpServletResponse response) throws IOException , Exception {
        System.out.println("여기는 googleCallback");

        oauthOperations = googleConnectionFactory.getOAuthOperations();
        AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2Parameters.getRedirectUri(),
                null);

        String accessToken = accessGrant.getAccessToken();
        Long expireTime = accessGrant.getExpireTime();

        if (expireTime != null && expireTime < System.currentTimeMillis()) {
            accessToken = accessGrant.getRefreshToken();
            System.out.printf("accessToken is expired. refresh token = {}", accessToken);

        }

        Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
        Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();
        System.out.println(connection);

        
        PlusOperations plusOperations = google.plusOperations();
        Person profile = plusOperations.getGoogleProfile();
        System.out.println("User Uid : " + profile.getId());
        System.out.println("User Name : " + profile.getDisplayName());
        System.out.println("User Email : " + profile.getAccountEmail());
        System.out.println("============================================");

        int mailAtIndex = profile.getAccountEmail().indexOf("@");

        UserVO userVO = new UserVO();
        userVO.setUid(profile.getAccountEmail().substring(0,mailAtIndex));
        userVO.setUpw(profile.getId());
        userVO.setUname(profile.getDisplayName());
        userVO.setEmail(profile.getAccountEmail());

        if(service.hashbyid(userVO) == null){
            System.out.println("ifTest");
            //hashbyid과정에서 upw인코딩되어서 다시 원래로 돌림.
            userVO.setUpw(profile.getId());

            try{
                service.joinPost(userVO);
            }catch(Exception e){
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.print("<script>alert('이미 가입된 메일 또은 아이디입니다');location.href='/thearc/user/login';</script>");
                out.flush();
            }
        }

        userVO.setUpw(profile.getId());
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
         /*   out.print("<script>");
            out.print("alert('해당 아이디의 비밀번호가 일치하지 않습니다.');");
            out.print("</script>");
            out.flush();*/
        out.print("<script type='text/javascript' src='/thearc/resources/bootstrap/js/jquery-1.10.2.min.js'></script>");
        out.print("<script>");
        out.print("$(function(){");
        out.print("var form = $('<form></form>');");
        out.print("form.attr({action:'/thearc/login' , method:'post'});");
        out.print("form.appendTo('body');");
        out.print("$('<input></input>').attr({type:'hidden',name:'username',value:'"+userVO.getUid()+"'}).appendTo(form);");
        out.print("$('<input></input>').attr({type:'hidden',name:'password',value:'"+userVO.getUpw()+"'}).appendTo(form);");
        out.print("$('<input></input>').attr({type:'hidden',name:'${_csrf.parameterName}',value: '${_csrf.token}'}).appendTo(form);");
        out.print("form.submit();");
        out.print("});");
        out.print("</script>");
        out.flush();

       /* OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
        AccessGrant accessGrant = oauthOperations.exchangeForAccess(code , googleOAuth2Parameters.getRedirectUri(),
                null);

        String accessToken = accessGrant.getAccessToken();
        Long expireTime = accessGrant.getExpireTime();
        if (expireTime != null && expireTime < System.currentTimeMillis()) {
            accessToken = accessGrant.getRefreshToken();
            System.out.printf("accessToken is expired. refresh token = {}", accessToken);
        }
        Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
        Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();

        PlusOperations plusOperations = google.plusOperations();
        Person profile = plusOperations.getGoogleProfile();
        UserVO vo = new UserVO();
        System.out.println(profile.getDisplayName()); //jeongwon heo
        System.out.println(profile.getAccountEmail()); //
        System.out.println(profile.getImageUrl()); //https://lh6.googleusercontent.com/-DEKeeH9GkSE/AAAAAAAAAAI/AAAAAAAAAAA/AGDgw-hptFL4LRa5ZeJ5Cq8j2dpPwRFVpA/mo/photo.jpg?sz=50
        System.out.println(profile.getUrl()); // https://plus.google.com/101299826901289893543
        System.out.println(profile.toString()); // jeongwon heo
        System.out.println(profile.getAboutMe()); //
        System.out.println(profile.getBirthday()); //
        System.out.println(profile.getEmailAddresses()); //
        System.out.println(profile.getEmails());//
        System.out.println(profile.getFamilyName()); //heo
        System.out.println(profile.getGender()); //male
        System.out.println(profile.getGivenName()); //jeongwon*/

      /*  vo.setUser_email("구글 로그인 계정");
        vo.setUser_name(profile.getDisplayName());
        vo.setUser_snsId("g"+profile.getId());
        HttpSession session = request.getSession();
        vo = service.googleLogin(vo);
*/
//        session.setAttribute("login", vo );



        return "/user/login";
    }

}
