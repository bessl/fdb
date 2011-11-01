package model;

public class Helpers {
	
	public static String slugify(String s) {
		String slug = s.toLowerCase();
		slug = slug.replace(" ", "-");
		slug = slug.replace("--", "-");
		slug = slug.replaceAll("[^a-z0-9-]", "");
		if (slug.length() > 10) 
			slug = slug.substring(0, 10);
		return slug;
	}
}
