package org.example.application.springboot.web.config;

import org.example.adapter.accountingrecord.AccountingRecordRepositoryImpl;
import org.example.adapter.gateway.repository.springboot.accountingrecord.AccountingRecordRepositoryPeer;
import org.example.adapter.gateway.repository.springboot.ledger.LedgerRepositoryPeer;
import org.example.adapter.ledger.LedgerRepositoryImpl;
import org.example.model.DomainEventBus;
import org.example.usecase.accountingrecord.AccountingRecordRepository;
import org.example.usecase.ledger.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:/application.properties")
@Configuration("LedgerRepositoryInjection")
public class RepositoryInjection {

    private LedgerRepositoryPeer ledgerRepositoryPeer;
    private AccountingRecordRepositoryPeer accountingRecordRepositoryPeer;

    @Autowired
    public void setLedgerRepositoryPeer(LedgerRepositoryPeer ledgerRepositoryPeer) {
        this.ledgerRepositoryPeer = ledgerRepositoryPeer;
    }

    @Autowired
    public void setAccountingRecordRepositoryPeer(AccountingRecordRepositoryPeer accountingRecordRepositoryPeer) {
        this.accountingRecordRepositoryPeer = accountingRecordRepositoryPeer;
    }

    @Bean(name="ledgerRepository")
    public LedgerRepository boardRepository() {
        return new LedgerRepositoryImpl(ledgerRepositoryPeer);
    }

    @Bean(name="accountingRecordRepository")
    public AccountingRecordRepository accountingRecordRepository() {
        return new AccountingRecordRepositoryImpl(accountingRecordRepositoryPeer);
    }

    @Bean(name="ledgerEventBus")
    public DomainEventBus eventBus() {
        return new DomainEventBus();
    }
}
