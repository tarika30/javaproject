package com.kukri.sports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kukri.sports.dao.MovieCatalogDao;
import com.kukri.sports.model.MovieCatalog;

@Service
public class MovieCatalogServiceImpl implements MovieCatalogService {
	
	@Autowired	
	public MovieCatalogDao movieCatalogDao;

	@Override
	public List<MovieCatalog> findAll() {
		return movieCatalogDao.findAll();
	}

	@Override
	public MovieCatalog findByName(String name){
		return movieCatalogDao.findByName(name);
	}

	@Override
	public MovieCatalog saveMovie(MovieCatalog movie) {
		movieCatalogDao.save(movie);
		return movie;
	}

	@Override
	public List<MovieCatalog> getAllByRating(Float rating) {
		return movieCatalogDao.getAllByRating(rating);
		
	}

	@Override
	public List<MovieCatalog> getAllByDirector(String director) {
		return movieCatalogDao.getAllByDirector(director);
	}

	@Override
	public List<MovieCatalog> getAllByDirectorAndRating(String director, Float rating) {
		return movieCatalogDao.getAllByDirectorAndRating(director,rating);
	}

	@Override
	public Integer delete(MovieCatalog movie) {
		 movieCatalogDao.delete(movie);
		return null;
	}
}
