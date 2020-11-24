
package com.kukri.sports.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author tarika
 *
 */

@Entity
@Table(name = "movie_catalog")
public class MovieCatalog {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_generator")
	@SequenceGenerator(name="movie_generator", sequenceName="movie_generator",allocationSize=1)
	@Column(name = "id", nullable = false, unique = true)
	public Integer id;


	@Column(name = "movies", nullable = false, length = 255)
	public String name;

	@Column(name = "director", nullable = false, length = 255)
	public String director;

	@Column(name = "rating", nullable = false)
	public Float rating;
	
	public MovieCatalog() {
		//super();
	}

	public MovieCatalog(Integer id, String name, String director, Float rating) {
		super();
		this.id = id;
		this.name = name;
		this.director = director;
		this.rating = rating;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	
	

}
