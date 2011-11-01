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
	private void setSlug() {
		slug = Helpers.slugify(getTitle());
	}

}
