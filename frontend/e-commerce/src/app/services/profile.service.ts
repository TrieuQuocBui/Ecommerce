import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from '../helpers/URL';
import { UserModel } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

constructor(private http:HttpClient) { }

  getProfile(){
    return this.http.get<UserModel>(`${baseUrl}/profile`);
  }

  update(infoUser:UserModel){
    return this.http.put(`${baseUrl}/profile`,infoUser);
  }

  uploadImage(formData:any){
    return this.http.put(`${baseUrl}/profile/upload`,formData)
  }
}
