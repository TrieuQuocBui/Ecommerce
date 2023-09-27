import { Injectable } from '@angular/core';
import { ProductModel } from '../models/product';
import Cart_Item from '../models/cartItem';
import { AuthService } from './auth.service';
import { Role } from '../models/role.enum';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import baseUrl from '../helpers/URL';
import OrderModel from '../models/order';

const CART_KEY = 'localCart';
@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(private authenService:AuthService,private http:HttpClient,private authService:AuthService) {}

  public saveProductsIntoCart(cartItem:Cart_Item) {
    let cartData:Cart_Item[] = [];
    let updateCartItem:Cart_Item[] = [];
    let checkCart:Cart_Item[] = [];

    if ( this.authenService.getUser() && this.authenService.getRole() === Role.USER){
      cartItem.accountName = this.authenService.getUser().username;
    } else {
      cartItem.accountName = "";
    }

    if (this.getCart()){
      cartData = this.getCartItems();
      checkCart = cartData.filter(item => item.product?.id != cartItem.product);

      if ( cartData.length > checkCart.length){
        updateCartItem = cartData.filter(item => item.product?.id == cartItem.product);
        updateCartItem[0].quantity = updateCartItem[0].quantity! + cartItem?.quantity!;
        cartData = checkCart.concat(updateCartItem);
      } else {
        cartData.push(cartItem);
      }
      sessionStorage.removeItem(CART_KEY);

    } else {
      cartData.push(cartItem);
      
    }
    sessionStorage.setItem(CART_KEY,JSON.stringify(cartData))!;

  }

  public getCartItems(){
    return JSON.parse(sessionStorage.getItem(CART_KEY)!);
  }

  public getCart(){
    return sessionStorage.getItem(CART_KEY)!;
  }

  public setSelected(idP:number){
    let listItem:Cart_Item[] = this.getCartItems();
    listItem = listItem.map( item => {
      if ( item.product?.id == idP && item.select == false){
        item.select = true;
      } else if (item.product?.id == idP && item.select != false){
        item.select = false;
      }
      return item;
    })
    sessionStorage.removeItem(CART_KEY);
    sessionStorage.setItem(CART_KEY,JSON.stringify(listItem))!;
  }

  public changeQuantity(idP:number, quantityP:number){
    let listItem:Cart_Item[] = this.getCartItems();
    listItem = listItem.map( item => {
      if ( item.product?.id == idP){
        item.quantity = quantityP;
      } 
      return item;
    })
    sessionStorage.removeItem(CART_KEY);
    sessionStorage.setItem(CART_KEY,JSON.stringify(listItem))!;
  }

  public submitOrder(province:string,district:string,ward:string,addressHome:string,note:string){
    let order:OrderModel;
    let listItem:Cart_Item[] = this.getCartItems();
    let listItemAreNotSelect:Cart_Item[];
    let listItemAreSelected:Cart_Item[];
    listItemAreSelected = listItem.filter( item => item.select == true);
    listItemAreNotSelect = listItem.filter( item => item.select != true);
    sessionStorage.removeItem(CART_KEY);
    sessionStorage.setItem(CART_KEY,JSON.stringify(listItemAreNotSelect))!;
    order = {
      province:province,
      district:district,
      ward:ward,
      addressHome:addressHome,
      note:note,
      detailsOrders:listItemAreSelected
    }
    return this.http.post(`${baseUrl}/order`,order);
    
  }

  public LoggedCart(){
    let cartData:Cart_Item[] = [];
    if (this.getCart()){
      cartData = this.getCartItems();
      cartData = cartData.map((item) => {
        item.accountName = this.authenService.getUser().username;
        return item;
      });
    }
    sessionStorage.removeItem(CART_KEY);
    sessionStorage.setItem(CART_KEY,JSON.stringify(cartData))!;
  }
}
