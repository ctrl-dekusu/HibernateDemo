package com.hibernate.hibernate_demo.dao;

import com.hibernate.hibernate_demo.entity.Course;
import com.hibernate.hibernate_demo.entity.Instructor;
import com.hibernate.hibernate_demo.entity.InstructorDetail;
import com.hibernate.hibernate_demo.entity.Student;
import java.util.List;

/**
 * Data Access Object (DAO) interface defining persistence operations
 * for Instructors, Instructor Details, Courses, and Students.
 */
public interface AppDao {
  /**
   * Saves a new instructor entity.
   *
   * @param theInstructor the instructor entity to persist
   */
  void save(Instructor theInstructor);

  /**
   * Retrieves an instructor by their unique identifier.
   *
   * @param theId the unique identifier of the instructor
   * @return the instructor entity, or {@code null} if not found
   */
  Instructor findInstructorById(int theId);

  /**
   * Deletes an instructor along with their associated courses by ID.
   *
   * @param theId the unique identifier of the instructor to delete
   */
  void deleteInstructorWithCoursesById(int theId);

  /**
   * Retrieves an instructor detail entity by its unique identifier.
   *
   * @param theId the unique identifier of the instructor detail
   * @return the instructor detail entity, or {@code null} if not found
   */
  InstructorDetail findInstructorDetailById(int theId);

  /**
   * Deletes an instructor detail record by its unique identifier.
   *
   * @param theId the unique identifier of the instructor detail to delete
   */
  void deleteInstructorDetail(int theId);

  /**
   * Retrieves an instructor entity by ID.
   *
   * @param theId the unique identifier of the instructor
   * @return the instructor entity, or {@code null} if not found
   */
  Instructor findInstructorWithCourses(int theId);

  /**
   * Finds all courses associated with a specific instructor ID.
   *
   * @param thisId the unique identifier of the instructor
   * @return a list of courses associated with the given instructor
   */
  List<Course> findCourseByInstructorId(int thisId);

  /**
   * Retrieves an instructor and their associated courses 
   * using a JOIN FETCH query.
   *
   * @param theId the unique identifier of the instructor
   * @return the instructor entity with fetched courses, 
   * or {@code null} if not found
   */
  Instructor findInstructorByIdJoinFetch(int theId);

  /**
   * Updates an existing instructor's details in the database.
   *
   * @param theInstructor the instructor entity to update
   */
  void update(Instructor theInstructor);

  /**
   * Deletes an instructor record by ID without deleting associated courses.
   *
   * @param theId the unique identifier of the instructor to delete
   */
  void deleteInstructorById(int theId);

  /**
   * Saves a new course entity.
   *
   * @param theCourse the course entity to persist
   */
  void save(Course theCourse);

  /**
   * Retrieves a course along with its reviews by course ID.
   *
   * @param theId the unique identifier of the course
   * @return the course entity with fetched reviews, 
   * or {@code null} if not found
   */
  Course findCourseAndReviewsByCourseId(int theId);

  /**
   * Deletes a course record by its unique identifier.
   *
   * @param theId the unique identifier of the course to delete
   */
  void deleteCourseById(int theId);

  /**
   * Retrieves a course along with enrolled students by course ID.
   *
   * @param theId the unique identifier of the course
   * @return the course entity with fetched students, 
   * or {@code null} if not found
   */
  Course findCourseAndStudentByCourseId(int theId);

  /**
   * Retrieves a student along with enrolled courses by student ID.
   *
   * @param theId the unique identifier of the student
   * @return the student entity with fetched courses, 
   * or {@code null} if not found
   */
  Student findStudentAndCoursesByStudentId(int theId);

  /**
   * Updates an existing student's details in the database.
   *
   * @param student the student entity to update
   */
  void update(Student student);

  /**
   * Deletes a student record by their unique identifier.
   *
   * @param theId the unique identifier of the student to delete
   */
  void deleteStudentById(int theId);
}