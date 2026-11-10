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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entity class representing a Course record in the database.
 */
@Entity 
@Table(name = "course")
public class Course {

    /** The unique identifier for the course. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** The title of the course. */
    @Column(name = "title")
    private String title;

    /** The instructor assigned to teach this course. */
    @ManyToOne(
        cascade = {CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.PERSIST,
                CascadeType.REFRESH}
    )
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    /** The list of reviews written for this course. */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> review;

    /** The list of students enrolled in this course. */
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.DETACH, 
                CascadeType.MERGE, 
                CascadeType.PERSIST, 
                CascadeType.REFRESH}
    )
    @JoinTable(
        name = "course_student",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    /**
     * Default no-argument constructor required by JPA.
     */
    public Course() {}

    /**
     * Constructs a new {@code Course} with the specified title.
     *
     * @param title the title of the course
     */
    public Course(String title) {
        this.title = title;
    }

    /**
     * Gets the course ID.
     *
     * @return the unique identifier of the course
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the course ID.
     *
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the course.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the course.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the instructor teaching this course.
     *
     * @return the assigned instructor
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Sets the instructor for this course.
     *
     * @param instructor the instructor entity to set
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Gets the list of reviews associated with this course.
     *
     * @return the list of reviews
     */
    public List<Review> getReview() {
        return review;
    }

    /**
     * Sets the list of reviews for this course.
     *
     * @param review the list of reviews to set
     */
    public void setReview(List<Review> review) {
        this.review = review;
    }

    /**
     * Gets the list of students enrolled in this course.
     *
     * @return the list of enrolled students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets the list of students for this course.
     *
     * @param students the list of students to set
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Returns a string representation of the course.
     *
     * @return string containing course details
     */
    @Override
    public String toString() {
        return "Course [id = " + getId() + 
            ", title = " + getTitle() + "]";
    }

    /**
     * Adds a review to this course's list of reviews.
     *
     * @param rev the review entity to add
     */
    public void addReview(Review rev) {
        if(review == null) {
            review = new ArrayList<>();
        }
        review.add(rev);
    }

    /**
     * Adds a student to this course's list of enrolled students.
     *
     * @param stud the student entity to add
     */
    public void addStudent(Student stud) {
        if(students == null) {
            students = new ArrayList<>();
        }
        students.add(stud);
    }
}