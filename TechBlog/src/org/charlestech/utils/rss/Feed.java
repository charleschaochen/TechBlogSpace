package org.charlestech.utils.rss;

import java.util.ArrayList;
import java.util.List;

/**
 * Feed Object
 *
 * @author http://www.javaarch.net/jiagoushi/479.htm
 */
public class Feed {

    final String title; // Feed title
    final String link; // Feed link
    final String description; // Feed description
    final String language; // Feed language
    final String copyright; // Feed copy right
    // Feed items
    final List<FeedMessage> entries = new ArrayList<FeedMessage>();

    public Feed(String title, String link, String description, String language,
                String copyright) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
    }

    public List<FeedMessage> getMessages() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getCopyright() {
        return copyright;
    }

    @Override
    public String toString() {
        return "Feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", title=" + title + "]";
    }

}
