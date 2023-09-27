import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { NotfoundComponent } from './components/notfound/notfound.component';

const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  // lazy loading use download necessary files when browser request to it
  {path:'admin',loadChildren:() => import('./components/admin/admin.module').then(m => m.AdminModule)},
  {path:'',loadChildren:() => import('./components/user/user.module').then(m => m.UserModule)},
  {path: '**', component: NotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{preloadingStrategy:PreloadAllModules})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
