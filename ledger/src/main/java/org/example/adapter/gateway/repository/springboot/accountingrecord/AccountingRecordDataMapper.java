package org.example.adapter.gateway.repository.springboot.accountingrecord;

import org.example.entity.model.accountingrecord.AccountingRecord;

import java.util.ArrayList;
import java.util.List;

public class AccountingRecordDataMapper {
    public static AccountingRecordData domainToData(AccountingRecord accountingRecord) {
        return new AccountingRecordData(accountingRecord.getId(),
                accountingRecord.getLedgerId(),
                accountingRecord.getName(),
                accountingRecord.getType(),
                accountingRecord.getDate(),
                accountingRecord.getAmount());
    }

    public static List<AccountingRecordData> domainToData(List<AccountingRecord> accountingRecords) {
        List<AccountingRecordData> accountingRecordData = new ArrayList<>();
        accountingRecords.forEach(accountingRecord -> accountingRecordData.add(domainToData(accountingRecord)));
        return accountingRecordData;
    }

    public static AccountingRecord dataToDomain(AccountingRecordData accountingRecordData) {
        return new AccountingRecord(accountingRecordData.getAccountingRecordId(),
                accountingRecordData.getLedgerId(),
                accountingRecordData.getName(),
                accountingRecordData.getType(),
                accountingRecordData.getDate(),
                accountingRecordData.getAmount());
    }

    public static List<AccountingRecord> dataToDomain(List<AccountingRecordData> accountingRecordDatas) {
        List<AccountingRecord> accountingRecords = new ArrayList<>();
        accountingRecordDatas.forEach(accountingRecordData -> accountingRecords.add(dataToDomain(accountingRecordData)));
        return accountingRecords;
    }
}
