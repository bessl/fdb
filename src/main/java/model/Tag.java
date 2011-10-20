package model;

public class Tag {
	
	private String title;
	private String slug;
	
	public Tag(String title) {
		setTitle(title);
		setSlug();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if (title == null)
			throw new IllegalArgumentException("Tag hat keinen Titel");
		this.title = title;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug() {
		slug = getTitle().toLowerCase();
		slug = slug.replace(" ", "-");
		slug = slug.replace("--", "-");
		slug = slug.replaceAll("[^a-z0-9-]", "");
		if (slug.length() > 10) 
			slug = slug.substring(0, 10);
	}

}
