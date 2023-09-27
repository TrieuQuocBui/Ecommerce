import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AccountModel } from '../models/account';
import { ParamsHome } from '../models/paramsHome';
import { Pagination } from '../models/pagination';
import baseUrl from '../helpers/URL';
import { MessageModel } from '../models/message';
import { StatusModal } from '../models/status';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  
  constructor(private http: HttpClient) {}

  getCustomerAccountList(page: number, limit: number) {
    const params: ParamsHome = {
      page: page,
      limit: limit,
    };
    const queryParams = new HttpParams({ fromObject: params as any });
    return this.http.get<Pagination>(`${baseUrl}/admin/account/users`, {
      params: queryParams,
    });
  }

  inacctiveAccount(account: AccountModel) {
    return this.http.delete<MessageModel>(`${baseUrl}/admin/account/user/${account.id}`);
  }

  acctiveAccount(account: AccountModel) {
    let statusModal = new StatusModal();
    statusModal.status = true;
    return this.http.put<MessageModel>(`${baseUrl}/admin/account/user/${account.id}`,statusModal);
  }
}
