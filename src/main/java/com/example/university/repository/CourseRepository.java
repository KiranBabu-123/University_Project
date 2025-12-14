/*
 *
 * You can use the following import statements
 * 
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.university.repository;

import com.example.university.model.*;
import java.util.*;

public interface CourseRepository {

    ArrayList<Course> getAllCourses();
    Course addCourse(Course course);
    Course getCourseById(int courseId);
    Course updateCourse(int courseId, Course course);
    void deleteCourse(int courseId);
    Professor getCourseProfessor(int courseId);
    List<Student> getCourseStudents(int courseId);
}
