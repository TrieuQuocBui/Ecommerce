import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { SupplierModel } from 'src/app/models/supplier';
import { SupplierService } from 'src/app/services/supplier.service';

@Component({
  selector: 'app-form-adding-supplier',
  templateUrl: './form-adding-supplier.component.html',
  styleUrls: ['./form-adding-supplier.component.css'],
})
export class FormAddingSupplierComponent implements OnInit {
  messageError?: string;
  message?: string;

  constructor(
    private formBuilder: FormBuilder,
    private supplierService: SupplierService
  ) {}

  ngOnInit() {
    this.supplier = this.formBuilder.group({
      id: [null, Validators.required],
      name: [null, Validators.required],
      sdt: [null, Validators.required],
      address: [null, Validators.required],
      status: [null],
    });
  }

  supplier: FormGroup = new FormGroup({
    id: new FormControl(null),
    name: new FormControl(null),
    sdt: new FormControl(null),
    address: new FormControl(null),
    status: new FormControl(true),
  });

  get f(): { [key: string]: AbstractControl } {
    return this.supplier.controls;
  }

  checked($event: boolean, arg1: null, arg2: string) {
    if ($event) {
      this.onSubmit();
    } else {
      this.messageError = 'huỷ thành công';
    }
  }

  onSubmit() {
    let supplier = new SupplierModel();
    supplier.id = this.f['id'].value;
    supplier.name = this.f["name"].value;
    supplier.address = this.f['address'].value;
    supplier.sdt = this.f['sdt'].value;
    supplier.status = this.f['status'].value;
    this.supplierService.createSupplier(supplier).subscribe(
      (result) => {
        this.message = result.message;
        setTimeout(() => {
          this.message = undefined
        }, 3000);
      },
      (error) => {
        this.message = error.error.message;
        setTimeout(() => {
          this.message = undefined
        }, 3000);
        
      }
    );


  }
}
