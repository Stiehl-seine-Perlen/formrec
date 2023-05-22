package de.thi.formrec.service;

import de.thi.formrec.FormRecognizer;
import de.thi.formrec.model.Receipt;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/formrec")
public class UploadReceipt {

    @Inject
    FormRecognizer formRecognizer;

    @Path("/upload")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Receipt uploadReceipt(String url) throws IOException {
        return formRecognizer.recognize(url);
    }
}
