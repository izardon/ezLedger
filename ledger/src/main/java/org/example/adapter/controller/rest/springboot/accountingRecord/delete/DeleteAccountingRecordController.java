package org.example.adapter.controller.rest.springboot.accountingRecord.delete;

import org.example.usecase.accountingrecord.delete.DeleteAccountingRecordInput;
import org.example.usecase.accountingrecord.delete.DeleteAccountingRecordOutput;
import org.example.usecase.accountingrecord.delete.DeleteAccountingRecordUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class DeleteAccountingRecordController {
    private DeleteAccountingRecordUseCase deleteAccountingRecordUseCase;

    @Autowired
    public DeleteAccountingRecordController(DeleteAccountingRecordUseCase deleteAccountingRecordUseCase) {
        this.deleteAccountingRecordUseCase = deleteAccountingRecordUseCase;
    }

    @DeleteMapping(path = "/${EZ_LEDGER_PREFIX}/ledgers/{ledgerId}/accountingRecords/{accountingRecordId}")
    public String deleteAccountingRecord(@PathVariable("ledgerId") String ledgerId,
                                         @PathVariable("accountingRecordId") String accountingRecordId) {
        DeleteAccountingRecordInput input = new DeleteAccountingRecordInput();
        DeleteAccountingRecordOutput output = new DeleteAccountingRecordOutput();

        input.setAccountingRecordId(accountingRecordId);
        deleteAccountingRecordUseCase.execute(input, output);

        return output.getAccountingRecordId();
    }
}
