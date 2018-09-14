package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			int studentID = 1;
			
			// start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();	
			
			// retrieve student based on id: primary key
			System.out.println("\nGetting student with id: " + studentID);
			
			
			Student myStudent = session.get(Student.class, studentID);
			
			System.out.println("Updating student...");
			myStudent.setFirstName("Scooby");
			
			session.getTransaction().commit();
			
			//  NEW CODE
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			System.out.println("Updating email for all students:");
			
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
