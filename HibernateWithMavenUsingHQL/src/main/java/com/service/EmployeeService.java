package com.service;

import org.hibernate.Session;
import com.dao.EmployeeDAO;
import com.entity.Employee;
import java.util.Scanner;
import java.util.List;

public class EmployeeService {
	public void insertData(Session s,Scanner sc) {

		EmployeeDAO obj = new EmployeeDAO(s);
		Employee e = new Employee();
		try {
			System.out.println("Enter name");
			String name = sc.next();

			System.out.println("Enter age");
			while (!sc.hasNextInt()) {
				sc.next();
			}
			int age = sc.nextInt();
			System.out.println("Enter salary");
			while (!sc.hasNextInt()) {
				sc.next();
			}
			int salary = sc.nextInt();
			e.setAge(age);
			e.setName(name);
			e.setSalary(salary);
			obj.insertData(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<Employee> fetchData(Session s) {
		EmployeeDAO obj = new EmployeeDAO(s);
		List<Employee> allRecords = obj.fetchData();
		return allRecords;
	}

	public void UpdateData(Session s,Scanner sc) {
		EmployeeDAO obj = new EmployeeDAO(s);
		Employee e = new Employee();
		try {
			System.out.println("Enter the id to Update the record :");
			int id = sc.nextInt();
			e.setEmp_id(id);
			System.out.println("1. Update Name");
			System.out.println("2.Update salary");
			System.out.println("3.Update age");
			System.out.println("Enter choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter name ");
				String name = sc.next();
				e.setName(name);
				break;
			case 2:
				System.out.println("Enter salary ");
				int salary = sc.nextInt();
				e.setSalary(salary);
				break;
			case 3:
				System.out.println("Enter age");
				int age = sc.nextInt();
				e.setAge(age);
				break;
			default:
				System.out.println("Wrong choice");
			}
			obj.updateData(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deleteData(Session s,Scanner sc) {
		Employee e = new Employee();
		EmployeeDAO obj = new EmployeeDAO(s);
		System.out.println("Enter the id to delete ");
		int id = sc.nextInt();
		obj.deleteData(id);

	}
}
