package org.example.usecase.ledger.query.getcontent;

import org.example.entity.model.accountingrecord.AccountingRecord;
import org.example.entity.model.ledger.Ledger;
import org.example.model.DomainEventBus;
import org.example.usecase.accountingrecord.AccountingRecordRepository;
import org.example.usecase.ledger.LedgerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetLedgerContentUseCase {

    private LedgerRepository ledgerRepository;

    private AccountingRecordRepository accountingRecordRepository;
    private DomainEventBus domainEventBus;

    public GetLedgerContentUseCase(LedgerRepository ledgerRepository, AccountingRecordRepository accountingRecordRepository, DomainEventBus domainEventBus) {
        this.ledgerRepository = ledgerRepository;
        this.accountingRecordRepository = accountingRecordRepository;
        this.domainEventBus = domainEventBus;
    }

    public void execute(GetLedgerContentInput input, GetLedgerContentOutput output) {
        Optional<Ledger> ledger = ledgerRepository.findById(input.getLedgerId());

        if (ledger.isPresent()) {
            List<AccountingRecord> accountingRecords = new ArrayList<>();

            for (String accountingRecordId: ledger.get().getAccountingRecordIds()) {
                Optional<AccountingRecord> accountingRecord = accountingRecordRepository.findById(accountingRecordId);

                if(accountingRecord.isPresent()) {
                    accountingRecords.add(accountingRecord.get());
                } else {
                    throw new RuntimeException("AccountingRecord not found, accountingRecord id = " + accountingRecordId);
                }
            }

            Ledger selectedLedger = ledger.get();
            selectedLedger.clearDomainEvents();

            output.setLedgerId(selectedLedger.getId());
            output.setLedgerName(selectedLedger.getName());
            output.setAccountingRecords(accountingRecords);
            output.setCommittedAccountingRecord(selectedLedger.getCommittedAccountingRecords());
        } else {
            throw new RuntimeException("ledger not found, ledger id = " + input.getLedgerId());
        }
    }
}
