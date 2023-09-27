/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SupplierService } from './supplier.service';

describe('Service: Supplier', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SupplierService]
    });
  });

  it('should ...', inject([SupplierService], (service: SupplierService) => {
    expect(service).toBeTruthy();
  }));
});
