package com.simian.it.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simian.it.entity.Admin;


@Repository
public interface IAdminDao extends JpaRepository<Admin, Integer>{
	Admin findByName(String name);
}
