import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from '../pagination/pagination.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ModalComponent } from '../modal/modal.component';
import { ModalDirective } from 'src/app/directives/modal.directive';
import { NotificationComponent } from '../notification/notification.component';
import { OrderComponent } from '../admin/order/order.component';

@NgModule({
  imports: [CommonModule, FontAwesomeModule],
  declarations: [
    PaginationComponent,
     ModalComponent,
     ModalDirective,
     NotificationComponent,
     ModalComponent,
    ],
  exports: [
    PaginationComponent,
     ModalComponent,
     ModalDirective,
     NotificationComponent,
     ModalComponent,
    ],
})
export class ShareModule {}
