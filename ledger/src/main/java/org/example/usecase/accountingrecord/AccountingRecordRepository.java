package org.example.usecase.accountingrecord;

import org.example.entity.model.accountingrecord.AccountingRecord;
import org.example.entity.model.ledger.Ledger;

import java.util.Optional;

public interface AccountingRecordRepository {

    void save(AccountingRecord accountingRecord);

    void delete(AccountingRecord accountingRecord);

    Optional<AccountingRecord> findById(String accountingRecordId);
}
