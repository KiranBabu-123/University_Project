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

public interface ProfessorRepository {
    ArrayList<Professor> getAllProfessors();
    Professor addProfessor(Professor professor);
    Professor getProfessorById(int professorId);
    Professor updateProfessor(int professorId, Professor professor);
    void deleteProfessor(int professorId);
    List<Course> getProfessorCourses(int professorId);
}
