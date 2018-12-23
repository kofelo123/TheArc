package com.thearc.controller;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thearc.domain.ConfigProfile;
import com.thearc.domain.UserVO;
import com.thearc.service.UserService;
import com.thearc.util.sns.NaverLoginBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 허정원
 * since 2018-11-25
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-11-25
 *
 * </pre>
 */
/**
 * Handles requests for the application home page.
 */
@Controller
public class NaverLoginController {

    @Autowired
    private UserService service;

    /* NaverLoginBO */
    @Autowired
    private NaverLoginBO naverLoginBO;

    @Autowired
    private ConfigProfile configProfile;

    private String apiResult = null;

    /*@Autowired
    private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
        this.naverLoginBO = naverLoginBO;
    }*/

    //로그인 첫 화면 요청 메소드
    @RequestMapping(value = "/naverlogin2", method = { RequestMethod.GET, RequestMethod.POST })
    public String login(Model model, HttpSession session) {

        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
        System.out.println("네이버:" + naverAuthUrl);

        //네이버
//        model.addAttribute("url", naverAuthUrl);

        /* 생성한 인증 URL을 View로 전달 */
        return "redirect:"+naverAuthUrl;
    }

    //네이버 로그인 성공시 callback호출 메소드
    @RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
    public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, RedirectAttributes rttr, HttpServletResponse response)
            throws IOException , Exception{
        System.out.println("여기는 callback");
        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken);
        System.out.println(apiResult);

        System.out.println("JSON TEST");

        JsonParser parser = new JsonParser();

        JsonObject jsonObj = (JsonObject) parser.parse(apiResult);
//        JsonArray jsonArray = (JsonArray) jsonObj.get("response");
        JsonObject jsonObj2 =  (JsonObject)jsonObj.get("response");
        System.out.println(jsonObj2.get("id"));
        System.out.println(jsonObj2.get("email"));
        System.out.println(jsonObj2.get("name"));

        UserVO userVO = new UserVO();

        //네이버에서 네이버아이디정보 제공x -> 이메일에서 @(at) 앞의 문자열로 아이디처리 하려고함.
        String mail = jsonObj2.get("email").toString().replaceAll("\"","");
        int mailAtIndex=mail.indexOf("@");

        userVO.setUid(mail.substring(0,mailAtIndex));
        System.out.println("test:"+userVO.getUid());
        //비밀번호는 네이버에서 제공해주는 숫자값의 아이디인 고유번호가 있는데 그걸로 대체한다.
        userVO.setUpw(jsonObj2.get("id").toString().replaceAll("\"",""));
        userVO.setUname(jsonObj2.get("name").toString().replaceAll("\"",""));
        userVO.setEmail(jsonObj2.get("email").toString().replaceAll("\"",""));

        if(service.hashbyid(userVO) == null) {
            System.out.println("ifTest");

            //hashbyid과정에서 upw인코딩되어서 다시 원래로 돌림.
            userVO.setUpw(jsonObj2.get("id").toString().replaceAll("\"", ""));
            try{
                service.joinPost(userVO);
            }catch(Exception e){
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.print("<script>alert('이미 가입된 메일 또은 아이디입니다.');location.href='/thearc/user/login';</script>");
                out.flush();
            }


        }
        userVO.setUpw(jsonObj2.get("id").toString().replaceAll("\"", ""));

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
            out.print("form.attr({action:'http://"+configProfile.getIpAddress()+"/thearc/login' , method:'post'});");
            out.print("form.appendTo('body');");
            out.print("$('<input></input>').attr({type:'hidden',name:'username',value:'"+userVO.getUid()+"'}).appendTo(form);");
            out.print("$('<input></input>').attr({type:'hidden',name:'password',value:'"+userVO.getUpw()+"'}).appendTo(form);");
            out.print("$('<input></input>').attr({type:'hidden',name:'${_csrf.parameterName}',value: '${_csrf.token}'}).appendTo(form);");
            out.print("form.submit();");
            out.print("});");
            out.print("</script>");
            out.flush();
//            response.sendRedirect("/thearc/user/login");

//           rttr.addFlashAttribute("msg","snsLogin");
//           rttr.addFlashAttribute("userVO",userVO);




        System.out.println("PassTest");


//        JsonElement element = parser.parse(apiResult);
//        String resultcode = element.getAsJsonObject().get("resultcode").getAsString();
//        System.out.println("resultcode:"+resultcode);


//        System.out.println(apiResult);
//        model.addAttribute("result", apiResult);

        return "/user/login";

        /* 네이버 로그인 성공 페이지 View 호출 */
//        return "/user/naverSuccess";
    }
}

