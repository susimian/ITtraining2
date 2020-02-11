package com.simian.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simian.it.dao.ICommentDao;
import com.simian.it.entity.Comment;

@Service
public class CommentService {
	@Autowired
	private ICommentDao commentDao;
	
	@RequestMapping("/save")
	public String save(Comment comment) {
		commentDao.save(comment);
		return "success";
	}
}
