package GeneratorClassDemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class Demo {

	public static void main(String[] args) {
		
		Demo demo = new Demo();
		//demo.addEmployee();
		demo.displayallemps();

	}
	public void addEmployee()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t= session.beginTransaction();
		
		Employee e=new Employee();
		//where id is auto increment(default)
		e.setName("EP");
		e.setGender("MALE");
		e.setContact("4568971237");
		
         session.persist(e);
         t.commit();
         
         System.out.println("Employee Added Successfully");
     
        session.close();
         sf.close();
	}
	public void displayallemps()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		String hql = "from Employee";//select  * from employee_table
		Query<Employee> qry = session.createQuery(hql,Employee.class);
		List<Employee> emps = qry.getResultList();
		
		System.out.println("Total Employees = "+emps.size());
		
		for(Employee e : emps)
		{
			System.out.println("ID = "+e.getId());
			System.out.println("NAME = "+e.getName());
			System.out.println("GENDER = "+e.getGender());
			System.out.println("CONTACT = "+e.getContact());
		}
		session.close();
		sf.close();
		
		
	}

}
