import com.thearc.domain.ConfigProfile;
import com.thearc.domain.LoginDTO;
import com.thearc.domain.UserVO;
import com.thearc.service.UserServiceImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;

import java.sql.Connection;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/test-root-context.xml",
                                "classpath:spring/root-context.xml",
                                "classpath:/spring/security-context.xml"})
@ActiveProfiles("server")
//@Profile("server")
public class DeployTest {

    @Autowired
    private DataSource ds;

    @Autowired
    private UserServiceImpl userService;
    
    private LoginDTO loginDTO;

    @Autowired
    private ConfigProfile configProfile;

    private static Logger logger = LoggerFactory.getLogger(DeployTest.class);

    @Test
    public void testConnection()throws Exception{



        try(Connection con = ds.getConnection()){
            logger.info("==Test DataSource Connection==");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testLogin()throws  Exception{
        loginDTO = new LoginDTO();
        loginDTO.setUid("test_thearc");
//        loginDTO.setUpw("test_thearc");
        loginDTO.setUpw("$2a$10$tI56mHDKbJRDp01OqXCPyOKYD2NEaKWeW8vedeSHkp4jLs4U2G76C");
        UserVO findUser = userService.testLogin(loginDTO);

        //검증
        assertThat(findUser.getUid(),is(loginDTO.getUid()));

        logger.info("==Test Login==");
    }

}