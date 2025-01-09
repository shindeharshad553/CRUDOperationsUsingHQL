package com.dao;

import com.entity.Employee;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class EmployeeDAO {
	Session s;

	public EmployeeDAO(Session s) {
		this.s = s;
	}

	// CRUD operation methods using HQL
	public void insertData(Employee e) {
//		Query q = s.createQuery("insert into Employee (name,salary,age) values(:name,:salary,:age)");
//		q.setParameter("name", e.getName());
//		q.setParameter("salary", e.getSalary());
//		q.setParameter("age", e.getAge());
//		q.executeUpdate();
		s.persist(e);
	}

	public List<Employee> fetchData() {
		Query<Employee> q = s.createQuery("from Employee");
		return q.list();
	}

	public void updateData(Employee e) {

		Query q = s.createQuery("from Employee where emp_id=:id");
		q.setParameter("id", e.getEmp_id());
		Employee res = (Employee) q.uniqueResult();
		if (e.getName() != null)
			res.setName(e.getName());
		if (e.getAge() != 0)
			res.setAge(e.getAge());
		if (e.getSalary() != 0)
			res.setSalary(e.getSalary());

		Query q2 = s.createQuery("Update Employee set name=:n,age=:a,salary=:s where emp_id=:id");
		q2.setParameter("id", res.getEmp_id());
		q2.setParameter("n", res.getName());
		q2.setParameter("a", res.getAge());
		q2.setParameter("s", res.getSalary());
		int isUpdated = q2.executeUpdate();
		if (isUpdated > 0)
			System.out.println("Updated successfully");
		else
			System.out.println("No record found");

	}

	public void deleteData(int id) {
		Employee e = s.get(Employee.class, id);
		Query q = s.createQuery("delete from Employee where emp_id=:id");
		q.setParameter("id", id);
		int isDeleted = q.executeUpdate();
		if (isDeleted > 0)
			System.out.println("deleted successfully");
		else
			System.out.println("No record found");

	}
}
