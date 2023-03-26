package org.example.entity.model.accountingrecord;

import org.example.entity.model.accountingrecord.event.AccountingRecordCreated;
import org.example.entity.model.accountingrecord.event.AccountingRecordDeleted;
import org.example.model.AggregateRoot;
import org.example.model.ValueObject;

import java.util.Date;

public class AccountingRecord extends AggregateRoot<String> {
    private String ledgerId;
    private String name;
    private String type;
    private String date;
    private int amount;

    public AccountingRecord(String id, String ledgerId, String name, String type, String date, int amount) {
        super(id);
        this.ledgerId = ledgerId;
        this.name = name;
        this.type = type;
        this.date = date;
        this.amount = amount;

        addDomainEvent(new AccountingRecordCreated(new Date(), ledgerId, id));
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

    public void delete() {
        addDomainEvent((new AccountingRecordDeleted(
                new Date(),
                this.ledgerId,
                getId()
        )));
    }
}
