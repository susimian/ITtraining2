package com.simian.it.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simian.it.entity.Comment;

@Repository
public interface ICommentDao extends JpaRepository<Comment, Integer>{

}
