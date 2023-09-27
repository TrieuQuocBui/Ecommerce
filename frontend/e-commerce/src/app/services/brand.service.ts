import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BrandModel } from '../models/brand';
import baseUrl from '../helpers/URL';
import { Pagination } from '../models/pagination';
import { ParamsHome } from '../models/paramsHome';
import { MessageModel } from '../models/message';


@Injectable({
  providedIn: 'root',
})
export class BrandService {
  
  
  constructor(private http: HttpClient) {}

  getBrandList(page:number,limit:number) {
    const params:ParamsHome = {
      page:page,
      limit:limit
    }
    const queryParams = new HttpParams({ fromObject: params as any});
    return this.http.get<Pagination>(`${baseUrl}/admin/brand`,{params:queryParams});
  }

  createBrand(brand :FormData){
    return this.http.post<MessageModel>(`${baseUrl}/admin/brand`,brand);
  }

  deleteBrand(object: BrandModel) {
    return this.http.delete<MessageModel>(`${baseUrl}/admin/brand/`+ object.id);
  }

  getBrandById(brandId: number) {
    return this.http.get<BrandModel>(`${baseUrl}/admin/brand/${brandId}`);
  }

  updateBrand(brand: BrandModel, brandId: number) {
    return this.http.put<MessageModel>(`${baseUrl}/admin/brand/${brandId}`,brand);
  }

  uploadFile(brandId: number,formData:FormData){
    return this.http.put<MessageModel>(`${baseUrl}/admin/brand/${brandId}/upload`,formData);
  }

  getAllBrand() {
    return this.http.get<BrandModel[]>(`${baseUrl}/admin/brand/all`);
  }
}
