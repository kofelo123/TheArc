import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
  *
  * @author Jeongwon Heo
  * since 2018. 7. 5.
  * <pre>
  * << 개정이력(Modification Information) >>
  *
  *      수정일                   수정자                수정내용
  *  -----------    --------    ---------------------------
  *  2018. 7. 5.     허정원                  유효성검사에 쓰일 테스트작성
  *
  * </pre>
  */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring/root-context.xml",
								 "classpath:spring/servlet-context.xml"})	
public class ValidTest {

	private static final Logger logger = LoggerFactory.getLogger(ValidTest.class);

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.info("setup...");
	}
	
	/*
	@Test
	public void testDoA() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.
						 post("/user/idcheck2").
						 );
		
		mockMvc.perform(MockMvcRequestBuilders.post("/user/idcheck2"));
		

					
	}
*/	
}
