package org.example.adapter.controller.rest.springboot.ledger.ecit;


import org.example.usecase.accountingrecord.edit.EditAccountingRecordInput;
import org.example.usecase.accountingrecord.edit.EditAccountingRecordOutput;
import org.example.usecase.accountingrecord.edit.EditAccountingRecordUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class EditAccountingRecordController {
    private EditAccountingRecordUseCase editAccountingRecordUseCase;

    @Autowired
    public EditAccountingRecordController(EditAccountingRecordUseCase editAccountingRecordUseCase) {
        this.editAccountingRecordUseCase = editAccountingRecordUseCase;
    }

    @PutMapping(path = "/${EZ_LEDGER_PREFIX}/ledgers/{ledgerId}/accountingRecords/{accountingRecordId}")
    public String editAccountingRecord(@PathVariable("ledgerId") String ledgerId,
                                       @PathVariable("accountingRecordId") String accountingRecordId,
                                       @RequestBody String accountingRecordInfo) {
        EditAccountingRecordInput input = new EditAccountingRecordInput();
        EditAccountingRecordOutput output = new EditAccountingRecordOutput();

        String name = "";
        String type = "";
        String date = "";
        int amount = 0;

        try {
            JSONObject paymentRecordJSON = new JSONObject(accountingRecordInfo);
            name = paymentRecordJSON.getString("name");
            type = paymentRecordJSON.getString("type");
            date = paymentRecordJSON.getString("date");
            amount = paymentRecordJSON.getInt("amount");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        input.setAccountingRecordId(accountingRecordId);
        input.setName(name);
        input.setType(type);
        input.setDate(date);
        input.setAmount(amount);
        editAccountingRecordUseCase.execute(input, output);

        return output.getAccountingRecordId();
    }
}
