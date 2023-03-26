package org.example.adapter.controller.rest.springboot.ledger.getcontent;

import org.example.adapter.presenter.ledger.getcontent.LedgerContentViewModel;
import org.example.usecase.accountingrecord.AccountingRecordDto;
import org.example.usecase.accountingrecord.AccountingRecordDtoMapper;
import org.example.usecase.ledger.query.getcontent.GetLedgerContentInput;
import org.example.usecase.ledger.query.getcontent.GetLedgerContentOutput;
import org.example.usecase.ledger.query.getcontent.GetLedgerContentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GetLedgerContentController {
    private GetLedgerContentUseCase getLedgerContentUseCase;

    @Autowired
    public GetLedgerContentController(GetLedgerContentUseCase getLedgerContentUseCase) {
        this.getLedgerContentUseCase = getLedgerContentUseCase;
    }

    @GetMapping(path = "/${EZ_LEDGER_PREFIX}/ledgers/{ledgerId}/")
    public LedgerContentViewModel getLedgerContent(@PathVariable("ledgerId") String ledgerId) {
        GetLedgerContentInput input = new GetLedgerContentInput();
        GetLedgerContentOutput output = new GetLedgerContentOutput();
        List<AccountingRecordDto> accountingRecordDtos;
        AccountingRecordDtoMapper accountingRecordDtoMapper = new AccountingRecordDtoMapper();

        input.setLedgerId(ledgerId);
        getLedgerContentUseCase.execute(input, output);
        accountingRecordDtos = accountingRecordDtoMapper.domainToDto(output.getAccountingRecords());

        return new LedgerContentViewModel(output.getLedgerId(), output.getLedgerName(), accountingRecordDtos);
    }

}
