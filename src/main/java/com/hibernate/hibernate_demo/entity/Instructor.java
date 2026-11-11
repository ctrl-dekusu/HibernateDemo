package com.hibernate.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Instructor record in the database.
 */
@Entity
@Table(name = "instructor")
public class Instructor {

  /** The unique identifier for the instructor. */
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  /** The first name of the instructor. */
  @Column(name = "first_name")
  private String firstName;

  /** The last name of the instructor. */
  @Column(name = "last_name")
  private String lastName;

  /** The email address of the instructor. */
  @Column(name = "email")
  private String email;

  /** The detailed profile information for this instructor. */
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "instructor_detail_id")
  private InstructorDetail instructorDeets;

  /** The list of courses taught by this instructor. */
  @OneToMany(
          mappedBy = "instructor",
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
  public Instructor() {}

  /**
   * Constructs a new {@code Instructor} with personal information.
   *
   * @param firstName the instructor's first name
   * @param lastName  the instructor's last name
   * @param email     the instructor's email address
   */
  public Instructor(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  /**
   * Gets the instructor ID.
   *
   * @return the unique identifier of the instructor
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the instructor ID.
   *
   * @param id the unique identifier to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the first name of the instructor.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name of the instructor.
   *
   * @param firstName the first name to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name of the instructor.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name of the instructor.
   *
   * @param lastName the last name to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the email address of the instructor.
   *
   * @return the email address
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email address of the instructor.
   *
   * @param email the email address to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the detail object for this instructor.
   *
   * @return the instructor detail entity
   */
  public InstructorDetail getInstructorDeets() {
    return instructorDeets;
  }

  /**
   * Sets the detail object for this instructor.
   *
   * @param instructorDeets the instructor detail entity to set
   */
  public void setInstructorDeets(InstructorDetail instructorDeets) {
    this.instructorDeets = instructorDeets;
  }

  /**
   * Gets the list of courses taught by this instructor.
   *
   * @return the list of courses
   */
  public List<Course> getCourses() {
    return courses;
  }

  /**
   * Sets the list of courses taught by this instructor.
   *
   * @param courses the list of courses to set
   */
  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  /**
   * Returns a string representation of the instructor.
   *
   * @return string containing instructor details
   */
  @Override
  public String toString() {
    return "Instructor [id = " + getId() + 
            ", firstName = " + getFirstName() + 
            ", lastName = " + getLastName() + 
            ", email = " + getEmail() + "]";
  }

  /**
   * Convenience method to add a course to this instructor and 
   * update the course's instructor reference.
   *
   * @param theCourse the course entity to add
   */
  public void add(Course theCourse) {
    // initializes list if it's null
    if (this.courses == null) {
      this.courses = new ArrayList<>();
    }
    // add the course to instructor's list
    this.courses.add(theCourse);
    // tell course who instructor is
    // 'this' refers to the current Instructor object
    theCourse.setInstructor(this);
  }
}