import { Component, Input, OnInit, TemplateRef } from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {
  @Input() idModal!:string;
  @Input('template') template!: TemplateRef<any>;
  constructor() { }

  ngOnInit() {
  }

}
