package org.example.usecase.eventHandler;

import com.google.common.eventbus.Subscribe;
import org.example.entity.model.accountingrecord.event.AccountingRecordCreated;
import org.example.entity.model.accountingrecord.event.AccountingRecordDeleted;
import org.example.entity.model.ledger.Ledger;
import org.example.model.DomainEventBus;
import org.example.usecase.ledger.LedgerRepository;

import java.util.Optional;

public class NotifyLedger {
    private LedgerRepository ledgerRepository;
    private DomainEventBus domainEventBus;

    public NotifyLedger(LedgerRepository ledgerRepository, DomainEventBus domainEventBus) {
        this.ledgerRepository = ledgerRepository;
        this.domainEventBus = domainEventBus;
    }

    @Subscribe
    public void whenAccountingRecordCreated(AccountingRecordCreated accountingRecordCreated) {
        Optional<Ledger> ledger = ledgerRepository.findById(accountingRecordCreated.getLedgerId());

        if (ledger.isPresent()) {
            Ledger selectedLedger = ledger.get();
            selectedLedger.clearDomainEvents();

            selectedLedger.commitAccountingRecordCreation(accountingRecordCreated.getAccountingRecordId());
            ledgerRepository.save(selectedLedger);
            domainEventBus.postAll(selectedLedger);
        } else {
            throw new RuntimeException("Ledger not found, ledger id = " + accountingRecordCreated.getLedgerId());
        }
    }

    @Subscribe
    public void whenAccountingRecordDeleted(AccountingRecordDeleted accountingRecordDeleted) {
        Optional<Ledger> ledger = ledgerRepository.findById(accountingRecordDeleted.getLedgerId());

        if (ledger.isPresent()) {
            Ledger selectedLedger = ledger.get();
            selectedLedger.clearDomainEvents();
            selectedLedger.commitAccountingRecordDeletion(accountingRecordDeleted.getAccountingRecordId());
            ledgerRepository.save(selectedLedger);
            domainEventBus.postAll(selectedLedger);
        } else {
            throw new RuntimeException("Ledger not found, ledger id = " + accountingRecordDeleted.getLedgerId());
        }
    }
}
