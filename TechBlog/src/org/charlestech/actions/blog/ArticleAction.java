package org.charlestech.actions.blog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.charlestech.beans.blog.ArticleBean;
import org.charlestech.dao.AdminDao;
import org.charlestech.dao.ArticleCategoryDao;
import org.charlestech.dao.ArticleDao;
import org.charlestech.dao.ArticleTagDao;
import org.charlestech.po.Article;
import org.charlestech.po.ArticleReply;
import org.charlestech.po.ArticleTag;
import org.charlestech.utils.DateUtils;
import org.charlestech.utils.IdEncrypter;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Article Operation CGI
 *
 * @author Charles Chen
 */
public class ArticleAction extends ActionSupport {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -7125541942783151466L;
    // private static Logger logger = Logger
    // .getLogger(ArticleCategoryAction.class);
    /* DAO Instances */
    private ArticleDao articleDao;
    private ArticleTagDao atd;
    private ArticleCategoryDao acd;
    private AdminDao ad;
    private ArticleBean articleBean;

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void setArticleBean(ArticleBean articleBean) {
        this.articleBean = articleBean;
    }

    public void setAtd(ArticleTagDao atd) {
        this.atd = atd;
    }

    public void setAcd(ArticleCategoryDao acd) {
        this.acd = acd;
    }

    public void setAd(AdminDao ad) {
        this.ad = ad;
    }

	/* DAO Instances */

    private String title;
    private String content;
    private String category;
    private String tags;
    private String settop;
    private String state;

    /**
     * Create new article
     *
     * @return
     * @throws Exception
     */
    public String addArticle() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = out = response.getWriter();
        Article article = new Article();
        article.setArticleTitle(title);
        article.setArticleContent(content);
        article.setArticleState(Integer.parseInt(state));
        article.setAuthor(ad.findByName("charles"));
        article.setCategory(acd.findById(Integer.parseInt(category)));
        article.setPostTime(DateUtils.now_yyyy_MM_dd_HH_mm_ss());
        article.setUpdateTime(DateUtils.now_yyyy_MM_dd_HH_mm_ss());
        article.setSetTop(Integer.parseInt(settop));
        if (articleBean.addOrUpdateArticle(article, 0) > 0) {
            out.print("{retcode:0,mess:'New article created'}");
            // Save article tags
            String[] tags_arr = tags.split(";");
            ArticleTag articleTag;
            for (String tag : tags_arr) {
                articleTag = new ArticleTag();
                articleTag.setTagName(tag);
                articleTag.setArticle(article);
                atd.save(articleTag);
            }

            // Update rss file
            articleBean.updateRss();
            return null;
        }

        out.print("{retcode:-1,mess:'Failed to create'}");
        return null;
    }

    /**
     * Get all tags
     *
     * @return
     * @throws IOException
     */
    public String getAllTags() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = out = response.getWriter();
        // Get tags list
        List<ArticleTag> tags = atd.findAll();
        JSONArray tags_json = JSONArray.fromObject(tags);
        out.print(tags_json);
        return null;
    }

    private String articleId;
    private String visitor;
    private String email;
    private String website;
    private String replyContent;

    /**
     * Add a new article reply
     *
     * @return
     * @throws IOException
     */
    public String addReply() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = out = response.getWriter();

        ArticleReply reply = new ArticleReply();
        reply.setVisitorName("".equals(visitor) ? "匿名" : visitor);
        reply.setVisitorMail(email);
        reply.setVisitorWebsite(website);
        reply.setArticle(articleDao.findById(Integer.parseInt(articleId)));
        reply.setReplyContent(replyContent);
        if (articleBean.addReply(reply) > 0) {
            out.print("{retcode:0,mess:'Reply successfully'}");
            return null;
        }
        out.print("{retcode:-1,mess:'Reply failed'}");
        return null;
    }

    /**
     * Get most recent article replies
     *
     * @return
     * @throws IOException
     */
    public String getRecentArticleReplies() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = out = response.getWriter();
        // Get recent 5 article replies
        List<ArticleReply> replies = articleBean.getArticleRepliesByCount(5);
        // Set visitor default image
        String visitorImg = getVisitorImg();
        for (ArticleReply reply : replies) {
            reply.setVisitorImg(visitorImg);
            reply.getArticle().setArticleUid(
                    IdEncrypter.encrypt(reply.getArticle().getArticleId()));
        }
        JSONArray replies_json = JSONArray.fromObject(replies);
        out.print(replies_json.toString());
        return null;
    }

    /**
     * Get visitor default image
     *
     * @return
     */
    private String getVisitorImg() {
        return ServletActionContext.getServletContext().getInitParameter(
                "visitor_img");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSettop() {
        return settop;
    }

    public void setSettop(String settop) {
        this.settop = settop;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}
