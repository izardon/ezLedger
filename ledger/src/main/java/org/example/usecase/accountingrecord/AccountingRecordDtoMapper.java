package org.example.usecase.accountingrecord;

import org.example.entity.model.accountingrecord.AccountingRecord;

import java.util.ArrayList;
import java.util.List;

public class AccountingRecordDtoMapper {
    public AccountingRecordDto domainToDto(AccountingRecord accountingRecord) {
        return new AccountingRecordDto(
                accountingRecord.getId(),
                accountingRecord.getName(),
                accountingRecord.getType(),
                accountingRecord.getDate(),
                accountingRecord.getAmount()
        );
    }

    public List<AccountingRecordDto> domainToDto(List<AccountingRecord> accountingRecords) {
        List<AccountingRecordDto> accountingRecordDtos = new ArrayList<>();
        accountingRecords.forEach(accountingRecord -> accountingRecordDtos.add(domainToDto((AccountingRecord) accountingRecord)));
        return accountingRecordDtos;
    }
}
