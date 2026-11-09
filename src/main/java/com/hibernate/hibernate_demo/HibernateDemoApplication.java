package com.hibernate.hibernate_demo;

import com.hibernate.hibernate_demo.dao.AppDao;
import com.hibernate.hibernate_demo.dao.StudentDao;
import com.hibernate.hibernate_demo.entity.Course;
import com.hibernate.hibernate_demo.entity.Instructor;
import com.hibernate.hibernate_demo.entity.InstructorDetail;
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
    // @Bean
    // CommandLineRunner commandLineRunner(StudentDao studentDao) {
    //     return runner -> {
    //         // createStudent(studentDao);
    //         // readStudent(studentDao);
    //         // queryStudents(studentDao);
    //         // queryStudentLastName(studentDao);
    //         // updateStudent(studentDao);
    //         // deleteStudent(studentDao);
    //         // deleteAllStudent(studentDao);
    //     };
    // }

    @Bean
    CommandLineRunner commandLineRunner(AppDao app) {
        return runner -> {
            // createInstructor(app);
            // findInstructor(app);
            // deleteInstructor(app);
            // findInstructorDetail(app);
            // deleteInstructorDetail(app);
            // createInstructorWithCourses(app);
            // findInstructorWithCourses(app);
            // findCoursesForInstructor(app);
            // findInstructorWithCoursesJoinFetch(app);
            // updateInstructor(app);
            deleteInstructorOnly(app);
        };
    }

    private void createInstructor(AppDao app) {
        // Instructor instructor = 
        //     new Instructor("Vicky",
        //                     "Magtatanggol",
        //                     "vicky.mags@example.com");

        // InstructorDetail deets = 
        //     new InstructorDetail("Math",
        //                     "Solving problems");

        Instructor instructor = 
            new Instructor("Google",
                            "Gemini",
                            "google.gemini@example.com");

        InstructorDetail deets = 
            new InstructorDetail("AI Generated Image",
                            "Solving problems");
                    
        // associate the objects
        instructor.setInstructorDeets(deets);

        // save the instructor
        // this will also save the details object
        // because of the CascadeType.ALL
        System.out.println("Information Saved!");
        app.save(instructor);
    }

    private void findInstructor(AppDao app) {
        int id = 1;
        System.out.println("Find ID: " + id);

        Instructor instructor = app.findInstructorById(id);

        System.out.println("Instructor: " + instructor);
        System.out.println("Details: " + instructor.getInstructorDeets());
    }

    private void deleteInstructor(AppDao app) {
        int id = 3;
        System.out.println("Delete ID: " + id);

        app.deleteInstructorWithCoursesById(id);
        System.out.println("Deleted Successfully!");
    }

    private void findInstructorDetail(AppDao app) {
        // get the instructor detail object
        int id = 2;
        InstructorDetail instructorDeets = app.findInstructorDetailById(id);
        
        // print the instructor detail
        System.out.println("Details: " + instructorDeets);

        // print the associated instructor
        System.out.println("Instructor: " + instructorDeets.getInstructor());
    }

    private void deleteInstructorDetail(AppDao app) {
        int id = 3; 
        System.out.println("Delete ID: " + id);

        app.deleteInstructorDetail(id);
        System.out.println("Deleted Successfully!");
    }

    private void createInstructorWithCourses(AppDao app) {
        // create the instructor details
        Instructor instructor =
            new Instructor("Peri",
                        "Winkle",
                        "peri.winkle@example.com");
        InstructorDetail deets =
            new InstructorDetail("Science",
                        "Sprinkling dust");

        // associate the objects
        instructor.setInstructorDeets(deets);

        // create courses
        Course course1 = new Course("Physics");
        Course course2 = new Course("Biology");

        // add courses to instructor
        instructor.add(course1);
        instructor.add(course2);

        // save the instructor
        System.out.println("Course Saved: " + instructor.getCourses());
        app.save(instructor);
    }

    private void findInstructorWithCourses(AppDao app) {
        int id = 4;
        System.out.println("Find Instructor ID: " + id);

        Instructor instructor = app.findInstructorById(id);

        System.out.println("Instructor: " + instructor);
        System.out.println("Courses: " + instructor.getCourses());
    }

    private void findCoursesForInstructor(AppDao app) {
        int id = 4;

        // find the instructor
        System.out.println("Find Instructor ID: " + id);
        Instructor instructor = app.findInstructorById(id);

        System.out.println("Instructor: " + instructor);

        // find courses for instructor
        System.out.println("Courses from ID: " + id);
        List<Course> courses = app.findCourseByInstructorId(id);

        // associate the objects
        instructor.setCourses(courses);

        // get the courses
        System.out.println(instructor.getCourses());
    }

    private void findInstructorWithCoursesJoinFetch(AppDao app) {
        int id = 4;
        System.out.println("Find Instructor ID: " + id);

        Instructor instructor = app.findInstructorByIdJoinFetch(id);

        System.out.println("Instructor: " + instructor);
        System.out.println("Courses: " + instructor.getCourses());
    }
    
    private void updateInstructor(AppDao app) {
        int id = 3;
        System.out.println("Find Instructor ID: " + id);

        Instructor instructor = app.findInstructorById(id);

        // change instructor's data
        instructor.setLastName("ChatGPT");

        // update the data
        System.out.println("Updated!");
        app.update(instructor);
    }

    private void deleteInstructorOnly(AppDao app) {
        int id = 4; 
        System.out.println("Delete Instructor: " + id);

        app.deleteInstructorById(id);
        System.out.println("Deleted Successfully!");
    }

    /**
     * Creates and saves a new Student entity to the database.
     *
     * @param studentDao data access object for Student entity operations
     */
    private void createStudent(final StudentDao studentDao) {
        System.out.println("Creating Student Information...");
        // Student student = new Student("Light", "Yagami",
        //         "light.yagami@example.com");

        // Student student = new Student("Dexter", "Lauron",
        //         "light.yagami@example.com");

        Student student = new Student("Romel", "Lauron",
                "light.yagami@example.com");

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
        List<Student> students = studentDao.findByLastName("Lauron");

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