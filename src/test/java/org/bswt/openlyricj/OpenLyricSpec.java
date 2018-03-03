package org.bswt.openlyricj;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import info.openlyrics.namespace._2009.song.LinesType;
import info.openlyrics.namespace._2009.song.LinesType.Br;
import info.openlyrics.namespace._2009.song.LyricsType;
import info.openlyrics.namespace._2009.song.ObjectFactory;
import info.openlyrics.namespace._2009.song.PropertiesType;
import info.openlyrics.namespace._2009.song.Song;
import info.openlyrics.namespace._2009.song.TitlesType;
import info.openlyrics.namespace._2009.song.VerseType;

public class OpenLyricSpec
{
	@Test 
	public void marshall() throws JAXBException
	{
		TitlesType titlesType = new TitlesType();
		titlesType.setTitle("Amazing Gracie");
		PropertiesType propertiesType = new PropertiesType();
		propertiesType.getTitles().add(titlesType);
		
		LinesType linesType = new LinesType();
		linesType.getContent().add("Amazing grace");
		//linesType.getContent().add("<br/>");
		ObjectFactory of = new ObjectFactory();
		
		//linesType.getContent().add(e)
		linesType.getContent().add(of.createLinesTypeBr(of.createLinesTypeBr()));
		//linesType.getContent().add("how sweet the sound");
		
		VerseType verseType = new VerseType();
		verseType.setName("v1");
		verseType.setLines(linesType);
		
		LyricsType lyricsType = new LyricsType();
		lyricsType.getVerse().add(verseType);
		
		Song song = new Song();
		song.setCreatedIn("foo");
		song.setVersion("0.8");
		song.setModifiedIn("boofoo");
		song.setModifiedDate("2018-02-26T12:17:59");
		song.setProperties(propertiesType);
		song.setLyrics(lyricsType);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Song.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		marshaller.marshal(song, System.out);
		
	}

	@Test
	public void test() throws IOException, JAXBException
	{
		try (final InputStream is = this.getClass().getClassLoader().getResourceAsStream("openlyrics-org-dataformat-example.xml")) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Song.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<Song> jaxbSong = unmarshaller.unmarshal(new StreamSource(is), Song.class);
			Song song = jaxbSong.getValue();
			
			assertNotNull("song has properties", song.getProperties());
			assertNotNull("property has titles", song.getProperties().getTitles());
			assertEquals("song title set", "Amazing Grace", song.getProperties().getTitles().get(0).getTitle());
			assertNotNull("song has lyrics", song.getLyrics());
			assertEquals("song has one verse", 1, song.getLyrics().getVerse().size());
			assertEquals("verse name set", "v1", song.getLyrics().getVerse().get(0).getName());
		}
		
	}

}
