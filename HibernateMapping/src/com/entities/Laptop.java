package com.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Laptop {
	@Id
	private int l_id;
	private String l_name;
	@OneToOne(mappedBy="laptop")
	private Student student;

	public Laptop() {
	}

	public Laptop(int l_id, String l_name, Student student) {
		super();
		this.l_id = l_id;
		this.l_name = l_name;
		this.student = student;
	}

	public int getL_id() {
		return l_id;
	}

	public void setL_id(int l_id) {
		this.l_id = l_id;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Laptop [l_id=" + l_id + ", l_name=" + l_name + ", student" + student + "]";
	}

}
