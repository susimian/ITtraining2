package com.simian.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simian.it.dao.IStudentDao;
import com.simian.it.entity.Student;

@Service
public class StudentService {
	@Autowired
	private IStudentDao studentDao;
	
	public Boolean isNameExisted(String name) {
		Student dataStudent = studentDao.findByName(name);
		if(dataStudent != null) {
			return true;
		}
		return false;
	}
	public Boolean login(Student student) {
		Student dataStudent = studentDao.findByName(student.getName());
		if(student.equals(dataStudent)) {
			return true;
		}
		return false;
	}

	public Boolean save(Student student) {
		// TODO Auto-generated method stub
		student = studentDao.save(student);
		if(student.getId() != null) {
			return true;
		}
		return false;
	}

	// 删
	public Boolean deleteOne(Integer id) {
		try {
			studentDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 改
	public Boolean update(Student student) {
		if (studentDao.save(student) == null) {
			return false;
		}
		return true;
	}

	// 查
	public Student getById(Integer id) {
		Student student = new Student();
		try {
			student = studentDao.findById(id).get();
		} catch (Exception e) {
			student = null;
		}
		return student;
	}

	public Page<Student> getByPageIndex(Integer index, Integer size) {
		index--;// PageRequest从0开始，用户习惯从1开始
		Pageable pageable = PageRequest.of(index, size);
		Page<Student> student = studentDao.findAll(pageable);
		return student;
	}

	public Page<Student> getByKeyword(String keyword, Integer index, Integer size) {
		index--;// PageRequest从0开始，用户习惯从1开始
		Page<Student> student = studentDao.findByKeyword(keyword, PageRequest.of(index, size));
		return student;
	}
}
