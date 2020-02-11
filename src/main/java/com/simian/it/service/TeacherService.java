package com.simian.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simian.it.dao.ITeacherDao;
import com.simian.it.entity.Student;
import com.simian.it.entity.Teacher;

@Service
public class TeacherService {
	@Autowired
	private ITeacherDao teacherDao;

	Integer success = 1;
	Integer fail = 0;
	
	public Boolean login(Teacher teacher) {
		Teacher dataTeacher = teacherDao.findByName(teacher.getName());
		if(teacher.equals(dataTeacher)) {
			return true;
		}
		return false;
	}
	
	public Integer save(Teacher teacher) {
		teacher = teacherDao.save(teacher);
		
		if(teacher.getId() != null) {
			return success;
		}
		return fail;
	}

	// 删
	public Boolean deleteOne(Integer id) {
		try {
			teacherDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 改
	public Boolean update(Teacher teacher) {
		if (teacherDao.save(teacher) == null) {
			return false;
		}
		return true;
	}

	// 查
	public Teacher getById(Integer id) {
		Teacher teacher = new Teacher();
		try {
			teacher = teacherDao.findById(id).get();
		} catch (Exception e) {
			teacher = null;
		}
		return teacher;
	}

	public Page<Teacher> getByPageIndex(Integer index, Integer size) {
		index--;// PageRequest从0开始，用户习惯从1开始
		Pageable pageable = PageRequest.of(index, size);
		Page<Teacher> teacher = teacherDao.findAll(pageable);
		return teacher;
	}

	public Page<Teacher> getByKeyword(String keyword, Integer index, Integer size) {
		index--;// PageRequest从0开始，用户习惯从1开始
		Page<Teacher> teacher = teacherDao.findByKeyword(keyword, PageRequest.of(index, size));
		return teacher;
	}
}
