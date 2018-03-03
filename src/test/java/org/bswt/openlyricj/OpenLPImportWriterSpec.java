package org.bswt.openlyricj;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static javax.xml.xpath.XPathConstants.NODESET;
import static javax.xml.xpath.XPathConstants.STRING;

import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.bswt.openlyricj.OpenLPImportWriter;
import org.bswt.openlyricj.model.Lyrics;
import org.bswt.openlyricj.model.Properties;
import org.bswt.openlyricj.model.Song;
import org.bswt.openlyricj.model.Verse;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OpenLPImportWriterSpec
{
	private OpenLPImportWriter openLPImportWriter;
	
	@Before
	public void setUp()
	{
		openLPImportWriter = new OpenLPImportWriter();
	}
	
	@Test
	public void writeSong() throws IOException, ParserConfigurationException, SAXException, JAXBException, XPathExpressionException
	{
		Song song = new Song();
		Properties properties = new Properties();
		properties.setTitles("Amazing Grace");
		properties.setAuthors("John Newton");
		
		song.setProperties(properties);
		
		Lyrics lyrics = new Lyrics();
		
		List<Verse> verses = new ArrayList<Verse>();
		
		
		Verse verse = new Verse();
		verse.setName("v1");
		verse.setLines("Amazing Grace! how sweet the sound<br/>That saved a wretch like me;<br/>I once was lost, but now am found,<br/>Was blind, but now I see.");
		verses.add(verse);
		
		verse = new Verse();
		verse.setName("v2");
		verse.setLines("’Twas grace that taught my heart to fear,<br/>And grace my fears relieved;<br/>How precious did that grace appear,<br/>The hour I first believed!");
		verses.add(verse);
		
		verse = new Verse();
		verse.setName("v3");
		verse.setLines("Through many dangers, toils and snares<br/>I have already come;<br/>’Tis grace that brought me safe thus far,<br/>And grace will lead me home.");
		verses.add(verse);
		
		verse = new Verse();
		verse.setName("v4");
		verse.setLines("The Lord has promised good to me,<br/>His word my hope secures;<br/>He will my shield and portion be<br/>As long as life endures.");
		verses.add(verse);
		
		verse = new Verse();
		verse.setName("v5");
		verse.setLines("Yes, when this heart and flesh shall fail,<br/>And mortal life shall cease,<br/>I shall possess within the veil<br/>A life of joy and peace.");
		verses.add(verse);
		
		verse = new Verse();
		verse.setName("v6");
		verse.setLines("When we’ve been there a thousand years,<br/>Bright shining as the sun,<br/>We’ve no less days to sing God’s praise<br/>Than when we first begun.");
		verses.add(verse);
		
		lyrics.setVerses(verses);
		
		song.setLyrics(lyrics);
		
		File file = File.createTempFile("openlp-test", ".xml");
		StreamResult sr = new StreamResult(file);
		openLPImportWriter.write(song, sr);
		
		// read output file back in
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder db;
		db = dbFactory.newDocumentBuilder();
		Document doc = db.parse(file);
		XPath xp = XPathFactory.newInstance().newXPath();
		final Map<String, String> prefixMap = new HashMap<>();
		prefixMap.put("olp", "http://openlyrics.info/namespace/2009/song");
		xp.setNamespaceContext(new NamespaceContext() {
			public String getNamespaceURI(String prefix) {
		        return prefixMap.get(prefix); 
		    }
		    public Iterator<String> getPrefixes(String val) {
		        return null;
		    }
		    public String getPrefix(String uri) {
		        return null;
		    }
		});
		
		// check song root element
		assertEquals("namespace set properly", "http://openlyrics.info/namespace/2009/song", doc.getDocumentElement().getNamespaceURI());
		assertEquals("song version correct", 1, ((NodeList)xp.evaluate("/olp:song[@version='0.8']", doc, NODESET)).getLength());
		assertEquals("created in correct", 1, ((NodeList)xp.evaluate("/olp:song[@createdIn='OpenLP 2.4.6']", doc, NODESET)).getLength());
		assertEquals("created modified date", 1, ((NodeList)xp.evaluate("/olp:song[string(@modifiedDate)]", doc, NODESET)).getLength());
		
		assertEquals("title set", "Amazing Grace", (String) xp.evaluate("/olp:song/olp:properties/olp:titles/olp:title", doc, STRING));
		
		assertEquals("authors set", "John Newton", (String) xp.evaluate("/olp:song/olp:properties/olp:authors/olp:author", doc, STRING));
		
		// TODO: MOAR
		
	}
	
}
