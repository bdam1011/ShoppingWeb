package tw.org.iii.cma.dao;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import tw.org.iii.cma.domain.CustomerBean;


@Repository
public class CustomerDAOHibernate implements CustomerDAO {
	@PersistenceContext //JPA特有的
	private Session session;
	
	public Session getSession() {
		return this.session;
	}

	@Override
	public CustomerBean select(String custid) {
		if(custid!=null) {
			return this.getSession().get(CustomerBean.class, custid);
		}
		return null;
	}
	@Override
	public boolean update(byte[] password, String email, java.util.Date birth, String custid) {
		if(custid!=null) {
			CustomerBean temp = this.getSession().get(CustomerBean.class, custid);
			if(temp!=null) {
				temp.setPassword(password);
				temp.setEmail(email);
				temp.setBirth(birth);
				return true;
			}
		}
		return false;
	}
}
