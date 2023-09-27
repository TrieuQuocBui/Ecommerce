import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './common/navbar/navbar.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HeaderComponent } from './common/header/header.component';
import { FooterComponent } from './common/footer/footer.component';
import { BrandComponent } from './brand/brand.component';
import { ShareModule } from '../ShareModule/PaginationShare.module';
import { ProfileComponent } from './profile/profile.component';
import { FormAddingBrandComponent } from './brand/form-adding-brand/form-adding-brand.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormUpdatingBrandComponent } from './brand/form-updating-brand/form-updating-brand.component';
import { AccountComponent } from './account/account.component';
import { SupplierComponent } from './supplier/supplier.component';
import { FormAddingSupplierComponent } from './supplier/form-adding-supplier/form-adding-supplier.component';
import { FormUpdatingSupplierComponent } from './supplier/form-updating-supplier/form-updating-supplier.component';
import { ConfigComponent } from './config/config.component';
import { FormUpdatingConfigComponent } from './config/form-updating-config/form-updating-config.component';
import { FormAddingConfigComponent } from './config/form-adding-config/form-adding-config.component';
import { ProductComponent } from './product/product.component';
import { FormAddingProductComponent } from './product/form-adding-product/form-adding-product.component';
import { FormUpdatingProductComponent } from './product/form-updating-product/form-updating-product.component';
import { PreloadAllModules, RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    AdminComponent,
    HomeComponent,
    NavbarComponent,
    HeaderComponent,
    FooterComponent,
    BrandComponent,
    ProfileComponent,
    FormAddingBrandComponent,
    FormUpdatingBrandComponent,
    AccountComponent,
    SupplierComponent,
    FormAddingSupplierComponent,
    FormUpdatingSupplierComponent,
    ConfigComponent,
    FormUpdatingConfigComponent,
    FormAddingConfigComponent,
    ProductComponent,
    FormAddingProductComponent,
    FormUpdatingProductComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FontAwesomeModule,
    ShareModule,
    ReactiveFormsModule,
  ],
  
})
export class AdminModule { }
