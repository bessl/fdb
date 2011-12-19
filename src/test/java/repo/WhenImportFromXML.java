package repo;

import java.io.File;
import java.util.LinkedList;

import junit.framework.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import model.Movie;

import org.junit.Before;
import org.junit.Test;

import repo.XMLImport;


public class WhenImportFromXML {

	  @Before
	  public void setUp() throws Exception {
	  }
	  	  
	  @Test
	  public void withValidDataWeShouldBeAbleToGetAListOfMovieObjects() {
		  File xmlfile = new File(getClass().getClassLoader().getResource("miro.xml").getPath());
			  
		  XMLImport importer = new XMLImport();
		  importer.findAll(xmlfile);
		  LinkedList<Movie> movies = importer.getMovies();
	
		  Assert.assertEquals("http://python.mirocommunity.org/video/5007/pygotham-2011-machine-learning", movies.get(0).getUrl());
		  Assert.assertEquals("http://a.images.blip.tv/Gloriajw-TmuxIPythonAwesome119-688.jpg", movies.get(1).getUrlThumbnail());
		  Assert.assertEquals("http://blip.tv/pygotham/go-go-gadget-python-5585288", movies.get(4).getUrlPlayer());
		  Assert.assertEquals("PyGotham 2011: Real-time Web: Gevent and Socket.io", movies.get(6).getTitle());
		  Assert.assertTrue(movies.get(2).getSummary().contains("Presented by James Dennis"));
		  assertThat("List size", movies.size(), equalTo(30));
	  }  

}
