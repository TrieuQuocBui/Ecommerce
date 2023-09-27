import { ReceiptModel } from "./receipt";

export class SupplierModel{
    id?:string;
    name?:string;
    sdt?:string;
    address?:String;
    status?:boolean;
    receipt?:ReceiptModel[];
}