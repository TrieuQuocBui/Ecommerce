/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { FooterUserComponent } from './footer-user.component';

describe('FooterUserComponent', () => {
  let component: FooterUserComponent;
  let fixture: ComponentFixture<FooterUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FooterUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FooterUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
