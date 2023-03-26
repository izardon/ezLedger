package org.example.adapter.presenter.ledger.getcontent;

import org.example.usecase.accountingrecord.AccountingRecordDto;

import java.util.List;

public class LedgerContentViewModel {
    private String ledgerId;
    private String ledgerName;
    private List<AccountingRecordDto> accountingRecordDtos;

    public LedgerContentViewModel(String ledgerId, String ledgerName, List<AccountingRecordDto> accountingRecordDtos) {
        this.ledgerId = ledgerId;
        this.ledgerName = ledgerName;
        this.accountingRecordDtos = accountingRecordDtos;
    }

    public String getLedgerId() {
        return ledgerId;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public List<AccountingRecordDto> getAccountingRecordDtos() {
        return accountingRecordDtos;
    }
}
