package org.example.application.springboot.web.config;

import org.example.model.DomainEventBus;
import org.example.usecase.accountingrecord.AccountingRecordRepository;
import org.example.usecase.accountingrecord.delete.DeleteAccountingRecordUseCase;
import org.example.usecase.accountingrecord.edit.EditAccountingRecordUseCase;
import org.example.usecase.eventHandler.NotifyLedger;
import org.example.usecase.ledger.LedgerRepository;
import org.example.usecase.accountingrecord.create.CreateAccountingRecordUseCase;
import org.example.usecase.ledger.query.getcontent.GetLedgerContentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("LedgerUseCaseInjection")
public class UseCaseInjection {
    private LedgerRepository ledgerRepository;

    private AccountingRecordRepository accountingRecordRepository;
    private DomainEventBus eventBus;

    @Bean(name="createNotifyLedger")
    public NotifyLedger createNotifyLedger() {
        return new NotifyLedger(ledgerRepository, eventBus);
    }

    @Bean(name="CreateAccountingRecordUseCase")
    public CreateAccountingRecordUseCase createAccountingRecordUseCase() {
        return new CreateAccountingRecordUseCase(accountingRecordRepository, eventBus);
    }

    @Bean(name="EditAccountingRecordUseCase")
    public EditAccountingRecordUseCase editAccountingRecordUseCase() {
        return new EditAccountingRecordUseCase(accountingRecordRepository, eventBus);
    }

    @Bean(name="DeleteAccountingRecordUseCase")
    public DeleteAccountingRecordUseCase deleteAccountingRecordUseCase() {
        return new DeleteAccountingRecordUseCase(accountingRecordRepository, eventBus);
    }

    @Bean(name="GetLedgerContentUseCase")
    public GetLedgerContentUseCase getLedgerContentUseCase() {
        return new GetLedgerContentUseCase(ledgerRepository, accountingRecordRepository, eventBus);
    }

    @Autowired
    public void setLedgerRepository(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    @Autowired
    public void setAccountingRecordRepository(AccountingRecordRepository accountingRecordRepository) {
        this.accountingRecordRepository = accountingRecordRepository;
    }
    @Autowired
    public void setEventBus(DomainEventBus eventBus) { this.eventBus = eventBus; }

}
