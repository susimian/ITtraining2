package com.simian.it.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.simian.it.entity.Courseclass;

@Repository
public interface ICourseclassDao extends JpaRepository<Courseclass, Integer>{
	@Query(value="select n from Courseclass n where n.name like %:keyword%")
	Page<Courseclass> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
