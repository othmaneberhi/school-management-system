package com.ensah.schoolmanagementsystem.service;


import com.ensah.schoolmanagementsystem.bo.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherService {
    public Optional<Teacher> getTeacherById(Long id);
    public List<Teacher> getAllSTeachers();
    public void addTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    public void deleteTeacher(Teacher teacher);
}
