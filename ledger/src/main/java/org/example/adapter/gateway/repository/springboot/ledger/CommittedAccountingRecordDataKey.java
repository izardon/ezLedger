package org.example.adapter.gateway.repository.springboot.ledger;

import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CommittedAccountingRecordDataKey implements Serializable {

    @Column(name = "ledger_id")
    String ledgerId;

    @Column(name = "accounting_record_id")
    String accountingRecordId;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CommittedAccountingRecordDataKey)) {
            return false;
        }
        CommittedAccountingRecordDataKey key = (CommittedAccountingRecordDataKey)obj;
        return ledgerId.equals(key.ledgerId) && ledgerId.equals(key.accountingRecordId);
    }

    @Override
    public int hashCode() {
        return new ImmutablePair<String, String>(ledgerId, accountingRecordId).hashCode();
    }

    public CommittedAccountingRecordDataKey() {

    }

    public CommittedAccountingRecordDataKey(String ledgerId, String accountingRecordId) {
        this.ledgerId = ledgerId;
        this.accountingRecordId = accountingRecordId;
    }

    public String getLedgerId() {
        return ledgerId;
    }

    public String getAccountingRecordId() {
        return accountingRecordId;
    }

    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId;
    }

    public void setAccountingRecordId(String accountingRecordId) {
        this.accountingRecordId = accountingRecordId;
    }
}
