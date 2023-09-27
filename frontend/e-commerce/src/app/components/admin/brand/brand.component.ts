import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faAdd,faSearch,faDeleteLeft,faInfo,faAdjust} from '@fortawesome/free-solid-svg-icons';
import { EPagination } from 'src/app/helpers/pagination.enum';
import { BrandModel } from 'src/app/models/brand';
import { BrandService } from 'src/app/services/brand.service';
@Component({
  selector: 'app-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.css']
})
export class BrandComponent implements OnInit {
  brandList!:BrandModel[];
  message?:string;

  ImageUrl:string = "http://localhost:8080/image/";

  iCon = faAdd;
  iconSearch = faSearch;
  iconUpdate = faAdjust;
  iconDelete =faDeleteLeft;
  iconInfo = faInfo;

  totalPages!:number
  currentPage!:number;
  itemsPerPage!:number;
  totalItems!:number;
  page?:number;
  limit?:number;

  constructor(private router:Router,private brandService:BrandService,private route:ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe((params) =>{
      this.page = params['page'] || EPagination.page;
      this.limit = params['limit'] || EPagination.litmit;
      this.getBrandList();
    })
    
  }

  // get totalPages() {
  //   return Math.ceil(this.totalItems / this.itemsPerPage);
  // }

  getBrandList(){
    this.brandService.getBrandList(this.page!,this.limit!).subscribe( (result) =>{
      this.brandList = result.content;
      this.currentPage = result.pageNumber + 1;
      this.itemsPerPage = result.pageSize;
      this.totalItems = result.totalElements;
      this.totalPages = result.totalPages;
    })
  }

  onPageChange(pageNumber: number) {
    if (pageNumber > 0 &&  pageNumber <= this.totalItems){
      this.currentPage = pageNumber!;
      this.limit = this.itemsPerPage;
      this.router.navigate(['/admin/brand'], { queryParams: { page: this.currentPage,limit:this.limit},queryParamsHandling: 'merge' });
    }
  }

  redirectPage(url:string){
    this.router.navigate([url]);
  }

  checked(value:boolean,object:Object,action:string){
    if (value){
      this.submitConfirm(object);
    }
  }

  submitConfirm(object: Object) {
    this.brandService.deleteBrand(object).subscribe( (result) =>{
      this.message = result.message!;
      this.getBrandList();
      
    },
    (error) =>{
      this.message = error.error.message;
    })
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }
 

}
