import { BrandModel } from "./brand";
import { ConfigModel } from "./config";

export interface ProductModel{
    id?:number;
    img?:string;
    name?:string;
    price?:string;
    description?:string;
    status?:boolean;
    config?:ConfigModel;
    brand?:BrandModel;
}