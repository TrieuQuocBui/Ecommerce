import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RegisterModel } from '../models/register';
import baseUrl from '../helpers/URL';
import { LoginModel } from '../models/login';
import { AccountModel } from '../models/account';

const reqHeader = new HttpHeaders().set('Content-Type', 'application/json');
const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  constructor(private http: HttpClient) {}

  createUser(data: RegisterModel) {
    return this.http.post(`${baseUrl}/register`, data, { headers: reqHeader });
  }

  login(data: LoginModel) {
    return this.http.post(`${baseUrl}/login`, data, { headers: reqHeader });
  }

  logout(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY,token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY)!;
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): AccountModel {
    return JSON.parse(sessionStorage.getItem(USER_KEY)!);
  }

  public getRole():string{
    return this.getUser().role?.name!;
  }

  public isLoggedIn():boolean{
    let tokenStr = sessionStorage.getItem(TOKEN_KEY);
    if ( tokenStr == undefined || tokenStr == '' || tokenStr == null){
      return false;
    } else {
      return true;  
    }
  }

}
