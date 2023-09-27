import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appModal]'
})
export class ModalDirective {
  @Input('appModal') modalId!:string;
  @Input('positionClose') positionClose!:number;
  @Input('widthSize') width!:string;
  @Input('openModal') checkedOpen:boolean = false;

  constructor() { }

  ngAfterViewInit() {
    // Tự động mở modal sau khi view đã khởi tạo
    if (this.checkedOpen){
      this.openModal();
    }
  }

  @HostListener('click')
  openModal(){
    const modal = document.getElementById(this.modalId);
    if (modal){
      const divContent = modal?.childNodes[0] as HTMLDivElement;
      modal.style.display = 'block';
      divContent.style.width = this.width;
    }
  }

  @HostListener('window:click',['$event.target'])
  closeModal(target: HTMLElement){
    const modal = document.getElementById(this.modalId)!;
    const span = modal.querySelector('.modal-content .close')!;
    const confirmBtn = modal.querySelector('.modal-content .container-modal .clearfix .confirmbtn')!;
    const cancelBtn = modal.querySelector('.modal-content .container-modal .clearfix .cancelbtn')!;
    if (modal && target === modal) {
      modal.style.display = 'none';
    } 

    if (modal && target === span){
      modal.style.display = 'none';
    }

    if (modal && (target === confirmBtn || target === cancelBtn)){
      modal.style.display = 'none';
    }
  }


}
