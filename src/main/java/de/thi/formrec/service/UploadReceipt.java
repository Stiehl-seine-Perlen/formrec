package de.thi.formrec.service;

import de.benevolo.entities.finance.FinancialTransaction;
import de.thi.formrec.FormRecognizer;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Set;


@Path("/formrec")
public class UploadReceipt {

    @Inject
    FormRecognizer formRecognizer;

    @Inject
    ReceiptTransform receiptTransform;


    @Path("/upload")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Set<FinancialTransaction> uploadReceipt(String url) throws IOException {
        return receiptTransform.transform(formRecognizer.recognize(url));
    }
}
