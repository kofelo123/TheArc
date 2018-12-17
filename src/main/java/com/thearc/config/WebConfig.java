package com.thearc.config;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import javax.servlet.ServletContext;

/**
 * @author 허정원
 * since 2018-12-14
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-12-14
 *
 * </pre>
 */


public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ServletConfig.class};
    }



    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[] { characterEncodingFilter };
    }

/*    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

//        servletContext.setAttribute("spring.profiles.active", "local");
        servletContext.setInitParameter("spring.profiles.default","local");
//        servletContext.setInitParameter("log4jConfiguration","classpath:/log4j-local.xml");
//        servletContext.addListener();


    }*/
   @Override
   protected void customizeRegistration(ServletRegistration.Dynamic registration) {

//       registration.setInitParameter("spring.profiles.active", "local");

//        registration.
   }

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(RequestContextListener.class);
        servletContext.setInitParameter("spring.profiles.active", "local");
        //Uma solução melhor, seria usar uma  variavel de ambiente para escolehr o profile ativo dinamicamente;
//		env.getProperties().get("spring.profiles.active");
    }
}
