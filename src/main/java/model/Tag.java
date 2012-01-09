package model;

import java.io.Serializable;

public class Tag extends ModelBase implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
	private String slug;

	public Tag() {
	}
	
	public Tag(String title) {
		setTitle(title);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null)
			throw new IllegalArgumentException("Tag hat keinen Titel!");
		this.title = title;
		setSlug();
	}

	public String getSlug() {
		return slug;
	}

	private void setSlug() {
		slug = Helpers.slugify(getTitle());
	}
	
}