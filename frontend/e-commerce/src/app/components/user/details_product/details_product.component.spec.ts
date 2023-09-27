/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { Details_productComponent } from './details_product.component';

describe('Details_productComponent', () => {
  let component: Details_productComponent;
  let fixture: ComponentFixture<Details_productComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Details_productComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Details_productComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
