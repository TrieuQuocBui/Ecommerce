import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pagination } from '../models/pagination';
import { ParamsHome } from '../models/paramsHome';
import baseUrl from '../helpers/URL';
import { ConfigModel } from '../models/config';
import { MessageModel } from '../models/message';

@Injectable({
  providedIn: 'root',
})
export class ConfigService {
  
  constructor(private http: HttpClient) {}

  getConfigList(page: number, limit: number) {
    const params: ParamsHome = {
      page: page,
      limit: limit,
    };
    const queryParams = new HttpParams({ fromObject: params as any });
    return this.http.get<Pagination>(`${baseUrl}/admin/config`, {
      params: queryParams,
    });
  }

  deleteConfig(object: ConfigModel) {
    return this.http.delete<MessageModel>(`${baseUrl}/admin/config/${object.id}`,);
  }

  createConfig(config: ConfigModel) {
    return this.http.post<MessageModel>(`${baseUrl}/admin/config`,config);
  }

  findById(configId: string) {
    return this.http.get<ConfigModel>(`${baseUrl}/admin/config/${configId}`);
  }

  updateConfig(config: ConfigModel) {
    return this.http.put<MessageModel>(`${baseUrl}/admin/config/${config.id}`,config);
  }

  getAllConfig() {
    return this.http.get<ConfigModel[]>(`${baseUrl}/admin/config/all`);
  }
}
