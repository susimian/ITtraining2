package com.simian.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simian.it.dao.IScoreDao;
import com.simian.it.entity.Score;

@Service
public class ScoreService {
	@Autowired
	private IScoreDao scoreDao;
	
	public void save(Integer student_id, Integer courseclass_id, Integer theory, Integer practice) {
		Score score = new Score();
		score.getStudent().setId(student_id);
		score.getCourseclass().setId(courseclass_id);
		score.setTheory(theory);
		score.setPractice(practice);
		scoreDao.save(score);
	}
}
