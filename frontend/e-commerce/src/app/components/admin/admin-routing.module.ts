import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AdminGuard } from 'src/app/guards/admin.guard';
import { AdminComponent } from './admin.component';
import { BrandComponent } from './brand/brand.component';
import { ProfileComponent } from './profile/profile.component';
import { FormAddingBrandComponent } from './brand/form-adding-brand/form-adding-brand.component';
import { FormUpdatingBrandComponent } from './brand/form-updating-brand/form-updating-brand.component';
import { AccountComponent } from './account/account.component';
import { SupplierComponent } from './supplier/supplier.component';
import { FormAddingSupplierComponent } from './supplier/form-adding-supplier/form-adding-supplier.component';
import { FormUpdatingSupplierComponent } from './supplier/form-updating-supplier/form-updating-supplier.component';
import { ConfigComponent } from './config/config.component';
import { FormAddingConfigComponent } from './config/form-adding-config/form-adding-config.component';
import { FormUpdatingConfigComponent } from './config/form-updating-config/form-updating-config.component';
import { ProductComponent } from './product/product.component';
import { FormUpdatingProductComponent } from './product/form-updating-product/form-updating-product.component';
import { FormAddingProductComponent } from './product/form-adding-product/form-adding-product.component';
import { OrderComponent } from './order/order.component';

const routes: Routes = [

  {path:'',component:AdminComponent,children:[
    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path:'home',component:HomeComponent},
    {path:'brand',component:BrandComponent},
    {path:'brand/adding',component:FormAddingBrandComponent},
    {path:'brand/updating/:brandId',component:FormUpdatingBrandComponent},
    {path:'profile',component:ProfileComponent},
    {path:'account',component:AccountComponent},
    {path:'supplier',component:SupplierComponent},
    {path:'supplier/adding',component:FormAddingSupplierComponent},
    {path:'supplier/updating/:supplierId',component:FormUpdatingSupplierComponent},
    {path:'config',component:ConfigComponent},
    {path:'config/adding',component:FormAddingConfigComponent},
    {path:'product',component:ProductComponent},
    {path:'product/adding',component:FormAddingProductComponent},
    {path:'product/updating/:productId',component:FormUpdatingProductComponent},
    {path:'order',loadChildren: () => import('./order/order.module').then(m => m.OrderModule)},
  ],canActivate:[AdminGuard]} // canActivate check authentication
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
