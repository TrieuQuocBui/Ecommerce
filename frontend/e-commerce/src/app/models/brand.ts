import { ProductModel } from "./product";

export class BrandModel{
    id?:number;
    name?:string;
    img?:string;
    status?:boolean;
    products?:ProductModel[];
}