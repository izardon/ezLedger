package org.example.entity.model.ledger;

import org.example.model.ValueObject;

public class CommittedAccountingRecord extends ValueObject {
    private String ledgerId;
    private String accountingRecordId;

    public CommittedAccountingRecord(String ledgerId, String accountingRecordId) {
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
