package com.ensah.schoolmanagementsystem.repository;

import com.ensah.schoolmanagementsystem.bo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student,Long> {
}
