package org.example.adapter.gateway.repository.springboot.ledger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="ledger")
public class LedgerData {
    @Id
    @Column(name="ledger_id")
    private String ledgerId;
    @Column(name="ledger_name")
    private String ledgerName;

    @OneToMany(mappedBy = "ledger", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<CommittedAccountingRecordData> committedAccountingRecords;

    public LedgerData() {
    }

    public LedgerData(String ledgerId) {
        this.ledgerId = ledgerId;
    }

    public LedgerData(String ledgerId, String ledgerName, List<CommittedAccountingRecordData> committedAccountingRecords) {
        this.ledgerId = ledgerId;
        this.ledgerName = ledgerName;
        this.committedAccountingRecords = new HashSet<>();
        this.committedAccountingRecords.addAll(committedAccountingRecords);
    }

    public String getLedgerId() {
        return ledgerId;
    }

    public String getLedgerName() {
        return ledgerName;
    }
    public Set<CommittedAccountingRecordData> getCommittedAccountingRecords() {
        return committedAccountingRecords;
    }
}
