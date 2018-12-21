package com.thearc.config;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mobile.device.DeviceResolverRequestFilter;
import org.springframework.test.context.ActiveProfiles;
//import org.springframework.util.Log4jConfigurer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.Log4jConfigListener;
import org.springframework.web.util.Log4jConfigListener;


import javax.servlet.*;
import javax.servlet.ServletContext;
import java.io.FileNotFoundException;

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

        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();

        DeviceResolverRequestFilter deviceResolverRequestFilter = new DeviceResolverRequestFilter();



        return new Filter[] { characterEncodingFilter, hiddenHttpMethodFilter , deviceResolverRequestFilter };
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

       registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
   }

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {

        servletContext.setInitParameter("spring.profiles.active", "server");
        servletContext.addListener(RequestContextListener.class);

        servletContext.setInitParameter("log4jConfigLocation", "classpath:/log4j-"+servletContext.getInitParameter("spring.profiles.active")+".xml");
        servletContext.addListener(Log4jConfigListener.class);


//        servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");

        super.onStartup(servletContext);


/*
        try {
            Log4jConfigurer.initLogging("classpath:/log4j-local.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
*/




//        Log4jConfigListener log4jListener = new Log4jConfigListener();
//        servletContext.addListener( log4jListener );

        //Uma solução melhor, seria usar uma  variavel de ambiente para escolehr o profile ativo dinamicamente;
//		env.getProperties().get("spring.profiles.active");
    }


}
