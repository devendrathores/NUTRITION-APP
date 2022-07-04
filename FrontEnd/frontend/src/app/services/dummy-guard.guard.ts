import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class DummyGuardGuard implements CanActivate {

constructor(private loginService:LoginService,private router:Router){}


  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      
      if(this.loginService.isLoggedIn()){
        return true;
      }
     this.router.navigate(['login']);/*Same as window.location.href, but didn't load the whole page*/
      return false;
  }
  
}



/*Angular guard is an interface which can be implemented to decide if a route can be activated.
 If all guards return true, navigation continues. If any guard returns false, navigation is cancelled.
*/ 