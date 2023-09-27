import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appDisplayModel]'
})
export class DisplayModelDirective {

  // el operation DOM
  constructor(private el: ElementRef) { }

  // handle event of DOM
  @HostListener('click',['$event'])
  onClick(btn: { target: { parentNode: HTMLElement; offsetParent: { style: { display: string; }; }; }; }){
    const parentElement = btn.target.parentNode.parentNode as HTMLElement; // provides spectical properties and method of tag element that
    parentElement.style.display = 'none';
    console.log(parentElement);
  }
}
