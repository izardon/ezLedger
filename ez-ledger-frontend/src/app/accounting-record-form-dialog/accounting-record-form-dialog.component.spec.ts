import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountingRecordFormDialogComponent } from './accounting-record-form-dialog.component';

describe('AccountingRecordFormDialogComponent', () => {
  let component: AccountingRecordFormDialogComponent;
  let fixture: ComponentFixture<AccountingRecordFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountingRecordFormDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountingRecordFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
