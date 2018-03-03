package org.bswt.openlyricj;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import org.bswt.openlyricj.model.Song;

public class OpenLPImportWriter
{
	public void write(Song song, StreamResult streamResult) throws JAXBException
	{
		JAXBContext jbc = JAXBContext.newInstance(Song.class);
		Marshaller marshaller = jbc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		/*
		marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler()
		{
			@Override
			public void escape(char[] ac, int i, int j, boolean flag, Writer writer) throws IOException
			{
				writer.write(ac, i, j);
			}
		});
		*/
		marshaller.marshal(song, streamResult);
	}
}
