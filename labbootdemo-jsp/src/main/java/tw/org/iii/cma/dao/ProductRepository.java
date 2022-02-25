package tw.org.iii.cma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.org.iii.cma.domain.ProductBean;

public interface ProductRepository 
			extends JpaRepository<ProductBean, Integer> {
	
}
