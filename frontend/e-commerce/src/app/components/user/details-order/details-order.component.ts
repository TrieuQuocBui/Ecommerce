import { Component, Input, OnInit } from '@angular/core';
import OrderModel from 'src/app/models/order';

@Component({
  selector: 'app-details-order',
  templateUrl: './details-order.component.html',
  styleUrls: ['./details-order.component.css']
})
export class DetailsOrderComponent implements OnInit {
  @Input() order!:OrderModel;
  totalMoney: number = 0;

  constructor() { }

  ngOnInit() {
    this.order.detailsOrders?.forEach(element => {
      this.totalMoney += element.quantity! * element.price!;
    });
  }

}
