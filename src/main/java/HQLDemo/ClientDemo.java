package HQLDemo;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

public class ClientDemo {
	
	public static void main(String[] args) {
        ClientDemo operations = new ClientDemo();
        operations.addDepartment();
        //operations.delete();
        
    }
    public void addDepartment() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();
        Department d = new Department();
        d.setName("CSE0");
        d.setLocation("Vijayawada");
        d.setHoDname("Amarendra");
        session.persist(d);
        t.commit();
        System.out.println("Department Added Successfully");
        session.close();
        sf.close();
    }
    public void delete() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Department ID to delete:");
        int departmentId = sc.nextInt();
        String hql = "delete from Department where departmentId=?1";
        MutationQuery qry = session.createMutationQuery(hql);
        qry.setParameter(1, departmentId);
        int n = qry.executeUpdate();
        t.commit();
        if (n > 0) {
            System.out.println("Department Deleted Successfully");
        } else {
            System.out.println("Department ID Not Found");
        }
        sc.close();
        session.close();
        sf.close();
    }
    
}
