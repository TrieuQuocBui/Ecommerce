import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Status_Order } from 'src/app/helpers/status_order.enum';
import OrderModel from 'src/app/models/order';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-list-order',
  templateUrl: './list-order.component.html',
  styleUrls: ['./list-order.component.css']
})
export class ListOrderComponent implements OnInit {
  orderList!:OrderModel[];
  page!:number;
  limit!:number;

  constructor(private route:ActivatedRoute,private orderService:OrderService) { }

  ngOnInit() {
    this.getOrderList();
  }

  getOrderList(){
    this.route.queryParams.subscribe((params) =>{
      const page = params['page'];
      const limit = params['limit'];

      if (page == undefined){
        this.page = 1;
      } else {
        this.page = page;
      }

      if (limit == undefined){
        this.limit = 9;
      } else {
        this.limit = limit;
      }
    })

    this.orderService.getOrderList(this.page,this.limit).subscribe((relust) =>{
      this.orderList = relust;
    },
    (error) =>{
      
    })
  }

  submitConfirm(item:OrderModel){
    item.status = Status_Order.Finish;
    this.orderService.finishOrder(item).subscribe((data) =>{
      console.log(data);
    })
  }

  submitCancle(item:OrderModel){
    this.orderService.cancelOrder(item.id!).subscribe((data) =>{
      this.getOrderList();
    })
  }

  checked(value:boolean,object:Object,action:string){
    if ( value && action == 'cancel'){
        this.submitCancle(object);
    } else if (value && action == 'finish'){
        this.submitConfirm(object);
    }

  }
}
