package org.bswt.openlyricj.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Lyrics
{
	private List<Verse> verses;
	
	@XmlElement(name="verse")
	public List<Verse> getVerses()
	{
		return verses;
	}

	public void setVerses(List<Verse> verses)
	{
		this.verses = verses;
	}


}
