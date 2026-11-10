package com.hibernate.hibernate_demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Entity class representing a Student record in the database.
 */
@Entity 
@Table(name = "student")
public class Student {

    /** The unique identifier for the student. */
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** The first name of the student. */
    @Column(name = "first_name")
    private String firstName;

    /** The last name of the student. */
    @Column(name = "last_name")
    private String lastName;

    /** The email address of the student. */
    @Column(name = "email")
    private String email;

    /** The list of courses the student is enrolled in. */
    @ManyToMany(
        mappedBy = "students",
        fetch = FetchType.LAZY,
        cascade = {CascadeType.DETACH, 
                CascadeType.MERGE, 
                CascadeType.PERSIST, 
                CascadeType.REFRESH}
    )
    private List<Course> courses;

    /**
     * Default no-argument constructor required by JPA.
     */
    public Student() {}

    /**
     * Constructs a new {@code Student} with personal information.
     *
     * @param firstName the student's first name
     * @param lastName  the student's last name
     * @param email     the student's email address
     */
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Gets the student ID.
     *
     * @return the unique identifier of the student
     */
    public int getId() { return id; }

    /**
     * Sets the student ID.
     *
     * @param id the unique identifier to set
     */
    public void setId(int id) { this.id = id; }

    /**
     * Gets the first name of the student.
     *
     * @return the first name
     */
    public String getFirstName() { return firstName; }

    /**
     * Sets the first name of the student.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Gets the last name of the student.
     *
     * @return the last name
     */
    public String getLastName() { return lastName; }

    /**
     * Sets the last name of the student.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Gets the email address of the student.
     *
     * @return the email address
     */
    public String getEmail() { return email; }

    /**
     * Sets the email address of the student.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Gets the list of courses enrolled by this student.
     *
     * @return the list of courses
     */
    public List<Course> getCourses() { return courses; }

    /**
     * Sets the list of courses enrolled by this student.
     *
     * @param courses the list of courses to set
     */
    public void setCourses(List<Course> courses) { this.courses = courses; }

    /**
     * Returns a string representation of the student.
     *
     * @return string containing student details
     */
    @Override 
    public String toString() {
        return "Student [id = " + getId() +
                ", firstName = " + getFirstName() +
                ", lastName = " + getLastName() +
                ", email = " + getEmail() + "]";
    }

    /**
     * Convenience method to add a course to this student's course list 
     * and establish a bidirectional link.
     *
     * @param course the course entity to add
     */
    public void addCourse(Course course) {
        if(courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.addStudent(this);
    }
}