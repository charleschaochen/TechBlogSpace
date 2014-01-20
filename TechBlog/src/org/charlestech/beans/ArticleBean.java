package org.charlestech.beans;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.charlestech.dao.AdminArticleReplyDao;
import org.charlestech.dao.ArticleDao;
import org.charlestech.dao.ArticleReplyDao;
import org.charlestech.dao.ArticleTagDao;
import org.charlestech.po.AdminArticleReply;
import org.charlestech.po.Article;
import org.charlestech.po.ArticleReply;
import org.charlestech.po.ArticleTag;
import org.charlestech.utils.DateUtils;
import org.charlestech.utils.IdEncrypter;
import org.charlestech.utils.rss.Feed;
import org.charlestech.utils.rss.FeedMessage;
import org.charlestech.utils.rss.RSSFeedWriter;

/**
 * Article Bean
 * 
 * @TODO Provides effective interfaces of article for actions
 * @author Charles Chen
 * 
 */
public class ArticleBean {
	private ArticleDao ad;
	private ArticleTagDao atd;
	private ArticleReplyDao ard;
	private AdminArticleReplyDao aard;

	public void setAd(ArticleDao ad) {
		this.ad = ad;
	}

	public void setArd(ArticleReplyDao ard) {
		this.ard = ard;
	}

	public void setAard(AdminArticleReplyDao aard) {
		this.aard = aard;
	}

	public void setAtd(ArticleTagDao atd) {
		this.atd = atd;
	}

	/**
	 * Get all published articles by page
	 * 
	 * @param pageNo
	 * @param pagesize
	 * @return
	 */
	public List<Article> getPublishedArticles(int pageNo, int pagesize) {
		int state = 1, first = (pageNo - 1) * pagesize;
		return ad.findByPage(state, first, pagesize);
	}

	/**
	 * Get all published articles count
	 * 
	 * @return
	 */
	public int getPublishedCount() {
		return ad.findAllPublished().size();
	}

	/**
	 * Get tags list by article
	 * 
	 * @param articleId
	 * @return
	 */
	public List<ArticleTag> getTags(Article article) {
		return atd.findByArticleId(article.getArticleId());
	}

	/**
	 * Delete tags of article
	 * 
	 * @param article
	 */
	public void deleteTags(Article article) {
		List<ArticleTag> tags = atd.findByArticleId(article.getArticleId());
		atd.delete(tags);
	}

	/**
	 * Get all published articles by category and tag name
	 * 
	 * @param categoryId
	 * @param tagName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public List<Article> getPublishedArticles(String categoryId, String tagName)
			throws UnsupportedEncodingException {
		List<Article> articles = new ArrayList<Article>();
		// Filter by category
		if (categoryId == null || "".equals(categoryId)) {
			articles = ad.findAllPublished();
		} else {
			articles = ad.findByCategory(1, Integer.parseInt(categoryId));
		}
		// Filter by tag
		if (tagName != null && !"".equals(tagName)) {
			return filterByTagName(articles, URLDecoder
					.decode(tagName, "utf-8"));
		}
		return articles;
	}

	/**
	 * Get all available(not deleted) articles by category and tag name
	 * 
	 * @param categoryId
	 * @param tagName
	 * @return
	 */
	public List<Article> getAllAvailableArticles(String categoryId,
			String tagName) {
		List<Article> articles = new ArrayList<Article>();
		// Filter by category
		if (categoryId == null || "".equals(categoryId)) {
			articles = ad.findAll();
		} else {
			articles = ad.findByCategory(0, Integer.parseInt(categoryId));
		}
		// Filter by tag
		if (tagName != null && !"".equals(tagName)) {
			return filterByTagName(articles, tagName);
		}
		return articles;
	}

	/**
	 * Filter articles by tag name
	 * 
	 * @param articles
	 * @param tagName
	 * @return
	 */
	private List<Article> filterByTagName(List<Article> articles, String tagName) {
		List<Article> filtered_articles = new ArrayList<Article>();
		List<ArticleTag> tags = atd.findByTagName(tagName);
		for (Article article : articles) {
			for (ArticleTag tag : tags) {
				if (article.getArticleId() == tag.getArticle().getArticleId()) {
					filtered_articles.add(article);
				}
			}
		}
		return filtered_articles;
	}

	/**
	 * Paginate articles list
	 * 
	 * @param articles
	 * @param pageNo
	 * @param pagesize
	 * @return
	 */
	public List<Article> pagination(List<Article> articles, int pageNo,
			int pagesize) {
		List<Article> paged_articles = new ArrayList<Article>();
		int first = (pageNo - 1) * pagesize;
		int total = articles.size();
		for (int i = first; i < total && i < first + pagesize; i++) {
			paged_articles.add(articles.get(i));
		}
		return paged_articles;
	}

	/**
	 * Get summary text of the content text
	 * 
	 * @param content
	 * @param length
	 * @return
	 */
	public static String summary(String content, int length) {
		String plainText = content.replaceAll("<[^>]*>", "");
		int len = plainText.length();
		return plainText.substring(0, length < len ? length : len) + "......";
	}

	/**
	 * Get article object by UID
	 * 
	 * @param uid
	 * @return
	 */
	public Article getArticleByUid(String uid) {
		String articleIdStr = IdEncrypter.decrypt(uid);// Descrypt the uid
		if (articleIdStr == null)
			return null;
		int articleId = Integer.parseInt(articleIdStr);
		return ad.findById(articleId);
	}

	/**
	 * Get article replies by article ID
	 * 
	 * @param articleId
	 * @return
	 */
	public List<ArticleReply> getArticleReplies(int articleId) {
		List<ArticleReply> replies = ard.findByArticle(articleId);
		// Get administrators' replies of each article reply
		for (ArticleReply reply : replies) {
			reply.setReplies(aard.findByArticleReply(reply.getReplyId()));
		}
		return replies;
	}

	/**
	 * Add new article reply
	 * 
	 * @param reply
	 * @return
	 */
	public int addReply(ArticleReply reply) {
		return ard.save(reply);
	}

	/**
	 * Get partial article replies
	 * 
	 * @param count
	 * @return
	 */
	public List<ArticleReply> getArticleRepliesByCount(int count) {
		return ard.findPartial(count);
	}

	/**
	 * Add article viewed times
	 * 
	 * @param article
	 */
	public void increViewTimes(Article article) {
		article.setViewTimes(article.getViewTimes() + 1);
		ad.update(article);
	}

	/**
	 * Add or update article
	 * 
	 * @param article
	 * @param flag
	 *            0:add,1:update
	 * @return >0:success,<=0:failed
	 */
	public int addOrUpdateArticle(Article article, int flag) {
		// Add article
		if (flag == 0) {
			return ad.save(article);
		}
		// Update article
		if (flag == 1) {
			ad.update(article);
			return 1;
		}
		return 0;
	}

	/**
	 * Delete article logically
	 * 
	 * @param articleId
	 */
	public void deleteArticle(Article article) {
		ad.unavailable(article);
	}

	/**
	 * Delete article reply logically
	 * 
	 * @param reply
	 * @return -2:Reply id is invalid;-1:Reply does not exist;0:success;
	 */
	public int deleteArticleReply(String replyId) {
		int id;
		try {
			id = Integer.parseInt(replyId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -2;
		}
		ArticleReply reply = ard.findById(id);
		if (reply == null) {
			return -1;
		}
		ard.unavailable(reply);
		return 0;
	}

	/**
	 * Delete administrator article reply logically
	 * 
	 * @param reply
	 * @return -2:Reply id is invalid;-1:Reply does not exist;0:success;
	 */
	public int deleteAdminArticleReply(String replyId) {
		int id;
		try {
			id = Integer.parseInt(replyId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -2;
		}
		AdminArticleReply reply = aard.findById(id);
		if (reply == null) {
			return -1;
		}
		aard.unavailable(reply);
		return 0;
	}

	/**
	 * Get administrator replies of a specific article reply
	 * 
	 * @param replyId
	 * @return
	 */
	public List<AdminArticleReply> getAdminReplies(int replyId) {
		return aard.findByArticleReply(replyId);
	}

	private String copyright;
	private String rssTitle;
	private String rssDesc;
	private String rssLang;
	private String rssLink;
	private String rssPath;

	/**
	 * Update the rss xml file
	 * 
	 * @throws Exception
	 */
	public void updateRss() throws Exception {
		List<Article> articles = getPublishedArticles(1, 10);
		String pubDate = DateUtils.now_yyyy_MM_dd_HH_mm_ss();
		// Create feed
		Feed rssFeeder = new Feed(rssTitle, rssLink, rssDesc, rssLang,
				copyright, pubDate);
		for (Article article : articles) {
			// Add feed message for article
			FeedMessage feed = new FeedMessage();
			feed.setTitle(article.getArticleTitle());
			feed.setDescription(summary(article.getArticleContent(), 200));
			feed.setAuthor(article.getAuthor().getName());
			feed.setGuid("http://www.charlestech.org/view_article.shtml?uid="
					+ IdEncrypter.encrypt(article.getArticleId()));
			feed.setLink("http://www.charlestech.org/view_article.shtml?uid="
					+ IdEncrypter.encrypt(article.getArticleId()));
			rssFeeder.getMessages().add(feed);
		}
		// Write feed file
		RSSFeedWriter writer = new RSSFeedWriter(rssFeeder,
				ServletActionContext.getServletContext().getRealPath(rssPath));
		writer.write();
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public void setRssTitle(String rssTitle) {
		this.rssTitle = rssTitle;
	}

	public void setRssDesc(String rssDesc) {
		this.rssDesc = rssDesc;
	}

	public void setRssLang(String rssLang) {
		this.rssLang = rssLang;
	}

	public void setRssLink(String rssLink) {
		this.rssLink = rssLink;
	}

	public void setRssPath(String rssPath) {
		this.rssPath = rssPath;
	}

}
