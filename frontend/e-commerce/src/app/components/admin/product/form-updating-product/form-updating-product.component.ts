import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { filter } from 'rxjs';
import { BrandModel } from 'src/app/models/brand';
import { ConfigModel } from 'src/app/models/config';
import { ProductModel } from 'src/app/models/product';
import { BrandService } from 'src/app/services/brand.service';
import { ConfigService } from 'src/app/services/config.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-form-updating-product',
  templateUrl: './form-updating-product.component.html',
  styleUrls: ['./form-updating-product.component.css'],
})
export class FormUpdatingProductComponent implements OnInit {
  productModal!: ProductModel;
  brandList!: BrandModel[];
  configList!: ConfigModel[];
  productId!: number;
  imageUrl!: string;
  messageErrorName?: string;
  avatarFile!: File;
  messageSuccess?: string;
  action!: string;
  messageErrorImage?: string;
  checkChangeImage: boolean = false;
  brandModel!: BrandModel;
  configModel!: ConfigModel;

  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private configService: ConfigService,
    private brandService: BrandService,
    private route: ActivatedRoute
  ) {
    this.product = this.formBuilder.group({
      name: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
      status: [null, Validators.required],
      img: [null],
      config: [null, Validators.required],
      brand: [null, Validators.required],
    });
  }

  ngOnInit() {
    this.productId = this.route.snapshot.params['productId'];
  

    this.findProduct(this.productId);

    this.getAllBrand();
    this.getAllConfig();
    
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

  get f(): { [key: string]: AbstractControl } {
    return this.product.controls;
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

  getAllBrand() {
    this.brandService.getAllBrand().subscribe((result) => {
      this.brandList = result.filter((item) => {
        return item.id != this.brandModel.id;
      })
    });
  }

  getAllConfig() {
    this.configService.getAllConfig().subscribe((result) => {
      this.configList = result.filter((item) => {
        return item.id != this.configModel.id;
      })
    });
  }

  onSubmit() {
    const formData = new FormData();
    this.productModal = {
      name: this.f['name'].value,
      config: this.f['config'].value,
      status: this.f['status'].value,
      brand: this.f['brand'].value,
      description: this.f['description'].value,
      price: this.f['price'].value,
    };
    if (this.checkChangeImage) {
      formData.append('image', this.avatarFile);
      this.productService.uploadFile(this.productId, formData).subscribe(
        (result) => {
          this.messageSuccess = result.message;
        },
        (error) => {
          this.messageErrorName = error.error.message;
        }
      );
    }
    this.productService.updateProduct(this.productModal, this.productId).subscribe(
      (result) => {
        this.messageSuccess = result.message;
      },
      (error) => {
        this.messageErrorName = error.error.message;
      }
    );
  }

  findProduct(productId: number) {
    this.productService.findById(productId).subscribe((result) => {
      this.productModal = result;
      this.f['name'].setValue(this.productModal.name);
      this.f['status'].setValue(this.productModal.status);
      this.f['config'].setValue(this.productModal.config);
      this.f['brand'].setValue(this.productModal.brand);
      this.f['description'].setValue(this.productModal.description);
      this.f['price'].setValue(this.productModal.price);
      this.configModel = result.config!;
      this.brandModel = result.brand!;
      this.imageUrl = `http://localhost:8080/image/${this.productModal.img}`;
    });
  }
}
