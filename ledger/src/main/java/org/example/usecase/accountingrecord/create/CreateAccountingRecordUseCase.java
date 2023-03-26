package org.example.usecase.accountingrecord.create;

import org.example.entity.model.accountingrecord.AccountingRecord;
import org.example.model.DomainEventBus;
import org.example.usecase.accountingrecord.AccountingRecordRepository;
import org.example.usecase.ledger.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class CreateAccountingRecordUseCase {
    private AccountingRecordRepository accountingRecordRepository;
    private DomainEventBus domainEventBus;

    @Autowired
    public CreateAccountingRecordUseCase(AccountingRecordRepository accountingRecordRepository, DomainEventBus domainEventBus) {
        this.accountingRecordRepository = accountingRecordRepository;
        this.domainEventBus = domainEventBus;
    }

    public void execute(CreateAccountingRecordInput input, CreateAccountingRecordOutput output) {
        String accountingRecordId = UUID.randomUUID().toString();
        AccountingRecord accountingRecord = new AccountingRecord(accountingRecordId,
                input.getLedgerId(),
                input.getName(),
                input.getType(),
                input.getDate(),
                input.getAmount());

        accountingRecordRepository.save(accountingRecord);
        domainEventBus.postAll(accountingRecord);

        output.setAccountingRecordId(accountingRecord.getId());
    }
}
