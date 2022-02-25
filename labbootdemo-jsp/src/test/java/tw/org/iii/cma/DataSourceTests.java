package tw.org.iii.cma;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataSourceTests {
	@Autowired
	private SessionFactory sessionFactory;
	@Test
	public void testSessionFactory() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		List list = session.createNativeQuery("select * from emp").list();
		if(list!=null && !list.isEmpty()) {
			for(Object obj : list) {
				Object[] array = (Object[]) obj;
				System.out.println(array[0]+":"+array[1]+":"+array[2]);
			}
		}
		
		transaction.commit();
		session.close();
	}
	
	
	
	
	@Autowired
	private DataSource dataSource;

	@Test
	public void testDataSource() throws Exception {
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select * from dept");
		while (rset.next()) {
			String col1 = rset.getString(1);
			String col2 = rset.getString(2);
			System.out.println(col1 + ":" + col2);
		}
		rset.close();
		stmt.close();
		conn.close();
	}
	
}
