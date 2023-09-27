import { AccountModel } from "./account";
import { RoleModel } from "./role";

export interface JwtModel{
    token?:string;
    account?:AccountModel;
}