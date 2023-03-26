package org.example.adapter.gateway.repository.springboot.ledger;

import javax.persistence.*;

@Entity
public class CommittedAccountingRecordData {

    @EmbeddedId
    CommittedAccountingRecordDataKey id;

    @ManyToOne
    @MapsId("ledgerId")
    @JoinColumn(name = "ledger_id")
    private LedgerData ledger;

    public CommittedAccountingRecordData() {

    }

    public CommittedAccountingRecordData(String ledgerId, String accountingRecordId) {
        this.ledger = new LedgerData(ledgerId);
        this.id = new CommittedAccountingRecordDataKey(ledgerId, accountingRecordId);
    }

    public CommittedAccountingRecordDataKey getId() {
        return id;
    }

}
