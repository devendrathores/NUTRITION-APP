/*We are using this class for Angular Interceptor. 
 Angular Interceptor will basically takes the token and put it inside every request.
 Format=>Authorization: Bearer token*/

import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { LoginService } from "./login.service";


@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    constructor(private loginService:LoginService){}
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
        //add the jwt token inside request
        const token=this.loginService.getToken();
        let authReq=req;
        if(token!=null){
           authReq= req.clone({setHeaders:{Authorization:`Bearer ${token}`}});
        }
        return next.handle(authReq);
    }

}

export const authInterceptorProviders=[
    {
        provide:HTTP_INTERCEPTORS,
        useClass:AuthInterceptor,
        multi:true,
    }
]