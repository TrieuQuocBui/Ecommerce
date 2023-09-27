import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from "@angular/core";
import { ShareModule } from "../../ShareModule/PaginationShare.module";
import { CommonModule } from "@angular/common";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { ApproveComponent } from "./approve/approve.component";
import { OrderComponent } from "./order.component";
import { OrderRoutingModule } from "./order-routing.module";

@NgModule({
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    declarations:[
        OrderComponent,
        ApproveComponent
    ],
    imports:[
        OrderRoutingModule,
        CommonModule,
        FontAwesomeModule,
        ShareModule
    ],
})
export class OrderModule{}