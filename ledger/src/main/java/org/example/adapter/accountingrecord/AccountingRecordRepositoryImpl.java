package org.example.adapter.accountingrecord;

import org.example.adapter.gateway.repository.springboot.accountingrecord.AccountingRecordData;
import org.example.adapter.gateway.repository.springboot.accountingrecord.AccountingRecordDataMapper;
import org.example.adapter.gateway.repository.springboot.accountingrecord.AccountingRecordRepositoryPeer;
import org.example.adapter.gateway.repository.springboot.ledger.LedgerData;
import org.example.adapter.gateway.repository.springboot.ledger.LedgerDataMapper;
import org.example.adapter.gateway.repository.springboot.ledger.LedgerRepositoryPeer;
import org.example.entity.model.accountingrecord.AccountingRecord;
import org.example.entity.model.ledger.Ledger;
import org.example.usecase.accountingrecord.AccountingRecordRepository;
import org.example.usecase.ledger.LedgerRepository;

import java.util.Optional;

public class AccountingRecordRepositoryImpl implements AccountingRecordRepository {
    private AccountingRecordRepositoryPeer accountingRecordRepositoryPeer;

    public AccountingRecordRepositoryImpl(AccountingRecordRepositoryPeer accountingRecordRepositoryPeer) {
        this.accountingRecordRepositoryPeer = accountingRecordRepositoryPeer;
    }

    @Override
    public void save(AccountingRecord accountingRecord) {
        AccountingRecordData accountingRecordData = AccountingRecordDataMapper.domainToData(accountingRecord);
        accountingRecordRepositoryPeer.save(accountingRecordData);
    }

    @Override
    public void delete(AccountingRecord accountingRecord) {
        AccountingRecordData accountingRecordData = AccountingRecordDataMapper.domainToData(accountingRecord);
        accountingRecordRepositoryPeer.delete(accountingRecordData);
    }

    @Override
    public Optional<AccountingRecord> findById(final String accountingRecordId) {
        Optional<AccountingRecordData> accountingRecordData = accountingRecordRepositoryPeer.findById(accountingRecordId);
        if (accountingRecordData.isPresent()) {
            AccountingRecordData selectedAccountingRecordData = accountingRecordData.get();
            AccountingRecord result = AccountingRecordDataMapper.dataToDomain(selectedAccountingRecordData);
            return Optional.of(result);
        }
        return Optional.empty();
    }
}
