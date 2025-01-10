package com.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entities.Laptop;
import com.entities.Student;

public class MainClass {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Laptop.class);
		cfg.addAnnotatedClass(Student.class);
		SessionFactory sf = cfg.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();

		Laptop l = new Laptop();
		l.setL_id(12934);
		l.setL_name("dell");

		Student stud = new Student();
		stud.setS_id(1);
		stud.setS_name("harshad");
		stud.setLaptop(l);
		l.setStudent(stud);
		
		s.persist(l);
		s.persist(stud);
		
		tr.commit();
		s.close();

	}

}
