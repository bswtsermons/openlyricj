package org.bswt.openlyricj.model;

import javax.xml.bind.annotation.XmlAttribute;

//@XmlAccessorType(XmlAccessType.FIELD)
public class Verse
{
	private String name;
	
	private String lines;

	public String getLines()
	{
		return lines;
	}

	public void setLines(String lines)
	{
		this.lines = lines;
	}

	@XmlAttribute
	//@XmlJavaTypeAdapter(CDATAAdapter.class)
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Verse [name=" + name + ", lines=" + lines + "]";
	}
}
