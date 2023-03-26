package org.example.usecase.accountingrecord;

public class AccountingRecordDto {
    private String id;
    private String name;
    private String type;
    private String date;
    private int amount;

    public AccountingRecordDto(String id, String name, String type, String date, int amount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }
}
