package org.example.entity.model.accountingrecord.event;

import org.example.model.DomainEvent;

import java.util.Date;

public class AccountingRecordCreated extends DomainEvent {
    private String ledgerId;
    private String accountingRecordId;

    public AccountingRecordCreated(Date occurredOn, String ledgerId, String accountingRecordId) {
        super(occurredOn);
        this.ledgerId = ledgerId;
        this.accountingRecordId = accountingRecordId;
    }

    public String getLedgerId() {
        return ledgerId;
    }

    public String getAccountingRecordId() {
        return accountingRecordId;
    }
}
