import { RoleModel } from "./role";


export interface RegisterModel{
    username:string;
    password:string;
    confirmPassword:string;
    role:RoleModel;
}