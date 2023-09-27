import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faCirclePlus } from '@fortawesome/free-solid-svg-icons';
import Cart_Item from 'src/app/models/cartItem';
import { ProductModel } from 'src/app/models/product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-details_product',
  templateUrl: './details_product.component.html',
  styleUrls: ['./details_product.component.css'],
})
export class DetailsProductComponent implements OnInit {
  facompare = faCirclePlus;
  product?: ProductModel;
  cartItem!: Cart_Item;
  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private cartService:CartService
  ) {}

  ngOnInit() {
    const productId = this.route.snapshot.params['productId'];
    this.getProductById(productId);
  }

  getProductById(productId: number) {
    this.productService.getProductById(productId).subscribe(
      (result) => {
        this.product = result;
      },
      (error) => {

      }
    );
  }

  onAddIntoCart(productId?:number){
    let product:ProductModel;
    productId && this.productService.getProductById(productId).subscribe((result)=>{
      this.product = result;
    })

    
    this.cartItem = {
      product : this.product,
      quantity: 1,
      price:10.500,
      select:false,
    }
    let cart = this.cartService.saveProductsIntoCart(this.cartItem);
  }
}
