package com.controller;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.entity.Employee;
import com.service.EmployeeService;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass {

	public static void main(String[] args) {
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		// Initialize Hibernate Configuration
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Employee.class);

		// Create SessionFactory
		try (SessionFactory sf = cfg.buildSessionFactory(); Scanner sc = new Scanner(System.in)) {
			EmployeeService obj = new EmployeeService();
			System.out.println("********CRUD operations using HQL********");
			System.out.println("1. Insert Data");
			System.out.println("2. Read Data");
			System.out.println("3. Update Data");
			System.out.println("4. Delete Data");
			System.out.println("5. Exit");

			int choice = -1;
			do {
				System.out.println("\nEnter the choice:");
				while (!sc.hasNextInt()) {
					sc.next();
				}
				choice = sc.nextInt();
				sc.nextLine();
				try (Session s = sf.openSession()) {
					switch (choice) {
					case 1:
						// Insert data
						Transaction tr1 = s.beginTransaction();
						obj.insertData(s,sc);
						tr1.commit();
						break;
					case 2:
						// Fetch data
						List<Employee> allEmployees = obj.fetchData(s);
						for (Employee emp : allEmployees) {
							System.out.println(emp);
						}
						break;
					case 3:
						// Update data
						Transaction tr2 = s.beginTransaction();
						obj.UpdateData(s,sc);
						tr2.commit();
						break;
					case 4:
						// Delete data
						Transaction tr3 = s.beginTransaction();
						obj.deleteData(s,sc);
						tr3.commit();
						break;
					case 5:
						System.out.println("Exiting the program...");
						break;
					default:
						System.out.println("Invalid choice. Please try again.");
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("An error occurred during the operation.");
				}
			} while (choice != 5);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
