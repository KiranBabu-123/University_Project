/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.university.controller;

import com.example.university.model.*;
import com.example.university.service.StudentJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class StudentController {

    @Autowired
    StudentJpaService studentJpaService;

    // API 14: GET /students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentJpaService.getAllStudents();
    }

    // API 15: POST /students
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        return studentJpaService.addStudent(student);
    }

    // API 16: GET /students/{studentId}
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {
        return studentJpaService.getStudentById(studentId);
    }

    // API 17: PUT /students/{studentId}
    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable int studentId, @RequestBody Student student) {
        return studentJpaService.updateStudent(studentId, student);
    }

    // API 18: DELETE /students/{studentId}
    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable int studentId) {
        studentJpaService.deleteStudent(studentId);
    }

    // API 19: GET /students/{studentId}/courses
    @GetMapping("/students/{studentId}/courses")
    public List<Course> getStudentCourses(@PathVariable int studentId) {
        return studentJpaService.getStudentCourses(studentId);
    }
}