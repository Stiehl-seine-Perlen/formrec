package de.thi.formrec.service;

import de.benevolo.entities.finance.FinancialTransaction;
import de.thi.formrec.model.Receipt;
import org.javamoney.moneta.Money;
import javax.enterprise.context.ApplicationScoped;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReceiptTransform {

    public Set<FinancialTransaction> transform(Receipt receiptOld) {
        return receiptOld.getItems().stream()
                .map(item -> {
                    FinancialTransaction transaction = new FinancialTransaction();

                    Money totalPrice = item.getTotalPrice();
                    transaction.setAmount((totalPrice != null) ? totalPrice.getNumberStripped() : null);

                    String description = item.getDescription();
                    transaction.setTransactionName((description != null) ? description : null);

                    Date date = Date.valueOf(receiptOld.getDate());
                    transaction.setDateCreated((date != null) ? date : null);
                    return transaction;
                }).collect(Collectors.toSet());
    }
}
