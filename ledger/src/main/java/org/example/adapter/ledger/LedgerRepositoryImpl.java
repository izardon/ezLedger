package org.example.adapter.ledger;

import org.example.adapter.gateway.repository.springboot.ledger.LedgerData;
//import org.example.adapter.gateway.repository.springboot.ledger.LedgerDataMapper;
import org.example.adapter.gateway.repository.springboot.ledger.LedgerDataMapper;
import org.example.adapter.gateway.repository.springboot.ledger.LedgerRepositoryPeer;
import org.example.entity.model.ledger.Ledger;
import org.example.usecase.ledger.LedgerRepository;

import java.util.Optional;

public class LedgerRepositoryImpl implements LedgerRepository {
    private LedgerRepositoryPeer ledgerRepositoryPeer;

    public LedgerRepositoryImpl(LedgerRepositoryPeer ledgerRepositoryPeer) {
        this.ledgerRepositoryPeer = ledgerRepositoryPeer;
    }

    @Override
    public void save(Ledger ledger) {
        LedgerData ledgerData = LedgerDataMapper.domainToData(ledger);
        ledgerRepositoryPeer.save(ledgerData);
    }

    @Override
    public Optional<Ledger> findById(final String ledgerId) {
        Optional<LedgerData> ledgerData = ledgerRepositoryPeer.findById(ledgerId);
        if (ledgerData.isPresent()) {
            LedgerData selectedLedgerData = ledgerData.get();
            Ledger result = LedgerDataMapper.dataToDomain(selectedLedgerData);
            return Optional.of(result);
        }
        return Optional.empty();
    }
}
