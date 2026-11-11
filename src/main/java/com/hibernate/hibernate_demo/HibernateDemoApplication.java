package com.hibernate.hibernate_demo;

import com.hibernate.hibernate_demo.dao.AppDao;
import com.hibernate.hibernate_demo.dao.StudentDao;
import com.hibernate.hibernate_demo.entity.Course;
import com.hibernate.hibernate_demo.entity.Instructor;
import com.hibernate.hibernate_demo.entity.InstructorDetail;
import com.hibernate.hibernate_demo.entity.Review;
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

  /**
   * Executes application database operations on startup using AppDao.
   *
   * @param app data access object for application entity operations
   * @return a CommandLineRunner instance to run on startup
   */
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
      // deleteInstructorOnly(app);
      // createCourseAndReviews(app);
      // retrieveCourseAndReviews(app);
      // deleteCourseAndReviews(app);
      // createCourseAndStudents(app);
      // findCourseAndStudents(app);
      // findStudentAndCourses(app);
      // addMoreCoursesForStudent(app);
      // deleteCourseById(app);
      // deleteStudent(app);
    };
  }

  /**
   * Creates and saves sample instructor entities along with their details.
   *
   * @param app data access object for application entity operations
   */
  private void createInstructor(AppDao app) {
    Instructor instructor1 = 
        new Instructor("Vicky",
                        "Magtatanggol",
                        "vicky.mags@example.com");

    InstructorDetail deets1 = 
        new InstructorDetail("Math",
                        "Solving problems");

    Instructor instructor2 = 
        new Instructor("Google",
                        "Gemini",
                        "google.gemini@example.com");

    InstructorDetail deets2 = 
        new InstructorDetail("AI Generated Image",
                        "Solving problems");
                
    // associate the objects
    instructor1.setInstructorDeets(deets1);
    instructor2.setInstructorDeets(deets2);

    // save the instructor
    // this will also save the details object
    // because of the CascadeType.ALL
    app.save(instructor1);
    app.save(instructor2);
    System.out.println("Information Saved!");
  }

  /**
   * Finds and prints an instructor and associated details by 
   * primary key ID.
   *
   * @param app data access object for application entity operations
   */
  private void findInstructor(AppDao app) {
    int id = 1;
    System.out.println("Find ID: " + id);

    Instructor instructor = app.findInstructorById(id);

    System.out.println("Instructor: " + instructor);
    System.out.println("Details: " + instructor.getInstructorDeets());
  }

  /**
   * Deletes an instructor along with their associated courses 
   * by primary key ID.
   *
   * @param app data access object for application entity operations
   */
  private void deleteInstructor(AppDao app) {
    int id = 1;
    System.out.println("Delete ID: " + id);

    app.deleteInstructorWithCoursesById(id);
    System.out.println("Deleted Successfully!");
  }

  /**
   * Finds and prints an instructor detail object and its 
   * associated instructor.
   *
   * @param app data access object for application entity operations
   */
  private void findInstructorDetail(AppDao app) {
    // get the instructor detail object
    int id = 2;
    InstructorDetail instructorDeets = app.findInstructorDetailById(id);
    
    // print the instructor detail
    System.out.println("Details: " + instructorDeets);

    // print the associated instructor
    System.out.println("Instructor: " + instructorDeets.getInstructor());
  }

  /**
   * Deletes an instructor detail record by primary key ID.
   *
   * @param app data access object for application entity operations
   */
  private void deleteInstructorDetail(AppDao app) {
    int id = 2; 
    System.out.println("Delete ID: " + id);

    app.deleteInstructorDetail(id);
    System.out.println("Deleted Successfully!");
  }

  /**
   * Creates an instructor with associated details and courses, 
   * then persists them.
   *
   * @param app data access object for application entity operations
   */
  private void createInstructorWithCourses(AppDao app) {
    // create the instructor details
    Instructor instructor = new Instructor(
        "Peri",
        "Winkle",
        "peri.winkle@example.com"
    );
    InstructorDetail deets = new InstructorDetail(
        "Science",
        "Sprinkling dust"
    );

    // associate the objects
    instructor.setInstructorDeets(deets);

    // create courses
    Course course1 = new Course("Physics");
    Course course2 = new Course("Biology");

    // add courses to instructor
    instructor.add(course1);
    instructor.add(course2);

    // save the instructor
    app.save(instructor);
    System.out.println("Course Saved: " + instructor.getCourses());
  }

  /**
   * Finds an instructor by ID and fetches associated courses.
   *
   * @param app data access object for application entity operations
   */
  private void findInstructorWithCourses(AppDao app) {
    int id = 3;
    System.out.println("Find Instructor ID: " + id);

    Instructor instructor = app.findInstructorById(id);

    System.out.println("Instructor: " + instructor);
    System.out.println("Courses: " + instructor.getCourses());
  }

  /**
   * Retrieves an instructor and queries courses explicitly 
   * by instructor ID.
   *
   * @param app data access object for application entity operations
   */
  private void findCoursesForInstructor(AppDao app) {
    int id = 3;

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

  /**
   * Finds an instructor and lazy-loaded courses in a single 
   * query using join fetch.
   *
   * @param app data access object for application entity operations
   */
  private void findInstructorWithCoursesJoinFetch(AppDao app) {
    int id = 3;
    System.out.println("Find Instructor ID: " + id);

    Instructor instructor = app.findInstructorByIdJoinFetch(id);

    System.out.println("Instructor: " + instructor);
    System.out.println("Courses: " + instructor.getCourses());
  }
  
  /**
   * Updates an existing instructor's profile information in the database.
   *
   * @param app data access object for application entity operations
   */
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

  /**
   * Deletes an instructor record by ID without removing 
   * associated courses.
   *
   * @param app data access object for application entity operations
   */
  private void deleteInstructorOnly(AppDao app) {
    int id = 3; 
    System.out.println("Delete Instructor: " + id);

    app.deleteInstructorById(id);
    System.out.println("Deleted Successfully!");
  }

  /**
   * Creates a course with attached review entities 
   * and persists it to the database.
   *
   * @param app data access object for application entity operations
   */
  private void createCourseAndReviews(AppDao app) {
    // create a course
    Course course = new Course("How to Speed Run Spring in 1 Day");
    // add some reviews
    course.addReview(new Review("Great speed 2x...loved it!!!"));
    course.addReview(new Review("I wanted the speed 0.75x :(("));
    course.addReview(
        new Review("Hoping to do a speed of 3x :D. Anyways, nice!"));

    // save the course
    app.save(course);
    System.out.println("Course Saved!");
  }

  /**
   * Retrieves a course and its associated reviews by course ID.
   *
   * @param app data access object for application entity operations
   */
  private void retrieveCourseAndReviews(AppDao app) {
    int id = 3;
    System.out.println("Get Course and Reviews from ID: "+ id);
    // get the course and reviews
    Course course = app.findCourseAndReviewsByCourseId(id);

    // print the course
    System.out.println(course);

    // print the reviews
    System.out.println(course.getReview());
  }
  
  /**
   * Deletes a course and its cascading reviews by course ID.
   *
   * @param app data access object for application entity operations
   */
  private void deleteCourseAndReviews(AppDao app) {
    int id = 3;
    System.out.println("Delete Course: " + id);

    app.deleteCourseById(id);
    System.out.println("Deleted Successfully!");
  }

  /**
   * Creates a course, associates new student entities with it, 
   * and persists the course.
   *
   * @param app data access object for application entity operations
   */
  private void createCourseAndStudents(AppDao app) {
    // create a course
    Course course = 
        new Course("How to Be A FullStack Dev in Half A Day");
    
    // create the students
    Student student1 = new Student(
        "Quick",
        "Brown", 
        "quick.brown@gmail.com"
    );
    Student student2 = new Student(
        "Fox",
        "Jumps",
        "fox.jumps@gmail.com"
    );

    // add student to the course
    course.addStudent(student1);
    course.addStudent(student2);

    // save the course and associate students
    app.save(course);
    System.out.println("Successfully Enrolled!");
  }

  /**
   * Finds a course along with its enrolled students by course ID.
   *
   * @param app data access object for application entity operations
   */
  private void findCourseAndStudents(AppDao app) {
    int id = 4;
    Course course = app.findCourseAndStudentByCourseId(id);

    System.out.println("Course: " + course);
    System.out.println("Students: " + course.getStudents());
  }

  /**
   * Finds a student along with their enrolled courses by student ID.
   *
   * @param app data access object for application entity operations
   */
  private void findStudentAndCourses(AppDao app) {
    int id = 1;
    Student student = app.findStudentAndCoursesByStudentId(id);

    System.out.println("Student: " + student);
    System.out.println("Courses: " + student.getCourses());
  }

  /**
   * Adds multiple new course enrollments to an existing student by ID.
   *
   * @param app data access object for application entity operations
   */
  private void addMoreCoursesForStudent(AppDao app) {
    int id = 1;
    Student student = app.findStudentAndCoursesByStudentId(id);

    Course course1 = 
        new Course("How to Test Without Running Application");
    Course course2 = new Course("How to Develop Without Computer");
    Course course3 = new Course("How to Be a Legit Developer");

    student.addCourse(course1);
    student.addCourse(course2);
    student.addCourse(course3);

    app.update(student);
    System.out.println("Successfully Enrolled to New Courses!");
  }

  /**
   * Deletes a course record by its primary key ID.
   *
   * @param app data access object for application entity operations
   */
  private void deleteCourseById(AppDao app) {
    int id = 4;
    System.out.println("Delete Course: " + id);

    app.deleteCourseById(id);
    System.out.println("Deleted Successfully!");
  }

  /**
   * Deletes a student entity by primary key ID using AppDao.
   *
   * @param app data access object for application entity operations
   */
  private void deleteStudent(AppDao app) {
    int id = 1;
    System.out.println("Delete Student: " + id);

    app.deleteStudentById(id);
    System.out.println("Deleted Successfully");
  }

  /**
   * Creates and saves a new Student entity to the database.
   *
   * @param studentDao data access object for Student entity operations
   */
  private void createStudent(final StudentDao studentDao) {
    System.out.println("Creating Student Information...");
    // Student student = new Student(
    //     "Light", 
    //     "Yagami",
    //     "light.yagami@example.com"
    // );

    // Student student = new Student(
    //     "Dexter", 
    //     "Lauron",
    //     "light.yagami@example.com"
    // );

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