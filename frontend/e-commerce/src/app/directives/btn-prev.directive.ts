import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appBtnPrev]'
})
export class BtnPrevDirective {

  constructor(private el:ElementRef) { }

  @HostListener('click')
  prveFun(){
    //1212px
    let elm = this.el.nativeElement.parentElement.children[0];
    var item = elm.getElementsByClassName("slideshow-item");
    elm.prepend(item[item.length - 1]);
  }

}
