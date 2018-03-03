package org.bswt.openlyricj.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime>
{
	private static final DateTimeFormatter XML_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");
	
	@Override
	public LocalDateTime unmarshal(String v) throws Exception
	{
		return LocalDateTime.parse(v, XML_FORMAT);
	}

	@Override
	public String marshal(LocalDateTime v) throws Exception
	{
		String ret = "";
		if (null != v)
		{
			ret = v.format(XML_FORMAT);
		}
		return ret;
	}

}
