import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appBtnNext]'
})
export class BtnNextDirective {

  // ElementRef get a tag current
  constructor(private el:ElementRef) {}

  @HostListener('click')
  nextFun(){
    let elm = this.el.nativeElement.parentElement.children[0];
    var item = elm.getElementsByClassName("slideshow-item");
    elm.append(item[0]);
  }

}
