package org.charlestech.service.rest;


import org.charlestech.beans.apps.compress.YUICompressor;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/*
 * @Description Javascript and CSS file compressing service
 * @author Charles Chen
 * @date 14-4-22
 * @version 1.0
 */
@Component
@Path("/compressor")
public class CompressService {
    /**
     * Compress file according to the type
     *
     * @param source   Source code
     * @param type     File type
     * @param callback Coss-site callback
     * @return
     */
    @GET
    @Path("/compress")
    @Produces("application/json")
    public Response compress(@QueryParam("source") String source, @QueryParam("type") String type, @QueryParam("callback") String callback) {
        String response = null;
        String compressed = compressor.compress(source, type);  // Get compressed result
        if (compressed == null) {
            response = "{\"retcode\": \"-1\", \"mess\": \"Compress error. Please ensure your file type is correct, or try again\", \"result\": \"\"}";
        } else {
            compressed = compressed.replaceAll("\"", "%22");    // Escape the "
            response = "{\"retcode\": \"0\", \"mess\": \"Compress Successfully\", \"result\": \"" + compressed + "\"}";
        }
        if (callback != null) {
            // If the request is cross-site, add callback prefix
            response = callback + "(" + response + ")";
        }
        return Response.status(200).header("Content-Type", "application/json; charset=utf-8").entity(response).build();
    }

    private YUICompressor compressor;

    public void setCompressor(YUICompressor compressor) {
        this.compressor = compressor;
    }
}
