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
import com.example.university.service.ProfessorJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ProfessorController {

    @Autowired
    ProfessorJpaService professorJpaService;

    // API 1: GET /professors
    @GetMapping("/professors")
    public ArrayList<Professor> getAllProfessors() {
        return professorJpaService.getAllProfessors();
    }

    // API 2: POST /professors
    @PostMapping("/professors")
    public Professor addProfessor(@RequestBody Professor professor) {
        return professorJpaService.addProfessor(professor);
    }

    // API 3: GET /professors/{professorId}
    @GetMapping("/professors/{professorId}")
    public Professor getProfessorById(@PathVariable int professorId) {
        return professorJpaService.getProfessorById(professorId);
    }

    // API 4: PUT /professors/{professorId}
    @PutMapping("/professors/{professorId}")
    public Professor updateProfessor(@PathVariable int professorId, @RequestBody Professor professor) {
        return professorJpaService.updateProfessor(professorId, professor);
    }

    // API 5: DELETE /professors/{professorId}
    @DeleteMapping("/professors/{professorId}")
    public void deleteProfessor(@PathVariable int professorId) {
        professorJpaService.deleteProfessor(professorId);
    }

    // API 6: GET /professors/{professorId}/courses
    @GetMapping("/professors/{professorId}/courses")
    public List<Course> getProfessorCourses(@PathVariable int professorId) {
        return professorJpaService.getProfessorCourses(professorId);
    }
}