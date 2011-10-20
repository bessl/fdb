package model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class WhenAddingTagsToAMovie {

	private Tag tag1;
	private Tag tag2;
	private Movie movie;

	@Before
	public void setUp() throws Exception {
		movie = new Movie();
		tag1 = new Tag("Programming");
		tag2 = new Tag("Conference");
	}

	@Test
	public void aNewMovieShouldNotHaveAnyTags() {		
		assertThat("No Tags", movie.getTags().size(), equalTo(0));
	}
	
	@Test
	public void addTagsToMovie() {		
		movie.addTag(tag1);
		movie.addTag(tag2);

		assertThat("Add Tags", movie.getTags().size(), equalTo(2));
		Assert.assertEquals("Programming", movie.getTags().get(0).getTitle());
		Assert.assertEquals("Conference", movie.getTags().get(1).getTitle());
	}
	
	  @Test(expected = IllegalArgumentException.class)
	  public void withWrongTagWeShouldGetAnException() {
		  movie.addTag(null);
	  }

}
