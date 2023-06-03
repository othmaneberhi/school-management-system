package com.ensah.schoolmanagementsystem.service;

import com.ensah.schoolmanagementsystem.bo.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    public Optional<Student> getStudentById(Long id);
    public List<Student> getAllStudents();
    public void addStudent(Student student);

    void updateStudent(Student student);

    public void deleteStudent(Student student);
    public List<Student> getAllStudentsByOrderByLastName();
    public Optional<Student> getStudentByEmail(String email);

    public List<Student> getAllStudentsBySimilarName(String name);

    List<Student> getAllStudentsBySimilarPhone(String phone);
    List<Student> getAllStudentsBySimilarEmail(String phone);
}
