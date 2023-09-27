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
  selector: 'app-form-updating-brand',
  templateUrl: './form-updating-brand.component.html',
  styleUrls: ['./form-updating-brand.component.css'],
})
export class FormUpdatingBrandComponent implements OnInit {
  brandModel!: BrandModel;
  messageErrorName?: string;
  avatarFile!: File;
  messageSuccess?: string;
  action!: string;
  imageUrl!: string;
  brandId!: number;
  messageErrorImage?: string;
  checkChangeImage: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private brandService: BrandService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.brandId = this.route.snapshot.params['brandId'];
    this.findBrand(this.brandId);
    this.brand = this.formBuilder.group({
      name: [null, Validators.required],
      status: [null, Validators.required],
    });
  }

  brand: FormGroup = new FormGroup({
    name: new FormControl(null),
    status: new FormControl(false),
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

    if (this.checkChangeImage) {
      formData.append('image', this.avatarFile);
      this.brandService.uploadFile(this.brandId, formData).subscribe(
        (result) => {
          this.messageSuccess = result.message;
        },
        (error) => {
          this.messageErrorName = error.error.message;
        }
      );
    }
    this.brandService.updateBrand(this.brandModel, this.brandId).subscribe(
      (result) => {
        this.messageSuccess = result.message;
      },
      (error) => {
        this.messageErrorName = error.error.message;
      }
    );
  }

  onKeydownName(event: any) {
    if (this.messageErrorName) {
      this.messageErrorName = undefined;
    }
  }

  onFileChange(event: any) {
    // Is the checking changing the image?
    this.checkChangeImage = true;

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

  findBrand(brandId: number) {
    this.brandService.getBrandById(brandId).subscribe((result) => {
      this.brandModel = result;
      this.f['name'].setValue(this.brandModel.name);
      this.f['status'].setValue(this.brandModel.status);
      this.imageUrl = `http://localhost:8080/image/${this.brandModel.img}`;
    });
  }
}
