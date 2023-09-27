import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from '../helpers/URL';
import { ParamsHome } from '../models/paramsHome';
import OrderModel from '../models/order';

const reqHeader = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class OrderService {

constructor(private http: HttpClient) { }

  getOrderList(page:number,limit:number){
    const params:ParamsHome = {
      page:page,
      limit:limit
    }
    const queryParams = new HttpParams({ fromObject: params as any});
    return this.http.get<OrderModel[]>(`${baseUrl}/order/list`,{params:queryParams});
  }

  cancelOrder(idOrder:number){
    return this.http.delete(`${baseUrl}/order/${idOrder}/cancel`);
  }

  finishOrder(order:OrderModel){
    return this.http.put(`${baseUrl}/order/${order.id}/finish`,order);
  }
}
