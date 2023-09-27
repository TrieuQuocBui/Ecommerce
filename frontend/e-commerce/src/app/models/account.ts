import { RoleModel } from "./role";
import { UserModel } from "./user";

export class AccountModel{
    id?:number;
    username?:string;
    role?:RoleModel;
    status?:boolean;
    user?:UserModel;
}