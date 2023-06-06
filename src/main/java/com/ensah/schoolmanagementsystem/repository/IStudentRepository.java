package com.ensah.schoolmanagementsystem.repository;

import com.ensah.schoolmanagementsystem.bo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student,Long> {
    public List<Student> findAllByOrderByLastName();
    public Student findByEmailEquals(String email);
    @Query("SELECT s FROM Student s WHERE SOUNDEX(s.firstName) = SOUNDEX(:name) OR SOUNDEX(s.lastName) = SOUNDEX(:name)")
    public List<Student> findBySimilarName(@Param("name") String name);
    public List<Student> findByPhoneContaining(String phone);
    public List<Student> findByEmailContaining(String email);
    public List<Student> findByCneContaining(String cne);
}
