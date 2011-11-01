package model;

public class Slug {

	private String slug;
	
	public String getSlug() {
		return slug;
	}
	public void setSlug(String s) {
		slug = s.toLowerCase();
		slug = slug.replace(" ", "-");
		slug = slug.replace("--", "-");
		slug = slug.replaceAll("[^a-z0-9-]", "");
		if (slug.length() > 10) 
			slug = slug.substring(0, 10);
	}
	
}
