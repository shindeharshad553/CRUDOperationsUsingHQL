package com.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import com.entities.Department;
import com.entities.Employee;
import java.util.List;
import java.util.ArrayList;

public class MainClass {
	public static void main(String args[]) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Department.class);
		cfg.addAnnotatedClass(Employee.class);
		SessionFactory sf = cfg.buildSessionFactory();
		try (Session s = sf.openSession()) {
			Transaction tr = s.beginTransaction();

			Employee emp1 = new Employee();
			Employee emp2 = new Employee();
			Department d = new Department();
			d.setDept_id(101);
			d.setDept_name("Developer");

			emp1.setEmp_name("Jay");
			emp1.setDept(d);

			emp2.setEmp_name("Rakesh");
			emp2.setDept(d);

			d.getEmp().add(emp1);
			d.getEmp().add(emp2);

			s.persist(d);
			s.persist(emp1);
			s.persist(emp2);

			Department d1 = new Department();
			d1.setDept_id(102);
			d1.setDept_name("devops");

			Employee emp3 = new Employee();
			emp3.setEmp_name("Pankaj");
			emp3.setDept(d1);
			d1.getEmp().add(emp3);
			s.persist(d1);
			s.persist(emp3);

			Employee emp4 = new Employee();
			emp4.setEmp_name("Jayesh");
			emp4.setDept(d1);
			d1.getEmp().add(emp4);
			
			s.persist(emp4);
			s.persist(d1);
			
			tr.commit();
			s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
