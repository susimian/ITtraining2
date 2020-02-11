package com.simian.it.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.simian.it.entity.Admin;
import com.simian.it.entity.Student;
@Repository
public interface IStudentDao extends JpaRepository<Student, Integer>{
	@Query(value="select n from Student n where n.name like %:keyword%")
	Page<Student> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
	Student findByName(String name);
}
