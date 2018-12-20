package com.thearc.persistence;

import com.thearc.config.RootConfig;
import com.thearc.config.SecurityConfig;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.fail;

/**
 * @author 허정원
 * since 2018-10-09
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-10-09
 *
 * </pre>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, SecurityConfig.class})
@Log4j
@ActiveProfiles("local")
public class DataSourceTests {

@Setter(onMethod_ ={@Autowired})
private DataSource dataSource;

@Test
public void testConnection(){
    try(Connection con = dataSource.getConnection()){
        log.info(con);
    }catch(Exception e){
        fail(e.getMessage());
    }
}

}
