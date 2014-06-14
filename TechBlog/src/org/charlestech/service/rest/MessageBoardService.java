package org.charlestech.service.rest;

import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverSpi;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import org.charlestech.po.wechat.VideoMessage;
import org.charlestech.utils.DateUtils;
import org.charlestech.utils.StreamUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * @Description Message board service
 * @author Charles Chen
 * @date 14-3-23
 * @version 1.0
 */
@Component
@Path("/message_board")
public class MessageBoardService {
    private String messageFilePath;

    /**
     * Get messages
     *
     * @return
     */
    @GET
    @Path("/get_messages")
    @Produces("application/json")
    public String getMessages() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(getMessageFilePath()), "UTF-8"));
            List<VisitorMessage> messages = new ArrayList<VisitorMessage>();
            String line;
            while ((line = br.readLine()) != null) {
                String time = line.split(" ")[0] + " " + line.split(" ")[1];
                String content = line.split(" ")[2];
                messages.add(new VisitorMessage(time, content));
            }
            String resp_content = JSONArray.fromObject(messages).toString();
            return "{\"retcode\": \"-1\", \"mess\": " + resp_content + "}";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "{\"retcode\": \"-1\", \"mess\": \"Post message exception\"}";
    }

    @POST
    @Path("/add_message/{content}")
    @Produces("application/json")
    public String addMessage(@PathParam("content") String content) {
        BufferedWriter bw = null;
        try {
            File file = new File(getMessageFilePath());
            if (!file.exists()) file.createNewFile();
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));
            String time = DateUtils.now_yyyy_MM_dd_HH_mm_ss();
            bw.write(time + " " + content);
            bw.newLine();
            return "{\"retcode\": \"0\", \"mess\": \"Post message successfully\"}";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "{\"retcode\": \"-1\", \"mess\": \"Post message exception\"}";
    }

    public String getMessageFilePath() {
        return ServletActionContext.getServletContext().getRealPath(messageFilePath);
    }

    public void setMessageFilePath(String messageFilePath) {
        this.messageFilePath = messageFilePath;
    }
}