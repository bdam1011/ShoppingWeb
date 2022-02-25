package tw.org.iii.cma;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
//run as JUnit Test
//JUnit Test用於測試程式
//確保測試程式放在
///labbootdemo-jsp/src/test/java下的tw.org.iii.cma package下，
//就可用@SpringBootTest自動掃描測試檔
@SpringBootTest
class LabbootdemoJspApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MessageSource messageSource;
	
	@Test
	public void testMessageSource() {
		String mess1 = messageSource.getMessage(
				"login.username.required", null,Locale.TAIWAN);
		System.out.println("mess1="+mess1);
	}
	

}
