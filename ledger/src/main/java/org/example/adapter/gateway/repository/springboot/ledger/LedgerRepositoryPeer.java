package org.example.adapter.gateway.repository.springboot.ledger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerRepositoryPeer extends CrudRepository<LedgerData, String> {
}
