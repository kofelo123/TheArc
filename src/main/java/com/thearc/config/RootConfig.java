package com.thearc.config;

import com.thearc.domain.ConfigProfile;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


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
@Configuration
@ComponentScan(basePackages={"com.thearc.service","com.thearc.task"})
@MapperScan("com.thearc.mapper")
@EnableScheduling
@EnableAspectJAutoProxy
@EnableTransactionManagement
//@PropertySource("classpath:/spring/setting.properties")
public class RootConfig {

   /* @Value("${Local.Url}")
    String url;*/

    @Autowired
    private ConfigProfile configProfile;



    public RootConfig() {
    }

    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        hikariConfig.setJdbcUrl(configProfile.getUrl());
        hikariConfig.setUsername(configProfile.getUserName());
        hikariConfig.setPassword(configProfile.getPassword());

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }
/*
    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://127.0.0.1/thearc");
        hikariConfig.setUsername("thearc");
        hikariConfig.setPassword("thearc");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }
*/

    @Bean
    public SqlSessionFactory sqlSessionFactory(ApplicationContext applicationContext) throws Exception{
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        return(SqlSessionFactory) sqlSessionFactory.getObject();
    }

    @Bean(destroyMethod = "clearCache")
    public SqlSessionTemplate sqlSession(ApplicationContext applicationContext) throws Exception{
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory(applicationContext));
        return sqlSessionTemplate;
    }

    @Bean
    public DataSourceTransactionManager txManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public static EnvironmentStringPBEConfig environmentVariablesConfiguration() {
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPasswordEnvName("APP_ENCRYPTION_PASSWORD");
        return config;
    }

    @Bean
    public static StandardPBEStringEncryptor configurationEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setConfig(environmentVariablesConfiguration());
        encryptor.setPassword("rktwlsrud");
        return encryptor;
    }

    @Bean
    public static EncryptablePropertyPlaceholderConfigurer propertyConfigurer() {
        EncryptablePropertyPlaceholderConfigurer configurer = new EncryptablePropertyPlaceholderConfigurer(configurationEncryptor());
        configurer.setLocations(new ClassPathResource("/spring/setting.properties"));
        return configurer;
    }

/*    @Bean
    public EncryptablePropertyPlaceholderConfigurer propertyConfigurator(){

        EncryptablePropertyPlaceholderConfigurer configurer = new EncryptablePropertyPlaceholderConfigurer(configurationEncryptor());

//        List<String> list = new ArrayList<>();
//        list.add("classpath:spring/setting.properties");
        configurer.setLocation(new DefaultResourceLoader().getResource("classpath:spring/setting.properties"));
//        configurer.setLocation(new ClassPathResource("spring/setting.properties"));


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
    }*/


    @Profile("local")
    @Bean
    public ConfigProfile localConfig(@Value("${Local.Url}") String url,@Value("${Local.UserName}") String userName,@Value("${Local.Password}") String password,@Value("${Local.IpAddress}") String ipAddress,@Value("${Local.UploadPath}") String uploadPath,
                                @Value("${MailPw}") String mailPw, @Value("${Local.naverClientId}") String naverClientId, @Value("${Local.naverSecret}")String naverSecret, @Value("${Local.googleClientId}") String googleClientId, @Value("${Local.googleSecret}") String googleSecret){
        ConfigProfile configProfile = new ConfigProfile();
        configProfile.setUrl(url);
        configProfile.setUserName(userName);
        configProfile.setPassword(password);
        configProfile.setIpAddress(ipAddress);
        configProfile.setUploadPath(uploadPath);
        configProfile.setMailPw(mailPw);
        configProfile.setNaverClientId(naverClientId);
        configProfile.setNaverSecret(naverSecret);
        configProfile.setGoogleClientId(googleClientId);
        configProfile.setGoogleSecret(googleSecret);

        return configProfile;
    }
    @Profile("server")
    @Bean
    public ConfigProfile serverConfig(@Value("${Server.Url}") String url,@Value("${Server.UserName}") String userName,@Value("${Server.Password}") String password,@Value("${Server.IpAddress}") String ipAddress,@Value("${Server.UploadPath}") String uploadPath,
                                @Value("${MailPw}") String mailPw, @Value("${Server.naverClientId}") String naverClientId, @Value("${Server.naverSecret}")String naverSecret, @Value("${Server.googleClientId}") String googleClientId, @Value("${Server.googleSecret}") String googleSecret){
        ConfigProfile configProfile = new ConfigProfile();
        configProfile.setUrl(url);
        configProfile.setUserName(userName);
        configProfile.setPassword(password);
        configProfile.setIpAddress(ipAddress);
        configProfile.setUploadPath(uploadPath);
        configProfile.setMailPw(mailPw);
        configProfile.setNaverClientId(naverClientId);
        configProfile.setNaverSecret(naverSecret);
        configProfile.setGoogleClientId(googleClientId);
        configProfile.setGoogleSecret(googleSecret);

        return configProfile;
    }
/*
    @Profile("local")
    @Bean
    public ConfigProfile config(){
        ConfigProfile configProfile = new ConfigProfile();
        configProfile.setUrl("jdbc:log4jdbc:mysql://127.0.0.1/thearc");
        configProfile.setUserName("thearc");
        configProfile.setPassword("thearc");
        configProfile.setIpAddress("localhost");
        configProfile.setUploadPath("C:\\\\zzz\\\\upload");
        configProfile.setMailPw("ekflrktmaajfl");
        configProfile.setNaverClientId("0k9ycdgXO65t3vGApBqf");
        configProfile.setNaverSecret("6t5nZggxgm");
        configProfile.setGoogleClientId("755792594137-ds9engajnsjvip5mvpetccoql5568af9.apps.googleusercontent.com");
        configProfile.setGoogleSecret("aP8wZE8SuNf6A1Q43qrKeT6U");

        return configProfile;
    }
*/

 /*    @Bean
    @Profile("server")
    public ConfigProfile config(){
        ConfigProfile configProfile = new ConfigProfile();
        configProfile.setUrl("");
        configProfile.setUserName("");
        configProfile.setPassword("");
        configProfile.setIpAddress("");
        configProfile.setUploadPath("");
        configProfile.setMailPw("");
        configProfile.setNaverClientId("");
        configProfile.setNaverSecret("");
        configProfile.setGoogleClientId("");
        configProfile.setGoogleSecret("");
    }
*/



}


    /*@Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }*/