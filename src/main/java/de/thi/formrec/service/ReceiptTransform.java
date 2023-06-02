package de.thi.formrec.service;

import de.benevolo.entities.finance.FinancialTransaction;
import de.thi.formrec.model.Receipt;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReceiptTransform {

    public Set<FinancialTransaction> transform(Receipt receiptOld) {
        return receiptOld.getItems().stream()
                .map(item -> {
                    FinancialTransaction transaction = new FinancialTransaction();
                    transaction.setAmount(item.getTotalPrice().getNumberStripped());
                    transaction.setTransactionName(item.getDescription());
                    return transaction;
                }).collect(Collectors.toSet());
    }
}
