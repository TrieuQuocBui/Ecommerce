/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { NotApproveYetComponent } from './not-approve-yet.component';

describe('NotApproveYetComponent', () => {
  let component: NotApproveYetComponent;
  let fixture: ComponentFixture<NotApproveYetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotApproveYetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotApproveYetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
