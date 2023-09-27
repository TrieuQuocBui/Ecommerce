import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user.component';
import { HomeComponent } from './home/home.component';
import { DetailsProductComponent } from './details_product/details_product.component';
import { CartComponent } from './cart/cart.component';
import { CompareProductComponent } from './compare-product/compare-product.component';
import { ProfileComponent } from './profile/profile.component';
import { InfoOrderComponent } from './info-order/info-order.component';
import { ListOrderComponent } from './list-order/list-order.component';

const routes: Routes = [
  {path:'',component:UserComponent,children:[
    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path:'home',component:HomeComponent},
    {path:'products/:productId',component:DetailsProductComponent},
    {path:'cart',component:CartComponent},
    {path:'profile',component:ProfileComponent},
    {path:'order/list',component:ListOrderComponent},
    {path:'compare/:name',component:CompareProductComponent},
    {path:'compare',component:CompareProductComponent},// optional param is we create one path same but it doesn't has param
    {path:'order',component:InfoOrderComponent},
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
