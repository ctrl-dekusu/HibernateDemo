package com.hibernate.hibernate_demo.dao;

import com.hibernate.hibernate_demo.entity.Course;
import com.hibernate.hibernate_demo.entity.Instructor;
import com.hibernate.hibernate_demo.entity.InstructorDetail;
import com.hibernate.hibernate_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Repository implementation of {@link AppDao} providing JPA/Hibernate 
 * persistence logic.
 */
@Repository
public class AppDaoImplement implements AppDao {
    
  /** The JPA entity manager. */
  private EntityManager em;

  /**
   * Constructs a new {@code AppDaoImplement} with the 
   * specified {@link EntityManager}.
   *
   * @param em the entity manager to inject
   */
  public AppDaoImplement(EntityManager em) {
    this.em = em;
  }
  
  /** {@inheritDoc} */
  @Override 
  @Transactional 
  public void save(Instructor theInstructor) {
    em.persist(theInstructor);
  }

  /** {@inheritDoc} */
  @Override
  public Instructor findInstructorById(int theId) {
    return em.find(Instructor.class, theId);
  }

  /** {@inheritDoc} */
  @Override 
  @Transactional 
  public void deleteInstructorWithCoursesById(int theId) {
    // retrieve the instructor
    Instructor instructor = em.find(Instructor.class, theId);

    // delete the instructor
    em.remove(instructor);
  }

  /** {@inheritDoc} */
  @Override 
  public InstructorDetail findInstructorDetailById(int theId) {
    return em.find(InstructorDetail.class, theId);
  }

  /** {@inheritDoc} */
  @Override 
  @Transactional 
  public void deleteInstructorDetail(int theId) {
    // retrieve instructor details
    InstructorDetail instructorDeets = em
        .find(InstructorDetail.class, theId);

    // remove the associated object reference
    instructorDeets.getInstructor().setInstructorDeets(null);
    
    // delete instructor details
    em.remove(instructorDeets);
  }
  
  /** {@inheritDoc} */
  @Override
  public Instructor findInstructorWithCourses(int theId) {
    return em.find(Instructor.class, theId);
  }

  /** {@inheritDoc} */
  @Override 
  public List<Course> findCourseByInstructorId(int theId) {
    TypedQuery<Course> query = em
        .createQuery("FROM Course WHERE instructor.id=:data", 
        Course.class);
    query.setParameter("data", theId);

    List<Course> courses = query.getResultList();
    return courses;
  }

  /** {@inheritDoc} */
  @Override
  public Instructor findInstructorByIdJoinFetch(int theId) {
    // create query
    TypedQuery<Instructor> query = em
        .createQuery(
            "SELECT i FROM Instructor i " + 
            "JOIN FETCH i.courses " + 
            "JOIN FETCH i.instructorDeets " +
            "WHERE i.id=:data", Instructor.class);
    query.setParameter("data", theId);

    Instructor instructor = query.getSingleResult();
    return instructor;
  }

  /** {@inheritDoc} */
  @Override 
  @Transactional 
  public void update(Instructor instructor) {
    em.merge(instructor);
  }

  /** {@inheritDoc} */
  @Override 
  @Transactional 
  public void deleteInstructorById(int theId) {
    // retrieve instructor
    Instructor instructor = em.find(Instructor.class, theId);

    // break the associations of courses for instructor
    List<Course> courses = instructor.getCourses();
    for (Course c : courses) {
      // remove the instructor from the courses
      c.setInstructor(null);
    }

    // delete only the instructor
    // does not include courses associated
    em.remove(instructor);
  }

  /** {@inheritDoc} */
  @Override
  @Transactional 
  public void save(Course theCourse) {
    em.persist(theCourse);
  }

  /** {@inheritDoc} */
  @Override
  public Course findCourseAndReviewsByCourseId(int theId) {
    // create query
    TypedQuery<Course> query = em
        .createQuery(
            "SELECT c FROM Course c " + 
            "JOIN FETCH c.review " +
            "WHERE c.id=:data", Course.class);
    query.setParameter("data", theId);

    // execute query
    Course course = query.getSingleResult();

    return course;
  }

  /** {@inheritDoc} */
  @Override 
  @Transactional 
  public void deleteCourseById(int theId) {
    // retrieve the course
    Course course = em.find(Course.class, theId);

    // delete the course
    em.remove(course);
  }

  /** {@inheritDoc} */
  @Override 
  public Course findCourseAndStudentByCourseId(int theId) {
    // create query
    TypedQuery<Course> query = em
        .createQuery(
            "SELECT c FROM Course c " +
            "JOIN FETCH c.students " +
            "WHERE c.id=:data", Course.class);
    query.setParameter("data", theId);

    // execute query
    Course course = query.getSingleResult();

    return course;
  }

  /** {@inheritDoc} */
  @Override
  public Student findStudentAndCoursesByStudentId(int theId) {
    // create query
    TypedQuery<Student> query = em
        .createQuery(
            "SELECT s FROM Student s " +
            "JOIN FETCH s.courses " +
            "WHERE s.id=:data", Student.class);
    query.setParameter("data", theId);

    // execute query
    Student student = query.getSingleResult();

    return student;
  }

  /** {@inheritDoc} */
  @Override 
  @Transactional 
  public void update(Student theStudent) {
    em.merge(theStudent);
  }

  /** {@inheritDoc} */
  @Override 
  @Transactional 
  public void deleteStudentById(int theId) {
    // retrieve student
    Student student = em.find(Student.class, theId);

    if (student != null) {
      // get the course
      List<Course> courses = student.getCourses();

      // break the associations of courses for student
      for (Course c : courses) {
        c.getStudents().remove(student);
      }
    }

    // delete only the student 
    // does not include courses associated
    em.remove(student);
  }
}