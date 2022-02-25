package tw.org.iii.cma.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import tw.org.iii.cma.domain.ProductBean;



@Repository
public class ProductDAOHibernate implements ProductDAO {
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}
	
	@Override
	public ProductBean select(Integer id) {
		if(id!=null) {
			return this.getSession().get(ProductBean.class, id);
		}
		return null;
	}
	
	@Override
	public List<ProductBean> select() {
		return this.getSession().createQuery(
				"FROM ProductBean", ProductBean.class).list();
	}
	
	@Override
	public ProductBean insert(ProductBean bean) {
		if(bean!=null && bean.getId()!=null) {
			ProductBean temp = this.getSession().get(ProductBean.class, bean.getId());
			if(temp==null) {
				Serializable pk = this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}
	
	@Override
	public ProductBean update(String name, Double price,
			java.util.Date make, Integer expire, Integer id) {
		if(id!=null) {
			ProductBean temp = this.getSession().get(ProductBean.class, id);
			if(temp!=null) {
				temp.setName(name);
				temp.setPrice(price);
				temp.setMake(make);
				temp.setExpire(expire);
				return temp;
			}
		}
		return null;
	}
	
	public ProductBean update(ProductBean bean) {
		if(bean!=null && bean.getId()!=null) {
			ProductBean temp = this.getSession().get(ProductBean.class, bean.getId());
			if(temp!=null) {
				return (ProductBean) this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id!=null) {
			ProductBean temp = this.getSession().get(ProductBean.class, id);
			if(temp!=null) {
				this.getSession().delete(temp);
				return true;
			}
		}
		return false;
	}
}
