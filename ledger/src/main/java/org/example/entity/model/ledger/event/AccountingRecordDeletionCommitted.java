package org.example.entity.model.ledger.event;

import org.example.model.DomainEvent;

import java.util.Date;

public class AccountingRecordDeletionCommitted extends DomainEvent {

    public AccountingRecordDeletionCommitted(Date date) {
        super(date);
    }
}
