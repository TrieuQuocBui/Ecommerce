import { ProductModel } from "./product";

export class ConfigModel {
  id?: number;

  nameConfig?:string;

  ram?: string;

  cpu?: string;

  displaySize?: string;

  graphicCard?: string;

  operatingSystem?: string;

  weight?: string;

  madeIn?: string;

  hardDrive?: string;

  madeYear?:number;

  size?:string;

  products?:ProductModel[];
}
