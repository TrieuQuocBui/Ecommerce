import Cart_Item from "./cartItem";

export default class OrderModel{
    id?:number;
    province?:string;
    district?:string;
    ward?:string;
    addressHome?:string;
    note?:string;
    orderDay?:Date;
    status?:number;
    detailsOrders?:Cart_Item[];
}