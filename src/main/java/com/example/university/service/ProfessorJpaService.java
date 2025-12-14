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
public class ProfessorJpaService implements ProfessorRepository {

    @Autowired
    private ProfessorJpaRepository professorJpaRepository;

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Override
    public ArrayList<Professor> getAllProfessors() {
        List<Professor> list = professorJpaRepository.findAll();
        return new ArrayList<>(list);
    }

    @Override
    public Professor addProfessor(Professor professor) {
        return professorJpaRepository.save(professor);
    }

    @Override
    public Professor getProfessorById(int professorId) {
        try {
            return professorJpaRepository.findById(professorId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Professor updateProfessor(int professorId, Professor professor) {
        try {
            Professor newProfessor = professorJpaRepository.findById(professorId).get();
            if (professor.getProfessorName() != null)
                newProfessor.setProfessorName(professor.getProfessorName());
            if (professor.getDepartment() != null)
                newProfessor.setDepartment(professor.getDepartment());
            return professorJpaRepository.save(newProfessor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProfessor(int professorId) {
        try {
            Professor professor = professorJpaRepository.findById(professorId).get();

            // Set professor to null for all their courses
            List<Course> courses = courseJpaRepository.findAll();
            for (Course course : courses) {
                if (course.getProfessor() != null && course.getProfessor().getProfessorId() == professorId) {
                    course.setProfessor(null);
                }
            }
            courseJpaRepository.saveAll(courses);

            professorJpaRepository.deleteById(professorId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Course> getProfessorCourses(int professorId) {
        try {
            Professor professor = professorJpaRepository.findById(professorId).get();
            List<Course> courses = courseJpaRepository.findAll();
            List<Course> professorCourses = new ArrayList<>();
            for (Course course : courses) {
                if (course.getProfessor() != null && course.getProfessor().getProfessorId() == professorId) {
                    professorCourses.add(course);
                }
            }
            return professorCourses;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}