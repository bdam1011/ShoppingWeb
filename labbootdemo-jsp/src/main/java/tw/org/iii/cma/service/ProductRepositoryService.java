package tw.org.iii.cma.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.org.iii.cma.dao.ProductDAO;
import tw.org.iii.cma.dao.ProductRepository;
import tw.org.iii.cma.domain.ProductBean;
//cf p.124
@Service
@Transactional
public class ProductRepositoryService {
	@Autowired
	private ProductRepository productRepository;
//	private ProductDAO productDao;

	
//select
	public List<ProductBean> select(ProductBean bean) {
		List<ProductBean> result = null;
		if(bean!=null && bean.getId()!=null && !bean.getId().equals(0)) {

//			ProductBean temp = productDao.select(bean.getId());
			Optional<ProductBean> optional = productRepository.findById(bean.getId());
			if(optional.isPresent()) {
				result = new ArrayList<ProductBean>();
				result.add(optional.get());
			}
			
//			if(temp!=null) {
//				result = new ArrayList<ProductBean>();
//				result.add(temp);
//			}
		} else {
//			result = productDao.select();
			result =productRepository.findAll();
		}
		return result;
	}
	
	
	
//insert	
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		if(bean!=null && bean.getId()!=null) {
//			result = productDao.insert(bean);
			boolean exist = productRepository.existsById(bean.getId());
			//若資料不存在，則insert
			if(!exist) {
				return productRepository.save(bean);
			}
		}
		return result;
	}

//update
	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if(bean!=null && bean.getId()!=null) {
//			result = productDao.update(bean.getName(), bean.getPrice(),
//					bean.getMake(), bean.getExpire(), bean.getId());
			boolean exist = productRepository.existsById(bean.getId());
			//若資料存在，則update
			if(exist) {
				return productRepository.save(bean);
			}
		}
		return result;
	}
	
//delete
	public boolean delete(ProductBean bean) {
		boolean result = false;
		if(bean!=null && bean.getId()!=null) {
//			result = productDao.delete(bean.getId());
			boolean exist = productRepository.existsById(bean.getId());
			//若資料存在，則delete
			if(exist) {
				productRepository.deleteById(bean.getId());
				result = true;
			}
		
		}
		return result;
	}
}
