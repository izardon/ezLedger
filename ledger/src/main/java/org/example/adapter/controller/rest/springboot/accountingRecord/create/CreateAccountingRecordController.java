package org.example.adapter.controller.rest.springboot.accountingRecord.create;

import org.example.usecase.accountingrecord.create.CreateAccountingRecordUseCase;
import org.example.usecase.accountingrecord.create.CreateAccountingRecordInput;
import org.example.usecase.accountingrecord.create.CreateAccountingRecordOutput;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CreateAccountingRecordController {
    private CreateAccountingRecordUseCase createAccountingRecordUseCase;

    @Autowired
    public CreateAccountingRecordController(CreateAccountingRecordUseCase createAccountingRecordUseCase) {
        this.createAccountingRecordUseCase = createAccountingRecordUseCase;
    }

    @PostMapping(path = "/${EZ_LEDGER_PREFIX}/ledgers/{ledgerId}/accountingRecords/")
    public String createPaymentRecord(@PathVariable("ledgerId") String ledgerId,
                                      @RequestBody String accountingRecordInfo) {
        CreateAccountingRecordInput input = new CreateAccountingRecordInput();
        CreateAccountingRecordOutput output = new CreateAccountingRecordOutput();

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

        input.setLedgerId(ledgerId);
        input.setName(name);
        input.setType(type);
        input.setDate(date);
        input.setAmount(amount);
        createAccountingRecordUseCase.execute(input, output);

        return output.getAccountingRecordId();
    }
}
