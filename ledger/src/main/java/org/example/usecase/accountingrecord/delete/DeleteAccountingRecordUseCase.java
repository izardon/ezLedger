package org.example.usecase.accountingrecord.delete;

import org.example.entity.model.accountingrecord.AccountingRecord;
import org.example.model.DomainEventBus;
import org.example.usecase.accountingrecord.AccountingRecordRepository;

import java.util.Optional;
import java.util.UUID;

public class DeleteAccountingRecordUseCase {
    private AccountingRecordRepository accountingRecordRepository;
    private DomainEventBus domainEventBus;

    public DeleteAccountingRecordUseCase(AccountingRecordRepository accountingRecordRepository, DomainEventBus domainEventBus) {
        this.accountingRecordRepository = accountingRecordRepository;
        this.domainEventBus = domainEventBus;
    }

    public void execute(DeleteAccountingRecordInput input, DeleteAccountingRecordOutput output) {
        Optional<AccountingRecord> accountingRecord = accountingRecordRepository.findById(input.getAccountingRecordId());

        if(accountingRecord.isPresent()) {
            AccountingRecord selectedAccountingRecord = accountingRecord.get();
            selectedAccountingRecord.clearDomainEvents();
            selectedAccountingRecord.delete();
            accountingRecordRepository.delete(selectedAccountingRecord);
            domainEventBus.postAll(selectedAccountingRecord);
            output.setAccountingRecordId(selectedAccountingRecord.getId());
        } else {
            throw new RuntimeException("accountingRecord not found, accountingRecord id = " + input.getAccountingRecordId());
        }
    }
}
