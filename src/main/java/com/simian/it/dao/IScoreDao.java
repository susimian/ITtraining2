package com.simian.it.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simian.it.entity.Score;

@Repository
public interface IScoreDao extends JpaRepository<Score, Integer>{

}
