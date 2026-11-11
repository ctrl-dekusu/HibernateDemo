package com.hibernate.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a Review record in the database.
 */
@Entity 
@Table(name = "review")
public class Review {

  /** The unique identifier for the review. */
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  /** The comment content of the review. */
  @Column(name = "comments")
  private String comments;

  /**
   * Default no-argument constructor required by JPA.
   */
  public Review() {}

  /**
   * Constructs a new {@code Review} with specified comments.
   *
   * @param comments the comment content
   */
  public Review(String comments) {
    this.comments = comments;
  }

  /**
   * Gets the review ID.
   *
   * @return the unique identifier
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the review ID.
   *
   * @param id the unique identifier to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the review comments.
   *
   * @return the comment content
   */
  public String getComments() {
    return comments;
  }

  /**
   * Sets the review comments.
   *
   * @param comments the comment content to set
   */
  public void setComments(String comments) {
    this.comments = comments;
  }

  /**
   * Returns a string representation of the review.
   *
   * @return string containing review details
   */
  @Override
  public String toString() {
    return "Review [id = " + getId() + 
      ", comments = " + getComments() + "]";
  }
}