import lombok.extern.log4j.Log4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 허정원
 * since 2018-12-12
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-12-12
 *
 * </pre>
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath:spring/root-context.xml",
        "classpath:/spring/security-context.xml"})
@Log4j
@ActiveProfiles("local")
public class JDBCEncryptor {

//    @Ignore
    @Test
    public void testJDBCEncryptor(){

            StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
            enc.setPassword("rktwlsrud");


            log.info(enc.encrypt("755792594137-ds9engajnsjvip5mvpetccoql5568af9.apps.googleusercontent.com"));
            log.info(enc.encrypt("aP8wZE8SuNf6A1Q43qrKeT6U"));
            log.info(enc.encrypt("755792594137-44ffp2ghof9gkjt3ua8b6a797n5v6dop.apps.googleusercontent.com"));
            log.info(enc.encrypt("moIgU14cGNtS9TcTfXOymJpp"));

            //        enc.setPassword("rktwlsrud");
            //        System.out.println(enc.encrypt("C:\\zzz\\upload"));
            //        System.out.println(enc.encrypt("ekflrktmaajfl"));
            //        System.out.println(enc.encrypt(""));

            }

}
