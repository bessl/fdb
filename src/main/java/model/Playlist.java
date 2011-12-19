package model;

import java.util.LinkedList;

public class Playlist {

	private LinkedList<Movie> movies;
	private String title;
	private String slug;

	public Playlist(String title) {
		setTitle(title);
		setSlug();
		this.movies = new LinkedList<Movie>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null)
			throw new IllegalArgumentException("Playlist hat keinen Titel");
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	private void setSlug() {
		slug = Helpers.slugify(getTitle());
	}
	
	public void addMovie(Movie movie) {
		if (movie == null)
			throw new IllegalArgumentException("Movie ist kein gültiges Objekt");
		movies.add(movie);
	}

	public LinkedList<Movie> getMovies(){
		return movies;
	}

}
