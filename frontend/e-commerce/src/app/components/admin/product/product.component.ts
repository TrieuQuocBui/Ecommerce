import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faAdd, faAdjust, faDeleteLeft, faInfo, faSearch } from '@fortawesome/free-solid-svg-icons';
import { interval } from 'rxjs';
import { EPagination } from 'src/app/helpers/pagination.enum';
import { ProductModel } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  productList?:ProductModel[];
  message?:string;

  isLoading: boolean = true;

  page: number = 1;
  limit: number = EPagination.litmit;

  ImageUrl:string = "http://localhost:8080/image/";

  iCon = faAdd;
  iconSearch = faSearch;
  iconUpdate = faAdjust;
  iconDelete =faDeleteLeft;
  iconInfo = faInfo;
  
  constructor(private router: Router,private productService:ProductService) { }

  ngOnInit() {
    this.loadingData();
  }

  loadingData(){
    this.getProductList();
    let subscription = interval(1000).subscribe(() => {
      if (this.isLoading){
        this.getProductList();
      }
    });
  }

  getProductList() {
    if (this.isLoading) {
      this.productService.getProductList(this.page, this.limit).subscribe((result) => {
        if (this.productList == undefined) {
          this.productList = result.content;
          
        } else {
          this.productList.push(...result.content);
          
        }
        if (result.lastPage) {
          this.isLoading = false;
        }
      });
    }
    this.page++;
  }

  redirectPage(url: string) {
    this.router.navigate([url]);
  }

  checked(value: boolean, object: Object, action: string) {
    if (value) {
      //this.submitConfirm(object);
    }
  }

  submitConfirm(object:ProductModel){
    // this.productService.deleteConfig(object).subscribe( (result) =>{
    //   this.message = result.message!;
    //   this.configList = this.configList.filter((item) => {
    //     return item.id !== object.id ? item : null;
    //   })
    // },
    // (error) =>{
    //   this.message = error.error.message;
    // })
    // setTimeout(() => {
    //   this.message = undefined;
    // }, 3000);
  }

}
