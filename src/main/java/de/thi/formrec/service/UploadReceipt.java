package de.thi.formrec.service;

import de.benevolo.entities.finance.FinancialTransaction;
import de.thi.formrec.FormRecognizer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Set;


@ApplicationScoped
public class UploadReceipt {

    @Inject
    FormRecognizer formRecognizer;

    @Inject
    ReceiptTransform receiptTransform;


    public Set<FinancialTransaction> uploadReceipt(String url) throws IOException {
        return receiptTransform.transform(formRecognizer.recognize(url));
    }
}
