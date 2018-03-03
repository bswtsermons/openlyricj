package org.bswt.openlyricj.model;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Properties
{
	private List<String> titles;
	
	private List<String> authors;
	
	@XmlElementWrapper(name = "authors")
	@XmlElement(name = "author")
	public List<String> getAuthors()
	{
		return authors;
	}

	public void setAuthors(String ... authors)
	{
		this.authors = Arrays.asList(authors);
	}
	
	public void setAuthors(List<String> authors)
	{
		this.authors = authors;
	}

	@XmlElementWrapper(name = "titles")
	@XmlElement(name = "title")
	public List<String> getTitles()
	{
		return titles;
	}

	public void setTitles(String ... titles)
	{
		this.titles = Arrays.asList(titles);
	}
	
	public void setTitles(List<String> titles)
	{
		this.titles = titles;
	}
	
	
}
