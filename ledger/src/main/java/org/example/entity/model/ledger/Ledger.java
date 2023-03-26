package org.example.entity.model.ledger;

import org.example.entity.model.ledger.event.AccountingRecordCreationCommitted;
import org.example.entity.model.ledger.event.AccountingRecordDeletionCommitted;
import org.example.model.AggregateRoot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class Ledger extends AggregateRoot<String> {

    private String name;

    private List<CommittedAccountingRecord> committedAccountingRecords;

    public Ledger(String id, String name){
        super(id);
        this.name = name;
        this.committedAccountingRecords = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAccountingRecordIds() {
        return committedAccountingRecords.stream().map(CommittedAccountingRecord::getAccountingRecordId).collect(toList());
    }
    public List<CommittedAccountingRecord> getCommittedAccountingRecords() {
        return committedAccountingRecords;
    }

    public void setCommittedAccountingRecords(List<CommittedAccountingRecord> committedAccountingRecords) {
        this.committedAccountingRecords = committedAccountingRecords;
    }

    public void commitAccountingRecordCreation(String accountingRecordId) {
        committedAccountingRecords.add(new CommittedAccountingRecord(this.id, accountingRecordId));
        addDomainEvent(new AccountingRecordCreationCommitted(new Date()));
    }

    public Optional<CommittedAccountingRecord> getCommittedAccountingRecordBy(String accountingRecordId) {
        return committedAccountingRecords.stream().filter(e -> e.getAccountingRecordId().equals(accountingRecordId)).findFirst();
    }

    public void commitAccountingRecordDeletion(String accountingRecordId) {
        CommittedAccountingRecord removedCommittedAccountingRecord = getCommittedAccountingRecordBy(accountingRecordId).get();
        committedAccountingRecords.remove(removedCommittedAccountingRecord);
        List<CommittedAccountingRecord> clone = new ArrayList<>(committedAccountingRecords);
        committedAccountingRecords.clear();
        for(int i = 0; i < clone.size(); i++) {
            committedAccountingRecords.add(new CommittedAccountingRecord(getId(), clone.get(i).getAccountingRecordId()));
        }
        
        addDomainEvent(new AccountingRecordDeletionCommitted(new Date()));
    }
}
