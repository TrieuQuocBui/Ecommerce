import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from '../helpers/URL';
import { ProductModel } from '../models/product';
import { ParamsHome } from '../models/paramsHome';
import { Pagination } from '../models/pagination';
import { MessageModel } from '../models/message';

const reqHeader = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  
  
  constructor(private http: HttpClient) {}

  public getProducts(page?: number, limit?: number) {
    const params: ParamsHome = {
      page: page,
      limit: limit,
    };
    const queryParams = new HttpParams({ fromObject: params as any });
    return this.http.get<Pagination>(
      `${baseUrl}/home?page=${page}&limit=${limit}`
    );
  }

  public getProductById(idProduct: number) {
    return this.http.get<ProductModel>(`${baseUrl}/product/${idProduct}`);
  }

  public getProductsByNameContaining(name: string) {
    const params = new HttpParams().set('name', name);
    return this.http.get<ProductModel[]>(`${baseUrl}/product/search`, {
      params,
    });
  }

  public getProductByName(name?: string) {
    return this.http.get<ProductModel>(`${baseUrl}/product/search/${name}`);
  }

  getProductList(page: number, limit: number) {
    const params: ParamsHome = {
      page: page,
      limit: limit,
    };
    const queryParams = new HttpParams({ fromObject: params as any });
    return this.http.get<Pagination>(`${baseUrl}/admin/product`, {
      params: queryParams,
    });
  }

  createProduct(formData: FormData) {
    return this.http.post<MessageModel>(`${baseUrl}/admin/product`, formData);
  }

  findById(productId: number) {
    return this.http.get<ProductModel>(`${baseUrl}/admin/product/${productId}`);
  }

  updateProduct(productModal: ProductModel, productId: number) {
    return this.http.put<MessageModel>(`${baseUrl}/admin/product/${productId}`,productModal);
  }

  uploadFile(productId: number, formData: FormData) {
    return this.http.put<MessageModel>(`${baseUrl}/admin/product/${productId}/upload`,formData);
  }
}
