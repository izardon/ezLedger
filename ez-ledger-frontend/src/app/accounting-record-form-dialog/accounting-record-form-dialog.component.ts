import { Component, OnInit, Inject } from '@angular/core';
import { inject } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AccountingRecord } from '../models/accounting-record.model';
import { HttpClient } from '@angular/common/http';
import * as moment from 'moment';

@Component({
  selector: 'app-accounting-record-form-dialog',
  templateUrl: './accounting-record-form-dialog.component.html',
  styleUrls: ['./accounting-record-form-dialog.component.css']
})

export class AccountingRecordFormDialogComponent implements OnInit {
  ledgerId: any;
  accountingRecordId: any;
  formattedDate: string;
  newAccountingRecord: AccountingRecord = new AccountingRecord();

  constructor(
    private http: HttpClient,
    public dialogRef: MatDialogRef<AccountingRecordFormDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.ledgerId = this.data.ledgerId;
    this.accountingRecordId = this.data.accountingRecord.id;
    this.formattedDate = "";

    if(this.accountingRecordId){
      this.newAccountingRecord.name = this.data.accountingRecord.name;
      this.newAccountingRecord.type = this.data.accountingRecord.type;
      this.newAccountingRecord.date = moment(this.data.accountingRecord.date, 'YYYYMMDD').toDate();
      this.newAccountingRecord.amount = this.data.accountingRecord.amount;
      this.onDateInput();
    }
  }

  ngOnInit(): void {
  }

  onDateInput() {
    console.log(this.newAccountingRecord.date);
    // 將HTML日期轉換為moment物件
    const momentDate = moment(this.newAccountingRecord.date);
    // 進行格式化
    this.formattedDate = momentDate.format('YYYYMMDD');
    console.log(this.formattedDate);
  }

  formValid(): boolean {
    return this.newAccountingRecord.name == ""
      || this.newAccountingRecord.type == ""
      || this.newAccountingRecord.date == ""
      || this.newAccountingRecord.amount == 0 ? false : true;
  }

  addAccountingRecord(): void {
    if (this.formValid()) {
      let body = {
        "name": this.newAccountingRecord.name,
        "type": this.newAccountingRecord.type,
        "date": this.formattedDate,
        "amount": this.newAccountingRecord.amount
      }

      let url = `http://localhost:8080/ez-ledger/ledgers/${this.ledgerId}/accountingRecords/`

      const requestOptions: Object = {
        /* other options here */
        responseType: 'text'
      }

      this.http.post<any>(url, body, requestOptions).subscribe();

      this.dialogRef.close(this.newAccountingRecord);
    }
  }

  editAccountingRecord(){
    if (this.formValid()) {
      let body = {
        "name": this.newAccountingRecord.name,
        "type": this.newAccountingRecord.type,
        "date": this.formattedDate,
        "amount": this.newAccountingRecord.amount
      }

      let url = `http://localhost:8080/ez-ledger/ledgers/${this.ledgerId}/accountingRecords/${this.accountingRecordId}`

      const requestOptions: Object = {
        /* other options here */
        responseType: 'text'
      }

      this.http.put<any>(url, body, requestOptions).subscribe();

      this.dialogRef.close(this.newAccountingRecord);
    } 
  }

}
