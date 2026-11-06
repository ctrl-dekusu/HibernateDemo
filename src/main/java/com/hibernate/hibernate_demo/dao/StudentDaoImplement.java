package com.hibernate.hibernate_demo.dao;

import com.hibernate.hibernate_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the StudentDao interface providing JPA 
 * EntityManager operations.
 */
@Repository
public class StudentDaoImplement implements StudentDao {

    /**
     * Entity manager for persistence context operations.
     */
    private final EntityManager em;

    /**
     * Constructs a new StudentDaoImplement with the given EntityManager.
     *
     * @param em the entity manager to inject
     */
    public StudentDaoImplement(final EntityManager em) {
        this.em = em;
    }

    /**
     * Saves a student entity to the database.
     *
     * @param theStudent the student entity to persist
     */
    @Override
    @Transactional
    public void save(final Student theStudent) {
        em.persist(theStudent);
    }

    /**
     * Retrieves a student entity by primary key ID.
     *
     * @param id the primary key of the student entity
     * @return the student entity, or null if not found
     */
    @Override
    public Student findById(final Integer id) {
        return em.find(Student.class, id);
    }

    /**
     * Retrieves all student records ordered by last name.
     *
     * @return a list of all student entities
     */
    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = em
                .createQuery("FROM Student ORDER BY lastName", 
                Student.class);

        return query.getResultList();
    }

    /**
     * Retrieves student records matching a specific last name.
     *
     * @param lastname the last name to search for
     * @return a list of matching student entities
     */
    @Override
    public List<Student> findByLastName(final String lastname) {
        TypedQuery<Student> query = em
                .createQuery("FROM Student WHERE lastName = :data",
                Student.class);

        query.setParameter("data", lastname);

        return query.getResultList();
    }

    /**
     * Updates an existing student entity in the database.
     *
     * @param theStudent the student entity containing updated fields
     */
    @Override
    @Transactional
    public void update(final Student theStudent) {
        em.merge(theStudent);
    }

    /**
     * Deletes a student entity by primary key ID.
     *
     * @param id the primary key of the student to delete
     */
    @Override
    @Transactional
    public void delete(final Integer id) {
        Student student = em.find(Student.class, id);

        em.remove(student);
    }

    /**
     * Deletes all student records from the database.
     *
     * @return the number of deleted records
     */
    @Override
    @Transactional
    public int deleteAll() {
        int numRows = em.createQuery("DELETE FROM Student")
                .executeUpdate();

        return numRows;
    }
}