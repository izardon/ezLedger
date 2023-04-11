package org.example.usecase.ledger.create;

import org.example.adapter.accountingrecord.AccountingRecordRepositoryImpl;
import org.example.adapter.gateway.repository.springboot.accountingrecord.AccountingRecordRepositoryPeer;
import org.example.adapter.gateway.repository.springboot.ledger.LedgerRepositoryPeer;
import org.example.adapter.ledger.LedgerRepositoryImpl;
import org.example.model.DomainEventBus;
import org.example.usecase.JpaApplicationTest;
import org.example.usecase.accountingrecord.AccountingRecordRepository;
import org.example.usecase.accountingrecord.create.CreateAccountingRecordInput;
import org.example.usecase.accountingrecord.create.CreateAccountingRecordOutput;
import org.example.usecase.accountingrecord.create.CreateAccountingRecordUseCase;
import org.example.usecase.ledger.LedgerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
@ContextConfiguration(classes = JpaApplicationTest.class)
@Rollback(false)
class CreateAccountingRecordUseCaseTest {
    @Autowired
    private AccountingRecordRepositoryPeer accountingRecordRepositoryPeer;

    @Test
    void create_accounting_record_should_succeed() {
        //Arrange
        DomainEventBus domainEventBus = new DomainEventBus();
        AccountingRecordRepository accountingRecordRepository = new AccountingRecordRepositoryImpl(accountingRecordRepositoryPeer);
        CreateAccountingRecordUseCase createAccountingRecordUseCase = new CreateAccountingRecordUseCase(accountingRecordRepository, domainEventBus);
        CreateAccountingRecordInput input = new CreateAccountingRecordInput();
        CreateAccountingRecordOutput output = new CreateAccountingRecordOutput();
        String ledgerId = "e8c1db78-50c7-4a05-b017-2109113e5ed4";
        input.setLedgerId(ledgerId);
        input.setName("早餐");
        input.setType("食物");
        input.setDate("20230317");
        input.setAmount(50);

        //Act
        createAccountingRecordUseCase.execute(input, output);

        //Assert
        Assertions.assertEquals("早餐", output.getName());
        Assertions.assertEquals("食物", output.getType());
        Assertions.assertEquals("20230317", output.getDate());
        Assertions.assertEquals(50, output.getAmount());
    }
}