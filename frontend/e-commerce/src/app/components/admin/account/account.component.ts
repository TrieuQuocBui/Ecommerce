import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faAdd,faSearch,faDeleteLeft,faInfo,faAdjust} from '@fortawesome/free-solid-svg-icons';
import { EPagination } from 'src/app/helpers/pagination.enum';
import { AccountModel } from 'src/app/models/account';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  iCon = faAdd;
  iconSearch = faSearch;
  iconUpdate = faAdjust;
  iconDelete =faDeleteLeft;
  iconInfo = faInfo;

  customerAccountList!:AccountModel[];

  ImageUrl:string = "http://localhost:8080/image/";

  message?:string;

  totalPages!:number
  currentPage!:number;
  itemsPerPage!:number;
  totalItems!:number;
  page?:number;
  limit?:number;

  constructor(private router:Router,private route:ActivatedRoute,private accountService:AccountService) { }

  ngOnInit() {
    // test
    this.route.queryParams.subscribe((params) =>{
      this.page = params['page'] || EPagination.page;
      this.limit = params['limit'] || EPagination.litmit;
      this.getCustomerAccountList();
    })
  }

  onPageChange(pageNumber: number) {
    if (pageNumber > 0 &&  pageNumber <= this.totalItems){
      this.currentPage = pageNumber!;
      this.limit = this.itemsPerPage;
      this.router.navigate(['/admin/account'], { queryParams: { page: this.currentPage,limit:this.limit},queryParamsHandling: 'merge' });
    }
  }

  getCustomerAccountList(){
    this.accountService.getCustomerAccountList(this.page!,this.limit!).subscribe((result) =>{
      this.customerAccountList = result.content;
      this.currentPage = result.pageNumber + 1;
      this.itemsPerPage = result.pageSize;
      this.totalItems = result.totalElements;
      this.totalPages = result.totalPages;
    })
  }

  checked(value:boolean,object:Object,action:string){
    console.log(action)
    if (value && action == 'acctive'){
      console.log("2")
      this.submitConfirmAcctive(object);
    } else if (value && action == 'inacctive'){
      console.log("1")
      this.submitConfirmInacctive(object);
    }
  }

  submitConfirmInacctive(object: Object) {
    
    this.accountService.inacctiveAccount(object).subscribe( (result) =>{
      this.message = result.message!;
      this.getCustomerAccountList();
    },
    (error) =>{
      this.message = error.error.message;
    })
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }

  submitConfirmAcctive(object: Object) {
    this.accountService.acctiveAccount(object).subscribe( (result) =>{
      this.message = result.message!;
      this.getCustomerAccountList();
    },
    (error) =>{
      this.message = error.error.message;
    })
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }

  redirectPage(url:string){
    this.router.navigate([url]);
  }

}
