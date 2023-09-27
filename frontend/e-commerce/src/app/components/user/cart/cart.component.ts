import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Cart_Item from 'src/app/models/cartItem';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  selectedItems: any[] = [];
  cart:Cart_Item[] = [];
  message?:string;
  constructor(private cartService:CartService,private router:Router,private authService:AuthService) { }

  ngOnInit() {
    this.loadCart();
    let listItem:Cart_Item[] = this.cartService.getCartItems();
    this.selectedItems = listItem.filter( item => item.select == true);
  }

  loadCart(){
    this.cart = this.cartService.getCartItems();
  }

  onCheckboxChange(event:any) {
    let idP = event.target.value;
    this.cartService.setSelected(idP);
    let listItem:Cart_Item[] = this.cartService.getCartItems();
    this.selectedItems = listItem.filter( item => item.select == true);
  }

  onQuantityChange(productId?:number,event?:any){
    let quantityP = event.target.value;
    if (quantityP <= 0){
        return
    } 
    productId && this.cartService.changeQuantity(productId,quantityP);
  }
  
  submitCart(){
    const currentUrl = this.router.url;
    localStorage.setItem('previousUrl', currentUrl);
    if (!this.authService.isLoggedIn()){
      this.router.navigate(['/login']);
    } else {
      this.router.navigate(['/order']);
    }
  }
}
