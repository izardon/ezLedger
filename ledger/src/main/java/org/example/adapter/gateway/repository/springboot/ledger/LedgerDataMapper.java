package org.example.adapter.gateway.repository.springboot.ledger;

import org.example.entity.model.ledger.CommittedAccountingRecord;
import org.example.entity.model.ledger.Ledger;
import org.example.entity.model.accountingrecord.AccountingRecord;

import java.util.ArrayList;
import java.util.List;

public class LedgerDataMapper {
    public static LedgerData domainToData(Ledger ledger) {
        List<CommittedAccountingRecord> committedAccountingRecords = ledger.getCommittedAccountingRecords();
        return new LedgerData(ledger.getId(), ledger.getName(), CommittedAccountingRecordDataMapper.domainToData(committedAccountingRecords));
    }

    public static Ledger dataToDomain(LedgerData ledgerData) {
        Ledger ledger = new Ledger(ledgerData.getLedgerId(), ledgerData.getLedgerName());
        ledger.setCommittedAccountingRecords(new ArrayList<>(CommittedAccountingRecordDataMapper.dataToDomain(ledgerData.getCommittedAccountingRecords())));
        return ledger;
    }
}
