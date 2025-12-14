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
import org.springframework.beans.factory.annotation.Autowired;
import com.example.university.service.CourseJpaService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class CourseController {

    @Autowired
    private CourseJpaService courseJpaService;

    // API 7: GET /courses
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseJpaService.getAllCourses();
    }

    // API 8: POST /courses
    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return courseJpaService.addCourse(course);
    }

    // API 9: GET /courses/{courseId}
    @GetMapping("/courses/{courseId}")
    public Course getCourseById(@PathVariable int courseId) {
        return courseJpaService.getCourseById(courseId);
    }

    // API 10: PUT /courses/{courseId}
    @PutMapping("/courses/{courseId}")
    public Course updateCourse(@PathVariable int courseId, @RequestBody Course course) {
        return courseJpaService.updateCourse(courseId, course);
    }

    // API 11: DELETE /courses/{courseId}
    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable int courseId) {
        courseJpaService.deleteCourse(courseId);
    }

    // API 12: GET /courses/{courseId}/professor
    @GetMapping("/courses/{courseId}/professor")
    public Professor getCourseProfessor(@PathVariable int courseId) {
        return courseJpaService.getCourseProfessor(courseId);
    }

    // API 13: GET /courses/{courseId}/students
    @GetMapping("/courses/{courseId}/students")
    public List<Student> getCourseStudents(@PathVariable int courseId) {
        return courseJpaService.getCourseStudents(courseId);
    }
}