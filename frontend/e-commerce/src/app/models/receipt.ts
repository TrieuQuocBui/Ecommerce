import { ReceiptDetailsModel } from "./receiptDetails";

export class ReceiptModel{
    id?:string;
    date?:Date;
    listReceiptDetail?:ReceiptDetailsModel[];
}