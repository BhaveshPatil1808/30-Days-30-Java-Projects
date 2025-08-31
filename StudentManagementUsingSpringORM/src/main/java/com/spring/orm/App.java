package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;


public class App {


	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

		StudentDao dao = context.getBean("studentDao", StudentDao.class);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean go=true;
		while(go) {
			System.out.println("1. Add Student");
			System.out.println("2. View all Student");
			System.out.println("3. get Student");
			System.out.println("4. delete Student");
			System.out.println("5. update Student");
			System.out.println("6. exit");
			System.out.println("\nEnter choice:-");
			try {
				int c = Integer.parseInt(br.readLine());
				switch(c) {
				case 1:
					System.out.println("Enter student id:");
					int id=Integer.parseInt(br.readLine());
					System.out.println("Enter Student Name:");
					String name=br.readLine();
					System.out.println("Enter Student City:-");
					String city=br.readLine();
					
					Student student = new Student(id,name,city);
					int r=dao.insert(student);
					System.out.println("Student inserted..");
					break;
				case 2:
					List<Student> list = dao.getAll();
					for(Student s:list) {
						System.out.println("Id:-"+s.getId()+"Name:-"+s.getName()+" City:-"+s.getCity());
					}
					break;
				case 3:
					System.out.println("Enter id:");
					int sid=Integer.parseInt(br.readLine());
					Student s=dao.get(sid);
					System.out.println("Id:-"+s.getId()+"Name:-"+s.getName()+" City:-"+s.getCity());
					break;
				case 4:
					System.out.println("Enter id:");
					int x=Integer.parseInt(br.readLine());
					dao.delete(x);
					break;
				case 5:
					System.out.println("Enter id:");
					int s_id=Integer.parseInt(br.readLine());
					System.out.println("Enter name:");
					String uName=br.readLine();
					System.out.println("Enter City:");
					String uCity=br.readLine();
					Student update=new Student(s_id,uName,uCity);
					dao.update(update);
					break;
				case 6:
					System.out.println("Thank you.....");
					go=false;
					break;
				default:
					System.out.println("Enter valid input...");
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
				System.out.println(e.getMessage());
			}
		}

	}
}
