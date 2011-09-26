package com.ff.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ff.test.dao.MovieDao;
import com.ff.test.domain.MovieSource;

@org.springframework.stereotype.Component("movieService")
public class MovieService {
	@Autowired
	private MovieDao movieDao;

	public void insert(MovieSource movie){
		movieDao.insert(movie);
	}
	
	public List<MovieSource> loadAll(){
		return movieDao.loadAll();
	}
	
	public List<MovieSource> search(List filters,int from, int to){
		return movieDao.search(filters, from, to);
	}
	
	public MovieSource load(Long id){
		return movieDao.load(id);
	}
	
	public Integer count(List filters){
		return movieDao.count(filters);
	}
	
	
	public MovieDao getMovieDao() {
		return movieDao;
	}

	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}
	
	
}
