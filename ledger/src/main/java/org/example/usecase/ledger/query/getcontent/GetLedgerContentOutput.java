package org.example.usecase.ledger.query.getcontent;

import org.example.entity.model.accountingrecord.AccountingRecord;
import org.example.entity.model.ledger.CommittedAccountingRecord;

import java.util.ArrayList;
import java.util.List;

public class GetLedgerContentOutput {

    private String ledgerId;
    private String ledgerName;

    private List<AccountingRecord> accountingRecords;
    private List<CommittedAccountingRecord> committedAccountingRecords = new ArrayList<>();

    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId;
    }

    public String getLedgerId() {
        return ledgerId;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public List<AccountingRecord> getAccountingRecords() {
        return accountingRecords;
    }

    public void setAccountingRecords(List<AccountingRecord> accountingRecords) {
        this.accountingRecords = accountingRecords;
    }
    public List<CommittedAccountingRecord> getCommittedAccountingRecords() {
        return committedAccountingRecords;
    }

    public void setCommittedAccountingRecord(List<CommittedAccountingRecord> committedAccountingRecords) {
        this.committedAccountingRecords = committedAccountingRecords;
    }
}
