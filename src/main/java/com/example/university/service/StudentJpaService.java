/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.university.service;

import com.example.university.model.*;
import com.example.university.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class StudentJpaService implements StudentRepository {

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    @Override
    public ArrayList<Student> getAllStudents() {
        List<Student> list = studentJpaRepository.findAll();
        return new ArrayList<>(list);
    }

    @Override
    public Student addStudent(Student student) {
        List<Integer> courseIds = new ArrayList<>();
        for (Course course : student.getCourses()) {
            courseIds.add(course.getCourseId());
        }

        List<Course> courses = courseJpaRepository.findAllById(courseIds);
        if (courses.size() != courseIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        student.setCourses(courses);

        for (Course course : courses) {
            course.getStudents().add(student);
        }

        Student newStudent = studentJpaRepository.save(student);
        courseJpaRepository.saveAll(courses);

        return newStudent;
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            return studentJpaRepository.findById(studentId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student updateStudent(int studentId, Student student) {
        try {
            Student newStudent = studentJpaRepository.findById(studentId).get();

            if (student.getStudentName() != null)
                newStudent.setStudentName(student.getStudentName());
            if (student.getEmail() != null)
                newStudent.setEmail(student.getEmail());

            if (student.getCourses() != null) {
                // Remove existing associations
                List<Course> existingCourses = newStudent.getCourses();
                for (Course course : existingCourses) {
                    course.getStudents().remove(newStudent);
                }
                courseJpaRepository.saveAll(existingCourses);

                // Add new associations
                List<Integer> courseIds = new ArrayList<>();
                for (Course course : student.getCourses()) {
                    courseIds.add(course.getCourseId());
                }

                List<Course> newCourses = courseJpaRepository.findAllById(courseIds);
                if (newCourses.size() != courseIds.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }

                for (Course course : newCourses) {
                    course.getStudents().add(newStudent);
                }

                courseJpaRepository.saveAll(newCourses);
                newStudent.setCourses(newCourses);
            }

            return studentJpaRepository.save(newStudent);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteStudent(int studentId) {
        try {
            Student student = studentJpaRepository.findById(studentId).get();
            List<Course> courses = student.getCourses();

            for (Course course : courses) {
                course.getStudents().remove(student);
            }
            courseJpaRepository.saveAll(courses);

            studentJpaRepository.deleteById(studentId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Course> getStudentCourses(int studentId) {
        try {
            Student student = studentJpaRepository.findById(studentId).get();
            return student.getCourses();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}