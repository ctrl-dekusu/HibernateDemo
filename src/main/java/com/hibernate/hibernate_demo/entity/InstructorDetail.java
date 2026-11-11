package com.hibernate.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entity class representing a Instructor_detail record in the database.
 */
@Entity 
@Table(name = "instructor_detail")
public class InstructorDetail {

  /** The unique identifier for the instructor detail. */
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  /** The primary subject field of the instructor. */
  @Column(name = "subject")
  private String subject;

  /** The hobby of the instructor. */
  @Column(name = "hobby")
  private String hobby;

  /** The associated instructor entity. */
  @OneToOne(
      mappedBy = "instructorDeets", 
      cascade = {CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.PERSIST,
          CascadeType.REFRESH}
  )
  private Instructor instructor;

  /**
   * Default no-argument constructor required by JPA.
   */
  public InstructorDetail() {}

  /**
   * Constructs a new {@code InstructorDetail} with subject 
   * and hobby details.
   *
   * @param subject the subject taught
   * @param hobby   the hobby of the instructor
   */
  public InstructorDetail(String subject, String hobby) {
    this.subject = subject;
    this.hobby = hobby;
  }

  /**
   * Gets the instructor detail ID.
   *
   * @return the unique identifier
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the instructor detail ID.
   *
   * @param id the unique identifier to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the subject field.
   *
   * @return the subject
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Sets the subject field.
   *
   * @param subject the subject to set
   */
  public void setSubject(String subject) {
    this.subject = subject;
  }

  /**
   * Gets the hobby.
   *
   * @return the hobby
   */
  public String getHobby() {
    return hobby;
  }

  /**
   * Sets the hobby.
   *
   * @param hobby the hobby to set
   */
  public void setHobby(String hobby) {
    this.hobby = hobby;
  }

  /**
   * Gets the associated instructor.
   *
   * @return the instructor entity
   */
  public Instructor getInstructor() {
    return instructor;
  }

  /**
   * Returns a string representation of the instructor detail.
   *
   * @return string containing instructor detail fields
   */
  @Override
  public String toString() {
    return "InstructorDetail [id = " + getId() + 
              ", subject = " + getSubject() + 
              ", hobby = " + getHobby() + "]";
  }
}