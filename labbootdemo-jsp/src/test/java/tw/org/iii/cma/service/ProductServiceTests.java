package tw.org.iii.cma.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.org.iii.cma.domain.ProductBean;

@SpringBootTest
public class ProductServiceTests {
	@Autowired
	private ProductService productService;

	@Test
	public void testSelects() {
		List<ProductBean> selects = productService.select(null);
		System.out.println("selects=" + selects);
	}
}
