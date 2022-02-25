package tw.org.iii.cma.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.org.iii.cma.domain.ProductBean;

@SpringBootTest
public class ProductDAOHibernateTests {
	@Autowired
	private ProductDAO productDao;
	
	@Test
	public void testSelect() {
		List<ProductBean> beans = productDao.select();
		System.out.println("bean="+beans);
	}
	
	@Test
	public void testUpdate() {
		
	}

}
