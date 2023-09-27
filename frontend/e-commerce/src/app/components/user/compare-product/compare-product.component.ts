import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductModel } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-compare-product',
  templateUrl: './compare-product.component.html',
  styleUrls: ['./compare-product.component.css'],
})
export class CompareProductComponent implements OnInit {
  // when we want to access into child component, directive and DOM el from parent component
  // @ViewChild return one object and @ViewChildren return QueryList
  @ViewChild('suggestedSearchLeft', { static: true }) // true work with OnInit, false work with ngAfterViewInit
  suggestedSearchLeft!: ElementRef;

  @ViewChild('suggestedSearchRight', { static: true })
  suggestedSearchRight!: ElementRef;

  productListLeft?: ProductModel[];
  productListRight?: ProductModel[];

  productLeft?:ProductModel;

  productRight?:ProductModel;

  heightSuggestedSearch?: number;

  constructor(private productService: ProductService, private route:ActivatedRoute) {}

  ngOnInit(): void {
    const name = this.route.snapshot.paramMap.get('name');
    if (name){
      this.productService.getProductByName(name).subscribe( (data) =>{
        this.productLeft = data;
      });
    }
  }

  calculateHeight(multiplier: number): number {
    const baseHeight = 78.2;
    return baseHeight * multiplier;
  }

  searchProductLeft(query: KeyboardEvent) {
    if (query) {
      const element = query.target as HTMLInputElement;
      this.productService
        .getProductsByNameContaining(element.value)
        .subscribe((data) => {
          this.productListLeft = data;
          let number = this.productListLeft && this.productListLeft.length;
          if (number >= 4) {
            this.heightSuggestedSearch = this.calculateHeight(4);
          } else {
            this.heightSuggestedSearch = this.calculateHeight(number);
          }
          this.suggestedSearchLeft.nativeElement.style.display = 'block';
          this.suggestedSearchLeft.nativeElement.style.height = this.heightSuggestedSearch + 'px';
        });
    }
  }

  hideSearchLeft() {
    this.productListLeft = undefined;
    this.suggestedSearchLeft.nativeElement.style.display = 'none';
  }

  selectProductLeft(name?:string){
    this.productService.getProductByName(name).subscribe( (data) =>{
      this.productLeft = data;
    });
  }

  searchProductRight(query: KeyboardEvent) {
    if (query) {
      const element = query.target as HTMLInputElement; // provides special properties and method of tag input
      this.productService
        .getProductsByNameContaining(element.value)
        .subscribe((data) => {
          this.productListRight = data;
          let number = this.productListRight && this.productListRight.length;
          if (number >= 4) {
            this.heightSuggestedSearch = this.calculateHeight(4);
          } else {
            this.heightSuggestedSearch = this.calculateHeight(number);
          }
          this.suggestedSearchRight.nativeElement.style.display = 'block';
          this.suggestedSearchRight.nativeElement.style.height = this.heightSuggestedSearch + 'px';
        });
    }
  }

  hideSearchRight() {
    this.productListRight = undefined;
    this.suggestedSearchRight.nativeElement.style.display = 'none';
  }

  selectProductRight(name?:string){
    this.productService.getProductByName(name).subscribe((data) =>{
      this.productRight = data;
    });
  }
}
