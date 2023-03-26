package org.example.adapter.presenter.ledger.getaccountingrecord;

public class AccountingRecordViewModel {
    private String id;
    private String name;
    private String type;
    private String date;
    private int price;

    public AccountingRecordViewModel(String id, String name, String type, String date, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.date = date;
        this.price = price;
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

    public int getPrice() {
        return price;
    }
}
