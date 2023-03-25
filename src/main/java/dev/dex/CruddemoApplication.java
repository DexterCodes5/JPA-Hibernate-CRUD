package dev.dex;

import dev.dex.dao.*;
import dev.dex.entity.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;

import java.util.*;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		// After all the beans are created the commandLineRunner will execute it's method
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
//				createStudent(studentDAO);
				createMultipleStudents(studentDAO);
				readStudent(studentDAO);
//				readByLastName(studentDAO);
//				updateStudent(studentDAO);
//				deleteStudent(studentDAO);
//				deleteStudentByLastName(studentDAO);
//				deleteAll(studentDAO);
 				readAllStudents(studentDAO);
			}
		};
	}

	private void deleteAll(StudentDAO studentDAO) {
		System.out.println("Deleted rows " + studentDAO.deleteAll());
	}

	private void deleteStudentByLastName(StudentDAO studentDAO) {
		studentDAO.deleteByLastName("Public");
	}

	private void deleteStudent(StudentDAO studentDAO) {
		studentDAO.delete(3);
	}

	private void updateStudent(StudentDAO studentDAO) {
		Student student = studentDAO.findById(1);
		student.setFirstName("John");
		studentDAO.update(student);
	}

	private void readByLastName(StudentDAO studentDAO) {
		List<Student> list = studentDAO.findByLastName("Doe");
		for (var s: list) {
			System.out.println(s);
		}
	}

	private void readAllStudents(StudentDAO studentDAO) {
		List<Student> list = studentDAO.findAll();
		for (var s: list) {
			System.out.println(s);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		// create Student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@mit.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display the id of the saved student
		int id = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + id);

		// retrieve student based on the id: primary key
		System.out.println("\nRetrieving student with id: " + id);
		Student s = studentDAO.findById(id);
		System.out.println(s);

	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// create multiple students
		System.out.println("Creating 3 student objects...");
		Student s1 = new Student("Paul", "Doe", "paul@mit.com");
		Student s2 = new Student("Mary", "Public", "mary@mit.com");
		Student s3 = new Student("Bonita", "Applebum", "bonita@mit.com");

		// save the student objects
		System.out.println("Saving the students...");
		studentDAO.save(s1);
		studentDAO.save(s2);
		studentDAO.save(s3);


	}


	private void createStudent(StudentDAO studentDAO) {
		// create Student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("John", "Doe", "john2@mit.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display the id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());

	}

}
