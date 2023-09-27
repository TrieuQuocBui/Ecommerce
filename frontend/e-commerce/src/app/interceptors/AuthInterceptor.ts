import {
    HTTP_INTERCEPTORS,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  // handle request before send to server
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let authReq = req;

    const token = this.authService.getToken();
    
    if (token != null) {
      // copy request and add headers contanin token into this request 
      authReq = req.clone({
        headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token),
      });
    }

    // send request  to server
    return next.handle(authReq);
  }
}

export const authInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ];
