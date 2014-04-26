package org.charlestech.actions.apps;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.charlestech.beans.apps.compress.YUICompressor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * @Description Javascript and CSS file compress action
 * @author Charles Chen
 * @date 14-4-21
 * @version 1.0
 */
public class CompressAction {
    private String source;
    private String type;
    private YUICompressor compressor;

    public String compress() throws IOException {
        ActionContext context = ActionContext.getContext();
        HttpServletResponse response = (HttpServletResponse) context
                .get(ServletActionContext.HTTP_RESPONSE);
        ServletOutputStream out = response.getOutputStream();
        String compressed = compressor.compress(source, type);
        if (compressed == null) {
            out.print("{\"retcode\": \"-1\", \"mess\": \"Compress error. Please ensure your file type is correct, or try again\", \"result\": \"\"}");
            return null;
        }
        compressed = compressed.replaceAll("\"", "%22");    // Escape the "
        out.print("{\"retcode\": \"0\", \"mess\": \"Compress Error\", \"result\": \"" + compressed + "\"}");
        return null;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setCompressor(YUICompressor compressor) {
        this.compressor = compressor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
