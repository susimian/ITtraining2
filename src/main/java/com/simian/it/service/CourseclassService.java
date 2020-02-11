package com.simian.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simian.it.dao.ICourseclassDao;
import com.simian.it.entity.Course;
import com.simian.it.entity.Courseclass;
import com.simian.it.entity.News;

@Service
public class CourseclassService {
	@Autowired
	private ICourseclassDao courseclassDao;
	
	Integer success = 1;
	Integer fail = 0;
	
	// 增
	public Integer save(Courseclass courseclass) {
		courseclass = courseclassDao.save(courseclass);
		
		if(courseclass.getId() != null) {
			return success;
		}
		return fail;
	}
	
	// 删
	public Boolean deleteOne(Integer id) {
		try {
			courseclassDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;	
	}
	
	// 改
	public Boolean update(Courseclass courseclass) {
		if(courseclassDao.save(courseclass) == null) {
			return false;
		}
		return true;
	}

	// 查
	public Courseclass getById(Integer id) {
		Courseclass courseclass = new Courseclass();
		try {
			courseclass = courseclassDao.findById(id).get();
		} catch (Exception e) {
			courseclass = null;
		}
		return courseclass;
	}
	public Page<Courseclass> getByPageIndex(Integer index, Integer size) {
		index--;// PageRequest从0开始，用户习惯从1开始
		Pageable pageable = PageRequest.of(index, size);
		Page<Courseclass> courseclass = courseclassDao.findAll(pageable);
		return courseclass;
	}
	public Page<Courseclass> getByKeyword(String keyword, Integer index, Integer size) {
		index--;// PageRequest从0开始，用户习惯从1开始
		Page<Courseclass> courseclass = courseclassDao.findByKeyword(keyword,PageRequest.of(index, size));
		return courseclass;
	}
}
