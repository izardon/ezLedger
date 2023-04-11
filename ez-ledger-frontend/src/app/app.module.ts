import { NgModule } from '@angular/core';
import { MatNativeDateModule } from '@angular/material/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
//import { DepartmentListComponent } from './department-list/department-list.component';
//import { EmployeeListComponent } from './employee-list/employee-list.component';
import { MatTableModule } from '@angular/material/table'
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import { MatDatepickerModule } from '@angular/material/datepicker';

import { DialogBoxComponent } from './dialog-box/dialog-box.component';

import { CommonModule } from '@angular/common';
import { HttpClientModule  } from '@angular/common/http';
import { AccountingRecordFormDialogComponent } from './accounting-record-form-dialog/accounting-record-form-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    //DepartmentListComponent,
    //EmployeeListComponent
    routingComponents,
    DialogBoxComponent,
    AccountingRecordFormDialogComponent,
  ],
  entryComponents: [DialogBoxComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    MatCardModule,
    MatToolbarModule,
    MatButtonModule,
    MatDialogModule,
    MatDatepickerModule,
    CommonModule,
    HttpClientModule,
    MatNativeDateModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }