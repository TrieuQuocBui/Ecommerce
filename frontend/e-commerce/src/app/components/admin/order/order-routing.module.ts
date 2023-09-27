import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { NotfoundComponent } from "../../notfound/notfound.component";
import { ApproveComponent } from "./approve/approve.component";
import { OrderComponent } from "./order.component";
import { DisapproveComponent } from "./disapprove/disapprove.component";
import { NotApproveYetComponent } from "./not-approve-yet/not-approve-yet.component";

const routes: Routes =[
    {path:'',component:OrderComponent,children:[
       
        {path:'approve',component:ApproveComponent},
        {path:'not-approve-yet',component:NotApproveYetComponent},
        {path:'disapprove',component:DisapproveComponent},
    ]},
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class OrderRoutingModule { }