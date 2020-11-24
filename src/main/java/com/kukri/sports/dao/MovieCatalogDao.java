
package com.kukri.sports.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kukri.sports.model.MovieCatalog;

/**
 * @author tarika
 *
 */

@Repository
public interface MovieCatalogDao extends JpaRepository<MovieCatalog, Integer>, JpaSpecificationExecutor<MovieCatalog> {

	public List<MovieCatalog> findAll();
	
	public MovieCatalog findByName(String name);

	@SuppressWarnings("unchecked")
	public MovieCatalog save(MovieCatalog movie);

	@Query(value="select * from kukrisports.movie_catalog where rating>= :rating", nativeQuery=true)
	public List<MovieCatalog> getAllByRating(Float rating);
	
	@Query(value="select * from kukrisports.movie_catalog where director= :director", nativeQuery=true)
	public List<MovieCatalog> getAllByDirector(String director);
	
	@Query(value="select * from kukrisports.movie_catalog where director= :director && rating>= :rating", nativeQuery=true)
	public List<MovieCatalog> getAllByDirectorAndRating(String director, Float rating);
}
