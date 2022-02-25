package tw.org.iii.cma.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.org.iii.cma.domain.CustomerBean;

@SpringBootTest
public class CustomerDAOHibernateTests {
	@Autowired
	private CustomerDAO customerDao;
	
	@Test
	public void testSelect() {
		CustomerBean bean = customerDao.select("Babe");
		System.out.println("bean="+bean);
	}
	
	@Test
	public void testUpdate() {
		boolean result = customerDao.update("E".getBytes(),
				"ellen@lab.com", new java.util.Date(0), "Ellen");
		System.out.println("result="+result);
	}
}
