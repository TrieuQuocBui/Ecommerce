import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { HeaderUserComponent } from './common/header-user/header-user.component';
import { UserComponent } from './user.component';
import { FooterUserComponent } from './common/footer-user/footer-user.component';
import { SlideshowUserComponent } from './common/slideshow-user/slideshow-user.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HomeComponent } from './home/home.component';
import { DetailsProductComponent } from './details_product/details_product.component';
import { CompareProductComponent } from './compare-product/compare-product.component';
import { BtnNextDirective } from 'src/app/directives/btn-next.directive';
import { BtnPrevDirective } from 'src/app/directives/btn-prev.directive';
import { CartComponent } from './cart/cart.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InfoOrderComponent } from './info-order/info-order.component';
import { ListOrderComponent } from './list-order/list-order.component';
import { DetailsOrderComponent } from './details-order/details-order.component';
import { ModalDirective } from 'src/app/directives/modal.directive';
import { NotificationComponent } from '../notification/notification.component';
import { ProfileComponent } from './profile/profile.component';
import { ShareModule } from '../ShareModule/PaginationShare.module';

@NgModule({
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  declarations: [
    UserComponent,
    HeaderUserComponent,
    SlideshowUserComponent,
    FooterUserComponent,
    HomeComponent,
    DetailsProductComponent,
    CompareProductComponent,
    BtnNextDirective,
    BtnPrevDirective,
    CartComponent,
    InfoOrderComponent,
    ListOrderComponent,
    DetailsOrderComponent,
    ProfileComponent,
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    ShareModule,
  ],
  providers: [],
  bootstrap: [UserComponent],
})
export class UserModule {}
