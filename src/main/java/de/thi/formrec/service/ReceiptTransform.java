package de.thi.formrec.service;

import de.benevolo.entities.finance.FinancialTransaction;
import de.thi.formrec.model.Receipt;

import javax.enterprise.context.ApplicationScoped;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReceiptTransform {

    public de.benevolo.entities.finance.Receipt transform(Receipt receiptOld){

        de.benevolo.entities.finance.Receipt receiptNew = new de.benevolo.entities.finance.Receipt();

        receiptNew.setFinancialTransactions(
                receiptOld.getItems().stream()
                        .map(item -> {
                            FinancialTransaction transaction = new FinancialTransaction();

                            transaction.setAmount(item.getTotalPrice().getNumberStripped());
                            transaction.setTransactionName(item.getDescription());

                            return transaction;
                        })
                        .collect(Collectors.toSet())
        );

        return receiptNew;
    }
}
