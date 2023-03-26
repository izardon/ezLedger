package org.example.entity.model.ledger.event;

import org.example.model.DomainEvent;

import java.util.Date;

public class AccountingRecordCreationCommitted extends DomainEvent {

    public AccountingRecordCreationCommitted(Date date) {
        super(date);
    }
}
