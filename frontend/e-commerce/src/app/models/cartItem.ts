import { jsonIgnore } from "json-ignore";
import { ProductModel } from "./product";

export default class Cart_Item{
    product?:ProductModel;
    quantity?:number;
    price?:number;
    @jsonIgnore()
    accountName?:string;
    
    @jsonIgnore()
    select?:boolean;
}

