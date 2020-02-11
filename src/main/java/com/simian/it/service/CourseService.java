package com.simian.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simian.it.dao.ICourseDao;
import com.simian.it.entity.Course;

@Service
public class CourseService {
	@Autowired
	private ICourseDao courseDao;
	
	// 增
	public Boolean save(Course course) {
		course = courseDao.save(course);
		
		if(course.getId() != null) {
			return true;
		}
		return false;
	}
	
	// 删
	public Boolean deleteOne(Integer id) {
		try {
			courseDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;	
	}
	
	// 改
	public Boolean update(Course course) {
		if(courseDao.save(course) == null) {
			return false;
		}
		return true;
	}
	
	//查
	public Course getById(Integer id) {
		Course course = new Course();
		try {
			course = courseDao.findById(id).get();
		} catch (Exception e) {
			course = null;
		}
		return course;
	}
	public Page<Course> getByPageIndex(Integer index, Integer size) {
		index--;// PageRequest从0开始，用户习惯从1开始
		Pageable pageable = PageRequest.of(index, size);
		Page<Course> course = courseDao.findAll(pageable);
		return course;
	}
	public Page<Course> getByKeyword(String keyword, Integer index, Integer size) {
		index--;// PageRequest从0开始，用户习惯从1开始
		Page<Course> course = courseDao.findByKeyword(keyword,PageRequest.of(index, size));
		return course;
	}
}
