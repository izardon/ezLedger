package org.example.usecase.accountingrecord.edit;

import org.example.model.DomainEventBus;
import org.example.usecase.accountingrecord.AccountingRecordRepository;

public class EditAccountingRecordUseCase {
    private AccountingRecordRepository accountingRecordRepository;
    private DomainEventBus domainEventBus;

    public EditAccountingRecordUseCase(AccountingRecordRepository accountingRecordRepository, DomainEventBus domainEventBus) {
        this.accountingRecordRepository = accountingRecordRepository;
        this.domainEventBus = domainEventBus;
    }

    public void execute(EditAccountingRecordInput input, EditAccountingRecordOutput output) {
        accountingRecordRepository.findById(input.getAccountingRecordId())
                .ifPresentOrElse(accountingRecord -> {
                    accountingRecord.clearDomainEvents();
                    accountingRecord.edit(input.getName(), input.getType(), input.getDate(), input.getAmount());
                    accountingRecordRepository.save(accountingRecord);
                    domainEventBus.postAll(accountingRecord);
                    output.setAccountingRecordId(accountingRecord.getId());
                }, () -> {
                    throw new RuntimeException("accountingRecord not found, accountingRecord id = " + input.getAccountingRecordId());
                });
    }

}
