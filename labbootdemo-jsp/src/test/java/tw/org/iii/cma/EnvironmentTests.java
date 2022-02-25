package tw.org.iii.cma;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
public class EnvironmentTests {
	
	@Value(value = "${login.fail.lock.count}")
	private Integer lockCount;
	
	@Autowired
	private Environment env;
	
	@Test
	public void testCustomProperties() {
		System.out.println("lockCount="+lockCount);
		Integer waitMinute = env.getProperty("login.fail.wait.min", Integer.class);
		System.out.println("waitMinute="+waitMinute);
	
	}
	
}
