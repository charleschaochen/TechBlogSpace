package org.charlestech.utils.rss;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.charlestech.beans.blog.ArticleBean;
import org.charlestech.po.Article;
import org.charlestech.utils.IdEncrypter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BuildArticleRss {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext.xml", "daoContext.xml"});
        ArticleBean ab = context.getBean("articleBean", ArticleBean.class);
        try {
            List<Article> articles = ab.getPublishedArticles(1, 10);

            String copyright = "Copyright by Charles Chen";
            String title = "Charles Tech Blog - Articles";
            String description = "Articles RSS from Charles Tech Blog";
            String language = "cn";
            String link = "http://www.charlestech.org";
            Calendar cal = new GregorianCalendar();
            Date creationDate = cal.getTime();
            SimpleDateFormat date_format = new SimpleDateFormat(
                    "EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
            String pubdate = date_format.format(creationDate);
            Feed rssFeeder = new Feed(title, link, description, language,
                    copyright);

            // Now add one example entry
            for (Article article : articles) {
                FeedMessage feed = new FeedMessage();
                feed.setTitle(article.getArticleTitle());
                feed.setDescription(ab
                        .summary(article.getArticleContent(), 200));
                feed.setAuthor(article.getAuthor().getName());
                feed
                        .setGuid("http://www.charlestech.org/view_article.shtml?uid="
                                + IdEncrypter.encrypt(article.getArticleId()));
                feed
                        .setLink("http://www.charlestech.org/view_article.shtml?uid="
                                + IdEncrypter.encrypt(article.getArticleId()));
                feed.setPubDate(article.getPostTime().substring(0, 19));
                rssFeeder.getMessages().add(feed);
            }

            // Now write the file
            RSSFeedWriter writer = new RSSFeedWriter(rssFeeder,
                    "D:\\Development\\TechBlogSpace\\TechBlog\\WebRoot\\tech_blog_rss.xml");
            writer.write();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
