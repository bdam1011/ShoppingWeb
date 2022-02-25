package tw.org.iii.cma.dao;

import tw.org.iii.cma.domain.CustomerBean;

public interface CustomerDAO {
	public abstract CustomerBean select(String custid);
	public abstract boolean update(byte[] password, String email,
			java.util.Date birth, String custid);
}