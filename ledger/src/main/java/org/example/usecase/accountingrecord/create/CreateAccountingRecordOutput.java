package org.example.usecase.accountingrecord.create;

public class CreateAccountingRecordOutput {

    private String accountingRecordId;

    private String name;

    private String date;
    private String type;
    private int amount;


    public String getAccountingRecordId() {
        return accountingRecordId;
    }

    public void setAccountingRecordId(String accountingRecordId) {
        this.accountingRecordId = accountingRecordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
