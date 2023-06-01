package com.ensah.schoolmanagementsystem.service;

import com.ensah.schoolmanagementsystem.bo.Teacher;
import com.ensah.schoolmanagementsystem.repository.ITeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService{
    private final ITeacherRepository teacherRepository;

    public TeacherService(ITeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        return this.teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> getAllSTeachers() {
        return this.teacherRepository.findAll();
    }

    @Override
    public void addTeacher(Teacher teacher) {
        this.teacherRepository.save(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        this.teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        this.teacherRepository.delete(teacher);
    }
}
