import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ProductModel } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import { faLaptop,faMicrochip,faMemory,faHardDrive,faWeight,faRug} from '@fortawesome/free-solid-svg-icons'
import { EPagination } from 'src/app/helpers/pagination.enum';
import { BrandModel } from 'src/app/models/brand';
import { BrandService } from 'src/app/services/brand.service';
import { Pagination } from 'src/app/models/pagination';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  displaySize = faLaptop;
  cpu = faMicrochip;
  ram = faMemory;
  hardDrive = faHardDrive;
  graphicCard = faRug;
  weight = faWeight

  currentPage!:number;
  itemsPerPage!:number;
  totalItems!:number;
  page?:number;
  limit?:number;
  brandId!:number;

  productList?:ProductModel[];
  brandList?:BrandModel[];

  constructor(private productService:ProductService,private brandService: BrandService, private route:ActivatedRoute,private router:Router) { }

  ngOnInit() {
    this.route.queryParams.subscribe((params) =>{
      this.page = params['page'] || EPagination.page;
      this.limit = params['limit'] || EPagination.litmit;
      this.loadPage();
    })
    
  }

  get totalPages() {
    return Math.ceil(this.totalItems / this.itemsPerPage);
  }

  onPageChange(pageNumber: number) {
    if (pageNumber > 0 &&  pageNumber <= this.totalItems){
      this.currentPage = pageNumber!;
      this.router.navigate(['/home'], { queryParams: { page: this.currentPage},queryParamsHandling: 'merge' });
    }
  }

  choseeBrand(idBrand:number) {
    this.brandId = idBrand;
    this.router.navigate(['/home'], { queryParams: { brand:this.brandId,page: this.currentPage},queryParamsHandling: 'merge' });
  }

  loadPage(){
    this.productService.getProducts(this.page,this.limit).subscribe((relust) =>{
      this.productList = relust.content;
      this.currentPage = relust.pageNumber + 1;
      this.itemsPerPage = relust.pageSize;
      this.totalItems = relust.totalElements;
    },
    (error) =>{
      
    })

    // this.brandService.getBrandList().subscribe((result) =>{
    //   this.brandList = result;
    // })
  }

  checked(value:boolean,object:Object,action:string){
    if (value){
      //this.submitConfirm(object);
    }
  }
}
