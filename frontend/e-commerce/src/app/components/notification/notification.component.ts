import { Component, EventEmitter, Input, OnInit, Output, TemplateRef } from '@angular/core';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  @Output() checked = new EventEmitter<boolean>();

  @Input('template') template!: TemplateRef<any>;

  @Input('title') title!: String;

  @Input('submitName') submitName!:String;

  constructor() { }

  ngOnInit() {
  }

  submitConfirm(){
    this.checked.emit(true);
  }

  submitCancel(){
    this.checked.emit(false);
  }

}
