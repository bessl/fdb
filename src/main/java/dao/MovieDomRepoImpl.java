package dao;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import model.Movie;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.LinkedList;

public class MovieDomRepoImpl {

	private LinkedList<Movie> movies;

	public MovieDomRepoImpl() {
		movies = new LinkedList<Movie>();
	}

	public void findAll(File xmlfile) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlfile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("entry");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					Movie m = new Movie();
					m.setTitle(getTagValue("title", eElement));
					m.setSummary(getTagValue("summary", eElement));
					m.setUrl(getAttributeValue("link", eElement, "href"));
					m.setUrlPlayer(getAttributeValue("media:player", eElement, "url"));
					m.setUrlThumbnail(getAttributeValue("media:thumbnail", eElement, "url"));
					
					movies.add(m);
					
					/*
					System.out.println("Title : "	+ getTagValue("title", eElement));
					System.out.println("Summary : "	+ getTagValue("summary", eElement));
					System.out.println("Link : " + getAttributeValue("link", eElement, "href"));
					System.out.println("MediaPlayerUrl : " + getAttributeValue("media:player", eElement, "url"));
					System.out.println("MediaThumbnailUrl : " + getAttributeValue("media:thumbnail", eElement, "url"));
					*/
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LinkedList<Movie> getMovies() {
		return this.movies;
	}
	
	private String getAttributeValue(String sTag, Element eElement, String attribute) {
		NamedNodeMap attrs = eElement.getElementsByTagName(sTag).item(0).getAttributes();
		return attrs.getNamedItem(attribute).getTextContent().trim();
	}
	
	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue().trim();
	}

}