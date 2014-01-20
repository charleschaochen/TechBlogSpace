package org.charlestech.utils.rss;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * RSS Feed Writer
 * 
 * @author http://www.vogella.com/articles/RSSFeed/
 * 
 */
public class RSSFeedWriter {

	private String outputFile; // Output file path
	private Feed rssfeed; // RSS feed to be written

	/**
	 * Constructor
	 * 
	 * @param rssfeed
	 * @param outputFile
	 */
	public RSSFeedWriter(Feed rssfeed, String outputFile) {
		this.rssfeed = rssfeed;
		this.outputFile = outputFile;
	}

	public void write() throws Exception {
		// Create a XMLOutputFactory
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		// Create XMLEventWriter
		XMLEventWriter eventWriter = outputFactory
				.createXMLEventWriter(new FileOutputStream(outputFile));
		// Create a EventFactory
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();

		// Create a line wrap character
		XMLEvent end = eventFactory.createCharacters("\n");

		// Create and write start <xml> tag
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		eventWriter.add(end); // Add a line wrap

		// Create and write start <rss> tag
		StartElement rssStart = eventFactory.createStartElement("", "", "rss");
		eventWriter.add(rssStart);
		eventWriter.add(eventFactory.createAttribute("version", "2.0"));
		eventWriter.add(end); // Add a line wrap

		// Create and write start <channel> tag
		eventWriter.add(eventFactory.createStartElement("", "", "channel"));
		eventWriter.add(end); // Add a line wrap

		/* Create and write the header informations */
		createNode(eventWriter, "title", rssfeed.getTitle());
		createNode(eventWriter, "link", rssfeed.getLink());
		createNode(eventWriter, "description", rssfeed.getDescription());
		createNode(eventWriter, "language", rssfeed.getLanguage());
		createNode(eventWriter, "copyright", rssfeed.getCopyright());
		createNode(eventWriter, "pubdate", rssfeed.getPubDate());
		/* Create and write the header informations */

		// Create and write items
		for (FeedMessage entry : rssfeed.getMessages()) {
			// Create and write start <item> tag
			eventWriter.add(eventFactory.createStartElement("", "", "item"));
			eventWriter.add(end); // Add a line wrap
			/* Create and write item informations */
			createNode(eventWriter, "title", entry.getTitle());
			createNode(eventWriter, "description", entry.getDescription());
			createNode(eventWriter, "link", entry.getLink());
			createNode(eventWriter, "author", entry.getAuthor());
			createNode(eventWriter, "guid", entry.getGuid());
			/* Create and write item informations */
			// Create and write end </item> tag
			eventWriter.add(eventFactory.createEndElement("", "", "item"));
		}

		eventWriter.add(end); // Add a line wrap
		// Create and write end </channel> tag
		eventWriter.add(eventFactory.createEndElement("", "", "channel"));
		eventWriter.add(end); // Add a line wrap
		// Create and write end </rss> tag
		eventWriter.add(eventFactory.createEndElement("", "", "rss"));
		eventWriter.add(end); // Add a line wrap

		// Create and write end </xml> tag
		eventWriter.add(eventFactory.createEndDocument());

		// Close the writer
		eventWriter.close();
	}

	/**
	 * Create a completed tag
	 * 
	 * @param eventWriter
	 * @param name
	 *            Tag name
	 * @param value
	 *            Tag value
	 * @throws XMLStreamException
	 */
	private void createNode(XMLEventWriter eventWriter, String name,
			String value) throws XMLStreamException {
		// Create a EventFactory
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		// Create a line wrap character
		XMLEvent end = eventFactory.createCharacters("\n");
		// Create a tab character
		XMLEvent tab = eventFactory.createCharacters("\t");
		// Create and write start tag
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab); // Add a tab
		eventWriter.add(sElement);
		// Create and write content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		// Create end tag
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end); // Add a line wrap
	}

	/**
	 * Main method for test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Create the rss feed
		String copyright = "Copyright hold by Lars Vogel";
		String title = "Eclipse and Java Information";
		String description = "Eclipse and Java Information";
		String language = "en";
		String link = "http://www.vogella.de";
		Calendar cal = new GregorianCalendar();
		Date creationDate = cal.getTime();
		SimpleDateFormat date_format = new SimpleDateFormat(
				"EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
		String pubdate = date_format.format(creationDate);
		Feed rssFeeder = new Feed(title, link, description, language,
				copyright, pubdate);

		// Now add one example entry
		FeedMessage feed = new FeedMessage();
		feed.setTitle("RSSFeed");
		feed.setDescription("This is a description");
		feed.setAuthor("nonsense@somewhere.de (Lars Vogel)");
		feed.setGuid("http://www.vogella.de/articles/RSSFeed/article.html");
		feed.setLink("http://www.vogella.de/articles/RSSFeed/article.html");
		rssFeeder.getMessages().add(feed);

		// Now write the file
		RSSFeedWriter writer = new RSSFeedWriter(rssFeeder,
				"e:/tech_blog_rss.xml");
		try {
			writer.write();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
