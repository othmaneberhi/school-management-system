package com.ensah.schoolmanagementsystem.service;

import com.ensah.schoolmanagementsystem.bo.Student;
import com.ensah.schoolmanagementsystem.repository.IStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{

    private final IStudentRepository studentRepository;
    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public List<Student> getAllStudentsByOrderByLastName() {
        return studentRepository.findAllByOrderByLastName();
    }

    @Override
    public Optional<Student> getStudentByEmail(String email) {
        return Optional.ofNullable(studentRepository.findByEmailEquals(email));
    }

    @Override
    public List<Student> getAllStudentsBySimilarName(String name) {
        return studentRepository.findBySimilarName(name);
    }

    @Override
    public List<Student> getAllStudentsBySimilarPhone(String phone) {
        return studentRepository.findByPhoneContaining(phone);
    }

    @Override
    public List<Student> getAllStudentsBySimilarEmail(String email) {
        return studentRepository.findByEmailContaining(email);
    }

}
