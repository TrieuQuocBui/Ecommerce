import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Province } from '../models/province';

const URL_ADDRESS = `https://vapi.vnappmob.com/api`

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

constructor(private http:HttpClient) { }

  getProvinces(){
    return this.http.get(`${URL_ADDRESS}/province/`);
  }

  getCities(idProvince:String){
    return this.http.get(`${URL_ADDRESS}/province/district/${idProvince}`);
  }

  getWards(idDistrict:number){
    return this.http.get(`${URL_ADDRESS}/province/ward/${idDistrict}`);
  }
}
