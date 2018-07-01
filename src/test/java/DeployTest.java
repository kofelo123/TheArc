
import com.thearc.domain.LoginDTO;
import com.thearc.domain.UserVO;
import com.thearc.persistence.UserDAOImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/test-root-context.xml"})
@ContextConfiguration(locations={"classpath:spring/test-root-context.xml"})

/**
 * TestCode Practice
 * @author 허정원
 * @since 2018.05.05
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일                수정자           수정내용
 *  -------    --------    ---------------------------
 *   2018.05.05  허정원          최초 생성
 *
 * </pre>
 */
public class DeployTest {

    @Inject
    private DataSource ds;
    @Inject
    private UserDAOImpl dao;
    private LoginDTO loginDTO;

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
        loginDTO.setUpw("test_thearc");
        UserVO findUser = dao.login(loginDTO);

        //검증
        assertThat(findUser.getUid(),is(loginDTO.getUid()));

        logger.info("==Test Login==");
    }



}
