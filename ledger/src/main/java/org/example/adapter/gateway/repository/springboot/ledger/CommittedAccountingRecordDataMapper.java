package org.example.adapter.gateway.repository.springboot.ledger;


import org.example.entity.model.ledger.CommittedAccountingRecord;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommittedAccountingRecordDataMapper {
    public static CommittedAccountingRecordData domainToData(CommittedAccountingRecord committedAccountingRecord) {
        return new CommittedAccountingRecordData(committedAccountingRecord.getLedgerId(), committedAccountingRecord.getAccountingRecordId());
    }

    public static List<CommittedAccountingRecordData> domainToData(List<CommittedAccountingRecord> committedAccountingRecords) {
        List<CommittedAccountingRecordData> committedAccountingRecordData = new ArrayList<>();
        committedAccountingRecords.forEach(committedAccountingRecord -> committedAccountingRecordData.add(domainToData(committedAccountingRecord)));
        return committedAccountingRecordData;
    }

    public static CommittedAccountingRecord dataToDomain(CommittedAccountingRecordData committedAccountingRecordData) {
        return new CommittedAccountingRecord(committedAccountingRecordData.getId().getLedgerId(), committedAccountingRecordData.getId().getAccountingRecordId());
    }

    public static Set<CommittedAccountingRecord> dataToDomain(Set<CommittedAccountingRecordData> committedAccountingRecordData) {
        Set<CommittedAccountingRecord> committedAccountingRecords = new HashSet<>();
        committedAccountingRecordData.forEach(committedAccountingRecord -> committedAccountingRecords.add(dataToDomain(committedAccountingRecord)));
        return committedAccountingRecords;
    }
}
