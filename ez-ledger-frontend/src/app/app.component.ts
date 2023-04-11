import { Component, OnInit, OnDestroy } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';
import { Observer, Subject, Subscription } from 'rxjs';
import { AccountingRecordFormDialogComponent } from './accounting-record-form-dialog/accounting-record-form-dialog.component';
import { AccountingRecord } from './models/accounting-record.model';

export interface ledger {
  id: string;
  name: string;
  accountingRecords: AccountingRecord[]
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  private updateTableSubject = new Subject<void>();

  LEDGER_ID: string= "e8c1db78-50c7-4a05-b017-2109113e5ed4";
  name: string="";
  columnsToDisplay: string[] = ["name", "type","date","amount","actions"];
  public newAccountingRecord = {id: "", name: "", type: "", date:"", amount:0};
  public accountingRecordDataArray: any;

  public ledgerContent: ledger = {
    id: "",
    name: "",
    accountingRecords: []
  };

  constructor(private http: HttpClient, public dialog: MatDialog) {
    this.accountingRecordDataArray = new MatTableDataSource<AccountingRecord>([...this.ledgerContent.accountingRecords]);

    this.updateTableSubject.subscribe(() => {
      // 發送API請求來取得資料
      this.getLedgerContent();
    });
  }

  ngOnInit() {
    this.getLedgerContent();
  }

  getLedgerContent() {
    let url = `http://localhost:8080/ez-ledger/ledgers/${this.LEDGER_ID}/`

    this.http.get<any>(url).subscribe(res => {
      this.ledgerContent.id = res.ledgerId
      this.ledgerContent.name = res.ledgerName
      this.ledgerContent.accountingRecords = res.accountingRecordDtos

      this.accountingRecordDataArray = [...this.ledgerContent.accountingRecords];
      console.log(res)
    })
  }

  deleteAccountingRecord(row_obj:any){
    if(row_obj.id!=""){
      let url = `http://localhost:8080/ez-ledger/ledgers/${this.LEDGER_ID}/accountingRecords/${row_obj.id}`

      const requestOptions: Object = {
        /* other options here */
        responseType: 'text'
      }

      const observer: Observer<any> = {
        next: data => {
          this.ledgerContent.accountingRecords = this.ledgerContent.accountingRecords.filter((value,key)=>{
            return value.id != row_obj.id;
          });
          this.accountingRecordDataArray = [...this.ledgerContent.accountingRecords];//refresh the dataSource
          Swal.fire('Deleted successfully..!')
        },
        error: error => Swal.fire('oops', error, 'error'),
        complete: () => console.log('completed deleteAccountingRecord')
      };
      this.http.delete<any>(url, requestOptions).subscribe(observer);
    } else {
      Swal.fire(':-|', 'Invalid row id!', 'error')
    }
  }

  openCreateDialog(): void {
    const dialogConfig = new MatDialogConfig();

    let dialogRef = this.dialog.open(AccountingRecordFormDialogComponent, {
      width: '300px',
      data: { 
        ledgerId: this.LEDGER_ID,
        accountingRecord: {},
        updateTableSubject: this.updateTableSubject
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.updateTableSubject.next();
    });
  }

  openEditDialog(row_obj:any) {
    if(row_obj.id!=""){
      const dialogConfig = new MatDialogConfig();

      let dialogRef = this.dialog.open(AccountingRecordFormDialogComponent, {
        width: '300px',
        data: { 
          ledgerId: this.LEDGER_ID,
          accountingRecord: row_obj,
          updateTableSubject: this.updateTableSubject
        }
      });

      dialogRef.afterClosed().subscribe(result => {
        this.updateTableSubject.next();
      });

    } else {
      Swal.fire(':-|', 'Invalid row id!', 'error')
    }
  }


}
