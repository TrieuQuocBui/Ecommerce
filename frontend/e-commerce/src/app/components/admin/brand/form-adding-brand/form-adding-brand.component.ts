import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BrandModel } from 'src/app/models/brand';
import { BrandService } from 'src/app/services/brand.service';

@Component({
  selector: 'app-form-adding-brand',
  templateUrl: './form-adding-brand.component.html',
  styleUrls: ['./form-adding-brand.component.css'],
})
export class FormAddingBrandComponent implements OnInit {
  brandModel!: BrandModel;
  messageErrorName?: string;
  avatarFile!: File;
  messageSuccess?: string;
  action!: string;
  imageUrl!: string;
  messageErrorImage?: string;

  constructor(
    private formBuilder: FormBuilder,
    private brandService: BrandService,
  ) {}

  ngOnInit() {
    this.imageUrl = `http://localhost:8080/image/default_image.jpg`;
    this.brand = this.formBuilder.group({
      name: [null, Validators.required],
      status: [null, Validators.required],
      image: [null, Validators.required],
    });
  }

  brand: FormGroup = new FormGroup({
    name: new FormControl(null),
    status: new FormControl(false),
    image: new FormControl(null),
  });

  get f(): { [key: string]: AbstractControl } {
    return this.brand.controls;
  }

  onSubmit() {
    const formData = new FormData();
    this.brandModel = {
      name: this.f['name'].value,
      status: this.f['status'].value,
    };

    formData.append('image', this.avatarFile);
    formData.append(
      'json',
      new Blob([JSON.stringify(this.brandModel)], { type: 'application/json' })
    );
    this.brandService.createBrand(formData).subscribe(
      (result) => {
        this.messageSuccess = result.message;
        console.log(this.messageSuccess);
        setTimeout(() => {
          this.messageSuccess = undefined
        }, 3000);
      },
      (error) => {
        this.messageErrorName = error.error.message;
        setTimeout(() => {
          this.messageSuccess = undefined
        }, 3000);
      }
    );
  }

  onKeydownName(event: any) {
    if (this.messageErrorName) {
      this.messageErrorName = undefined;
    }
  }

  onFileChange(event: any) {
    // get file image first
    this.avatarFile = event.target.files[0];
    if (this.avatarFile) {
      // create url to display an image when the user alters the image
      this.imageUrl = URL.createObjectURL(this.avatarFile);
    }
  }

  checked($event: boolean, arg1: null, arg2: string) {
    if ($event) {
      this.onSubmit();
    } else {
      this.messageErrorName = 'huỷ thành công';
    }
  }
}
