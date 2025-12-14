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

public interface StudentRepository {
    ArrayList<Student> getAllStudents();
    Student addStudent(Student student);
    Student getStudentById(int studentId);
    Student updateStudent(int studentId, Student student);
    void deleteStudent(int studentId);
    List<Course> getStudentCourses(int studentId);
}
