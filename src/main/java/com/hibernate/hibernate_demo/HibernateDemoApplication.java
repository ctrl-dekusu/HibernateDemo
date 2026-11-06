package com.hibernate.hibernate_demo;

import com.hibernate.hibernate_demo.dao.StudentDao;
import com.hibernate.hibernate_demo.entity.Student;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main application class for running Spring Boot Hibernate demo operations.
 */
@SpringBootApplication
public class HibernateDemoApplication {

    /**
     * Main entry point of the application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(final String[] args) {
        SpringApplication.run(HibernateDemoApplication.class, args);
    }

    /**
     * Executes database commands upon application startup.
     *
     * @param studentDao data access object for Student entity operations
     * @return a CommandLineRunner instance to run on startup
     */
    @Bean
    CommandLineRunner commandLineRunner(final StudentDao studentDao) {
        return runner -> {
            // createStudent(studentDao);
            // readStudent(studentDao);
            // queryStudents(studentDao);
            // queryStudentLastName(studentDao);
            // updateStudent(studentDao);
            // deleteStudent(studentDao);
            deleteAllStudent(studentDao);
        };
    }

    /**
     * Creates and saves a new Student entity to the database.
     *
     * @param studentDao data access object for Student entity operations
     */
    private void createStudent(final StudentDao studentDao) {
        System.out.println("Creating Student Information...");
        Student student = new Student("Light", "Yagami",
                "light.yagami@example.com");

        // Student student = new Student("Dexter", "Lauron",
        //         "light.yagami@example.com");

        // Student student = new Student("Romel", "Lauron",
        //         "light.yagami@example.com");

        System.out.println("Saving Student Information...");
        studentDao.save(student);

        System.out.println("Saved! Generated ID: " + student.getId());
    }

    /**
     * Reads and prints a Student entity by its primary key ID.
     *
     * @param studentDao data access object for Student entity operations
     */
    private void readStudent(final StudentDao studentDao) {
        Student student = studentDao.findById(1);
        System.out.println("Found the student: " + student);
    }

    /**
     * Queries and displays all Student records from the database.
     *
     * @param studentDao data access object for Student entity operations
     */
    private void queryStudents(final StudentDao studentDao) {
        List<Student> students = studentDao.findAll();

        for (Student s : students) {
            System.out.println(s);
        }
    }

    /**
     * Queries and displays Student records matching a given last name.
     *
     * @param studentDao data access object for Student entity operations
     */
    private void queryStudentLastName(final StudentDao studentDao) {
        List<Student> students = studentDao.findByLastName("Yagami");

        for (Student s : students) {
            System.out.println(s);
        }
    }

    /**
     * Updates an existing Student entity's information in the database.
     *
     * @param studentDao data access object for Student entity operations
     */
    private void updateStudent(final StudentDao studentDao) {
        int id = 2;
        System.out.println("Student ID: " + id);
        Student student = studentDao.findById(id);

        System.out.println("Updating student name...");
        student.setFirstName("Dark");

        studentDao.update(student);
        System.out.println("Updated Student Information: "
                + student);
    }

    /**
     * Deletes a Student entity from the database by primary key ID.
     *
     * @param studentDao data access object for Student entity operations
     */
    private void deleteStudent(final StudentDao studentDao) {
        int id = 1;
        System.out.println("Delete ID: " + id);

        studentDao.delete(id);
    }

    /**
     * Deletes all Student entities from the database.
     *
     * @param studentDao data access object for Student entity operations
     */
    private void deleteAllStudent(final StudentDao studentDao) {
        System.out.println("Deleting All Student Information...");
        int rowsDeleted = studentDao.deleteAll();
        System.out.println("Number of deleted rows: " + rowsDeleted);
    }
}