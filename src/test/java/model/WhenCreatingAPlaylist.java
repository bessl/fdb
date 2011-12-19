package model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class WhenCreatingAPlaylist {

	private Movie movie;

	@Before
	public void setUp() throws Exception {
		movie = new Movie();
	}

	// Slug - automatisch URL aus Titel erstellen.
	@Test
	public void movieTitleShouldSetASlugAutomatically() {
		String[][] titles = {
				{ "Spam Terry Jones, Michael Spam Palin, John Spam", "spam-terry", },
				{ "&*#$KADJA23SDdadsa@#$#$", "kadja23sdd" } ,
				{ "x  Kcd", "x-kcd" } 
		};
		
		for (String[] t : titles) {
			movie.setTitle(t[0]);
			assertThat(movie.getSlug(), is(equalTo(t[1])));
		}

	}

	// Rating

	@Test(expected = IllegalArgumentException.class)
	public void withWrongRatingValueWeShouldGetAnException() {
		movie.setRating(1000);
	}

	@Test
	public void newMovieShouldHaveDefaultRating() {
		assertThat("Default Rating", movie.getRating(), equalTo(3));
	}

	@Test
	public void updateMovieRating() {
		movie.setRating(2);

		assertThat("Update Rating", movie.getRating(), equalTo(2));
	}

}
