package org.charlestech.utils.rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

/**
 * RSS feed parser
 * 
 * @author http://www.vogella.com/articles/RSSFeed/
 * 
 */
public class RSSFeedParser {
	/* Define final variables for tag names */
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LANGUAGE = "language";
	static final String COPYRIGHT = "copyright";
	static final String LINK = "link";
	static final String AUTHOR = "author";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String GUID = "guid";
	/* Define final variables for tag names */

	final URL url; // Feed url

	/**
	 * Constructor
	 * 
	 * @param feedUrl
	 */
	public RSSFeedParser(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Read feed content from url, and gernerate feed object
	 * 
	 * @return Feed object
	 */
	public Feed readFeed() {
		Feed feed = null;
		try {
			boolean isFeedHeader = true;
			// Set header values initialised as empty
			String description = "";
			String title = "";
			String link = "";
			String language = "";
			String copyright = "";
			String author = "";
			String pubdate = "";
			String guid = "";

			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			// Read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					// Get feed header information
					String localPart = event.asStartElement().getName()
							.getLocalPart();
					if (ITEM.equals(localPart)) {
						if (isFeedHeader) {
							isFeedHeader = false;
							feed = new Feed(title, link, description, language,
									copyright, pubdate);
						}
						event = eventReader.nextEvent();
					}
					if (TITLE.equals(localPart)) {
						title = getCharacterData(event, eventReader);
						continue;
					}
					if (DESCRIPTION.equals(localPart)) {
						description = getCharacterData(event, eventReader);
						continue;
					}
					if (LINK.equals(localPart)) {
						link = getCharacterData(event, eventReader);
						continue;
					}
					if (GUID.equals(localPart)) {
						guid = getCharacterData(event, eventReader);
						continue;
					}
					if (LANGUAGE.equals(localPart)) {
						language = getCharacterData(event, eventReader);
						continue;
					}
					if (AUTHOR.equals(localPart)) {
						author = getCharacterData(event, eventReader);
						continue;
					}
					if (PUB_DATE.equals(localPart)) {
						pubdate = getCharacterData(event, eventReader);
						continue;
					}
					if (COPYRIGHT.equals(localPart)) {
						copyright = getCharacterData(event, eventReader);
					}
					continue;
				}
				if (event.isEndElement()) {
					// Get item information
					if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
						FeedMessage message = new FeedMessage();
						message.setAuthor(author);
						message.setDescription(description);
						message.setGuid(guid);
						message.setLink(link);
						message.setTitle(title);
						// Add into the feed
						feed.getMessages().add(message);
						event = eventReader.nextEvent();
						continue;
					}
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		return feed;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
			throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}

	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		RSSFeedParser parser = new RSSFeedParser(
				"http://www.vogella.de/article.rss");
		Feed feed = parser.readFeed();
		System.out.println(feed);
		for (FeedMessage message : feed.getMessages()) {
			System.out.println(message);

		}

	}
}
