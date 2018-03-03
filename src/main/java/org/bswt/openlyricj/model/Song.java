package org.bswt.openlyricj.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class Song
{
	@XmlAttribute
	private String version = "0.8";
	
	@XmlAttribute
	private String createdIn = "OpenLP 2.4.6";
	
	@XmlAttribute
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime modifiedDate = LocalDateTime.now();
	
	private Properties properties;
	
	private Lyrics lyrics;
	
	public Lyrics getLyrics()
	{
		return lyrics;
	}

	public void setLyrics(Lyrics lyrics)
	{
		this.lyrics = lyrics;
	}

	public Properties getProperties()
	{
		return properties;
	}

	public void setProperties(Properties properties)
	{
		this.properties = properties;
	}
}
