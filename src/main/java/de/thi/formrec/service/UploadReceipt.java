package de.thi.formrec.service;

import de.benevolo.entities.finance.Receipt;
import de.thi.formrec.FormRecognizer;

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
        //TODO Service to transform Markus Reicept to Matze Receipt
        //return formRecognizer.recognize(url);
        return  null;
    }
}
