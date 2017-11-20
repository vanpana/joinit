package com.cyberschnitzel.joinit.Domain.REST;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Path("/post")
public class TestPOST {
    @POST
    @Path("/test")
    @Consumes()
    public Response consumeJSON(String str) {
        String output = str;
        return Response.status(200).entity(output).build();
    }

}
