package com.spring.orm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.spring.orm.entities.Student;

public class StudentDao {

	private HibernateTemplate template;
	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	

	@Transactional
	public int insert(Student s) {

		Integer r = (Integer) this.template.save(s);
		return r;
	}
	
	//get the single data(object)
	public Student get(int id) {
		Student s = this.template.get(Student.class, id);
		return s;
	}
	//get all students
	public List<Student> getAll(){
		List<Student> list = this.template.loadAll(Student.class);
		return list;
	}
	//deleting data
	@Transactional
	public void delete(int id) {
		Student s=this.template.get(Student.class, id);
		this.template.delete(s);
		System.out.println(s.getName()+" is deleted");
	}
	
	//updationg data
	@Transactional
	public void update(Student s) {
		this.template.update(s);
		System.out.println("\nUpdated");
	}

}
