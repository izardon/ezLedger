package org.example.usecase.ledger;

import org.example.entity.model.ledger.Ledger;

import java.util.Optional;

public interface LedgerRepository {

    void save(Ledger ledger);

    Optional<Ledger> findById(String ledgerId);
}
