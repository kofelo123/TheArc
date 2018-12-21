package com.thearc.config;

import com.thearc.domain.ConfigProfile;
import com.thearc.util.sns.NaverLoginBO;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


@ComponentScan(basePackages={"com.thearc.controller","com.thearc.util","com.thearc.util.sns"})
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
@EnableWebMvc
public class ServletConfig implements WebMvcConfigurer {

    @Autowired
    private ConfigProfile configProfile;

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {

        InternalResourceViewResolver bean = new InternalResourceViewResolver();
            bean.setViewClass(JstlView.class);
            bean.setPrefix("/WEB-INF/views/");
            bean.setSuffix(".jsp");
            viewResolverRegistry.viewResolver(bean);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {

        resourceHandlerRegistry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(deviceResolverHandlerInterceptor());
    }


    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException{

        CommonsMultipartResolver resolver = new CommonsMultipartResolver();

        //10MB
        resolver.setMaxUploadSize(1024 * 1024 * 10);

        // 2MB
//        resolver.setMaxUploadSizePerFile(1024 * 1024 * 2);

        // 1MB
//        resolver.setMaxInMemorySize(1024 * 1024);

        //temp upload
//        resolver.setUploadTempDir(new FileSystemResource("C:\\\\zzz\\\\upload"));

        return resolver;

    }

   /* @Bean
    public EncryptablePropertyPlaceholderConfigurer propertyConfigurator(){

        EncryptablePropertyPlaceholderConfigurer configurer = new EncryptablePropertyPlaceholderConfigurer(configurationEncryptor());
//        List<String> list = new ArrayList<>();
//        list.add("classpath:spring/setting.properties");
        configurer.setLocation(new DefaultResourceLoader().getResource("classpath:spring/setting.properties"));

        return configurer;
    }

    @Bean
    public StandardPBEStringEncryptor configurationEncryptor(){

        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setConfig(environmentVariablesConfiguration());
        standardPBEStringEncryptor.setPassword("rktwlsrud");

        return standardPBEStringEncryptor;
    }

    @Bean
    public EnvironmentStringPBEConfig environmentVariablesConfiguration(){
        EnvironmentStringPBEConfig environmentStringPBEConfig = new EnvironmentStringPBEConfig();
        environmentStringPBEConfig.setAlgorithm("PBEWithMD5AndDES");
        environmentStringPBEConfig.setPasswordEnvName("APP_ENCRYPTION_PASSWORD");

        return environmentStringPBEConfig;
    }
*/

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
        bundleMessageSource.setDefaultEncoding("UTF-8");

        List<String> list = new ArrayList<>();
        list.add("classpath:i18n/hr");

        bundleMessageSource.setBasenames(list.get(0));

        return bundleMessageSource;

    }

/*    @Bean
    public String uploadPath(){
        String string = new String("C:\\\\zzz\\\\upload");
        return string;
    }*/

    @Bean
    public NaverLoginBO naverLoginBO(){
        return new NaverLoginBO();
    }

    @Bean
    public GoogleConnectionFactory googleConnectionFactory(){
        GoogleConnectionFactory factory =
                new GoogleConnectionFactory(configProfile.getGoogleClientId()
                                            ,configProfile.getGoogleSecret());
        return factory;
    }

    @Bean
    public OAuth2Parameters googleOAuth2Parameters(){
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setScope("https://www.googleapis.com/auth/userinfo.email");
        parameters.setRedirectUri("http://"+configProfile.getIpAddress()+":8080/thearc/googleSignInCallback");

        return parameters;
    }
    @Bean
    public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
        return new DeviceResolverHandlerInterceptor();
    }



////////////////////////






    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {

    }



    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {

    }



    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }

  /*  @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
        requestMappingHandlerMapping.setRemoveSemicolonContent(false);
        return requestMappingHandlerMapping;
    }
*/

}
