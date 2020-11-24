/**
 * 
 */
package com.kukri.sports.service;

import java.util.List;

import com.kukri.sports.model.MovieCatalog;

/**
 * @author tarika
 *
 */
public interface MovieCatalogService {

	public List<MovieCatalog> findAll();

	public MovieCatalog findByName(String name);

	public MovieCatalog saveMovie(MovieCatalog movie);
	
	public Integer delete(MovieCatalog movie);

	public List<MovieCatalog> getAllByRating(Float rating);

	public List<MovieCatalog> getAllByDirector(String director);

	public List<MovieCatalog> getAllByDirectorAndRating(String director, Float rating);

}
