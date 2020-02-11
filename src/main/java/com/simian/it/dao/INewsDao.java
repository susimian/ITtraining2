package com.simian.it.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.simian.it.entity.News;

@Repository
public interface INewsDao extends JpaRepository<News, Integer>{

	
	@Query(value="select n from News n where n.title like %:keyword%")
	Page<News> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
	Page<News> findByTitleLike(String title, Pageable pageable);
}
