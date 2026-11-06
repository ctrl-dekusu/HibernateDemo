package com.hibernate.hibernate_demo.dao;

import com.hibernate.hibernate_demo.entity.Student;
import java.util.List;

/**
 * Data Access Object interface for managing Student persistence operations.
 */
public interface StudentDao {

    /**
     * Saves a student entity to the database.
     *
     * @param theStudent the student entity to save
     */
    void save(final Student theStudent);

    /**
     * Retrieves a student entity by its primary key.
     *
     * @param id the unique identifier of the student
     * @return the found student entity, or null if not found
     */
    Student findById(final Integer id);

    /**
     * Retrieves all student records from the database.
     *
     * @return a list of all student entities
     */
    List<Student> findAll();

    /**
     * Retrieves student records matching a specific last name.
     *
     * @param lastname the last name to search for
     * @return a list of matching student entities
     */
    List<Student> findByLastName(final String lastname);

    /**
     * Updates an existing student entity in the database.
     *
     * @param theStudent the student entity containing updated details
     */
    void update(final Student theStudent);

    /**
     * Deletes a student entity by its primary key.
     *
     * @param id the unique identifier of the student to delete
     */
    void delete(final Integer id);

    /**
     * Deletes all student records from the database.
     *
     * @return the number of deleted records
     */
    int deleteAll();
}