package HCQLDemo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.build.AllowSysOut;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HCQLOperations
{
	public static void main(String args[])
	{
		HCQLOperations operations = new HCQLOperations();
		//operations.addStudent();	
		operations.restrictionsdemo();
		//operations.orderdemo();
		//perations.aggregatefunctions();
		//operations.hcqldemo();//use case
		
	}
	//add student by using persistent Object(PO)
	
	public void addStudent()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t= session.beginTransaction();
		
		
		 Student student = new Student();
         student.setId(104);
         student.setName("MSWD");
         student.setGender("FEMALE");
         student.setAge(50.5);
         student.setDepartment("ECE");
         student.setEmail("mswd@gmail.com");
         student.setContact("7418529630");
     
         session.persist(student);
         t.commit();
         
         System.out.println("Student Added Successfully");
     
        session.close();
         sf.close();
	}
	public void restrictionsdemo()
	   {
	     Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        
	        SessionFactory sf = configuration.buildSessionFactory();
	        Session session = sf.openSession();
	     
	        /*CriteriaBuilder cb = session.getCriteriaBuilder();
	        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
	        // from Student; [Complete Object]
	        Root<Student> root = cq.from(Student.class);
	        System.out.println("****All Student Objects****");
	        List<Student> students =  session.createQuery(cq).getResultList();
	        System.out.println("Students Count="+students.size());
	        for(Student s : students)
	        {
	          // use getter methods to print every property in Student object (s)
	          System.out.println(s.toString());
	        }*/
	        
	       /*CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<Student> cq = cb.createQuery(Student.class);
	      // from Student; [Complete Object]
	      Root<Student> root = cq.from(Student.class);
	      cq.select(root).where(cb.equal(root.get("gender"), "FEMALE"));
	      System.out.println("****Student Objects with equal criteria****");
	      List<Student> students =  session.createQuery(cq).getResultList();
	      System.out.println("Students Count="+students.size());
	      for(Student s : students)
	      {
	        // use getter methods to print every property in Student object (s)
	        System.out.println(s.toString());
	      }*/
	
	          
	             /*CriteriaBuilder cb = session.getCriteriaBuilder();
	             CriteriaQuery<Student> cq = cb.createQuery(Student.class);
	             // from Student; [Complete Object]
	             Root<Student> root = cq.from(Student.class);
	             System.out.println("****All Student Objects****");
	             List<Student> students =  session.createQuery(cq).getResultList();
	             System.out.println("Students Count="+students.size());
	             for(Student s : students)
	             {
	               // use getter methods to print every property in Student object (s)
	               System.out.println(s.toString());
	             }*/
	         CriteriaBuilder cb = session.getCriteriaBuilder();
		      CriteriaQuery<Student> cq = cb.createQuery(Student.class);
		      // from Student; [Complete Object]
		      Root<Student> root = cq.from(Student.class);
		      //cq.select(root).where(cb.greaterThan(root.get("age"), 30));
		      //cq.select(root).where(cb.greaterThan(root.get("age"),40));
		      //cq.select(root).where(cb.le(root.get("age"), 50));
		      //cq.select(root).where(cb.ge(root.get("age"), 40));
		      //cq.select(root).where(cb.notEqual(root.get("department"), "CSE"));
		      //cq.select(root).where(cb.like(root.get("name"), ""));
		      //cq.select(root).where(cb.like(root.get("department"), "C%"));
		      //cq.select(root).where(cb.like(root.get("department"), "%E"));
		      //cq.select(root).where(cb.like(root.get("email"), "%gmail%"));
		      //cq.select(root).where(cb.like(root.get("name"), "K__"));
		      //cq.select(root).where(cb.between(root.get("age"), 20, 50)); //between 20 and 50
		      
		      // not with any existing criteria
		      //cq.select(root).where(cb.not(cb.equal(root.get("department"),"ECE")));
		      List<String> depts = Arrays.asList("CSE","ECE","ME");
		      cq.select(root).where(root.get("department").in(depts)); //set of values
		   
		    		  
		      System.out.println("****Student Objects with equal criteria****");
		      List<Student> students =  session.createQuery(cq).getResultList();
		      System.out.println("Students Count="+students.size());
		      for(Student s : students)
		      {
		        // use getter methods to print every property in Student object (s)
		        System.out.println(s.toString());
		      }
	           
	        session.close();
	        sf.close();
	        
	   }
	
	public void orderdemo()//ascending //descending
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);
        
        //ascending order based on age
        //cq.orderBy(cb.asc(root.get("age")));
        
        //descending order based on name
        cq.orderBy(cb.desc(root.get("name")));
        
        
        System.out.println("****Order by Demo****");
        List<Student> students =  session.createQuery(cq).getResultList();
        System.out.println("Students Count="+students.size());
        for(Student s : students)
        {
          System.out.println(s.toString());
        }
        session.close();
        sf.close();
		
	}
	public void aggregatefunctions()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		//1  Count
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
        CriteriaQuery<Long> cq1 = cb1.createQuery(Long.class);
        Root<Student> root1 = cq1.from(Student.class);
        cq1.select(cb1.count(root1.get("name")));
        long totalcount = session.createQuery(cq1).getSingleResult();
        System.out.println("Total Students Count = "+totalcount);
        
        //2  Sum
        CriteriaBuilder cb2 = session.getCriteriaBuilder();
        CriteriaQuery<Double> cq2 = cb2.createQuery(Double.class);
        Root<Student> root2 = cq2.from(Student.class);
        cq2.select(cb2.sum(root2.get("age")));
        double totalage = session.createQuery(cq2).getSingleResult();
        System.out.println("Total Students Age = "+totalage);
        
        //3  Average
        CriteriaBuilder cb3 = session.getCriteriaBuilder();
        CriteriaQuery<Double> cq3 = cb3.createQuery(Double.class);
        Root<Student> root3 = cq3.from(Student.class);
        cq3.select(cb3.avg(root3.get("age")));
        double avgage = session.createQuery(cq3).getSingleResult();
        System.out.println("Average Students Age = "+avgage);
        
        //4  Minimum
        CriteriaBuilder cb4 = session.getCriteriaBuilder();
        CriteriaQuery<Integer> cq4 = cb4.createQuery(Integer.class);
        Root<Student> root4 = cq4.from(Student.class);
        cq4.select(cb4.min(root4.get("id")));
        Integer minsid = session.createQuery(cq4).getSingleResult();
        System.out.println("Minimum Student ID = "+minsid);
        
        //5  Maximum
        CriteriaBuilder cb5 = session.getCriteriaBuilder();
        CriteriaQuery<Integer> cq5 = cb5.createQuery(Integer.class);
        Root<Student> root5 = cq5.from(Student.class);
        cq5.select(cb5.max(root5.get("id")));
        Integer maxsid = session.createQuery(cq5).getSingleResult();
        System.out.println("Maximum Student ID = "+maxsid);
        
        //6  DistictCount
        CriteriaBuilder cb6 = session.getCriteriaBuilder();
        CriteriaQuery<Long> cq6 = cb6.createQuery(Long.class);
        Root<Student> root6 = cq6.from(Student.class);
        cq6.select(cb6.countDistinct(root6.get("department")));
        long distinctcount = session.createQuery(cq6).getSingleResult();
        System.out.println("Distinct Department Count = "+distinctcount);
        
        session.close();
        sf.close();
		
	}
	//display students of CSE Department based on age in ascending order
	public void hcqldemo()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);
        
        cq.select(root).where(cb.equal(root.get("department"), "CSE"));
        cq.orderBy(cb.asc(root.get("age")));
        
        List<Student> students =  session.createQuery(cq).getResultList();
        System.out.println("Students Count="+students.size());
        for(Student s : students)
        {
          System.out.println(s.toString());
        }
        session.close();
        sf.close();
	}

}
