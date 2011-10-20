package model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class WhenCreatingATag {

	  @Test(expected = IllegalArgumentException.class)
	  public void withWrongTagWeShouldGetAnException() {
		  Tag t1 = new Tag(null);
	  }
		  
	@Test
	public void tagTitleShouldSetAURLAutomatically() {
		Tag t1 = new Tag("foo bar");
		assertThat(t1.getTitle(), is(equalTo("foo bar")));
		assertThat(t1.getSlug(), is(equalTo("foo-bar")));
	}

}
