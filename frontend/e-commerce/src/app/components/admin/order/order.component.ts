import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faAdd, faAdjust, faDeleteLeft, faInfo, faSearch } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  
  message?:string;
  iconSearch = faSearch;
  constructor(private router: Router) { }

  ngOnInit() {
  }

  redirectPage(url: string) {
    this.router.navigate([url]);
  }

  checked(value: boolean, object: Object, action: string) {
    if (value) {
      //this.submitConfirm(object);
    }
  }

}
