package com.simian.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.simian.it.dao.INewsDao;
import com.simian.it.entity.News;

@Service
public class NewsService {
	@Autowired
	private INewsDao newsDao;
	
	//
	public Boolean save(News news) {
		news = newsDao.save(news);
		
		if(news.getId() != null) {
			return true;
		}
		return false;
	}
	
	//
	public Boolean deleteOne(Integer id) {
		try {
			newsDao.deleteById(id);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;	
	}
	public Boolean deleteSelected(Integer[] ids) {
		for(Integer id : ids) {
			try {
				newsDao.deleteById(id);
			}catch (IllegalArgumentException e) {
				return false;
			}
		}
		return true;
	}
	
	//
	public Boolean update(News news) {
		newsDao.save(news);
		return true;
	}
	
	//
	public News getById(Integer id) {
		News news = new News();
		try {
			news = newsDao.findById(id).get();
		} catch (Exception e) {
			news = null;
		}
		return news;
	}
	public Page<News> getByPageIndex(Integer index, Integer size) {
		index--;// PageRequest从0开始，用户习惯从1开始
		Pageable pageable = PageRequest.of(index, size);
		Page<News> news = newsDao.findAll(pageable);
		return news;
	}
	public Page<News> getByKeyword(String keyword, Integer index, Integer size) {
		index--;// PageRequest从0开始，用户习惯从1开始
		Page<News> news = newsDao.findByKeyword(keyword,PageRequest.of(index, size));
		return news;
	}
}
