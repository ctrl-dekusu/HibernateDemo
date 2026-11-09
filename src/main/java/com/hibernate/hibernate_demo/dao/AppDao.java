package com.hibernate.hibernate_demo.dao;

import java.util.List;

import com.hibernate.hibernate_demo.entity.Course;
import com.hibernate.hibernate_demo.entity.Instructor;
import com.hibernate.hibernate_demo.entity.InstructorDetail;

public interface AppDao {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorWithCoursesById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetail(int theId);
    Instructor findInstructorWithCourses(int theId);
    List<Course> findCourseByInstructorId(int thisId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor theInstructor);
    void deleteInstructorById(int theId);
}
