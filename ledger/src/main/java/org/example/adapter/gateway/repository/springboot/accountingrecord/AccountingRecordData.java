package org.example.adapter.gateway.repository.springboot.accountingrecord;

import javax.persistence.*;

@Entity
@Table(name="accounting_record")
public class AccountingRecordData {
    @Id
    @Column(name="accounting_record_id")
    private String accountingRecordId;

    @Column(name="ledger_id", nullable = false)
    private String ledgerId;
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private String date;

    @Column(name = "amount")
    private int amount;

    public AccountingRecordData() {

    }

    public AccountingRecordData(String accountingRecordId, String ledgerId, String name, String type, String date, int amount) {
        this.accountingRecordId = accountingRecordId;
        this.ledgerId = ledgerId;
        this.name = name;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public String getAccountingRecordId() {
        return accountingRecordId;
    }

    public String getLedgerId() {
        return ledgerId;
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
