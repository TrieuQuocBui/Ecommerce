/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { FormUpdatingBrandComponent } from './form-updating-brand.component';

describe('FormUpdatingBrandComponent', () => {
  let component: FormUpdatingBrandComponent;
  let fixture: ComponentFixture<FormUpdatingBrandComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormUpdatingBrandComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormUpdatingBrandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
