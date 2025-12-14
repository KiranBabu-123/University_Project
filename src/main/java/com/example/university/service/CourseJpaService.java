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
public class CourseJpaService implements CourseRepository {

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Autowired
    private ProfessorJpaRepository professorJpaRepository;

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    @Override
    public ArrayList<Course> getAllCourses() {
        List<Course> list = courseJpaRepository.findAll();
        return new ArrayList<>(list);
    }

    @Override
    public Course addCourse(Course course) {
        try {
            // Set professor
            if (course.getProfessor() != null) {
                int professorId = course.getProfessor().getProfessorId();
                Professor professor = professorJpaRepository.findById(professorId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
                course.setProfessor(professor);
            }

            // Set students
            if (course.getStudents() != null) {
                List<Integer> studentIds = new ArrayList<>();
                for (Student student : course.getStudents()) {
                    studentIds.add(student.getStudentId());
                }
                List<Student> students = studentJpaRepository.findAllById(studentIds);
                if (students.size() != studentIds.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                course.setStudents(students);
            }

            return courseJpaRepository.save(course);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Course getCourseById(int courseId) {
        try {
            return courseJpaRepository.findById(courseId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Course updateCourse(int courseId, Course course) {
        try {
            Course existingCourse = courseJpaRepository.findById(courseId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            if (course.getCourseName() != null)
                existingCourse.setCourseName(course.getCourseName());

            if (course.getCredits() != 0)
                existingCourse.setCredits(course.getCredits());

            // Update professor if provided
            if (course.getProfessor() != null) {
                int professorId = course.getProfessor().getProfessorId();
                Professor professor = professorJpaRepository.findById(professorId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
                existingCourse.setProfessor(professor);
            }

            // Update students if provided
            if (course.getStudents() != null) {
                List<Integer> studentIds = new ArrayList<>();
                for (Student student : course.getStudents()) {
                    studentIds.add(student.getStudentId());
                }
                List<Student> students = studentJpaRepository.findAllById(studentIds);
                if (students.size() != studentIds.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                existingCourse.setStudents(students);
            }

            return courseJpaRepository.save(existingCourse);

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            // Remove associations with students
            for (Student student : course.getStudents()) {
                student.getCourses().remove(course);
            }

            // Remove professor link
            course.setProfessor(null);

            courseJpaRepository.deleteById(courseId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Professor getCourseProfessor(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            return course.getProfessor();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Student> getCourseStudents(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            return course.getStudents();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}