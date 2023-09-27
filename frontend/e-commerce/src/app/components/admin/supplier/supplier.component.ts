import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  faAdd,
  faSearch,
  faDeleteLeft,
  faInfo,
  faAdjust,
} from '@fortawesome/free-solid-svg-icons';
import { interval } from 'rxjs';
import { EPagination } from 'src/app/helpers/pagination.enum';
import { Pagination } from 'src/app/models/pagination';
import { SupplierModel } from 'src/app/models/supplier';
import { SupplierService } from 'src/app/services/supplier.service';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.css'],
})
export class SupplierComponent implements OnInit {
  supplierList!: SupplierModel[];
  message?: string;
  isLoading: boolean = true;

  page: number = 1;
  limit: number = EPagination.litmit;

  iCon = faAdd;
  iconSearch = faSearch;
  iconUpdate = faAdjust;
  iconDelete = faDeleteLeft;
  iconInfo = faInfo;

  constructor(
    private supplierService: SupplierService,
    private router: Router
  ) {}

  ngOnInit() {
    
      this.loadingData();
    
    
  }

  loadingData(){
    this.getSupplierList();
    let subscription = interval(1000).subscribe(() => {
      if (this.isLoading){
        this.getSupplierList();
      }
      
    });
  }

  getSupplierList() {
    if (this.isLoading) {
      this.supplierService.getSupplier(this.page, this.limit).subscribe((result) => {
        if (this.supplierList == undefined) {
          this.supplierList = result.content;
        } else {
          this.supplierList.push(...result.content);
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
      this.submitConfirm(object);
    }
  }

  submitConfirm(object:SupplierModel){
    this.supplierService.deleteSupplier(object).subscribe( (result) =>{
      this.message = result.message!;
      this.supplierList = this.supplierList.filter((item) => {
        return item.id !== object.id ? item : null;
      })
    },
    (error) =>{
      this.message = error.error.message;
    })
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }
}
