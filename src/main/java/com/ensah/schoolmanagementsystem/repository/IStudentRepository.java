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

//    @Query("SELECT s FROM Student s WHERE " +
//            "(:id IS NULL OR s.id = :id) " +
//            "AND (:name IS NULL OR soundex(s.firstName) = soundex(:name)) " +
//            "AND (:name IS NULL OR soundex(s.lastName) = soundex(:name)) " +
//            "AND (:phone IS NULL OR s.phone LIKE %:phone%)")
//    public List<Student> searchStudents(@Param("id") Long id, @Param("name") String name, @Param("phone") String phone);

    public List<Student> findByPhoneContaining(String phone);
    public List<Student> findByEmailContaining(String email);
}
