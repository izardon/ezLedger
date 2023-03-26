package org.example.adapter.gateway.repository.springboot.accountingrecord;

import org.example.adapter.gateway.repository.springboot.ledger.LedgerData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingRecordRepositoryPeer extends CrudRepository<AccountingRecordData, String> {
}
