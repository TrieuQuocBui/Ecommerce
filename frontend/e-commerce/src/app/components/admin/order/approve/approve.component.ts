import { Component, OnInit } from '@angular/core';
import { faAdd, faAdjust, faDeleteLeft, faInfo, faSearch } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-approve',
  templateUrl: './approve.component.html',
  styleUrls: ['./approve.component.css']
})
export class ApproveComponent implements OnInit {
  orderList?:Object[];

  iconSearch = faSearch;
  iconUpdate = faAdjust;
  iconDelete =faDeleteLeft;
  iconInfo = faInfo;
  constructor() { }

  ngOnInit() {
  }

  checked(value: boolean, object: Object, action: string) {
    if (value) {
      //this.submitConfirm(object);
    }
  }

}
