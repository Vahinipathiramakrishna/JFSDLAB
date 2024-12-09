package CRUDOperations;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Controller {
	
	public static void main(String args[])
	{
		Controller controller = new Controller();
		//controller.addfaculty();
		controller.displayfacultybyid();
		//controller.updatefaculty();
		//controller.deletefaculty();
	}
	public void addfaculty()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t= session.beginTransaction();
		Scanner sc1=new Scanner(System.in);
		
		int id = sc1.nextInt();
		String name = sc1.nextLine();
		String gender = sc1.nextLine();
		String department = sc1.nextLine();
		double salary = sc1.nextDouble();
		String contactno = sc1.nextLine();
		
		Faculty faculty = new Faculty();
		faculty.setId(id);
		faculty.setName(name);
		faculty.setGender(gender);
		faculty.setDepartment(department);
		faculty.setSalary(salary);
		faculty.setContactno(contactno);
		
		session.persist(faculty);
		t.commit();
		
		System.out.println("Faculty Added Successfully");
		sc1.close();
		session.close();
		sf.close();
		
	}
	//display or find faculty based on ID Column 
	public void displayfacultybyid()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		//There is no need to create transaction object because there is no DML Operation
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter faculty ID:");
		int fid=sc.nextInt();
		
		Faculty faculty = session.find(Faculty.class, fid);
		if(faculty!=null)
		{
			System.out.println("Faculty ID:" +faculty.getId());
			System.out.println("Faculty Name:" +faculty.getName());
			System.out.println("Faculty Gender:" +faculty.getGender());
			System.out.println("Faculty Department:" +faculty.getDepartment());
			System.out.println("Faculty Salary:" +faculty.getSalary());
			System.out.println("Faculty Contact No:" +faculty.getContactno());
		}
		else
		{
			System.out.println("Faculty ID Not Found");
		}
		sc.close();
		session.close();
		sf.close();
	}
	public void updatefaculty()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction t= session.beginTransaction();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter faculty ID:");
		int fid=sc.nextInt();
		
		Faculty faculty = session.find(Faculty.class, fid);
		if(faculty!=null)
		{
			
			System.out.println("Enter Faculty Name:" );
			String fname=sc.next();
			System.out.println("Enter Faculty Salary:" );
			double fsalary = sc.nextDouble();
			System.out.println("Enter Faculty Contact No:");
			String fcontact =sc.next();
			
			faculty.setName(fname);
			faculty.setSalary(fsalary);
			faculty.setContactno(fcontact);
			
			t.commit();
			System.out.println("Faculty Updated Succesfully");
			
		}
		else
		{
			System.out.println("Faculty ID Not Found");
		}
		sc.close();
		session.close();
		sf.close();
		
		
	}
	public void deletefaculty()
	{
		
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction t= session.beginTransaction();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter faculty ID:");
		int fid=sc.nextInt();
		
		Faculty faculty = session.find(Faculty.class, fid);
		if(faculty!=null)
		{
			session.remove(faculty);
			t.commit();
			System.out.println("Faculty Deleted Successfully");
		}
		else
		{
			System.out.println("Faculty ID Not Found");
		}
		
		sc.close();
	}

}
