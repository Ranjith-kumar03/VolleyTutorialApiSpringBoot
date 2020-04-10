package com.ranjith.webapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ranjith.webapplication.model.Student;

public interface StudentDAO extends JpaRepository<Student, Integer> {

	@Query(value="select * from students",nativeQuery=true)
	List<Student> findByStudents();
	
	@Query(value="select * from students as s where s.roll_Number=:rollNumber",nativeQuery=true)
	Student findById(int rollNumber);
	
	@Transactional
	@Modifying
	@Query(value="insert into students (roll_number,age,name,sex) values(:roll_number,:age,:name,:sex)",nativeQuery=true)
	Integer addStudent(@Param("roll_number") Integer roll_number
								,@Param("age") Integer age
								,@Param("name") String name
								,@Param("sex") String sex);
}
