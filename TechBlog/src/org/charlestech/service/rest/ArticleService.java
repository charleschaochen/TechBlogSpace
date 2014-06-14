package org.charlestech.service.rest;

import net.sf.json.JSONArray;
import org.charlestech.dao.ArticleDao;
import org.charlestech.po.Article;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/*
 * @Description Articles Web Service
 * @author Charles Chen
 * @date 14-3-3
 * @version 1.0
 */
@Component
@Path("/articles")
public class ArticleService {
    private ArticleDao ad;

    public void setAd(ArticleDao ad) {
        this.ad = ad;
    }

    @GET
    @Path("/get_articles")
    @Produces("application/json")
    public Response getArticles(@QueryParam("callback") String callback) throws UnsupportedEncodingException {
        List<Article> articles = ad.findAllPublished();
        String jsonData = JSONArray.fromObject(articles).toString();
        if (callback != null)
            jsonData = callback + "(" + jsonData + ") ";
        Response response = Response.status(200).entity(jsonData).header("Content-Type", "application/json; charset=utf-8").build();
        return response;
    }
}
