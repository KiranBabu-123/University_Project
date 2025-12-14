/*
 *
 * You can use the following import statements
 * 
 * import javax.persistence.*;
 * 
 */

// Write your code here
package com.example.university.model;
import com.example.university.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int professorId;

    @Column(name = "name")
    private String professorName;

    @Column(name = "department")
    private String department;

    @OneToMany(mappedBy = "professor")
    @JsonIgnoreProperties("professor")
    private List<Course> courses;

    public Professor() {}

    public Professor(int professorId, String professorName, String department, List<Course> courses) {
        this.professorId = professorId;
        this.professorName = professorName;
        this.department = department;
        this.courses = courses;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}