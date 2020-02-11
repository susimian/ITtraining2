package com.simian.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simian.it.dao.IAdminDao;
import com.simian.it.entity.Admin;

@Service
public class AdminService {
	@Autowired
	private IAdminDao adminDao;
	public void save(Admin admin) {
		System.out.println("service");
		
		adminDao.save(admin);
	}
	public Boolean login(Admin admin) {
		Admin dataAdmin = adminDao.findByName(admin.getName());
		//admin.equals(dataAdmin);
		if(admin.equals(dataAdmin)) {
			return true;
		}
		return false;
	}
	
}
