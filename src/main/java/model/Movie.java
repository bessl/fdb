package model;

import java.util.LinkedList;

public class Movie {

	private String title;
	private String summary;
	private String url;
	private String urlThumbnail;
	private String urlPlayer;
	private String slug;
	private int rating;
	private LinkedList<Tag> tags;

	public Movie() {
		this.rating = 3;
		this.tags = new LinkedList<Tag>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.setSlug();
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlThumbnail() {
		return urlThumbnail;
	}

	public void setUrlThumbnail(String urlThumbnail) {
		this.urlThumbnail = urlThumbnail;
	}

	public String getUrlPlayer() {
		return urlPlayer;
	}

	public void setUrlPlayer(String urlPlayer) {
		this.urlPlayer = urlPlayer;
	}

	public String toString() {
		return "Movie: " + getTitle();
	}

	public void setRating(int rate) {
		if (rate > 0 && rate < 6)
			rating = rate;
		else
			throw new IllegalArgumentException(
					"Rating darf nur zwischen 1 bis 5 liegen");
	}

	public Integer getRating() {
		return rating;
	}

	public String getSlug() {
		return slug;
	}
	
	private void setSlug() {
		slug = title.toLowerCase();
		slug = slug.replace(" ", "-");
		slug = slug.replace("--", "-");
		slug = slug.replaceAll("[^a-z0-9-]", "");
		if (slug.length() > 20) 
			slug = slug.substring(0, 20);
	}

	public void addTag(Tag tag) {
		if (tag == null)
			throw new IllegalArgumentException("Tag ist kein gültiges Objekt");
		tags.add(tag);
	}

	public LinkedList<Tag> getTags(){
		return tags;
	}
	
}
