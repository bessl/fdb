package model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class StaticHelperTests {

	@Test
	public void movieTitleShouldSetASlugAutomatically() {
		assertThat(Helpers.slugify("Hallo du da hier bin ich"), is("hallo-du-d"));
	}

}
