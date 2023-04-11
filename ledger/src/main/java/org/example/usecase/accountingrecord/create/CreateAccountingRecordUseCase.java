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
        //產生會計記錄ID
        String accountingRecordId = UUID.randomUUID().toString();

        //建立會計記錄
        AccountingRecord accountingRecord = new AccountingRecord(accountingRecordId,
                input.getLedgerId(),
                input.getName(),
                input.getType(),
                input.getDate(),
                input.getAmount());

        //儲存
        accountingRecordRepository.save(accountingRecord);
        //發布事件
        domainEventBus.postAll(accountingRecord);

        //設定輸出
        output.setName(accountingRecord.getName());
        output.setDate(accountingRecord.getDate());
        output.setType(accountingRecord.getType());
        output.setAmount(accountingRecord.getAmount());
        output.setAccountingRecordId(accountingRecord.getId());
    }
}
