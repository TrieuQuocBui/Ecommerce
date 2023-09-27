import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { BrandModel } from 'src/app/models/brand';
import { ConfigModel } from 'src/app/models/config';
import { ProductModel } from 'src/app/models/product';
import { BrandService } from 'src/app/services/brand.service';
import { ConfigService } from 'src/app/services/config.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-form-adding-product',
  templateUrl: './form-adding-product.component.html',
  styleUrls: ['./form-adding-product.component.css'],
})
export class FormAddingProductComponent implements OnInit {
  productModal!: ProductModel;
  brandList!:BrandModel[];
  configList!:ConfigModel[];
  messageErrorName?: string;
  avatarFile!: File;
  messageSuccess?: string;
  action!: string;
  imageUrl!: string;
  messageErrorImage?: string;

  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private configService:ConfigService,
    private brandService:BrandService
  ) {}

  ngOnInit() {
    this.getAllBrand();
    this.getAllConfig();
    this.imageUrl = `http://localhost:8080/image/default_image.jpg`;
    this.product = this.formBuilder.group({
      name: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
      status: [null, Validators.required],
      img: [null, Validators.required],
      config: [null, Validators.required],
      brand: [null, Validators.required],
    });
  }

  product: FormGroup = new FormGroup({
    name: new FormControl(null),
    price: new FormControl(null),
    description: new FormControl(null),
    status: new FormControl(null),
    img: new FormControl(null),
    config: new FormControl(null),
    brand: new FormControl(null),
  });

  getAllBrand(){
    this.brandService.getAllBrand().subscribe((result) =>{
      this.brandList = result;
    })
  }

  getAllConfig(){
    this.configService.getAllConfig().subscribe((result) =>{
      this.configList = result;
    })
  }



  get f(): { [key: string]: AbstractControl } {
    return this.product.controls;
  }

  onSubmit() {
    console.log(this.product.value)
    const formData = new FormData();
    this.productModal = {
      name: this.f['name'].value,
      config: this.f['config'].value,
      status: this.f['status'].value,
      brand: this.f['brand'].value,
      description: this.f['description'].value,
      price: this.f['price'].value,
    };
    formData.append('image', this.avatarFile);
    formData.append(
      'json',
      new Blob([JSON.stringify(this.productModal)], { type: 'application/json' })
    );
    this.productService.createProduct(formData).subscribe(
      (result) => {
        this.messageSuccess = result.message;
        console.log(this.messageSuccess);
        setTimeout(() => {
          this.messageSuccess = undefined;
        }, 3000);
      },
      (error) => {
        this.messageErrorName = error.error.message;
        setTimeout(() => {
          this.messageSuccess = undefined;
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
