package de.thi.formrec.model;

import de.benevolo.entities.finance.FinancialTransaction;

import java.math.BigDecimal;

public class TransactionReceiptDTO {
    // This class is intended not to be saved in the database
    // -> only for transfer
    private FinancialTransaction financialTransaction;

    //quantity
    private BigDecimal amount;

    //Description
    private String transactionName;

    //region Constructors
    public TransactionReceiptDTO(FinancialTransaction financialTransaction, BigDecimal amount, String transactionName) {
        this.financialTransaction = financialTransaction;
        this.amount = amount;
        this.transactionName = transactionName;
    }

    public TransactionReceiptDTO() {

    }
    //endregion

    //region Getter and Setter

    public FinancialTransaction getFinancialTransaction() {
        return financialTransaction;
    }

    public void setFinancialTransaction(FinancialTransaction financialTransaction) {
        this.financialTransaction = financialTransaction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    //endregion
}
