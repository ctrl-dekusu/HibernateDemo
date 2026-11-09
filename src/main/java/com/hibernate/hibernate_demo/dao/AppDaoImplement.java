package com.hibernate.hibernate_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hibernate.hibernate_demo.entity.Course;
import com.hibernate.hibernate_demo.entity.Instructor;
import com.hibernate.hibernate_demo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDaoImplement implements AppDao {
    private EntityManager em;

    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }
    
    @Override 
    @Transactional 
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    @Override 
    @Transactional 
    public void deleteInstructorWithCoursesById(int theId) {
        // retrieve the instructor
        Instructor instructor = em.find(Instructor.class, theId);

        // delete the instructor
        em.remove(instructor);
    }

    @Override 
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

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
    
    @Override
    public Instructor findInstructorWithCourses(int theId) {
        return em.find(Instructor.class, theId);
    }

    @Override 
    public List<Course> findCourseByInstructorId(int theId) {
        TypedQuery<Course> query = em
            .createQuery("FROM Course WHERE instructor.id=:data", 
            Course.class);
        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();
        return courses;
    }

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

    @Override 
    @Transactional 
    public void update(Instructor instructor) {
        em.merge(instructor);
    }

    @Override 
    @Transactional 
    public void deleteInstructorById(int theId) {
        // retrieve instructor
        Instructor instructor = em.find(Instructor.class, theId);

        // break the associations of courses for instructor
        List<Course> courses = instructor.getCourses();
        for(Course c: courses) {
            // remove the instructor from the courses
            c.setInstructor(null);
        }

        // delete only the instructor
        // does not include courses associated
        em.remove(instructor);
    }
}
